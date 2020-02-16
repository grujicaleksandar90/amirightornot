package com.amirightornot.model;

import java.util.Date;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Comment {

  private String id;
  private String postId;
  private String postedBy;
  private Date datePosted;
  private String message;
  private String personRight;

}
