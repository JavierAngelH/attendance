/** 
 * StaffTerminationForm.java Created: Dec 13, 2016 JavierAngelH
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffTerminationForm - 
 *
 */

@SpringComponent
@UIScope
public class StaffTerminationForm extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfName = new TextField("NAME");
	
	TextField tfDesignation = new TextField("DESIGNATION");
	

	TextField tfEmployeeId = new TextField("EMPLOYEE ID");
	
	DateField dfEmployment = new DateField("DATE OF EMPLOYMENT");
	
	TextField tfReason = new TextField("REASON FOR LEAVING");

	TextField tfReturn = new TextField("CONSIDER FUTURE RETURN");

	TextField tfRecommendation = new TextField("WOULD YOU RECOMMEND TO A FRIEND");

	TextField tfManagement = new TextField("IS THERE ANYTHING MADE BY MANAGEMENT THAT MADE YOU LEAVE?");

	TextField tfSuggestion = new TextField("DO YOU HAVE ANY SUGGESTIONS FOR HOW THE COMPANY CAN IMPORVE SATISFACTION IN YOUR POSITION?");

	TextField tfComments = new TextField("COMMENTS");

	OptionGroup rehireOption = new OptionGroup("IS THIS EMPLOYEE ELEGIBLE FOR REHIRE?");
	ComboBox cbTerminationReason = new ComboBox("REASON FOR TERMINATION");


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
				
		
employeeService.submitTerminationForm(employee.getId(), tfReason.getValue(), 
		tfReturn.getValue(), tfRecommendation.getValue(), tfManagement.getValue(), 
		tfSuggestion.getValue(), tfComments.getValue(), rehireOption.getValue().toString(), cbTerminationReason.getValue().toString());

			Notification.show("Information saved succesfully.");
			}catch(Exception e){
String error = e.getMessage();

	Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("EXIT FORM");


		this.tfName.setReadOnly(false);
		this.tfName
				.setValue(this.employee.getFirstname().toUpperCase() + " " + this.employee.getLastname().toUpperCase());
		this.tfName.setReadOnly(true);


		this.tfDesignation.setReadOnly(false);
		this.tfDesignation.setValue(this.employee.getDepartment().toUpperCase());
		this.tfDesignation.setReadOnly(true);



		this.tfEmployeeId.setReadOnly(false);
		this.tfEmployeeId.setValue(this.employee.getId().toUpperCase());
		this.tfEmployeeId.setReadOnly(true);
		
	
		this.dfEmployment.setReadOnly(false);
		this.dfEmployment.setValue(this.employee.getEmpdate());
		this.dfEmployment.setReadOnly(true);
		
		this.tfReason.clear();
		
		this.tfReturn.clear();
		
		this.tfRecommendation.clear();
		this.tfManagement.clear();
		this.tfSuggestion.clear();
		this.tfComments.clear();

		
		rehireOption.clear();
		
		cbTerminationReason.setValue("");

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);


		cbTerminationReason.setInvalidAllowed(false);
		cbTerminationReason.addItem("Lay Off");
		cbTerminationReason.addItem("Position Eliminated");
		cbTerminationReason.addItem("Attendance");
		cbTerminationReason.addItem("Reorganization");
		cbTerminationReason.addItem("Violation of Company Policy");
		cbTerminationReason.addItem("Personal Reasons");
		cbTerminationReason.addItem("Took Another Position");
		cbTerminationReason.addItem("Retirement");
		cbTerminationReason.addItem("Relocating");
		cbTerminationReason.addItem("Returning to School");
		cbTerminationReason.addItem("Other");


		cbTerminationReason.setInvalidAllowed(false);


		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);


		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfEmployeeId.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfReason.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfReturn.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfRecommendation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfManagement.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfSuggestion.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfComments.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.rehireOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.rehireOption.addItem("YES");
		this.rehireOption.addItem("NO");
		this.rehireOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.cbTerminationReason.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		
		FormLayout form1 = new FormLayout(this.tfName, this.tfDesignation, this.tfEmployeeId, this.dfEmployment,
				this.tfReason, this.tfReturn, this.tfRecommendation, this.tfManagement, this.tfSuggestion, this.tfComments,
				this.rehireOption, this.cbTerminationReason);



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


