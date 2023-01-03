package com.example.primenumberworker.control;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
@Getter
public class PrimeConfig {

  @Value("${app.prime.array.size.max}")
  private BigInteger arrayMaxSize;

  @Value("${app.prime.thread.pool.size}")
  private Integer threadPoolSize;
}
