package com.meb.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Data;

@Entity
@Data
public class PaymentModel extends Util {
	
    private static final long serialVersionUID = 1L;

    @Column(name = "syskey", nullable = true)

	private int syskey;

	private double total_price;  
    private String paymentType;
    
    private boolean delete_status;
	public int getSyskey() {
		return syskey;
	}
	public void setSyskey(int syskey) {
		this.syskey = syskey;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public boolean isDelete_status() {
		return delete_status;
	}
	public void setDelete_status(boolean delete_status) {
		this.delete_status = delete_status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}  
}
