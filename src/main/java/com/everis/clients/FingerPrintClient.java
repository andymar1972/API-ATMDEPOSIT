package com.everis.clients;

import com.everis.external.FingerPrintResponse;
import com.everis.request.FingerPrintRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "finger-print", url = "localhost:8002")
public interface FingerPrintClient {

  @PostMapping("/core/fingerprints/validate")
  public FingerPrintResponse validate(@RequestBody FingerPrintRequest fingerRequest);

}
