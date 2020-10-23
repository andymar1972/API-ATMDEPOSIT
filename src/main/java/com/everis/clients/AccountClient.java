package com.everis.clients;

import com.everis.external.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accounts", url = "localhost:8005")
public interface AccountClient {

  @GetMapping("/core/accounts/cardNumber/{cardNumber}")
  public AccountResponse findByCardNumber(@PathVariable String cardNumber);

}
