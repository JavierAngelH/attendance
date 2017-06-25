/**
 * HRLeaveManagementLayout.java Created: Dec 12, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.LeaveApplication;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRLeaveManagementLayout -
 *
 */
@SpringComponent
@UIScope
public class HRLeaveManagementLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");

	TextField tfName = new TextField("NAME");

	TextField tfDesignation = new TextField("DESIGNATION");

	TextField tfLocation = new TextField("LOCATION");

	TextField tfExplanation = new TextField("EXPLANATION FOR LEAVE");

	TextField tfDays = new TextField("N° OF DAYS REQUIRED");

	TextField tfType = new TextField("TYPE OF LEAVE");

	TextField tfBalance = new TextField("BALANCE");

	RichTextArea taBackstopping = new RichTextArea("BACKSTOPPING ARRANGEMENT");
	
	DateField dfStartDate = new DateField("START DATE");
	
	DateField dfEndDate = new DateField("END DATE");

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

	public void setData() {

		this.titleLabel.setValue("LEAVE APPLICATIONS");
		
		this.tfName.setReadOnly(false);
		this.tfDesignation.setReadOnly(false);
		this.tfLocation.setReadOnly(false);
		this.tfExplanation.setReadOnly(false);
		this.tfDays.setReadOnly(false);
		this.tfType.setReadOnly(false);
		this.tfBalance.setReadOnly(false);
		this.taBackstopping.setReadOnly(false);
		this.dfEndDate.setReadOnly(false);
		this.dfStartDate.setReadOnly(false);
		
		this.dfEndDate.clear();
		
		this.dfStartDate.clear();

		this.tfName.clear();

		this.tfDesignation.clear();

		this.tfLocation.clear();

		this.tfExplanation.clear();

		this.tfDays.clear();
		this.tfType.clear();

		this.tfBalance.clear();

		this.taBackstopping.clear();

		this.tfName.setReadOnly(true);
		this.tfDesignation.setReadOnly(true);
		this.tfLocation.setReadOnly(true);
		this.tfExplanation.setReadOnly(true);
		this.tfDays.setReadOnly(true);
		this.tfType.setReadOnly(true);
		this.tfBalance.setReadOnly(true);
		this.taBackstopping.setReadOnly(true);
		this.cbEmployees.removeAllItems();

		this.dfEndDate.setReadOnly(true);
		this.dfStartDate.setReadOnly(true);

		BeanItemContainer<LeaveApplication> applicationBeanContainer = new BeanItemContainer<LeaveApplication>(LeaveApplication.class);

		List<LeaveApplication> applicationList = this.employeeService.getLeaveApplications();

		applicationBeanContainer.addAll(applicationList);

		this.cbEmployees.setContainerDataSource(applicationBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("employeeName");
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSizeFull();
		layoutMain.setSpacing(true);

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfExplanation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfDays.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfType.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfBalance.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.taBackstopping.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.dfEndDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfStartDate.addStyleName(ValoTheme.DATEFIELD_TINY);

		
		tfName.setSizeFull();
		tfDesignation.setSizeFull();
		tfLocation.setSizeFull();
		tfExplanation.setSizeFull();
		tfDays.setSizeFull();
		tfType.setSizeFull();
		tfBalance.setSizeFull();
		taBackstopping.setSizeFull();
		dfEndDate.setSizeFull();
		dfStartDate.setSizeFull();


		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbEmployees.setNullSelectionAllowed(false);

		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				LeaveApplication employee = (LeaveApplication) HRLeaveManagementLayout.this.cbEmployees.getValue();

				if (employee != null) {

					HRLeaveManagementLayout.this.tfName.setReadOnly(false);
					HRLeaveManagementLayout.this.tfDesignation.setReadOnly(false);
					HRLeaveManagementLayout.this.tfLocation.setReadOnly(false);
					HRLeaveManagementLayout.this.tfExplanation.setReadOnly(false);
					HRLeaveManagementLayout.this.tfDays.setReadOnly(false);
					HRLeaveManagementLayout.this.tfType.setReadOnly(false);
					HRLeaveManagementLayout.this.tfBalance.setReadOnly(false);
					HRLeaveManagementLayout.this.taBackstopping.setReadOnly(false);
					HRLeaveManagementLayout.this.dfStartDate.setReadOnly(false);
					HRLeaveManagementLayout.this.dfEndDate.setReadOnly(false);

					
					HRLeaveManagementLayout.this.tfName.setValue(employee.getEmployeeName());
					HRLeaveManagementLayout.this.tfDesignation.setValue(employee.getDesignation());
					HRLeaveManagementLayout.this.tfLocation.setValue(employee.getLocation());
					HRLeaveManagementLayout.this.tfExplanation.setValue(employee.getExplanation());
					HRLeaveManagementLayout.this.tfDays.setValue(employee.getDays() + "");
					HRLeaveManagementLayout.this.tfType.setValue(employee.getType());
					HRLeaveManagementLayout.this.tfBalance.setValue(employee.getBalance()+"");
					HRLeaveManagementLayout.this.taBackstopping.setValue(employee.getBackstoping());
					HRLeaveManagementLayout.this.dfStartDate.setValue(employee.getStartDate());
					HRLeaveManagementLayout.this.dfEndDate.setValue(employee.getEndDate());

					
					HRLeaveManagementLayout.this.tfName.setReadOnly(true);
					HRLeaveManagementLayout.this.tfDesignation.setReadOnly(true);
					HRLeaveManagementLayout.this.tfLocation.setReadOnly(true);
					HRLeaveManagementLayout.this.tfExplanation.setReadOnly(true);
					HRLeaveManagementLayout.this.tfDays.setReadOnly(true);
					HRLeaveManagementLayout.this.tfType.setReadOnly(true);
					HRLeaveManagementLayout.this.tfBalance.setReadOnly(true);
					HRLeaveManagementLayout.this.taBackstopping.setReadOnly(true);
					HRLeaveManagementLayout.this.dfStartDate.setReadOnly(true);
					HRLeaveManagementLayout.this.dfEndDate.setReadOnly(true);
				}

			}
		});
		
		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		FormLayout form1 = new FormLayout(this.tfName, this.tfLocation, this.dfStartDate, this.tfExplanation);

		FormLayout form2 = new FormLayout(this.tfDesignation, this.tfBalance, this.dfEndDate, this.tfType);

		form1.setSpacing(true);
		form1.setSizeFull();

		form2.setSpacing(true);
		form2.setSizeFull();

		layoutMain.addComponents(form1, form2);
		content.addComponents(layoutSearch, layoutMain, this.taBackstopping);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(this.taBackstopping, Alignment.TOP_CENTER);

		content.setExpandRatio(layoutSearch, 0.5f);
		content.setExpandRatio(layoutMain, 1.75f);
		content.setExpandRatio(this.taBackstopping, 1.75f);

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
