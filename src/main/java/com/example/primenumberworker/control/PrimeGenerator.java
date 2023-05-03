package com.example.primenumberworker.control;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@Slf4j
@Service
@AllArgsConstructor
public class PrimeGenerator implements Supplier<BigInteger> {

    private final PrimeConfig config;

    private List<String> primes;

    @Override
    public BigInteger get() {
        Map<BigInteger, Boolean> numberList = new ConcurrentHashMap<>();
        List<Callable<Pair<BigInteger,Boolean>>> tasks = new ArrayList<>();
        for (BigInteger i = BigInteger.valueOf(1); i.compareTo(config.getArrayMaxSize()) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger finalI = i;
            primes.add(i.toString());
            tasks.add(() -> {
                log.info("[Prime] Checking number: {}", finalI);
                return Pair.of(finalI, isPrime(finalI));
            });
        }
        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadPoolSize());
        try {
            var result = executor.invokeAll(tasks);
            for (Future<Pair<BigInteger, Boolean>> future: result) {
                numberList.put(future.get().getLeft(), future.get().getRight());
            }
        } catch (InterruptedException | ExecutionException e) {
            log.warn("[Prime] Exception occurred: " + e);
            throw new RuntimeException(e);
        }

        BigInteger[] values = numberList.keySet().stream().filter(this::isPrime).toList().toArray(new BigInteger[0]);
        return values[new Random().nextInt(values.length)];
    }

    private boolean isPrime(BigInteger number) {
        var prime = true;
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(number) < 0; i = i.add(BigInteger.ONE)) {
            if (number.remainder(i).compareTo(BigInteger.valueOf(0)) == 0) {
                prime = false;
                break;
            }
        }
        log.debug("[Prime] Number: {} isPrime: {}", number, prime);
        return prime;
    }
}
