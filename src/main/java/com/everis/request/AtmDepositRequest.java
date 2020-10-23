package com.everis.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class AtmDepositRequest {

  @NotNull(message = "Ingrese numero de documento")
  @Size(min = 8, max = 8, message = "Numero de documento debe tener {min} digitos")
  private String documentNumber;

}
