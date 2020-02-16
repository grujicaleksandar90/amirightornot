package com.amirightornot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

  private String street;
  private String suite;
  private String city;
  private String zipCode;
  
}
