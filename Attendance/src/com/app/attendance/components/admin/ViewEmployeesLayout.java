/**
 * ViewEmployeesLayout.java Created: Nov 16, 2016 JavierAngelH
 */

package com.app.attendance.components.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.Utilities;
import com.vaadin.data.Item;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * ViewEmployeesLayout -
 *
 */
@SpringComponent
@UIScope
public class ViewEmployeesLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	List<Employee> list = new ArrayList<>();

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
		this.table.removeAllItems();

		this.titleLabel.setValue("VIEW EMPLOYEES");

		this.list = this.employeeService.getEmployeeList();

		for (Employee employee2 : this.list) {

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Employee ID").setValue(employee2.getId());
			row.getItemProperty("First Name").setValue(employee2.getFirstname());
			row.getItemProperty("Last Name").setValue(employee2.getLastname());
			row.getItemProperty("Employment Status").setValue(employee2.getEmployment_status());
			row.getItemProperty("Role").setValue(employee2.getRole());
			try {
				FileResource resource = new FileResource(new File(Utilities.imagesPath + employee2.getPictureURL()));
				row.getItemProperty("Picture").setValue(new Embedded("", resource));

			} catch (Exception e) {
				System.out.println(e.getMessage());
				row.getItemProperty("Picture").setValue(null);

			}
		}

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();

		this.table.setWidth("100%");
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);

		this.table.addContainerProperty("Employee ID", String.class, null);
		this.table.addContainerProperty("First Name", String.class, null);
		this.table.addContainerProperty("Last Name", String.class, null);
		this.table.addContainerProperty("Employment Status", String.class, null);
		this.table.addContainerProperty("Role", String.class, null);
		this.table.addContainerProperty("Picture", Embedded.class, null);

		content.addComponent(this.table);
		content.setExpandRatio(this.table, 3);

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
