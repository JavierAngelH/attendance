/** 
 * HRTerminationForm.java Created: Dec 13, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.TerminationForm;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRTerminationForm - 
 *
 */
@SpringComponent
@UIScope
public class HRTerminationForm extends Panel {
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
	TextField cbTerminationReason = new TextField("REASON FOR TERMINATION");

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");


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

	public void setData() {

		this.titleLabel.setValue("EXIT FORMS");
		
		this.tfName.setReadOnly(false);
		this.tfDesignation.setReadOnly(false);
		this.tfEmployeeId.setReadOnly(false);
		this.dfEmployment.setReadOnly(false);	
		this.tfReturn.setReadOnly(false);		
		this.tfRecommendation.setReadOnly(false);
		this.tfManagement.setReadOnly(false);
		this.tfSuggestion.setReadOnly(false);
		this.tfComments.setReadOnly(false);
		this.tfReason.setReadOnly(false);
		this.cbTerminationReason.setReadOnly(false);
		this.rehireOption.setReadOnly(false);

		this.tfName.clear();
		this.tfDesignation.clear();
		this.tfEmployeeId.clear();
		this.dfEmployment.clear();		
		this.tfReturn.clear();		
		this.tfRecommendation.clear();
		this.tfManagement.clear();
		this.tfSuggestion.clear();
		this.tfComments.clear();
		this.tfReason.clear();
		this.cbTerminationReason.clear();
		this.rehireOption.clear();

		this.tfName.setReadOnly(true);
		this.tfDesignation.setReadOnly(true);
		this.tfEmployeeId.setReadOnly(true);
		this.dfEmployment.setReadOnly(true);	
		this.tfReturn.setReadOnly(true);		
		this.tfRecommendation.setReadOnly(true);
		this.tfManagement.setReadOnly(true);
		this.tfSuggestion.setReadOnly(true);
		this.tfComments.setReadOnly(true);
		this.tfReason.setReadOnly(true);
		this.cbTerminationReason.setReadOnly(true);
		this.rehireOption.setReadOnly(true);

		this.cbEmployees.removeAllItems();

		BeanItemContainer<TerminationForm> terminationBeanContainer = new BeanItemContainer<TerminationForm>(TerminationForm.class);

		List<TerminationForm> terminationList = this.employeeService.getTerminationForms();

		terminationBeanContainer.addAll(terminationList);

		this.cbEmployees.setContainerDataSource(terminationBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("employeeName");
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

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

		this.cbTerminationReason.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		
	
		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbEmployees.setNullSelectionAllowed(false);

		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				TerminationForm employee = (TerminationForm) HRTerminationForm.this.cbEmployees.getValue();

				if (employee != null) {

					HRTerminationForm.this.tfName.setReadOnly(false);
					HRTerminationForm.this.tfDesignation.setReadOnly(false);
					HRTerminationForm.this.tfEmployeeId.setReadOnly(false);
					HRTerminationForm.this.dfEmployment.setReadOnly(false);	
					HRTerminationForm.this.tfReturn.setReadOnly(false);		
					HRTerminationForm.this.tfRecommendation.setReadOnly(false);
					HRTerminationForm.this.tfManagement.setReadOnly(false);
					HRTerminationForm.this.tfSuggestion.setReadOnly(false);
					HRTerminationForm.this.tfComments.setReadOnly(false);
					HRTerminationForm.this.tfReason.setReadOnly(false);
					HRTerminationForm.this.rehireOption.setReadOnly(false);
					HRTerminationForm.this.cbTerminationReason.setReadOnly(false);
					
					HRTerminationForm.this.tfName.setValue(employee.getEmployeeName());
					HRTerminationForm.this.tfReason.setValue(employee.getLeavingReason());
					HRTerminationForm.this.rehireOption.setValue(employee.getRehire());
					HRTerminationForm.this.tfDesignation.setValue(employee.getDesignation());
					HRTerminationForm.this.tfEmployeeId.setValue(employee.getEmployeeId());
					HRTerminationForm.this.dfEmployment.setValue(employee.getEmploymentDate());
					HRTerminationForm.this.tfReturn.setValue(employee.getPossibleReturn());		
					HRTerminationForm.this.tfRecommendation.setValue(employee.getRecommendation());
					HRTerminationForm.this.tfManagement.setValue(employee.getManagementReason());
					HRTerminationForm.this.tfSuggestion.setValue(employee.getSuggestions());
					HRTerminationForm.this.tfComments.setValue(employee.getComments());
					HRTerminationForm.this.cbTerminationReason.setValue(employee.getTerminationReason());

					
					HRTerminationForm.this.tfName.setReadOnly(true);
					HRTerminationForm.this.tfDesignation.setReadOnly(true);
					HRTerminationForm.this.tfEmployeeId.setReadOnly(true);
					HRTerminationForm.this.dfEmployment.setReadOnly(true);	
					HRTerminationForm.this.tfReturn.setReadOnly(true);		
					HRTerminationForm.this.tfRecommendation.setReadOnly(true);
					HRTerminationForm.this.tfManagement.setReadOnly(true);
					HRTerminationForm.this.tfSuggestion.setReadOnly(true);
					HRTerminationForm.this.tfComments.setReadOnly(true);
					HRTerminationForm.this.tfReason.setReadOnly(true);
					HRTerminationForm.this.cbTerminationReason.setReadOnly(true);
					HRTerminationForm.this.rehireOption.setReadOnly(true);


				}

			}
		});

		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		FormLayout form1 = new FormLayout(this.tfName, this.tfDesignation, this.tfEmployeeId, this.dfEmployment,
				this.tfReason, this.tfReturn, this.tfRecommendation, this.tfManagement, this.tfSuggestion, this.tfComments,
				this.rehireOption, this.cbTerminationReason);



		form1.setSpacing(true);
		layoutMain.addComponents(form1);
		content.addComponents(layoutSearch, layoutMain);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
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
