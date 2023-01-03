package com.example.primenumberworker.boundary;

import com.example.primenumberworker.control.PrimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/prime")
@RestController
@RequiredArgsConstructor
public class PrimeResource {

  private final PrimeService primeService;

  @GetMapping("/random")
  public ResponseEntity<BigInteger> findRandomPrimeNumber() {
    return ok(primeService.generateRandomPrime());
  }
}
