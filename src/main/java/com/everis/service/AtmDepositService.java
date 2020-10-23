package com.everis.service;

import com.everis.request.AtmDepositRequest;
import com.everis.response.AtmDepositResponse;

public interface AtmDepositService {
  
  public AtmDepositResponse findByDocument(AtmDepositRequest atmRequest);

}
