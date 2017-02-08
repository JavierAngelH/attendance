/** 
 * StaffAppresialLayout.java Created: Jan 4, 2017 JavierAngelH
 */

package com.app.attendance.components.staff;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.model.Performance;
import com.app.attendance.service.EmployeeService;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffAppresialLayout - 
 *
 */

@SpringComponent
@UIScope
public class StaffAppresialLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfName = new TextField("NAME");
	
	TextField tfDesignation = new TextField("DEPARTMENT");
	
	TextField tfLocation = new TextField("LOCATION");

	TextArea tfObjective1 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 1");

	TextArea tfObjective2 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 2");

	TextArea tfObjective3 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 3");
	
	ComboBox cbReason = new ComboBox("REASON FOR APPRESIAL");

	Employee employee;

	@PostConstruct
	public void PostConstruct() {

		this.addStyleName(ValoTheme.PANEL_BORDERLESS);
		this.setSizeFull();

		this.root = new VerticalLayout();

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

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try{
				tfObjective1.validate();
				tfObjective2.validate();
				tfObjective3.validate();
				cbReason.validate();
				
				Performance performance = new Performance();
				performance.setObjective1(tfObjective1.getValue());
				performance.setObjective2(tfObjective2.getValue());
				performance.setObjective3(tfObjective3.getValue());
				performance.setReason(cbReason.getValue().toString());
				employeeService.saveStaffPerformance(employee.getId(), performance);
		Notification.show("Information saved succesfully.");
			}catch(Exception e){
String error = e.getMessage();

	Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("PERFORMANCE SELF APPRAISSAL ");


		this.tfName.setReadOnly(false);
		this.tfName
				.setValue(this.employee.getFirstname().toUpperCase() + " " + this.employee.getLastname().toUpperCase());
		this.tfName.setReadOnly(true);


		this.tfDesignation.setReadOnly(false);
		this.tfDesignation.setValue(this.employee.getDepartment().toUpperCase());
		this.tfDesignation.setReadOnly(true);


		this.tfLocation.setReadOnly(false);
		this.tfLocation.setValue(this.employee.getLocation().toUpperCase());
		this.tfLocation.setReadOnly(true);

		this.tfObjective1.clear();
		this.tfObjective2.clear();

		this.tfObjective3.clear();
		
		cbReason.setValue("");

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);


		cbReason.setInvalidAllowed(false);
		cbReason.addItem("Annual");
		cbReason.addItem("Probation");

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);


		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfObjective1.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfObjective2.addStyleName(ValoTheme.TEXTAREA_TINY);
		
		this.tfObjective3.addStyleName(ValoTheme.TEXTAREA_TINY);
		
		this.cbReason.addStyleName(ValoTheme.COMBOBOX_TINY);
		
		
		this.tfObjective1.setRequired(true);
		this.tfObjective2.setRequired(true);
		this.tfObjective3.setRequired(true);
		this.cbReason.setRequired(true);

		this.tfName.setWidth("300px");
		this.tfDesignation.setWidth("300px");
		this.tfLocation.setWidth("300px");
		this.tfObjective1.setWidth("300px");
		this.tfObjective2.setWidth("300px");
		this.tfObjective3.setWidth("300px");
		this.cbReason.setWidth("300px");
		
		FormLayout form1 = new FormLayout(this.tfName, this.tfDesignation, this.tfLocation, this.cbReason,
this.tfObjective1, this.tfObjective2, this.tfObjective3) ;

		form1.setWidth("80%");

		form1.setSpacing(true);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(this.sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1);
		content.addComponents(layoutMain, layoutButton);
		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutButton, Alignment.TOP_CENTER);
		content.setExpandRatio(layoutMain, 3);
		return content;

	}

	private Component buildHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setSizeUndefined();
		header.setWidth("100%");
		header.addStyleName("viewheader");
		header.setSpacing(true);
		Responsive.makeResponsive(header);
		this.titleLabel.setSizeUndefined();
		this.titleLabel.addStyleName(ValoTheme.LABEL_H2);
		this.titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		this.titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
		this.titleLabel.addStyleName(ValoTheme.LABEL_BOLD);

		header.addComponent(this.titleLabel);
		header.setComponentAlignment(this.titleLabel, Alignment.MIDDLE_CENTER);
		return header;
	}

}


