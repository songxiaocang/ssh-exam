package com.sxcpersonal.dao;

import java.util.List;

import com.sxcpersonal.domain.Customer;

public interface ICustomerDao {

	List<Customer> findAllCustomer();

	void addCustomer(Customer c);

	Customer findById(Integer id);

	void delCustomer(Customer customer);

	

	
}
