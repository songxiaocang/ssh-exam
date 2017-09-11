package com.sxcpersonal.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxcpersonal.dao.ICustomerDao;
import com.sxcpersonal.domain.Customer;

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {
	
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<Customer> findAllCustomer() {
	 return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	public void addCustomer(Customer c) {
		this.getHibernateTemplate().save(c);
		
	}

	@Override
	public Customer findById(Integer id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public void delCustomer(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	

	

}
