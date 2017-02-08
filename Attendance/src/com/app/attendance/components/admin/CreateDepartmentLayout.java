/**
a * CreateDepartmentLayout.java Created: Nov 17, 2016 JavierAngelH
 */

package com.app.attendance.components.admin;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * CreateDepartmentLayout -
 *
 */
@SpringComponent
@UIScope
public class CreateDepartmentLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfDepartmentId = new TextField("DEPARTMENT ID");

	TextField tfName = new TextField("DEPARTMENT NAME");

	TextField tfDescription = new TextField("DESCRIPTION");

	ComboBox cbManager = new ComboBox("MANAGER");

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

	ClickListener createUserListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			Department department = new Department();
			department.setDescription(CreateDepartmentLayout.this.tfDescription.getValue());
			department.setId(CreateDepartmentLayout.this.tfDepartmentId.getValue());
			department.setName(CreateDepartmentLayout.this.tfName.getValue());

			Employee selectedManager = (Employee) CreateDepartmentLayout.this.cbManager.getValue();
			department.setManageirid(selectedManager.getId());
			try {
				CreateDepartmentLayout.this.employeeService.insertDepartment(department);
				Notification.show("The department has been created");
			} catch (Exception e) {
				Notification.show("There's an error in one of the fields", Notification.Type.ERROR_MESSAGE);
			}

		}
	};

	public void setData() {

		this.titleLabel.setValue("CREATE DEPARTMENT");
		this.tfDepartmentId.clear();
		this.tfDescription.clear();
		this.tfName.clear();
		this.cbManager.setValue(null);

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		BeanItemContainer<Employee> managerBeanContainer = new BeanItemContainer<Employee>(Employee.class);

		List<Employee> managerList = this.employeeService.managerList();

		managerBeanContainer.addAll(managerList);

		this.cbManager.setContainerDataSource(managerBeanContainer);
		this.cbManager.setItemCaptionPropertyId("username");

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSizeFull();
		layoutMain.setSpacing(true);

		this.tfDepartmentId.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfDescription.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.cbManager.addStyleName(ValoTheme.COMBOBOX_TINY);

		FormLayout form1 = new FormLayout(this.tfDepartmentId, this.tfName);

		FormLayout form2 = new FormLayout(this.tfDescription, this.cbManager);

		form1.setSpacing(true);
		form1.setSizeFull();

		form2.setSpacing(true);
		form2.setSizeFull();

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);

		btnSave.addClickListener(this.createUserListener);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1, form2);
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
