/**
 * HRLeaveRequestsLayout.java Created: Nov 11, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Department;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.service.ReportsService;
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
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRLeaveRequestsLayout -
 *
 */
@SpringComponent
@UIScope
public class HRLeaveRequestsLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ReportsService reportsService;

	final Table table = new Table();

	Button btnPDF = new Button("Generate PDF");

	List<Employee> list = new ArrayList<>();

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

	ClickListener generatePDFListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			Embedded pdf = HRLeaveRequestsLayout.this.reportsService.buildLeavesReport(HRLeaveRequestsLayout.this.list,
					HRLeaveRequestsLayout.this.employeeService.getMonthName(),
					HRLeaveRequestsLayout.this.employeeService.getYear());

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
			HRLeaveRequestsLayout.this.getUI().addWindow(subWindow);
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

		this.titleLabel.setValue("REQUESTS FOR LEAVES");

		this.list = this.employeeService.leaveRequestsByHR();

		for (Employee employee2 : this.list) {
			this.table.addItem(employee2);
			Item row = HRLeaveRequestsLayout.this.table.getItem(employee2);
			row.getItemProperty("Name").setValue(employee2.getFirstname());
			row.getItemProperty("Department").setValue(employee2.getDepartment());
			row.getItemProperty("Location").setValue(employee2.getLocation());

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
					HRLeaveRequestsLayout.this.employeeService.approveRequestByHR(employee2.getStartDate(),
							employee2.getEndDate(), employee2.getId());
					HRLeaveRequestsLayout.this.setData();
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
					HRLeaveRequestsLayout.this.employeeService.declineRequestbyHR(employee2.getStartDate(),
							employee2.getEndDate(), employee2.getId());
					HRLeaveRequestsLayout.this.setData();
					Notification.show("Leave Request for " + employee2.getFirstname() + " declined.",
							Notification.Type.HUMANIZED_MESSAGE);

				}
			});
		}

		this.table.setColumnAlignment("Approve", Align.CENTER);
		this.table.setColumnAlignment("Decline", Align.CENTER);

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
		content.setSizeFull();
		content.setSpacing(true);

		this.table.setSizeFull();
		this.table.addStyleName(ValoTheme.TABLE_COMPACT);
		this.table.addStyleName(ValoTheme.TABLE_SMALL);

		this.table.addContainerProperty("Name", String.class, null);
		this.table.addContainerProperty("Department", String.class, null);
		this.table.addContainerProperty("Location", String.class, null);

		this.table.addContainerProperty("Start Date", DateField.class, null);
		this.table.addContainerProperty("End Date", DateField.class, null);
		this.table.addContainerProperty("Approve", Button.class, null);

		this.table.addContainerProperty("Decline", Button.class, null);

		HorizontalLayout layoutCboxes = new HorizontalLayout();
		layoutCboxes.setWidth("100%");

		this.cboxLocation.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			SimpleStringFilter simpleStringFilter;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Filterable filterable = (Filterable) HRLeaveRequestsLayout.this.table.getContainerDataSource();

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
				Filterable filterable = (Filterable) HRLeaveRequestsLayout.this.table.getContainerDataSource();

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
				Filterable filterable = (Filterable) HRLeaveRequestsLayout.this.table.getContainerDataSource();

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

		this.btnPDF.addClickListener(this.generatePDFListener);

		layoutButton.addComponent(this.btnPDF);
		layoutButton.setComponentAlignment(this.btnPDF, Alignment.TOP_CENTER);

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
		header.addComponent(this.titleLabel);
		header.setComponentAlignment(this.titleLabel, Alignment.MIDDLE_CENTER);
		return header;
	}

}
