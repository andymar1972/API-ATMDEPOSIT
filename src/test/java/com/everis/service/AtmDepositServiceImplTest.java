package com.everis.service;

import static org.mockito.Mockito.when;

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
import com.everis.response.AtmDepositResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AtmDepositServiceImplTest {

  @InjectMocks
  AtmDepositServiceImpl app;
  @Mock
  private PersonClient personClient;
  @Mock
  private FingerPrintClient fingerClient;
  @Mock
  private ReniecClient reniecClient;
  @Mock
  private CardClient cardClient;
  @Mock
  private AccountClient accountClient;

  AtmDepositResponse atmResponse;
  AtmDepositRequest atmRequest;

  /*
   * @Test void isFingerPrint() {
   * 
   * atmRequest = new AtmDepositRequest();
   * atmRequest.setDocumentNumber("10000000");
   * 
   * when(personClient.findByDocument("10000000")).thenReturn(new
   * PersonResponse(1L, "10000000", true, false));
   * 
   * PersonResponse personActual = personClient.findByDocument("10000000");
   * PersonResponse personExpected = new PersonResponse(1L, "10000000", true,
   * false);
   * 
   * app.findByDocument(atmRequest); assertEquals(personExpected, personActual);
   * 
   * }
   */

  @Test
  @Disabled
  void testGeneral() {

    List<Card> cards = new ArrayList<>();
    cards.add(new Card("1111222233334441", true));
    cards.add(new Card("1111222233334442", true));
    cards.add(new Card("1111222233334443", true));
    cards.add(new Card("1111222233334444", true));
    cards.add(new Card("1111222233334445", true));
    cards.add(new Card("1111222233334446", true));
    
    String document = "10000000";
    
    when(personClient
        .findByDocument(document)).thenReturn(new PersonResponse(1L, document, true, false));
    when(fingerClient.validate(new FingerPrintRequest(document)))
        .thenReturn(new FingerPrintResponse("FingerPrint", true));
    when(reniecClient
        .validate(new ReniecRequest(document))).thenReturn(new ReniecResponse("Reniec", true));
    when(cardClient.findByDocument(document)).thenReturn(new CardResponse(cards));
    when(accountClient.findByCardNumber("1111222233334441"))
        .thenReturn(new AccountResponse("1111222233334441XXX", 100.00));

    System.out.println(app.findByDocument(new AtmDepositRequest(document)));

  }

  @Test
  @Disabled
  void isFingerPrint() {
    when(personClient
        .findByDocument("10000000")).thenReturn(new PersonResponse(1L, "10000000", true, false));
    when(fingerClient.validate(new FingerPrintRequest("10000000")))
        .thenReturn(new FingerPrintResponse("FingerPrint", true));

    app.findByDocument(new AtmDepositRequest("10000000"));

  }

  @Test
  @Disabled
  void isReniec() {
    when(personClient
        .findByDocument("10000001")).thenReturn(new PersonResponse(1L, "10000001", false, false));
    when(reniecClient
        .validate(new ReniecRequest("10000001"))).thenReturn(new ReniecResponse("Reniec", true));

    app.findByDocument(new AtmDepositRequest("10000001"));

  }
}
