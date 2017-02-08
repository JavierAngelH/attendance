/** 
 * StaffLeaveManagementLayout.java Created: Dec 12, 2016 JavierAngelH
 */

package com.app.attendance.components.staff;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
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
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffLeaveManagementLayout - 
 *
 */

@SpringComponent
@UIScope
public class StaffLeaveManagementLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfName = new TextField("NAME");
	
	TextField tfDesignation = new TextField("DESIGNATION");
	
	TextField tfLocation = new TextField("LOCATION");

	TextField tfExplanation = new TextField("EXPLANATION FOR LEAVE");
	
	ComboBox cbDays = new ComboBox("N° OF DAYS REQUIRED");
	
	ComboBox cbType = new ComboBox("TYPE OF LEAVE");


	RichTextArea taBackstopping = new RichTextArea("BACKSTOPPING ARRANGEMENT");
	Employee employee;

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

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try{
				
		
employeeService.submitLeaveApplication(employee.getId(), cbType.getValue().toString(),
		tfExplanation.getValue(), Integer.parseInt(cbDays.getValue().toString()), taBackstopping.getValue());
			Notification.show("Information saved succesfully.");
			}catch(Exception e){
String error = e.getMessage();
if(error.equals("Number of days exceeds the remaining balance"))
				Notification.show(error, Notification.Type.ERROR_MESSAGE);
else
	Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("LEAVE APPLICATION");


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

		
		this.tfExplanation.clear();
		
		this.taBackstopping.clear();
		
		cbDays.setValue("");
		
		 cbType.setValue("");

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		for(int i=1; i<27; i++){
			this.cbDays.addItem(i);
		}
		
		cbDays.setInvalidAllowed(false);
		
		cbType.addItem("Annual Leave");
		cbType.addItem("Sick Leave");
		cbType.addItem("Maternity Leave");
		cbType.addItem("Compassionate Leave");
		cbType.addItem("Child care");

		cbType.setInvalidAllowed(false);


		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSizeFull();
		layoutMain.setSpacing(true);


		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);


		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfExplanation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		
		this.tfExplanation.setWidth("100%");
		
		this.taBackstopping.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.taBackstopping.setWidth("100%");

		this.cbDays.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbType.addStyleName(ValoTheme.COMBOBOX_TINY);

	

		FormLayout form1 = new FormLayout(this.tfName, this.tfLocation, this.tfExplanation);

		FormLayout form2 = new FormLayout(this.tfDesignation,this.cbDays, this.cbType );


		form1.setSpacing(true);
		form1.setSizeFull();

		form2.setSpacing(true);
		form2.setSizeFull();

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(this.sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1, form2);
		content.addComponents(layoutMain, this.taBackstopping, layoutButton);
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

