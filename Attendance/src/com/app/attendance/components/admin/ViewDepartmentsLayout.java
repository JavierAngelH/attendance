/**
 * ViewDepartmentsLayout.java Created: Nov 17, 2016 JavierAngelH
 */

package com.app.attendance.components.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Department;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Item;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * ViewDepartmentsLayout -
 *
 */
@SpringComponent
@UIScope
public class ViewDepartmentsLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	List<Department> list = new ArrayList<>();

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

		this.titleLabel.setValue("VIEW DEPARMENTS");

		this.list = this.employeeService.departmentList();

		for (Department department : this.list) {

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Department ID").setValue(department.getId());
			row.getItemProperty("Department Name").setValue(department.getName());
			row.getItemProperty("Manager ID").setValue(department.getManageirid());
			row.getItemProperty("Description").setValue(department.getDescription());

		}

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();

		this.table.setWidth("100%");
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);

		this.table.addContainerProperty("Department ID", String.class, null);
		this.table.addContainerProperty("Department Name", String.class, null);
		this.table.addContainerProperty("Manager ID", String.class, null);
		this.table.addContainerProperty("Description", String.class, null);

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
