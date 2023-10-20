package com.example.primenumberworker.boundary;

import com.example.primenumberworker.control.PrimeConfig;
import com.example.primenumberworker.control.PrimeGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/prime")
@RestController
@AllArgsConstructor
@Slf4j
public class PrimeResource {

  private final PrimeConfig config;

  @GetMapping("/random")
  public ResponseEntity<BigInteger> findRandomPrimeNumber() {
    log.info("PrimeResource.findRandomPrimeNumber GET /random");
    return ok(new PrimeGenerator(config, new ArrayList<>(), new LinkedList<>()).get());
  }
}
