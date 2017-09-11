package com.sxcpersonal.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxcpersonal.dao.IOrderDao;
import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.domain.Order;

@Repository
public class OrderDaoImpl extends HibernateDaoSupport implements IOrderDao {
	@Autowired
	public void setSuperSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	@Override
	public int findTotalCount(int customerid) {
		return ((Long) this.getHibernateTemplate().find("select count(*) from Order o where o.customer.id = ?",customerid).iterator().next()).intValue();
	}

	@Override
	public List<Order> findOrderByPage(Customer c, int begin, int pageSize) {
		DetachedCriteria dc =DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.eq("customer", c));
		return (List<Order>) this.getHibernateTemplate().findByCriteria(dc, begin, pageSize);
	}

	@Override
	public Order findOrderById(String oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	@Override
	public void delOrder(Order order) {
		this.getHibernateTemplate().delete(order);
	}

}
