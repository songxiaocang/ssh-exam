package com.sxcpersonal.service;

import java.util.List;

import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.domain.Order;
import com.sxcpersonal.domain.PageBean;

public interface IOrderService {

	PageBean<Order> findOrderByCustomerPage(int cid, int currPage, int pageSize);

	void delOrder(String oid);

}
