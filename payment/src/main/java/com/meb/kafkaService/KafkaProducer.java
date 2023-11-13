package com.meb.kafkaService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dto.DataRequest;
import com.meb.dto.LoggerDTO;
import com.meb.dto.OrderDTO;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String,String > kafkaTemplate;
    private final ObjectMapper objectMapper=new ObjectMapper();

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderSuccess(DataRequest dto) {
  		try {
  			String paymentDTOJson = objectMapper.writeValueAsString(dto);
  			 kafkaTemplate.send("success_topic", paymentDTOJson);  
  		} catch (JsonProcessingException e) {
  			e.printStackTrace();
  		}
  	         
      }
    
    public void Logger(LoggerDTO dto) {
  		try {
  			String paymentDTOJson = objectMapper.writeValueAsString(dto);
  			 kafkaTemplate.send("test-topic", paymentDTOJson);  
  		} catch (JsonProcessingException e) {
  			e.printStackTrace();
  		}
  	         
      }
}
