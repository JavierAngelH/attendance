/**
 * HRTimeSheetsLayout.java Created: Nov 12, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.service.ReportsService;
import com.app.attendance.utils.Utilities;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.Filterable;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.awt.Font;

/**
 * HRTimeSheetsLayout -
 *
 */
@SpringComponent
@UIScope
public class HRTimeSheetsLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ReportsService reportsService;

	final Table table = new Table();

	Button btnPDF = new Button("Generate PDF");

	Date currentDate = new Date();

	List<Employee> list = new ArrayList<>();
	List<Integer> daysList;

	ComboBox cboxDepartment = new ComboBox("SELECT DEPARTMENT");

	ComboBox cboxLocation = new ComboBox("SELECT LOCATION");

	TextField tfEmployee = new TextField("SELECT EMPLOYEE");

	BeanItemContainer<Employee> employeeBeans = new BeanItemContainer<Employee>(Employee.class);

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

	ClickListener createPDFListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {

		
			Embedded pdf = HRTimeSheetsLayout.this.reportsService.buildTimesheetReport(
					HRTimeSheetsLayout.this.daysList, HRTimeSheetsLayout.this.list,
					HRTimeSheetsLayout.this.employeeService.getMonthName(),
					HRTimeSheetsLayout.this.employeeService.getYear());

			Window subWindow = new Window();
			subWindow.setSizeFull();
			subWindow.setModal(true);
			subWindow.setCaption(null);
			VerticalLayout subContent = new VerticalLayout();
			subContent.setMargin(false);
			subContent.setSizeFull();
			subWindow.setContent(subContent);
			pdf.setSizeFull();
			subContent.addComponent(pdf);

			// Center it in the browser window
			subWindow.center();

			// Open it in the UI
			HRTimeSheetsLayout.this.getUI().addWindow(subWindow);

		}
	};

	@SuppressWarnings("unchecked")
	public void setData() {
		this.table.removeAllItems();
		this.cboxDepartment.removeAllItems();
		this.cboxDepartment.setNullSelectionAllowed(true);

		this.cboxLocation.removeAllItems();
		this.cboxLocation.setNullSelectionAllowed(true);
		this.tfEmployee.clear();
		this.titleLabel.setValue("EMPLOYEES ATTENDANCE");

		this.list = this.employeeService.HRTimesheet(new Date());

		

		for (Employee employee2 : this.list) {
			
			Calendar calendar = Calendar.getInstance();

			List<String> hoursList = employee2.getHoursArray();
			List<String> workedDaysList = employee2.getDaysArray();

			Object newItemId = this.table.addItem();
			Item row = this.table.getItem(newItemId);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Project").setValue(employee2.getProjectName());
			row.getItemProperty("Funder").setValue(employee2.getFunderName());
			row.getItemProperty("Department").setValue(employee2.getDepartment());
			row.getItemProperty("Location").setValue(employee2.getLocation());
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
			
			row.getItemProperty("Total H").setValue(totalHours.toString());
		}

		BeanItemContainer<Department> beans = new BeanItemContainer<Department>(Department.class);

		List<Department> departmentList = this.employeeService.departmentList();
		Department departmentAll = new Department();
		departmentAll.setDescription("ALL");

		beans.addItem(departmentAll);

		beans.addAll(departmentList);

		this.cboxDepartment.setContainerDataSource(beans);
		this.cboxDepartment.setNullSelectionAllowed(false);
		this.cboxDepartment.setValue(departmentAll);

		List<String> locationList = this.employeeService.locationList();

		this.cboxLocation.addItem("ALL");
		this.cboxLocation.addItems(locationList);
		this.cboxLocation.setValue("ALL");
		this.cboxLocation.setNullSelectionAllowed(false);

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);
		this.daysList = this.employeeService.listOfDays2Months();
		this.table.setWidth("100%");

		this.table.addContainerProperty("Name", String.class, null);
		this.table.addContainerProperty("Project", String.class, null);
		this.table.addContainerProperty("Funder", String.class, null);

		this.table.addContainerProperty("Department", String.class, null);
		this.table.addContainerProperty("Location", String.class, null);

		for (Integer i : this.daysList) {
			this.table.addContainerProperty(i.toString(), String.class, null);
		}
		this.table.addContainerProperty("Total H", String.class, null);


		// Allow column collapsing
		this.table.setColumnCollapsingAllowed(true);

		// Hide column "Name"
		this.table.setColumnCollapsed("Department", true);
		this.table.setColumnCollapsed("Location", true);

		HorizontalLayout layoutCboxes = new HorizontalLayout();
		layoutCboxes.setWidth("100%");

		this.cboxLocation.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			SimpleStringFilter simpleStringFilter;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Filterable filterable = (Filterable) HRTimeSheetsLayout.this.table.getContainerDataSource();

				if (this.simpleStringFilter != null) {
					filterable.removeContainerFilter(this.simpleStringFilter);

				}

				String selectedPropertyValue = (String) event.getProperty().getValue();

				// Only adds a {@link SimpleStringFilter} when no 'Null' value
				// is selected.
				if (selectedPropertyValue != null) {
					if (!selectedPropertyValue.equals("ALL")) {
						this.simpleStringFilter = new SimpleStringFilter("Location", selectedPropertyValue, false,
								true);

						filterable.addContainerFilter(this.simpleStringFilter);
					}
				}

			}

		});

		this.cboxDepartment.setItemCaptionPropertyId("description");
		this.tfEmployee.setImmediate(true);
		this.tfEmployee.setTextChangeEventMode(TextChangeEventMode.EAGER);

		this.tfEmployee.addTextChangeListener(new TextChangeListener() {

			private static final long serialVersionUID = 1L;

			Filter filter;

			@Override
			public void textChange(TextChangeEvent event) {
				Filterable filterable = (Filterable) HRTimeSheetsLayout.this.table.getContainerDataSource();

				if (this.filter != null) {
					filterable.removeContainerFilter(this.filter);
				}

				String selectedPropertyValue = event.getText();

				if (selectedPropertyValue != null) {
					if (!selectedPropertyValue.isEmpty()) {
						this.filter = new Like("Name", selectedPropertyValue + "%", false);
						filterable.addContainerFilter(this.filter);

					}
				}
			}

		});

		this.cboxDepartment.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			SimpleStringFilter simpleStringFilter;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Filterable filterable = (Filterable) HRTimeSheetsLayout.this.table.getContainerDataSource();

				if (this.simpleStringFilter != null) {
					filterable.removeContainerFilter(this.simpleStringFilter);
				}

				Department selectedPropertyValue = (Department) event.getProperty().getValue();

				if (selectedPropertyValue != null) {
					if (!selectedPropertyValue.getDescription().equals("ALL")) {
						String departmentName = selectedPropertyValue.getName();
						this.simpleStringFilter = new SimpleStringFilter("Department", departmentName, false, true);

						filterable.addContainerFilter(this.simpleStringFilter);
					}
				}

			}

		});

		layoutCboxes.addComponents(this.cboxDepartment, this.cboxLocation, this.tfEmployee);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		this.btnPDF.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.btnPDF.addStyleName(ValoTheme.BUTTON_SMALL);

		this.btnPDF.addClickListener(this.createPDFListener);

		layoutButton.addComponent(this.btnPDF);
		layoutButton.setComponentAlignment(this.btnPDF, Alignment.TOP_CENTER);
		content.setSpacing(true);
		content.addComponents(layoutCboxes, this.table, layoutButton);
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
