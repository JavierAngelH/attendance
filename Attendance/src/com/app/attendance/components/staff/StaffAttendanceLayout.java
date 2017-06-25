/**
 * StaffAttendanceLayout.java Created: Nov 12, 2016 JavierAngelH
 */

package com.app.attendance.components.staff;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Bean;
import com.app.attendance.model.Employee;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffAttendanceLayout -
 *
 */
@SpringComponent
@UIScope
public class StaffAttendanceLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	Date currentDate = new Date();

	TextField tfMonth = new TextField("MONTH");

	TextField tfName = new TextField("FULL NAME");

	DateField dfEmployment = new DateField("DATE OF EMPLOYMENT");

	TextField tfYear = new TextField("YEAR");

	TextField tfDesignation = new TextField("DESIGNATION");

	TextField tfLocation = new TextField("LOCATION");

	TextField tfSupervisor = new TextField("SUPERVISOR");

	PopupDateField popWorkedDay = new PopupDateField("DAY WORKED");

	ComboBox cbCodes = new ComboBox("CODES FOR NON WORK HOURS");

	PopupDateField startDate = new PopupDateField("START DATE");

	PopupDateField endDate = new PopupDateField("END DATE");
	
	ComboBox cbFunder = new ComboBox("FUNDER");

	ComboBox cbProject = new ComboBox("PROJECT");
	
	TextField tfHoursWorked = new TextField("HOURS WORKED");

	Employee employee;

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

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {

			Bean funder = (Bean) cbFunder.getValue();
			Bean project = (Bean) cbProject.getValue();

			if (StaffAttendanceLayout.this.startDate.isVisible()) {

				Bean codeBean = (Bean) StaffAttendanceLayout.this.cbCodes.getValue();
				String code = codeBean.getCode();
				StaffAttendanceLayout.this.employeeService.saveTimesheet(
						StaffAttendanceLayout.this.startDate.getValue(), 
						StaffAttendanceLayout.this.endDate.getValue(),
						StaffAttendanceLayout.this.employee.getId(), code, project.getId(), funder.getId());

			} else {
				StaffAttendanceLayout.this.employeeService.saveTimesheet(
						StaffAttendanceLayout.this.popWorkedDay.getValue(),
						StaffAttendanceLayout.this.popWorkedDay.getValue(), 
						StaffAttendanceLayout.this.employee.getId(),
						tfHoursWorked.getValue(),
						project.getId(), funder.getId());
			}
			Notification.show("Information saved succesfully.");

		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("ATTENDANCE TIMESHEET");

		this.tfMonth.setReadOnly(false);
		this.tfMonth.setValue(this.employeeService.getMonthName().toUpperCase());
		this.tfMonth.setReadOnly(true);

		this.tfName.setReadOnly(false);
		this.tfName
				.setValue(this.employee.getFirstname().toUpperCase() + " " + this.employee.getLastname().toUpperCase());
		this.tfName.setReadOnly(true);

		this.dfEmployment.setReadOnly(false);
		this.dfEmployment.setValue(this.employee.getEmpdate());
		this.dfEmployment.setReadOnly(true);

		this.tfYear.setReadOnly(false);
		this.tfYear.setValue(this.employeeService.getYear());
		this.tfYear.setReadOnly(true);

		this.tfDesignation.setReadOnly(false);
		this.tfDesignation.setValue(this.employee.getDepartment().toUpperCase());
		this.tfDesignation.setReadOnly(true);

		this.tfSupervisor.setReadOnly(false);
		this.tfSupervisor.setValue(this.employee.getManager().toUpperCase());
		this.tfSupervisor.setReadOnly(true);

		this.tfLocation.setReadOnly(false);
		this.tfLocation.setValue(this.employee.getLocation().toUpperCase());
		this.tfLocation.setReadOnly(true);

		this.popWorkedDay.setValue(this.currentDate);

		this.startDate.setVisible(false);
		this.endDate.setVisible(false);
		


	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);
		
		BeanItemContainer<Bean> funderBeanContainer = new BeanItemContainer<Bean>(Bean.class);
		List<Bean> funderList = this.employeeService.funderList();
		funderBeanContainer.addAll(funderList);
		this.cbFunder.setContainerDataSource(funderBeanContainer);
		this.cbFunder.setItemCaptionPropertyId("name");

		BeanItemContainer<Bean> projectBeanContainer = new BeanItemContainer<Bean>(Bean.class);
		List<Bean> projectList = this.employeeService.projectsList();
		projectBeanContainer.addAll(projectList);
		this.cbProject.setContainerDataSource(projectBeanContainer);
		this.cbProject.setItemCaptionPropertyId("name");
		
		
		BeanItemContainer<Bean> beansCodes = new BeanItemContainer<Bean>(Bean.class);
		List<Bean> codeList = this.employeeService.codesList();
		beansCodes.addAll(codeList);
		this.cbCodes.setContainerDataSource(beansCodes);
		this.cbCodes.setItemCaptionPropertyId("description");
		this.cbCodes.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Bean codeBean = (Bean) StaffAttendanceLayout.this.cbCodes.getValue();
				String code = codeBean.getCode();
				if (code.equalsIgnoreCase("V") || code.equalsIgnoreCase("T")) {
					StaffAttendanceLayout.this.startDate.setVisible(true);
					StaffAttendanceLayout.this.endDate.setVisible(true);

				} else {
					StaffAttendanceLayout.this.startDate.setVisible(false);
					StaffAttendanceLayout.this.endDate.setVisible(false);
				}

				if (code.equalsIgnoreCase("V")) {
					Date firstDate = StaffAttendanceLayout.this.startDate.getValue();
					Calendar newCalendar = Calendar.getInstance();
					newCalendar.setTime(firstDate);
					StaffAttendanceLayout.this.endDate.setRangeStart(newCalendar.getTime());

					Calendar endCalendar = Calendar.getInstance();
					endCalendar.setTime(newCalendar.getTime());
					endCalendar.set(Calendar.DAY_OF_YEAR, endCalendar.get(Calendar.DAY_OF_YEAR) + 25);

					StaffAttendanceLayout.this.endDate.setRangeEnd(endCalendar.getTime());
				} else {
					StaffAttendanceLayout.this.endDate.setRangeEnd(null);
				}

			}
		});
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSizeFull();
		layoutMain.setSpacing(true);

		this.tfMonth.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfMonth.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfYear.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfYear.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		this.tfSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSupervisor.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		
		this.tfHoursWorked.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.cbCodes.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbFunder.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbProject.addStyleName(ValoTheme.COMBOBOX_TINY);


		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfEmployment.setDateFormat("dd/MM/yyyy");
		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);

		this.startDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.startDate.setDateFormat("dd/MM/yyyy");
		this.startDate.setInvalidAllowed(false);

		this.endDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.endDate.setDateFormat("dd/MM/yyyy");
		this.endDate.setInvalidAllowed(false);

		Calendar calendarSpecialDates = Calendar.getInstance();
		calendarSpecialDates.set(Calendar.DAY_OF_YEAR, calendarSpecialDates.get(Calendar.DAY_OF_YEAR) + 1);

		this.startDate.setRangeStart(calendarSpecialDates.getTime());
		this.endDate.setRangeStart(calendarSpecialDates.getTime());

		this.startDate.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Bean code = (Bean) StaffAttendanceLayout.this.cbCodes.getValue();
				if (code != null) {
					if (code.getCode().equalsIgnoreCase("V")) {
						Date firstDate = StaffAttendanceLayout.this.startDate.getValue();
						Calendar newCalendar = Calendar.getInstance();
						newCalendar.setTime(firstDate);
						StaffAttendanceLayout.this.endDate.setRangeStart(newCalendar.getTime());

						Calendar endCalendar = Calendar.getInstance();
						endCalendar.setTime(newCalendar.getTime());
						endCalendar.set(Calendar.DAY_OF_YEAR, endCalendar.get(Calendar.DAY_OF_YEAR) + 25);

						StaffAttendanceLayout.this.endDate.setRangeEnd(endCalendar.getTime());
					} else {
						StaffAttendanceLayout.this.endDate.setRangeEnd(null);
					}
				}
			}
		});

		this.startDate.setValue(calendarSpecialDates.getTime());
		this.endDate.setValue(calendarSpecialDates.getTime());

		this.popWorkedDay.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.popWorkedDay.setDateFormat("dd");
		this.popWorkedDay.setInvalidAllowed(false);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		this.popWorkedDay.setRangeStart(calendar.getTime());
		this.popWorkedDay.setRangeEnd(this.currentDate);

		FormLayout form1 = new FormLayout(this.tfMonth, this.tfYear, this.tfLocation, this.startDate, this.cbFunder);

		FormLayout form2 = new FormLayout(this.tfName, this.tfDesignation, this.popWorkedDay, this.endDate, this.cbProject);

		FormLayout form3 = new FormLayout(this.dfEmployment, this.tfSupervisor, this.cbCodes, this.tfHoursWorked);

		form1.setSpacing(true);
		form1.setSizeFull();

		form2.setSpacing(true);
		form2.setSizeFull();

		form3.setSpacing(true);
		form3.setSizeFull();

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(this.sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1, form2, form3);
		content.addComponents(layoutMain, layoutButton);
		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutButton, Alignment.TOP_CENTER);
		content.setExpandRatio(layoutMain, 3);
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
