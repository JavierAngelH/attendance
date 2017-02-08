package com.app.attendance;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.component.hr.AbsentEmployeesLayout;
import com.app.attendance.component.hr.HRAppresialsLayout;
import com.app.attendance.component.hr.HRLeaveManagementLayout;
import com.app.attendance.component.hr.HRLeaveRequestsLayout;
import com.app.attendance.component.hr.HRTerminationForm;
import com.app.attendance.component.hr.HRTimeSheetsLayout;
import com.app.attendance.component.manager.ManagerAppresialLayout;
import com.app.attendance.component.manager.ManagerLeaveRequestsLayout;
import com.app.attendance.component.manager.ManagerTimeSheetLayout;
import com.app.attendance.components.HomePanel;
import com.app.attendance.components.LoginPanel;
import com.app.attendance.components.Menu;
import com.app.attendance.components.admin.CreateDepartmentLayout;
import com.app.attendance.components.admin.CreateUserLayout;
import com.app.attendance.components.admin.EditDepartmentLayout;
import com.app.attendance.components.admin.EditEmployeeLayout;
import com.app.attendance.components.admin.ViewDepartmentsLayout;
import com.app.attendance.components.admin.ViewEmployeesLayout;
import com.app.attendance.components.staff.StaffAppresialLayout;
import com.app.attendance.components.staff.StaffAttendanceLayout;
import com.app.attendance.components.staff.StaffLeaveManagementLayout;
import com.app.attendance.components.staff.StaffTerminationForm;
import com.app.attendance.components.staff.StaffTimesheetLayout;
import com.app.attendance.service.EmployeeService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

@SpringComponent
@UIScope
public class FrontEndView extends HorizontalLayout implements View {

	@Autowired
	LoginPanel loginPanel;

	@Autowired
	HomePanel homePanel;

	@Autowired
	ManagerTimeSheetLayout managertimesheetPanel;

	@Autowired
	Menu menu;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ManagerLeaveRequestsLayout managerLeavePanel;

	@Autowired
	AbsentEmployeesLayout absentEmployeesPanel;

	@Autowired
	HRLeaveRequestsLayout hrLeaveRequestsPanel;

	@Autowired
	HRTimeSheetsLayout hrTimesheetPanel;

	@Autowired
	StaffAttendanceLayout staffAttendancePanel;

	@Autowired
	CreateUserLayout createUserPanel;

	@Autowired
	ViewEmployeesLayout viewEmployeesLayout;

	@Autowired
	EditEmployeeLayout editEmployeePanel;

	@Autowired
	ViewDepartmentsLayout viewDepartmentPanel;

	@Autowired
	CreateDepartmentLayout createDepartmentPanel;

	@Autowired
	EditDepartmentLayout editDepartmentPanel;

	@Autowired
	StaffTimesheetLayout staffTimesheetPanel;
	
	@Autowired
	StaffLeaveManagementLayout staffLeaveManagementPanel;
	
	@Autowired
	HRLeaveManagementLayout hrLeaveManagementPanel;
	
	@Autowired
	StaffTerminationForm staffTerminationPanel;
	
	@Autowired
	HRTerminationForm hrTerminationPanel;
	
	@Autowired
	StaffAppresialLayout staffAppresialPanel;
	
	@Autowired
	ManagerAppresialLayout managerAppresialPanel;
	
	@Autowired
	HRAppresialsLayout hrAppresialsPanel;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	HorizontalLayout headerLayout = new HorizontalLayout();

	@PostConstruct
	void init() {
		this.setMargin(false);
		this.setSizeFull();
		this.addComponent(this.loginPanel);
		this.loginPanel.signin.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String username = FrontEndView.this.loginPanel.username.getValue();
				String password = FrontEndView.this.loginPanel.password.getValue();
				if (!FrontEndView.this.employeeService.verifyLogin(username, password)) {
					Notification.show("Incorrect Credentials", Notification.Type.WARNING_MESSAGE);
					FrontEndView.this.loginPanel.username.clear();
					FrontEndView.this.loginPanel.password.clear();
				} else {
					FrontEndView.this.setMainContent();
				}
			}
		});

	}

	void setMainContent() {
		this.menu.setData();
		this.removeComponent(this.loginPanel);
		this.addComponent(this.menu);
		this.addComponent(this.homePanel);
		this.homePanel.setData();
		this.setExpandRatio(this.homePanel, 3);

		this.menu.employeeTimesheetButton.addClickListener(this.employeeTimeSheetListener);
		this.menu.employeeLeaveRequestButton.addClickListener(this.managerLeaveRequestsListener);
		this.menu.hrAbsentEmployeesButton.addClickListener(this.absentEmployeeRequestListener);
		this.menu.hrLeaveRequestButton.addClickListener(this.hrLeaveRequestsListener);
		this.menu.hrTimeSheetButton.addClickListener(this.hrTimesheetListener);
		this.menu.timesheetButton.addClickListener(this.staffTimesheetListener);
		this.menu.adminCreateUser.addClickListener(this.createUserListener);
		this.menu.adminViewEmployees.addClickListener(this.adminViewEmployeesListener);
		this.menu.adminEditEmployee.addClickListener(this.editEmployeeListener);
		this.menu.adminViewDepartments.addClickListener(this.viewDepartmentListener);
		this.menu.adminCreateDepartment.addClickListener(this.createDepartmentListener);
		this.menu.adminEditDepartment.addClickListener(this.editDepartmentListener);
		this.menu.viewTimesheetButton.addClickListener(this.staffViewTimesheetListener);
		this.menu.leaveManagementButton.addClickListener(this.staffLeaveManagementListener);
		this.menu.hrLeaveRequestFormsButton.addClickListener(this.hrLeaveManagementListener);
		this.menu.staffDisengagementFormButton.addClickListener(this.staffDisengagementListener);
		this.menu.hrDisengagementFormButton.addClickListener(this.hrDisengagementListener);
		this.menu.staffAppraisaltFormButton.addClickListener(this.staffAppraisalListener);
		this.menu.managerAppraisaltFormButton.addClickListener(this.managerAppresialListener);
		this.menu.hrAppraisaltFormButton.addClickListener(this.hrAppresialListener);


		this.menu.logout.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				VaadinSession.getCurrent().close();
				FrontEndView.this.getUI().getSession().close();

				// Invalidate underlying session instead if login info is stored
				// there
				// VaadinService.getCurrentRequest().getWrappedSession().invalidate();

				// Redirect to avoid keeping the removed UI open in the browser
				FrontEndView.this.getUI().getPage().setLocation("");
			}
		});

	}
	
	
	ClickListener hrAppresialListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);



			FrontEndView.this.addComponent(FrontEndView.this.hrAppresialsPanel);
			FrontEndView.this.hrAppresialsPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.hrAppresialsPanel, 3);
			
		}

	};
	
	
	
	ClickListener managerAppresialListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);



			FrontEndView.this.addComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.managerAppresialPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.managerAppresialPanel, 3);
			
		}

	};
	
	
	
	ClickListener staffAppraisalListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			System.out.println("here");			
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.staffAppresialPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.staffAppresialPanel, 3);
			
		}

	};
	
	
	ClickListener hrDisengagementListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.hrTerminationPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.hrTerminationPanel, 3);
			
		}

	};
	
	ClickListener staffDisengagementListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);

			FrontEndView.this.addComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.staffTerminationPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.staffTerminationPanel, 3);
			
		}

	};
	
	ClickListener hrLeaveManagementListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.hrLeaveManagementPanel);
			
			FrontEndView.this.hrLeaveManagementPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.hrLeaveManagementPanel, 3);
		}

	};


	ClickListener staffLeaveManagementListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.staffLeaveManagementPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.staffLeaveManagementPanel, 3);
		}

	};

	
	ClickListener staffViewTimesheetListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.staffTimesheetPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.staffTimesheetPanel, 3);
		}

	};

	ClickListener editDepartmentListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.editDepartmentPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.editDepartmentPanel, 3);
		}

	};

	ClickListener createDepartmentListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.createDepartmentPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.createDepartmentPanel, 3);
		}

	};

	ClickListener viewDepartmentListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.viewDepartmentPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.viewDepartmentPanel, 3);
		}

	};

	ClickListener editEmployeeListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.editEmployeePanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.editEmployeePanel, 3);
		}

	};

	ClickListener adminViewEmployeesListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.viewEmployeesLayout.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.viewEmployeesLayout, 3);
		}

	};

	ClickListener createUserListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.createUserPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.createUserPanel, 3);
		}

	};

	ClickListener staffTimesheetListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);

			FrontEndView.this.addComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.staffAttendancePanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.staffAttendancePanel, 3);
		}

	};

	ClickListener hrTimesheetListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.hrTimesheetPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.hrTimesheetPanel, 3);
		}

	};

	ClickListener hrLeaveRequestsListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);

			FrontEndView.this.addComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.hrLeaveRequestsPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.hrLeaveRequestsPanel, 3);
		}

	};

	ClickListener employeeTimeSheetListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);

			FrontEndView.this.addComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.managertimesheetPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.managertimesheetPanel, 3);
		}

	};

	ClickListener managerLeaveRequestsListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.absentEmployeesPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.managerLeavePanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.managerLeavePanel, 3);
		}

	};

	ClickListener absentEmployeeRequestListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			FrontEndView.this.removeComponent(FrontEndView.this.homePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managertimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerLeavePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveRequestsPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAttendancePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createUserPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewEmployeesLayout);
			FrontEndView.this.removeComponent(FrontEndView.this.editEmployeePanel);
			FrontEndView.this.removeComponent(FrontEndView.this.viewDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.createDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.editDepartmentPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffTimesheetPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrLeaveManagementPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrTerminationPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.staffAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.managerAppresialPanel);
			FrontEndView.this.removeComponent(FrontEndView.this.hrAppresialsPanel);

			FrontEndView.this.addComponent(FrontEndView.this.absentEmployeesPanel);

			FrontEndView.this.absentEmployeesPanel.setData();
			FrontEndView.this.setExpandRatio(FrontEndView.this.absentEmployeesPanel, 3);
		}

	};

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.print("enter");
	}

}
