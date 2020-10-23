package com.everis.clients;

import com.everis.external.ReniecResponse;
import com.everis.request.ReniecRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reniec", url = "localhost:8003")
public interface ReniecClient {

  @PostMapping("/external/reniec/validate")
  public ReniecResponse validate(@RequestBody ReniecRequest reniecRequest);

}
