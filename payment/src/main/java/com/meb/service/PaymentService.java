package com.meb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dto.DataRequest;
import com.meb.dto.DataResponse;
import com.meb.dto.LogType;
import com.meb.dto.LoggerDTO;
import com.meb.dto.OrderDTO;
import com.meb.dto.PaymentDTO;
import com.meb.kafkaService.KafkaProducer;
import com.meb.model.PaymentModel;
import com.meb.persistence.PaymentRepository;
import com.meb.serviecInterface.PaymentServiceInterface;
import com.meb.util.FirebaseConfig;

import jakarta.transaction.Transactional;

@Service
public class PaymentService implements PaymentServiceInterface {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Transactional
	@Override
	public void savePayment(DataRequest dataRequest) {
		PaymentModel paymentModel = new PaymentModel();
		ObjectMapper objectMapper = new ObjectMapper();
		paymentModel.setKeyGenerate(dataRequest.getKey());
		paymentModel.setPaymentType(dataRequest.getPaymentDto().getPaymentType());
		paymentModel.setTotal_price(dataRequest.getPaymentDto().getTotal_price());
		paymentModel.setSyskey(dataRequest.getOrderDto().getSyskey());
		try {
			paymentRepository.save(paymentModel);
			LoggerDTO loggerDTO=new LoggerDTO();
			loggerDTO.setId(0);
			loggerDTO.setLogType(LogType.INFO);
			loggerDTO.setMessage("Payment SuccessFul");
			kafkaProducer.sendOrderSuccess(dataRequest);
			kafkaProducer.Logger(loggerDTO);
		} catch (Exception e) {
		}

	}

}
