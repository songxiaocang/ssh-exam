package com.sxcpersonal.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
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
public class CustomerMultiAction extends ActionSupport {
	@Autowired
	private ICustomerService customerService;
	
	private File cusImg;
	private String cusImgFileName;
	private String cusName;
	private String cusPhone;
	
	public File getCusImg() {
		return cusImg;
	}
	public void setCusImg(File cusImg) {
		this.cusImg = cusImg;
	}
	public String getCusImgFileName() {
		return cusImgFileName;
	}
	public void setCusImgFileName(String cusImgFileName) {
		this.cusImgFileName = cusImgFileName;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}


	@Action(value="addCustomer",results= {@Result(name="success",type="redirectAction",location="findAllCustomer"),
			@Result(name="input",location="/error.jsp")})
	public String addCustomer() {
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		File dest = new File(path, cusImgFileName);
		try {
			FileUtils.copyFile(cusImg, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Customer c = new Customer();
		c.setCusName(cusName);
		c.setCusPhone(cusPhone);
		String cusImgsrc = ServletActionContext.getRequest().getContextPath()+"/upload/"+cusImgFileName;
		c.setCusImgsrc(cusImgsrc);
		
		customerService.addCustomer(c);
		
		return SUCCESS;
	}
}
