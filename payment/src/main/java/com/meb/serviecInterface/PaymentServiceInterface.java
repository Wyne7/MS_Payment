package com.meb.serviecInterface;

import org.springframework.stereotype.Service;

import com.meb.dto.DataRequest;

@Service
public interface PaymentServiceInterface {
    
    void savePayment(DataRequest dataRequest);


}
