package com.meb.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meb.model.PaymentModel;

import jakarta.persistence.NamedQuery;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel,Integer>{

}
