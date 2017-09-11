package com.sxcpersonal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxcpersonal.dao.ICustomerDao;
import com.sxcpersonal.dao.IOrderDao;
import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.domain.Order;
import com.sxcpersonal.domain.PageBean;
import com.sxcpersonal.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private ICustomerDao customerDao;
	@Override
	public PageBean<Order> findOrderByCustomerPage(int cid, int currPage, int pageSize) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		int totalCount = orderDao.findTotalCount(cid);
		pageBean.setTotalCount(totalCount);
		
		int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
		pageBean.setTotalPage(totalPage);
		
		int begin = (currPage-1)*pageSize;
		Customer c = customerDao.findById(cid);
		List<Order> currContent = orderDao.findOrderByPage(c,begin,pageSize);
		pageBean.setCurrContent(currContent);
		
		return pageBean;
	}
	@Override
	public void delOrder(String oid) {
		Order order = orderDao.findOrderById(oid);
		orderDao.delOrder(order);
	}

}
