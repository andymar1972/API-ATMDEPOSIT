package com.everis.service;

import com.everis.clients.AccountClient;
import com.everis.clients.CardClient;
import com.everis.clients.FingerPrintClient;
import com.everis.clients.PersonClient;
import com.everis.clients.ReniecClient;
import com.everis.external.AccountResponse;
import com.everis.external.Card;
import com.everis.external.CardResponse;
import com.everis.external.FingerPrintResponse;
import com.everis.external.PersonResponse;
import com.everis.external.ReniecResponse;
import com.everis.request.AtmDepositRequest;
import com.everis.request.FingerPrintRequest;
import com.everis.request.ReniecRequest;
import com.everis.response.AccountResponseAtm;
import com.everis.response.AtmDepositResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmDepositServiceImpl implements AtmDepositService {

  @Autowired
  PersonClient personClient;
  @Autowired
  FingerPrintClient fingerClient;
  @Autowired
  ReniecClient reniecClient;
  @Autowired
  CardClient cardClient;
  @Autowired
  AccountClient accountClient;

  AtmDepositResponse atmResponse = new AtmDepositResponse();

  @Override
  public AtmDepositResponse findByDocument(AtmDepositRequest atmRequest) {
    
    // Operaciones Api Person
    PersonResponse personResponse = personClient.findByDocument(atmRequest.getDocumentNumber());
    
    // Operaciones API Fingerprints/API Reniec
    if (personResponse.isFingerPrint()) {
      FingerPrintRequest fingerRequest = new FingerPrintRequest();
      fingerRequest.setDocument(atmRequest.getDocumentNumber());
      FingerPrintResponse fingerResponse = fingerClient.validate(fingerRequest);
      atmResponse.setFingerprintEntityName(fingerResponse.getEntityName());
    } else {
      ReniecRequest reniecRequest = new ReniecRequest();
      reniecRequest.setDocument(atmRequest.getDocumentNumber());
      ReniecResponse reniecResponse = reniecClient.validate(reniecRequest);
      atmResponse.setFingerprintEntityName(reniecResponse.getEntityName());
    }
    
    // Operaciones API Accounts
    CardResponse cardResponse = cardClient.findByDocument(atmRequest.getDocumentNumber());
    List<Card> listCards = cardResponse.getCards();
    List<Card> cardsActive = listCards.stream()
        .filter(card -> card.isActive()).collect(Collectors.toList());
    List<AccountResponse> listAccountResponse = new ArrayList<>();
    List<AccountResponseAtm> listAccountResponseAtm = new ArrayList<>();
    cardsActive.parallelStream().forEach(card -> {
      AccountResponse accountResponse = accountClient.findByCardNumber(card.getCardNumber());
      listAccountResponse.add(accountResponse);
      AccountResponseAtm accountResponseAtm = new AccountResponseAtm();
      accountResponseAtm.setAccountNumber(accountResponse.getAccountNumber());
      listAccountResponseAtm.add(accountResponseAtm);
      atmResponse.setValidAccounts(listAccountResponseAtm);
      atmResponse.sumAmmount(accountResponse.getAmount());
    });
    
    return atmResponse;
  }

}
