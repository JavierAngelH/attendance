package com.app.attendance;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("dashboard")
@SpringUI
@PreserveOnRefresh
public class AttendanceUI extends UI {

	private static final long serialVersionUID = 1L;
	@Autowired
	FrontEndView frontEndView;

	@WebServlet(name = "attendance", urlPatterns = { "/*" }, asyncSupported = true)
	public static class Servlet extends SpringVaadinServlet {

		private static final long serialVersionUID = 1L;

	}

	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {
		// ContextLoaderListener for vaadin spring integration
	}

	@Configuration
	@EnableVaadin
	public static class MyConfiguration {
		// Configuration for vaadin spring integration
	}

	@Override
	protected void init(VaadinRequest request) {

		Page.getCurrent().setTitle("Human Resource Management");

		this.setContent(this.frontEndView);

	}
}