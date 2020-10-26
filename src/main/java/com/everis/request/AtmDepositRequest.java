package com.everis.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.everis.response.AccountResponseAtm;

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
public class AtmDepositRequest {

  @NotNull(message = "Ingrese numero de documento")
  @Size(min = 8, max = 8, message = "Numero de documento debe tener {min} digitos")
  private String documentNumber;

}
