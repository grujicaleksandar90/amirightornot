package com.amirightornot.model;

import java.util.Date;
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
  private Date datePosted;
  private String numberOfDays;

  private String message;

}
