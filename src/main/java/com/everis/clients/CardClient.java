package com.everis.clients;

import com.everis.external.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cards", url = "localhost:8004")
public interface CardClient {

  @GetMapping("/core/cards/documentNumber/{document}")
  public CardResponse findByDocument(@PathVariable String document);

}
