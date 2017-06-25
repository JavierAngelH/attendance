/**
 * ManagerTimeSheetLayout.java Created: Nov 8, 2016 JavierAngelH
 */

package com.app.attendance.component.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.Utilities;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * ManagerTimeSheetLayout -
 *
 */
@SpringComponent
@UIScope
public class ManagerTimeSheetLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	Button btnApproveAll = new Button("Approve All");

	Date currentDate = new Date();

	List<Employee> list = new ArrayList<>();
	List<Integer> daysList;

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

	ClickListener approveAllListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			ManagerTimeSheetLayout.this.employeeService.approveAll(ManagerTimeSheetLayout.this.currentDate,
					ManagerTimeSheetLayout.this.list);
			Notification.show("All timesheets has been approved.");

		}
	};

	@SuppressWarnings("unchecked")
	public void setData() {
		this.table.removeAllItems();
		Employee employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		String displayName = employee.getFirstname() + " " + employee.getLastname();
		this.titleLabel.setValue("EMPLOYEES ATTENDANCE");

		this.list = this.employeeService.workedHoursByEmployees(new Date(), employee.getId());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.currentDate);

		for (Employee employee2 : this.list) {

			List<String> hoursList = employee2.getHoursArray();
			List<String> workedDaysList = employee2.getDaysArray();

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Project").setValue(employee2.getProjectName());
			row.getItemProperty("Funder").setValue(employee2.getFunderName());

			Double totalHours = Double.valueOf(0);
			for (Integer i : this.daysList) {
				String value = "0";
				
				if(i>25){
					calendar.add(Calendar.MONTH, -1);					
				}else
					calendar.setTime(this.currentDate);


				if (Utilities.isWeekend(i, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))) {
					value = "W";
				} else {
					for (int j = 0; j < workedDaysList.size(); j++) {
						if (workedDaysList.get(j).equals(i.toString())) {
							value = hoursList.get(j);
							value = value.replace(".0", "");
						}

					}

				}

				row.getItemProperty(i.toString()).setValue(value);
				if(!value.equals("W")&&(!value.equals("V")))
				totalHours = totalHours + Double.valueOf(value);
			}
			
			row.getItemProperty("Total").setValue(totalHours.toString());

			Button approveBtn = new Button();
			approveBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			approveBtn.addStyleName(ValoTheme.BUTTON_TINY);
			approveBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
			approveBtn.setIcon(FontAwesome.CHECK);
			approveBtn.setDescription("Approve");

			approveBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ManagerTimeSheetLayout.this.employeeService.approveTimesheet(calendar.getTime(), employee2.getId());
					ManagerTimeSheetLayout.this.setData();
					Notification.show("Timesheet for " + employee2.getFirstname() + " approved.",
							Notification.Type.HUMANIZED_MESSAGE);

				}
			});
			row.getItemProperty("").setValue(approveBtn);

			Button declineBtn = new Button();
			declineBtn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			declineBtn.addStyleName(ValoTheme.BUTTON_TINY);
			declineBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
			declineBtn.setIcon(FontAwesome.CLOSE);
			declineBtn.setDescription("Decline");
			row.getItemProperty(" ").setValue(declineBtn);

			declineBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ManagerTimeSheetLayout.this.employeeService.declineTimesheet(calendar.getTime(), employee2.getId());
					ManagerTimeSheetLayout.this.setData();
					Notification.show("Timesheet for " + employee2.getFirstname() + " declined.",
							Notification.Type.HUMANIZED_MESSAGE);

				}
			});
		}

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		this.table.setWidth("100%");

		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);
		this.daysList = this.employeeService.listOfDays2Months();

		this.table.addContainerProperty("Name", String.class, null);
		this.table.addContainerProperty("Project", String.class, null);
		this.table.addContainerProperty("Funder", String.class, null);

		table.setColumnWidth("Project", 100);
		
		table.setColumnWidth("Funder", 100);

		for (Integer i : this.daysList) {
			this.table.addContainerProperty(i.toString(), String.class, null);
		}
		table.addContainerProperty("Total", String.class, null);
		this.table.addContainerProperty("", Button.class, null);

		this.table.addContainerProperty(" ", Button.class, null);

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
                                this.titleLabel.setIcon(FontAwesome.USER);

		header.addComponent(this.titleLabel);
		header.setComponentAlignment(this.titleLabel, Alignment.MIDDLE_CENTER);
		return header;
	}

}
