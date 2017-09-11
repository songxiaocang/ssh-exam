package com.sxcpersonal.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sxcpersonal.domain.Customer;
import com.sxcpersonal.service.ICustomerService;

@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private Customer customer = new Customer();
	@Autowired
	private ICustomerService customerService;
	
	@Override
	public Customer getModel() {
		return customer;
	}
	
	@Action(value="findAllCustomer",results= {@Result(name="success",location="/customerList.jsp")})
	public String findAllCustomer() {
		List<Customer> cs = customerService.findAllCustomer();
		ActionContext.getContext().getValueStack().set("cs", cs);
		return SUCCESS;
	}
	
	@Action(value="delCustomer",results= {@Result(name="success",type="redirect",location="findAllCustomer")})
	public String delCustomer() {
		customerService.delCustomer(customer);
		return SUCCESS;
	}
}
