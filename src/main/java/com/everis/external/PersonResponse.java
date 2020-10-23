package com.everis.external;

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
public class PersonResponse {
  
  private Long id;
  private String document;
  private boolean fingerPrint;
  private boolean blackList;

}
