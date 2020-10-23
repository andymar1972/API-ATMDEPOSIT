package com.everis.apis;

import com.everis.request.AtmDepositRequest;
import com.everis.response.AtmDepositResponse;
import com.everis.service.AtmDepositService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmDepositApi {

  @Autowired
  AtmDepositService atmService;

  @PostMapping("/atm/deposits/")
  public AtmDepositResponse findByDocument(@RequestBody @Valid AtmDepositRequest atmRequest) {
    return atmService.findByDocument(atmRequest);
  }

}
