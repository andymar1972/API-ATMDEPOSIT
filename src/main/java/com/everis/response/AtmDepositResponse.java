package com.everis.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AtmDepositResponse {

  private String fingerprintEntityName;
  private List<AccountResponseAtm> validAccounts;
  private double customerAmount;

  public void sumAmmount(double ammount) {
    this.customerAmount += ammount;
  }

}
