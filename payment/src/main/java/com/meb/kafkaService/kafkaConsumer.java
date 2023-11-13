package com.meb.kafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dto.DataRequest;
import com.meb.service.PaymentService;

@Service
public class kafkaConsumer {
	@Autowired
	private PaymentService service;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public kafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "payment_topic", groupId = "payment")
	public void consumeOrderEvent(String message) {

		try {
			double totalPrice=5000;
			ObjectMapper objectMapper = new ObjectMapper();
			DataRequest requestDTO = objectMapper.readValue(message, DataRequest.class);
			System.out.println(requestDTO);
			if (requestDTO.getPaymentDto().getTotal_price()>=totalPrice) {
				String paymentDTOJson = objectMapper.writeValueAsString(requestDTO);
				kafkaTemplate.send("fail_topic", paymentDTOJson);
			} else {
				service.savePayment(requestDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}
}
