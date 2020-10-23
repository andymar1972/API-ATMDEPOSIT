package com.everis.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AtmDepositResponse {

  private String fingerprintEntityName;
  private List<AccountResponseAtm> validAccounts;
  private double customerAmount;

  public void sumAmmount(double ammount) {
    this.customerAmount += ammount;
  }

}
