package com.amirightornot.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  private String id;
  private String postedBy;
  private LocalDate datePosted;
  private String numberOfDays;

  private String message;

}
