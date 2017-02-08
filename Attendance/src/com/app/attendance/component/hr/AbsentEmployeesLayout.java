/**
 * AbsentEmployeesLayout.java Created: Nov 9, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * AbsentEmployeesLayout -
 *
 */
@SpringComponent
@UIScope
public class AbsentEmployeesLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	Date currentDate = new Date();

	List<Employee> list = new ArrayList<>();

	ComboBox cboxEmployees = new ComboBox("SELECT EMPLOYEE");

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

	ClickListener approveAllListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			AbsentEmployeesLayout.this.employeeService.approveAll(AbsentEmployeesLayout.this.currentDate,
					AbsentEmployeesLayout.this.list);
			Notification.show("All timesheets has been approved.");

		}
	};

	@SuppressWarnings("unchecked")
	public void setData() {
		this.cboxEmployees.setNullSelectionAllowed(true);
		this.table.removeAllItems();
		this.cboxEmployees.removeAllItems();
		this.cboxEmployees.setNullSelectionAllowed(true);

		this.titleLabel.setValue("ABSENT EMPLOYEES");

		this.list = this.employeeService.absentEmployees(this.currentDate);

		for (Employee employee2 : this.list) {

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Department").setValue(employee2.getDepartment());
			row.getItemProperty("Manager").setValue(employee2.getManager());

		}

		Employee employeeAll = new Employee();
		employeeAll.setId("all");
		employeeAll.setFirstname("ALL");

		BeanItemContainer<Employee> beans = new BeanItemContainer<Employee>(Employee.class);
		beans.addBean(employeeAll);
		beans.addAll(this.list);

		this.cboxEmployees.setContainerDataSource(beans);
		this.cboxEmployees.setValue(employeeAll);
		this.cboxEmployees.setNullSelectionAllowed(false);

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);

		this.table.setWidth("100%");
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);

		this.table.addContainerProperty("Name", String.class, null);
		this.table.addContainerProperty("Department", String.class, null);
		this.table.addContainerProperty("Manager", String.class, null);

		HorizontalLayout layoutSearch = new HorizontalLayout();
		layoutSearch.setWidth("100%");

		VerticalLayout layoutCbox = new VerticalLayout();
		layoutCbox.setSizeFull();

		this.cboxEmployees.setItemCaptionPropertyId("firstname");

		layoutCbox.addComponent(this.cboxEmployees);

		layoutSearch.addComponent(layoutCbox);
		content.addComponents(layoutSearch, this.table);
		content.setExpandRatio(this.table, 3);

		this.cboxEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "synthetic-access" })
			@Override
			public void valueChange(ValueChangeEvent event) {
				Employee employee = (Employee) AbsentEmployeesLayout.this.cboxEmployees.getValue();
				AbsentEmployeesLayout.this.table.removeAllItems();
				if (employee != null) {
					if (!employee.getFirstname().equals("ALL")) {
						Object newItemId = AbsentEmployeesLayout.this.table.addItem();
						Item row = AbsentEmployeesLayout.this.table.getItem(newItemId);
						row.getItemProperty("Name").setValue(employee.getFirstname());
						row.getItemProperty("Department").setValue(employee.getDepartment());
						row.getItemProperty("Manager").setValue(employee.getManager());
					} else {
						for (Employee employee2 : AbsentEmployeesLayout.this.list) {

							Object newItemId = AbsentEmployeesLayout.this.table.addItem();
							Item row = AbsentEmployeesLayout.this.table.getItem(newItemId);
							row.getItemProperty("Name").setValue(employee2.getFirstname());
							row.getItemProperty("Department").setValue(employee2.getDepartment());
							row.getItemProperty("Manager").setValue(employee2.getManager());

						}
					}
				}
			}
		});

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
