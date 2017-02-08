/**
 * ManagerLeaveRequestsLayout.java Created: Nov 9, 2016 JavierAngelH
 */

package com.app.attendance.component.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * ManagerLeaveRequestsLayout -
 *
 */
@SpringComponent
@UIScope
public class ManagerLeaveRequestsLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	Button btnApproveAll = new Button("Approve All");

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

	ClickListener approveAllListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			ManagerLeaveRequestsLayout.this.employeeService.approveAllRequests(ManagerLeaveRequestsLayout.this.list);
			Notification.show("All leave requests has been approved.");

		}
	};

	@SuppressWarnings("unchecked")
	public void setData() {
		this.table.removeAllItems();
		Employee employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("EMPLOYEES LEAVE REQUESTS");

		this.list = this.employeeService.leaveRequestsByManager(employee.getId());

		for (Employee employee2 : this.list) {

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Department").setValue(employee2.getDepartment());
			DateField startDate = new DateField();
			startDate.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
			startDate.setDateFormat("dd/MM/yyyy");
			startDate.setValue(employee2.getStartDate());
			startDate.setReadOnly(true);
			row.getItemProperty("Start Date").setValue(startDate);
			DateField endDate = new DateField();
			endDate.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
			endDate.setDateFormat("dd/MM/yyyy");
			endDate.setValue(employee2.getEndDate());
			endDate.setReadOnly(true);

			row.getItemProperty("End Date").setValue(endDate);

			Button approveBtn = new Button();
			approveBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			approveBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
			approveBtn.setIcon(FontAwesome.CHECK);
			approveBtn.setDescription("Approve");

			approveBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ManagerLeaveRequestsLayout.this.employeeService.approveRequestByManager(employee2.getStartDate(),
							employee2.getEndDate(), employee2.getId());
					ManagerLeaveRequestsLayout.this.setData();
					Notification.show("Leave Request for " + employee2.getFirstname() + " approved.",
							Notification.Type.HUMANIZED_MESSAGE);

				}
			});
			row.getItemProperty("Approve").setValue(approveBtn);

			Button declineBtn = new Button();
			declineBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			declineBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
			declineBtn.setIcon(FontAwesome.CLOSE);
			declineBtn.setDescription("Decline");
			row.getItemProperty("Decline").setValue(declineBtn);

			declineBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ManagerLeaveRequestsLayout.this.employeeService.declineRequestByManager(employee2.getStartDate(),
							employee2.getEndDate(), employee2.getId());
					ManagerLeaveRequestsLayout.this.setData();
					Notification.show("Leave Request for " + employee2.getFirstname() + " declined.",
							Notification.Type.HUMANIZED_MESSAGE);

				}
			});
		}

		this.table.setColumnAlignment("Approve", Align.CENTER);
		this.table.setColumnAlignment("Decline", Align.CENTER);

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();

		this.table.setWidth("100%");
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);

		this.table.addContainerProperty("Name", String.class, null);
		this.table.addContainerProperty("Department", String.class, null);
		this.table.addContainerProperty("Start Date", DateField.class, null);
		this.table.addContainerProperty("End Date", DateField.class, null);
		this.table.addContainerProperty("Approve", Button.class, null);

		this.table.addContainerProperty("Decline", Button.class, null);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		this.btnApproveAll.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.btnApproveAll.addStyleName(ValoTheme.BUTTON_SMALL);
		this.btnApproveAll.addClickListener(this.approveAllListener);

		layoutButton.addComponent(this.btnApproveAll);
		layoutButton.setComponentAlignment(this.btnApproveAll, Alignment.TOP_CENTER);

		content.addComponents(this.table, layoutButton);
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
