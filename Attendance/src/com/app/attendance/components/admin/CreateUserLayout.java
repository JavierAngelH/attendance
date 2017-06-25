/**
 * CreateUserLayout.java Created: Nov 13, 2016 JavierAngelH
 */

package com.app.attendance.components.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.ImageUploader;
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * CreateUserLayout -
 *
 */
@SpringComponent
@UIScope
public class CreateUserLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfEmployeeId = new TextField("EMPLOYEE ID");

	TextField tfFirstName = new TextField("FIRST NAME");

	TextField tfLastName = new TextField("LAST NAME");

	TextField tfUsername = new TextField("USERNAME");

	PasswordField pfPassword = new PasswordField("PASSWORD");

	TextField tfLocation = new TextField("LOCATION");

	DateField dfEmployment = new DateField("DATE OF EMPLOYMENT");

	ComboBox cbManager = new ComboBox("MANAGER");

	ComboBox cbDepartment = new ComboBox("DEPARTMENT");

	ComboBox cbStatus = new ComboBox("EMPLOYMENT STATUS");

	ComboBox cbRole = new ComboBox("ROLE");

	String imageUrl = "";
	
	String signatureUrl = "";

	ImageUploader receiver = new ImageUploader();

	Upload uploadImage = new Upload("IMAGE", this.receiver);
	


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
			Employee employee = new Employee();
			employee.setId(CreateUserLayout.this.tfEmployeeId.getValue());
			employee.setFirstname(CreateUserLayout.this.tfFirstName.getValue());
			employee.setLastname(CreateUserLayout.this.tfLastName.getValue());
			employee.setUsername(CreateUserLayout.this.tfUsername.getValue());
			employee.setPassword(CreateUserLayout.this.pfPassword.getValue());
			employee.setLocation(CreateUserLayout.this.tfLocation.getValue());
			Department department = (Department) CreateUserLayout.this.cbDepartment.getValue();
			if (department != null) {
				employee.setDepartment(department.getName());
			}

			Employee manager = (Employee) CreateUserLayout.this.cbManager.getValue();
			if (manager != null) {
				employee.setManager(manager.getId());
			}

			employee.setEmpdate(CreateUserLayout.this.dfEmployment.getValue());
			if (CreateUserLayout.this.cbStatus.getValue() != null) {
				employee.setEmployment_status(CreateUserLayout.this.cbStatus.getValue().toString());
			}
			if (CreateUserLayout.this.cbRole.getValue() != null) {
				employee.setRole(CreateUserLayout.this.cbRole.getValue().toString());
			}

			employee.setPictureURL(CreateUserLayout.this.imageUrl);
			
			employee.setSignatureUrl(CreateUserLayout.this.signatureUrl);
			try {
				CreateUserLayout.this.employeeService.insertEmployee(employee);
				Notification.show("The employee has been created");
				CreateUserLayout.this.setData();
			} catch (Exception e) {
				e.printStackTrace();
				Notification.show("There's an error in one of the fields", Notification.Type.ERROR_MESSAGE);
			}

		}
	};

	public void setData() {

		this.titleLabel.setValue("CREATE EMPLOYEE");

		this.tfEmployeeId.clear();

		this.tfFirstName.clear();

		this.tfLastName.clear();

		this.tfUsername.clear();

		this.pfPassword.clear();
		this.tfLocation.clear();

		this.dfEmployment.clear();

		this.cbManager.setValue(null);

		this.cbDepartment.setValue(null);

		this.cbStatus.setValue(null);

		this.cbRole.setValue(null);


	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		BeanItemContainer<Department> departmentBeanContainer = new BeanItemContainer<Department>(Department.class);

		List<Department> departmentList = this.employeeService.departmentList();

		departmentBeanContainer.addAll(departmentList);

		this.cbDepartment.setContainerDataSource(departmentBeanContainer);
		this.cbDepartment.setItemCaptionPropertyId("description");

		BeanItemContainer<Employee> managerBeanContainer = new BeanItemContainer<Employee>(Employee.class);

		List<Employee> managerList = this.employeeService.managerList();

		managerBeanContainer.addAll(managerList);

		this.cbManager.setContainerDataSource(managerBeanContainer);
		this.cbManager.setItemCaptionPropertyId("username");

	

		List<String> statusList = this.employeeService.statusList();
		this.cbStatus.addItems(statusList);

		List<String> roleList = this.employeeService.roleList();
		this.cbRole.addItems(roleList);

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSizeFull();
		layoutMain.setSpacing(true);

		this.tfEmployeeId.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfFirstName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLastName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfUsername.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.pfPassword.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.cbDepartment.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbManager.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbRole.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbStatus.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfEmployment.setDateFormat("dd/MM/yyyy");

		this.uploadImage.addStyleName(ValoTheme.BUTTON_TINY);
		this.uploadImage.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.uploadImage.setImmediate(true);
		this.uploadImage.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				CreateUserLayout.this.imageUrl = event.getFilename();
				Notification.show("Image uploaded");
			}
		});
		

		FormLayout form1 = new FormLayout(this.tfEmployeeId, this.tfUsername, this.tfLocation, this.dfEmployment,
				this.cbRole);

		FormLayout form2 = new FormLayout(this.tfFirstName, this.pfPassword, this.cbDepartment, this.cbStatus);

		FormLayout form3 = new FormLayout(this.tfLastName, this.tfLocation, this.cbManager, this.uploadImage
		);

		form1.setSpacing(true);
		form1.setSizeFull();

		form2.setSpacing(true);
		form2.setSizeFull();

		form3.setSpacing(true);
		form3.setSizeFull();

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);

		btnSave.addClickListener(this.createUserListener);
		layoutButton.setComponentAlignment(btnSave, Alignment.MIDDLE_CENTER);

		layoutMain.addComponents(form1, form2, form3);
		content.addComponents(layoutMain, layoutButton);
		content.setExpandRatio(layoutMain, 3);
		content.setComponentAlignment(layoutMain, Alignment.MIDDLE_CENTER);
		content.setComponentAlignment(layoutButton, Alignment.MIDDLE_CENTER);


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
