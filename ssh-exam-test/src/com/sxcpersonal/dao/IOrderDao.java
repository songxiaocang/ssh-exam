package com.sxcpersonal.dao;

import java.util.List;

import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.domain.Order;

public interface IOrderDao {

	int findTotalCount(int customerid);

	List<Order> findOrderByPage(Customer c, int begin, int pageSize);

	Order findOrderById(String oid);

	void delOrder(Order order);

}
