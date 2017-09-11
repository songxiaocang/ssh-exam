package com.sxcpersonal.action;

import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.sxcpersonal.domain.Order;
import com.sxcpersonal.domain.PageBean;
import com.sxcpersonal.service.IOrderService;

@Controller
@Scope
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport {
	@Autowired
	private IOrderService orderService;
	
	private String customerid;
	

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	@Action(value="findOrder")
	public void findOrder() {
		try {
			ServletActionContext.getRequest().setCharacterEncoding("utf-8");
			System.out.println(customerid);
			int cid  = Integer.parseInt(customerid);
			int currPage =Integer.parseInt(ServletActionContext.getRequest().getParameter("currPage"));
			int pageSize =Integer.parseInt(ServletActionContext.getRequest().getParameter("pageSize"));
			
			PageBean<Order> pageBean = orderService.findOrderByCustomerPage(cid,currPage,pageSize);
			PropertyFilter filter = new PropertyFilter() {
				
				@Override
				public boolean apply(Object arg0, String arg1, Object arg2) {
					if ("cusPhone".equalsIgnoreCase(arg1)) {
						return false;
					}
//					if ("id".equalsIgnoreCase(arg1)) {
//						return false;
//					}
				
					if ("orders".equalsIgnoreCase(arg1)) {
						return false;
					}
					return true;
				}
			};
			
			String json = JSONArray.toJSONString(pageBean, filter, SerializerFeature.DisableCircularReferenceDetect);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			System.out.println("响应的数据是："+json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Action(value="delOrder",results= {@Result(name="success",type="redirectAction",location="findOrder?customerid=${customerid}")})
	public String delete() {
		String oid = ServletActionContext.getRequest().getParameter("oid");
		orderService.delOrder(oid);
		return SUCCESS;
	}
}
