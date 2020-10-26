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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

	@Test
	void whenFindPerson_ThenReturnPersonResponse() {

		String document = "10000000";

		PersonResponse personResponse = PersonResponse.builder().id(1L).document("10000000").fingerPrint(true)
				.blackList(false).build();

		when(personClient.findByDocument(document)).thenReturn(personResponse);

		PersonResponse person = app.findPersonClient("10000000");

		assertThat(person).isEqualTo(personResponse);

	}
	
	@Test
	void whenFindPerson_ThenReturnPersonResponseReniec() {

		String document = "10000001";

		PersonResponse personResponse = PersonResponse.builder().id(1L).document("10000001").fingerPrint(false)
				.blackList(false).build();

		when(personClient.findByDocument(document)).thenReturn(personResponse);

		PersonResponse person = app.findPersonClient("10000001");

		assertThat(person).isEqualTo(personResponse);

	}

	@Test
	void whenFindFingerPrint_ThenReturnFingerResponse() {

		FingerPrintRequest fingerRequest = FingerPrintRequest.builder().document("10000000").build();

		FingerPrintResponse fingerResponse = FingerPrintResponse.builder().entityName("FingerPrint").succes(true)
				.build();

		when(fingerClient.validate(fingerRequest)).thenReturn(fingerResponse);

		FingerPrintResponse finger = app.findFingerClient(fingerRequest);

		assertThat(finger).isEqualTo(fingerResponse);

	}

	@Test
	void whenFindReniec_ThenReturnReniecResponse() {

		ReniecRequest reniecRequest = ReniecRequest.builder().document("10000001").build();

		ReniecResponse reniecResponse = ReniecResponse.builder().entityName("Reniec").succes(true).build();

		when(reniecClient.validate(reniecRequest)).thenReturn(reniecResponse);

		ReniecResponse reniec = app.findReniecClient(reniecRequest);

		assertThat(reniec).isEqualTo(reniecResponse);

	}

	@Test
	void whenFindCard_ThenReturnCardResponse() {

		List<Card> cards = new ArrayList<>();
		cards.add(Card.builder().cardNumber("1111222233334441").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334442").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334443").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334444").active(false).build());
		//cards.add(Card.builder().cardNumber("1111222233334445").active(false).build());
		//cards.add(Card.builder().cardNumber("1111222233334446").active(false).build());

		CardResponse cardResponse = CardResponse.builder().cards(cards).build();

		when(cardClient.findByDocument("10000000")).thenReturn(cardResponse);

		CardResponse card = app.findCardClient("10000000");

		assertThat(card).isEqualTo(cardResponse);

	}
	
	@Test
	void whenFindCard_ThenReturnCardResponseReniec() {

		List<Card> cards = new ArrayList<>();
		cards.add(Card.builder().cardNumber("1111222233334441").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334442").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334443").active(true).build());
		//cards.add(Card.builder().cardNumber("1111222233334444").active(false).build());
		//cards.add(Card.builder().cardNumber("1111222233334445").active(false).build());
		//cards.add(Card.builder().cardNumber("1111222233334446").active(false).build());

		CardResponse cardResponse = CardResponse.builder().cards(cards).build();

		when(cardClient.findByDocument("10000001")).thenReturn(cardResponse);

		CardResponse card = app.findCardClient("10000001");

		assertThat(card).isEqualTo(cardResponse);

	}

	@Test
	void whenFindAccount_ThenReturnAccountResponse() {

		AccountResponse accountResponse = AccountResponse.builder().accountNumber("1111222233334441XXX").amount(100.00)
				.build();

		when(accountClient.findByCardNumber("1111222233334441")).thenReturn(accountResponse);

		AccountResponse account = app.findAccountClient("1111222233334441");

		assertThat(account).isEqualTo(accountResponse);

	}

	@Test
	void whenFindByDocument_ThenReturnAtmDepositResponseFingerPrint() {
		
		whenFindPerson_ThenReturnPersonResponse();
		whenFindFingerPrint_ThenReturnFingerResponse();
		whenFindReniec_ThenReturnReniecResponse();
		whenFindCard_ThenReturnCardResponse();
		whenFindAccount_ThenReturnAccountResponse();

		AtmDepositRequest atmRequest = AtmDepositRequest.builder().documentNumber("10000000").build();


		List<AccountResponseAtm> validAccounts = new ArrayList<>();
		
		validAccounts.add(AccountResponseAtm.builder().accountNumber("1111222233334441XXX").build());

		AtmDepositResponse atmResponse = AtmDepositResponse.builder().fingerprintEntityName("FingerPrint")
				.validAccounts(validAccounts).customerAmount(100.00).build();
		
		AtmDepositResponse atm = app.findByDocument(atmRequest);
		
		assertThat(atm).isEqualTo(atmResponse);

	}
	
	@Test
	void whenFindByDocument_ThenReturnAtmDepositResponseReniec() {
		
		whenFindPerson_ThenReturnPersonResponseReniec();
		whenFindReniec_ThenReturnReniecResponse();
		whenFindCard_ThenReturnCardResponseReniec();
		whenFindAccount_ThenReturnAccountResponse();

		AtmDepositRequest atmRequest = AtmDepositRequest.builder().documentNumber("10000001").build();


		List<AccountResponseAtm> validAccounts = new ArrayList<>();
		
		validAccounts.add(AccountResponseAtm.builder().accountNumber("1111222233334441XXX").build());

		AtmDepositResponse atmResponse = AtmDepositResponse.builder().fingerprintEntityName("Reniec")
				.validAccounts(validAccounts).customerAmount(100.00).build();
		
		AtmDepositResponse atm = app.findByDocument(atmRequest);
		
		assertThat(atm).isEqualTo(atmResponse);

	}

}
