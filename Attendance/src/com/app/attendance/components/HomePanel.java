/**
 * HomePanel.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Item;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HomePanel -
 *
 */
@SpringComponent
@UIScope
public class HomePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private VerticalLayout root;

	Label titleLabel = new Label();
	Table table = new Table();

	@Autowired
	EmployeeService employeeService;

	Employee employee;

	Button btnComeback = new Button("Confirm Return to Work");

	@PostConstruct
	public void PostConstruct() {

		this.addStyleName(ValoTheme.PANEL_BORDERLESS);
		this.setSizeFull();
		this.root = new VerticalLayout();
		this.root.setSizeFull();
		this.root.setMargin(true);
		this.root.addStyleName("dashboard-view");
		this.setContent(this.root);
		Responsive.makeResponsive(this.root);
		Component header = this.buildHeader();
		Component content = this.buildContent();

		this.root.addComponent(header);
		this.root.addComponent(content);
		this.root.setExpandRatio(content, 3);

	}

	@SuppressWarnings("unchecked")
	public void setData() {
		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		String lastname = "";
		if (this.employee.getLastname() != null) {
			lastname = this.employee.getLastname();
		}
		String displayName = this.employee.getFirstname() + " " + lastname;
		this.titleLabel.setValue("Welcome " + displayName);

		List<String> messages = this.employeeService.getEmployeetMessages(this.employee.getId());
		if (!messages.isEmpty()) {
			this.table.setVisible(true);
			for (String string : messages) {
				Object newItemId = this.table.addItem();
				Item row = this.table.getItem(newItemId);
				row.getItemProperty("Received Message").setValue(string);
			}
		} else {
			this.table.setVisible(false);
		}
		
		if(employee.getRole().equals("HR")){
			List<String> messagesHR = this.employeeService.getEmployeetMessages("HR");
			if (!messagesHR.isEmpty()) {
				this.table.setVisible(true);
				for (String string : messagesHR) {
					Object newItemId = this.table.addItem();
					Item row = this.table.getItem(newItemId);
					row.getItemProperty("Received Message").setValue(string);
				}
			} else {
				this.table.setVisible(false);
			}
			
			
			
			
		}
		

		if (this.employeeService.onVacation(this.employee.getId())) {

			this.btnComeback.setVisible(true);
		} else {
			this.btnComeback.setVisible(false);
		}
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		this.table.setWidth("100%");

		this.table.addContainerProperty("Received Message", String.class, null);

		this.table.setVisible(false);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		this.btnComeback.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		this.btnComeback.addStyleName(ValoTheme.BUTTON_SMALL);
		// btnComeback.addClickListener(approveAllListener);
		this.btnComeback.setVisible(false);
		layoutButton.addComponent(this.btnComeback);
		layoutButton.setComponentAlignment(this.btnComeback, Alignment.TOP_CENTER);

		this.btnComeback.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				HomePanel.this.employeeService.updateVacationStatus(HomePanel.this.employee.getId());
				HomePanel.this.setData();
				Notification.show("Information stored succesfully");

			}
		});

		content.addComponents(this.table, layoutButton);
		content.setExpandRatio(this.table, 3);
		return content;
	}

	private Component buildHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName("viewheader");
		header.setSpacing(true);
		Responsive.makeResponsive(header);
		this.titleLabel.setSizeUndefined();
		this.titleLabel.addStyleName(ValoTheme.LABEL_H1);
		this.titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		this.titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
		header.addComponent(this.titleLabel);

		return header;
	}

}
