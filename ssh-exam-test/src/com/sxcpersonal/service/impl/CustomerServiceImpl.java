package com.sxcpersonal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxcpersonal.dao.ICustomerDao;
import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.service.ICustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

	@Override
	public void addCustomer(Customer c) {
		customerDao.addCustomer(c);
		
	}

	@Override
	public void delCustomer(Customer customer) {
		customer = customerDao.findById(customer.getId());
		customerDao.delCustomer(customer);
	}

	

}
