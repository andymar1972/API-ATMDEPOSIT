package com.everis.clients;

import com.everis.external.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "persons", url = "localhost:8001")
public interface PersonClient {

  @GetMapping("/core/persons/documentNumber/{document}")
  public PersonResponse findByDocument(@PathVariable String document);

}
