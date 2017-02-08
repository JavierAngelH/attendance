/**
 * StaffTimesheetLayout.java Created: Nov 20, 2016 JavierAngelH
 */

package com.app.attendance.components.staff;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.Utilities;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffTimesheetLayout -
 *
 */
@SpringComponent
@UIScope
public class StaffTimesheetLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();
	Employee employee;

	@Autowired
	EmployeeService employeeService;

	private final Table table = new Table();

	Date currentDate = new Date();

	List<Employee> list = new ArrayList<>();
	List<Integer> daysList;

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
		employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("VIEW TIMESHEET");

		this.list = this.employeeService.getEmployeeTimesheet(new Date(), employee.getId());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.currentDate);

		for (Employee employee2 : this.list) {

			List<String> hoursList = employee2.getHoursArray();
			List<String> workedDaysList = employee2.getDaysArray();

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Project").setValue(employee2.getProjectName());
			for (Integer i : this.daysList) {
				String value = "0";

				if (Utilities.isWeekend(i, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))) {
					value = "W";
				} else {
					for (int j = 0; j < workedDaysList.size(); j++) {
						if (workedDaysList.get(j).equals(i.toString())) {
							value = hoursList.get(j);
						}

					}

				}

				row.getItemProperty(i.toString()).setValue(value);

			}

		}

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();

		this.table.setWidth("100%");
		this.table.setEditable(true);
		table.setImmediate(true);
		table.setSelectable(true);
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);
		this.daysList = this.employeeService.listOfDays();
		this.table.addContainerProperty("Name", String.class, null);

		this.table.addContainerProperty("Project", String.class, null);
		table.setColumnWidth("Project", 100);

		for (Integer i : this.daysList) {
			
			this.table.addContainerProperty(i.toString(), String.class, null);
			table.setColumnWidth(i.toString(), 30);
			table.setColumnAlignment(i.toString(), Align.LEFT);

		}

		table.setTableFieldFactory(new TableFieldFactory () {
		    public Field createField(Container container, Object itemId,
		            Object propertyId, Component uiContext) {
		    	
		        TextField field = new TextField((String) propertyId);
		        
	            field.setData(itemId);
	            field.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
	            field.addStyleName(ValoTheme.TEXTAREA_TINY);
	            field.setImmediate(true);
	            field.setSizeUndefined();
		        if (("Name".equals(propertyId))||("Project".equals(propertyId)))
		        {
		        	field.setReadOnly(true);
		        }else{
		        field.addTextChangeListener(new TextChangeListener() {
					
					@Override
					public void textChange(TextChangeEvent event) {
						
						if(field.getValue()!=null)
						if(!field.getValue().isEmpty()){
				            table.select(itemId);

						String totalHours = event.getText();
					
						Double numberHours = Double.parseDouble(totalHours);
						if(numberHours.equals(Double.valueOf(0))){
							field.setValue("0");

						}
						
						if(numberHours > 8){
							Notification.show("You can't put more than 8 hours for a day", Notification.TYPE_WARNING_MESSAGE);
							field.setValue("0");

						}
						else{
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(propertyId.toString()));
						int rowSelected = Integer.parseInt(table.getValue().toString());
						String projectNames = employee.getProjectName();
						String[] projects = projectNames.split(",");
						try{
						employeeService.saveTimesheet(cal.getTime(), cal.getTime(), employee.getId(),
						totalHours, projects[rowSelected-1], Integer.parseInt(employee.getFunderName()));
					
						Notification.show("Number of hours worked updated", Notification.Type.HUMANIZED_MESSAGE);

						}catch(Exception e){
							Notification.show("Please insert another number", Notification.Type.WARNING_MESSAGE);
						}
						}
						}
					}
				});
		       
		        }
			     
		        return field;
		    }
		});
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
