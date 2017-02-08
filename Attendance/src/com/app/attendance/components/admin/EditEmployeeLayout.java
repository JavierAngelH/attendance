/**
 * EditEmployeeLayout.java Created: Nov 16, 2016 JavierAngelH
 */

package com.app.attendance.components.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Bean;
import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.ImageUploader;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
 * EditEmployeeLayout -
 *
 */

@SpringComponent
@UIScope
public class EditEmployeeLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");

	Date currentDate = new Date();

	TextField tfEmployeeId = new TextField("EMPLOYEE ID");

	TextField tfHours = new TextField("HOURS OF WORK");

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

	ListSelect cbProject = new ListSelect("PROJECT");

	String imageUrl = "";

	ImageUploader receiver = new ImageUploader();

	List<Employee> managerList = new ArrayList<>();

	List<Bean> projectList = new ArrayList<>();

	ComboBox cbFunder = new ComboBox("FUNDER");

	List<Bean> funderList = new ArrayList<>();

	Upload uploadImage = new Upload("IMAGE", this.receiver);

	List<Department> departmentList = new ArrayList<>();

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

	ClickListener editUserListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unchecked")
		@Override
		public void buttonClick(ClickEvent event) {
			try {
				Employee employee = new Employee();
				employee.setId(EditEmployeeLayout.this.tfEmployeeId.getValue());
				employee.setFirstname(EditEmployeeLayout.this.tfFirstName.getValue());
				employee.setLastname(EditEmployeeLayout.this.tfLastName.getValue());
				employee.setUsername(EditEmployeeLayout.this.tfUsername.getValue());
				employee.setPassword(EditEmployeeLayout.this.pfPassword.getValue());
				employee.setLocation(EditEmployeeLayout.this.tfLocation.getValue());
				Department department = (Department) EditEmployeeLayout.this.cbDepartment.getValue();
				if (department != null) {
					employee.setDepartment(department.getName());
				}

				Employee manager = (Employee) EditEmployeeLayout.this.cbManager.getValue();
				if (manager != null) {
					employee.setManager(manager.getId());
				}

				employee.setEmpdate(EditEmployeeLayout.this.dfEmployment.getValue());
				if (EditEmployeeLayout.this.cbStatus.getValue() != null) {
					employee.setEmployment_status(EditEmployeeLayout.this.cbStatus.getValue().toString());
				}
				if (EditEmployeeLayout.this.cbRole.getValue() != null) {
					employee.setRole(EditEmployeeLayout.this.cbRole.getValue().toString());
				}

				employee.setPictureURL(EditEmployeeLayout.this.imageUrl);

				Double workedHours = Double.parseDouble(EditEmployeeLayout.this.tfHours.getValue());

				employee.setWorkedHours(workedHours);

				Set<Bean> selectProjects = (Set<Bean>) EditEmployeeLayout.this.cbProject.getValue();
				String projectIds = "";
				for (Bean bean : selectProjects) {
					projectIds = projectIds + bean.getId() + ",";
				}

				employee.setProjectName(projectIds);

				Bean funder = (Bean) EditEmployeeLayout.this.cbFunder.getValue();
				if (funder != null) {
					employee.setFunderName(funder.getId() + "");
				}

				EditEmployeeLayout.this.employeeService.updateEmployee(employee);
				Notification.show("The employee has been updated");
			} catch (Exception e) {
				Notification.show("You must fill the empty fields", Notification.Type.ERROR_MESSAGE);
			}

		}
	};

	public void setData() {

		this.titleLabel.setValue("EDIT EMPLOYEE");
		this.tfEmployeeId.setReadOnly(false);
		this.tfEmployeeId.clear();

		this.tfHours.clear();

		this.tfFirstName.clear();

		this.tfLastName.clear();

		this.tfUsername.clear();

		this.pfPassword.clear();
		this.tfLocation.clear();

		this.dfEmployment.clear();

		this.tfEmployeeId.setReadOnly(true);

		this.tfHours.setReadOnly(true);

		this.tfFirstName.setReadOnly(true);

		this.tfLastName.setReadOnly(true);

		this.tfUsername.setReadOnly(true);

		this.pfPassword.setReadOnly(true);
		this.tfLocation.setReadOnly(true);

		this.dfEmployment.setReadOnly(true);

		this.cbManager.setValue(null);

		this.cbDepartment.setValue(null);

		this.cbStatus.setValue(null);

		this.cbRole.setValue(null);

		this.cbProject.setValue(null);

		this.cbEmployees.removeAllItems();

		this.cbFunder.setValue(null);

		BeanItemContainer<Employee> departmentBeanContainer = new BeanItemContainer<Employee>(Employee.class);

		List<Employee> employeeList = this.employeeService.getEmployeeList();

		departmentBeanContainer.addAll(employeeList);

		this.cbEmployees.setContainerDataSource(departmentBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("username");
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		List<Employee> managerList1 = this.employeeService.managerList();

		List<Bean> projectList1 = this.employeeService.projectsList();

		BeanItemContainer<Bean> funderBeanContainer = new BeanItemContainer<Bean>(Bean.class);

		List<Bean> funderList1 = this.employeeService.funderList();

		funderBeanContainer.addAll(funderList1);

		this.cbFunder.setContainerDataSource(funderBeanContainer);
		this.cbFunder.setItemCaptionPropertyId("name");

		List<Department> departmentList1 = this.employeeService.departmentList();

		BeanItemContainer<Department> departmentBeanContainer = new BeanItemContainer<Department>(Department.class);

		departmentBeanContainer.addAll(departmentList1);

		this.cbDepartment.setContainerDataSource(departmentBeanContainer);
		this.cbDepartment.setItemCaptionPropertyId("description");

		BeanItemContainer<Employee> managerBeanContainer = new BeanItemContainer<Employee>(Employee.class);

		managerBeanContainer.addAll(managerList1);

		this.cbManager.setContainerDataSource(managerBeanContainer);
		this.cbManager.setItemCaptionPropertyId("username");

		BeanItemContainer<Bean> projectBeanContainer = new BeanItemContainer<Bean>(Bean.class);

		projectBeanContainer.addAll(projectList1);

		this.cbProject.setContainerDataSource(projectBeanContainer);
		this.cbProject.setItemCaptionPropertyId("name");
		this.cbProject.setSizeUndefined();
		this.cbProject.setMultiSelect(true);
		this.cbProject.setHeight("75px");

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

		this.tfHours.setValue("8");
		this.tfHours.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfUsername.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.pfPassword.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.cbDepartment.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbManager.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbRole.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbStatus.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbFunder.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbProject.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbEmployees.setNullSelectionAllowed(false);
		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfEmployment.setDateFormat("dd/MM/yyyy");

		this.uploadImage.addStyleName(ValoTheme.BUTTON_TINY);
		this.uploadImage.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.uploadImage.setImmediate(true);
		this.uploadImage.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				EditEmployeeLayout.this.imageUrl = event.getFilename();
				Notification.show("Image uploaded");
			}
		});

		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Employee employee = (Employee) EditEmployeeLayout.this.cbEmployees.getValue();

				EditEmployeeLayout.this.tfEmployeeId.setReadOnly(false);

				EditEmployeeLayout.this.cbManager.setValue(null);

				EditEmployeeLayout.this.cbDepartment.setValue(null);

				EditEmployeeLayout.this.cbStatus.setValue(null);

				EditEmployeeLayout.this.cbRole.setValue(null);

				EditEmployeeLayout.this.cbProject.setValue(null);

				EditEmployeeLayout.this.cbFunder.setValue(null);

				EditEmployeeLayout.this.tfHours.setReadOnly(false);

				EditEmployeeLayout.this.tfFirstName.setReadOnly(false);
				EditEmployeeLayout.this.tfLastName.setReadOnly(false);

				EditEmployeeLayout.this.tfUsername.setReadOnly(false);

				EditEmployeeLayout.this.pfPassword.setReadOnly(false);
				EditEmployeeLayout.this.tfLocation.setReadOnly(false);

				EditEmployeeLayout.this.dfEmployment.setReadOnly(false);
				if (employee != null) {

					EditEmployeeLayout.this.tfEmployeeId.setValue(employee.getId());

					EditEmployeeLayout.this.tfHours.setValue(employee.getWorkedHours().toString());

					EditEmployeeLayout.this.tfFirstName.setValue(employee.getFirstname());
					EditEmployeeLayout.this.tfLastName.setValue(employee.getLastname());

					EditEmployeeLayout.this.tfUsername.setValue(employee.getUsername());

					EditEmployeeLayout.this.pfPassword.setValue(employee.getPassword());
					EditEmployeeLayout.this.tfLocation.setValue(employee.getPassword());

					EditEmployeeLayout.this.dfEmployment.setValue(employee.getEmpdate());

					EditEmployeeLayout.this.cbRole.select(employee.getRole());
					EditEmployeeLayout.this.cbStatus.select(employee.getEmployment_status());

					EditEmployeeLayout.this.imageUrl = employee.getPictureURL();

					for (Department department : departmentList1) {
						if (department.getName().equalsIgnoreCase(employee.getDepartment())) {
							EditEmployeeLayout.this.cbDepartment.select(department);
						}

					}

					for (Employee manager : managerList1) {
						if (manager.getId().equalsIgnoreCase(employee.getManager())) {
							EditEmployeeLayout.this.cbManager.select(manager);
						}
					}

					String projectCodes = employee.getProjectName();

					if (projectCodes != null) {
						if (!projectCodes.isEmpty()) {
							String[] projectArray = projectCodes.split(",");

							for (Bean project : projectList1) {
								for (String id : projectArray) {

									if (Integer.parseInt(id) == project.getId()) {
										EditEmployeeLayout.this.cbProject.select(project);
									}
								}
							}
						}
					}
					if (employee.getFunderName() != null) {
						System.out.println(employee.getFunderName());

						for (Bean funder : funderList1) {
							if (funder.getId() == Integer.parseInt(employee.getFunderName())) {
								EditEmployeeLayout.this.cbFunder.select(funder);
							}
						}

					}

				}
				EditEmployeeLayout.this.tfEmployeeId.setReadOnly(true);

			}
		});

		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		FormLayout form1 = new FormLayout(this.tfEmployeeId, this.tfUsername, this.tfLocation, this.dfEmployment,
				this.cbRole, this.cbProject);

		FormLayout form2 = new FormLayout(this.tfFirstName, this.pfPassword, this.cbDepartment, this.cbStatus,
				this.tfHours);

		FormLayout form3 = new FormLayout(this.tfLastName, this.tfLocation, this.cbManager, this.uploadImage,
				this.cbFunder);

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

		Button btnDelete = new Button("Delete");
		btnDelete.addStyleName(ValoTheme.BUTTON_DANGER);
		btnDelete.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponents(btnSave, btnDelete);
		layoutButton.setSpacing(true);
		btnSave.addClickListener(this.editUserListener);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_RIGHT);
		layoutButton.setComponentAlignment(btnDelete, Alignment.TOP_LEFT);

		btnDelete.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EditEmployeeLayout.this.employeeService.removeEmployee(EditEmployeeLayout.this.tfEmployeeId.getValue());
				Notification.show("Employee deleted succesfully");
				EditEmployeeLayout.this.setData();

			}
		});

		layoutMain.addComponents(form1, form2, form3);
		content.addComponents(layoutSearch, layoutMain, layoutButton);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutButton, Alignment.TOP_CENTER);
		content.setExpandRatio(layoutMain, 3);

		this.cbProject.setWidth(this.tfEmployeeId.getWidth() + "" + this.tfEmployeeId.getWidthUnits());

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
