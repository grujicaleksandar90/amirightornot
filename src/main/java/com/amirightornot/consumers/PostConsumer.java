package com.amirightornot.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {

  @KafkaListener(topics = "posts_example1", groupId="group_id")
  public void consumer(String post) {
    System.out.println("Post is consumed: " + post);
    // Save to Elasticsearch
  }
}
