package com.sxcpersonal.service;

import java.util.List;

import com.sxcpersonal.domain.Customer;

public interface ICustomerService {

	List<Customer> findAllCustomer();

	void addCustomer(Customer c);

	void delCustomer(Customer customer);


}
