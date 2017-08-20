package com.app.attendance.components;

import com.app.attendance.ViewType;
import com.app.attendance.model.Employee;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
@SpringComponent
@UIScope
public final class Menu extends CustomComponent {

	private static final long serialVersionUID = 1L;
	public static final String ID = "dashboard-menu";
	private static final String STYLE_VISIBLE = "valo-menu-visible";
	public ValoMenuItemButton viewTimesheetButton;
	public ValoMenuItemButton timesheetButton;
	public ValoMenuItemButton leaveManagementButton;

	public ValoMenuItemButton employeeTimesheetButton;
	public ValoMenuItemButton employeeLeaveRequestButton;

	public ValoMenuItemButton hrTimeSheetButton;
	public ValoMenuItemButton hrLeaveRequestButton;
	public ValoMenuItemButton hrLeaveRequestFormsButton;
	public ValoMenuItemButton hrAbsentEmployeesButton;

	public ValoMenuItemButton adminCreateUser;
	public ValoMenuItemButton adminViewEmployees;
	public ValoMenuItemButton adminEditEmployee;
	public ValoMenuItemButton adminViewDepartments;
	public ValoMenuItemButton adminCreateDepartment;
	public ValoMenuItemButton adminEditDepartment;
	
	public ValoMenuItemButton staffDisengagementFormButton;
	public ValoMenuItemButton hrDisengagementFormButton;
	
	public ValoMenuItemButton staffAppraisaltFormButton;
	public ValoMenuItemButton managerAppraisaltFormButton;
	public ValoMenuItemButton hrAppraisaltFormButton;

	public ValoMenuItemButton staffPerformanceReviewStaffButton;
	public ValoMenuItemButton staffPerformanceReviewManagerButton;
	public ValoMenuItemButton staffPerformanceReviewHRButton;

	public ValoMenuItemButton staffselfServiceButton;
	public ValoMenuItemButton hrSelfServiceButton;


    private MenuItem settingsItem;

	public ValoMenuItemButton logout;

	private static final String STYLE_SELECTED = "selected";
	final CssLayout menuItemsLayout = new CssLayout();
	final CssLayout menuContent = new CssLayout();

	public Menu() {

		Responsive.makeResponsive(this);
		this.addStyleName("valo-menu");
		this.setId(Menu.ID);
		this.setSizeUndefined();
		this.setCompositionRoot(this.buildContent());
	}
	
	  private Component buildTitle() {

			VerticalLayout layoutTitle = new VerticalLayout();
			layoutTitle.setSpacing(true);

		  Label labelTitle = new Label("MENU");
			labelTitle.setStyleName(ValoTheme.LABEL_BOLD);
			labelTitle.addStyleName(ValoTheme.LABEL_COLORED);
			labelTitle.addStyleName(ValoTheme.LABEL_H3);
			labelTitle.setSizeUndefined();
			layoutTitle.addComponent(labelTitle);
			layoutTitle.setComponentAlignment(labelTitle, Alignment.MIDDLE_CENTER);


			return layoutTitle;
	        
	    }

	private Component buildContent() {

		this.menuContent.addStyleName("sidebar");
		this.menuContent.addStyleName(ValoTheme.MENU_PART);
		this.menuContent.addStyleName("no-vertical-drag-hints");
		this.menuContent.addStyleName("no-horizontal-drag-hints");
		this.menuContent.setWidth(null);
		this.menuContent.setHeight("100%");
        menuContent.addComponent(buildTitle());
		this.menuContent.addComponent(buildUserMenu());
		this.menuContent.addComponent(this.buildToggleButton());

		this.buildMenuItems();
		this.menuContent.addComponent(this.menuItemsLayout);

		Responsive.makeResponsive(this.menuContent);

		return this.menuContent;
	}

	
    private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
            
        return settings;
    }
	public void setData() {
		Employee employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		String lastname = "";
		if (employee.getLastname() != null) {
			lastname = employee.getLastname();
		}
		String displayName = employee.getFirstname() + " " + lastname;
		settingsItem.setText(displayName.toUpperCase());  
		String role = employee.getRole().trim().toUpperCase();
		switch (role) {
		case "STAFF":
			this.menuItemsLayout.addComponents(this.timesheetButton, this.leaveManagementButton,
					this.viewTimesheetButton, this.staffDisengagementFormButton, this.staffAppraisaltFormButton, 
					this.staffPerformanceReviewStaffButton, this.staffselfServiceButton);

			break;

		case "MANAGER":
			this.menuItemsLayout.addComponents(this.timesheetButton, this.viewTimesheetButton, 
					this.employeeTimesheetButton, this.employeeLeaveRequestButton, this.managerAppraisaltFormButton, this.staffPerformanceReviewManagerButton);

			break;
		case "HR":
			this.menuItemsLayout.addComponents(this.hrTimeSheetButton, this.hrLeaveRequestButton,
					this.hrLeaveRequestFormsButton, this.hrAbsentEmployeesButton, 
					this.hrDisengagementFormButton, this.hrAppraisaltFormButton, 
					this.staffPerformanceReviewHRButton, this.hrSelfServiceButton);

			break;

		case "ADMIN":
			this.menuItemsLayout.addComponents(this.adminCreateUser, this.adminViewEmployees, this.adminEditEmployee,
					this.adminViewDepartments, this.adminCreateDepartment, this.adminEditDepartment);

			break;

		default:
			break;
		}

		this.menuItemsLayout.addComponent(this.logout);

	}

	private Component buildToggleButton() {
		Button valoMenuToggleButton = new Button("", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("synthetic-access")
			@Override
			public void buttonClick(final ClickEvent event) {
				if (Menu.this.getCompositionRoot().getStyleName().contains(Menu.STYLE_VISIBLE)) {
					Menu.this.getCompositionRoot().removeStyleName(Menu.STYLE_VISIBLE);
				} else {
					Menu.this.getCompositionRoot().addStyleName(Menu.STYLE_VISIBLE);
				}
			}
		});
		valoMenuToggleButton.setIcon(FontAwesome.LIST);
		valoMenuToggleButton.addStyleName("valo-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		return valoMenuToggleButton;
	}

	private void buildMenuItems() {
		this.menuItemsLayout.addStyleName("valo-menuitems");
		this.menuItemsLayout.setHeight(100.0f, Unit.PERCENTAGE);
		this.menuItemsLayout.setWidthUndefined();

		
		this.viewTimesheetButton = new ValoMenuItemButton(ViewType.VIEW_TIMESHEET);
		this.viewTimesheetButton.addClickListener(this.buttonClickListener);

		this.timesheetButton = new ValoMenuItemButton(ViewType.TIMESHEET);
		this.timesheetButton.addClickListener(this.buttonClickListener);

		this.leaveManagementButton = new ValoMenuItemButton(ViewType.LEAVE_MANAGEMENT);
		this.leaveManagementButton.addClickListener(this.buttonClickListener);

		this.employeeTimesheetButton = new ValoMenuItemButton(ViewType.MANAGER_TIMESHEET);
		this.employeeTimesheetButton.addClickListener(this.buttonClickListener);

		this.employeeLeaveRequestButton = new ValoMenuItemButton(ViewType.MANAGER_LEAVE_REQUEST);
		this.employeeLeaveRequestButton.addClickListener(this.buttonClickListener);

		this.hrAbsentEmployeesButton = new ValoMenuItemButton(ViewType.HR_ABSENT_EMPLOYEES);
		this.hrAbsentEmployeesButton.addClickListener(this.buttonClickListener);

		this.hrLeaveRequestFormsButton = new ValoMenuItemButton(ViewType.HR_LEAVE_REQUEST_FORMS);
		this.hrLeaveRequestFormsButton.addClickListener(this.buttonClickListener);

		this.hrLeaveRequestButton = new ValoMenuItemButton(ViewType.HR_LEAVE_REQUEST);
		this.hrLeaveRequestButton.addClickListener(this.buttonClickListener);

		this.hrTimeSheetButton = new ValoMenuItemButton(ViewType.HR_TIMESHEET);
		this.hrTimeSheetButton.addClickListener(this.buttonClickListener);

		this.adminCreateUser = new ValoMenuItemButton(ViewType.ADMIN_CREATE_USER);
		this.adminCreateUser.addClickListener(this.buttonClickListener);

		this.adminViewEmployees = new ValoMenuItemButton(ViewType.ADMIN_VIEW_USER);
		this.adminViewEmployees.addClickListener(this.buttonClickListener);

		this.adminEditEmployee = new ValoMenuItemButton(ViewType.ADMIN_EDIT_USER);
		this.adminEditEmployee.addClickListener(this.buttonClickListener);

		this.adminViewDepartments = new ValoMenuItemButton(ViewType.ADMIN_VIEW_DEPARTMENT);
		this.adminViewDepartments.addClickListener(this.buttonClickListener);

		this.adminCreateDepartment = new ValoMenuItemButton(ViewType.ADMIN_CREATE_DEPARTMENT);
		this.adminCreateDepartment.addClickListener(this.buttonClickListener);

		this.adminEditDepartment = new ValoMenuItemButton(ViewType.ADMIN_EDIT_DEPARTMENT);
		this.adminEditDepartment.addClickListener(this.buttonClickListener);
		
		this.hrDisengagementFormButton = new ValoMenuItemButton(ViewType.HR_DISENGAGEMENT_FORM);
		this.hrDisengagementFormButton.addClickListener(this.buttonClickListener);

		
		this.staffDisengagementFormButton = new ValoMenuItemButton(ViewType.STAFF_DISENGAGEMENT_FORM);
		this.staffDisengagementFormButton.addClickListener(this.buttonClickListener);

		this.staffAppraisaltFormButton = new ValoMenuItemButton(ViewType.STAFF_APPRAISAL);
		this.staffAppraisaltFormButton.addClickListener(this.buttonClickListener);

		this.managerAppraisaltFormButton = new ValoMenuItemButton(ViewType.MANAGER_APPRAISAL);
		this.managerAppraisaltFormButton.addClickListener(this.buttonClickListener);

		this.hrAppraisaltFormButton = new ValoMenuItemButton(ViewType.HR_APPRAISAL);
		this.hrAppraisaltFormButton.addClickListener(this.buttonClickListener);
		
		this.staffPerformanceReviewStaffButton = new ValoMenuItemButton(ViewType.STAFF_PERFORMANCE_REVIEW_STAFF);
		this.staffPerformanceReviewStaffButton.addClickListener(this.buttonClickListener);

		this.staffPerformanceReviewManagerButton = new ValoMenuItemButton(ViewType.STAFF_PERFORMANCE_REVIEW_MANAGER);
		this.staffPerformanceReviewManagerButton.addClickListener(this.buttonClickListener);

		this.staffPerformanceReviewHRButton = new ValoMenuItemButton(ViewType.STAFF_PERFORMANCE_REVIEW_HR);
		this.staffPerformanceReviewHRButton.addClickListener(this.buttonClickListener);

		this.staffselfServiceButton= new ValoMenuItemButton(ViewType.STAFF_SELF_SERVICE);
		this.staffselfServiceButton.addClickListener(this.buttonClickListener);

		this.hrSelfServiceButton= new ValoMenuItemButton(ViewType.HR_SELF_SERVICE);
		this.hrSelfServiceButton.addClickListener(this.buttonClickListener);

		
		
		this.logout = new ValoMenuItemButton(ViewType.LOGOUT);
		this.logout.addClickListener(this.buttonClickListener);

	}

	ClickListener buttonClickListener = new ClickListener() {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			Menu.this.clearMenuSelection();
			event.getButton().addStyleName(Menu.STYLE_SELECTED);

		}
	};

	public void clearMenuSelection() {

		for (Component next : this.menuContent) {
			if (next instanceof ValoMenuItemButton) {
				next.removeStyleName(Menu.STYLE_SELECTED);
			}
		}
		this.viewTimesheetButton.removeStyleName(Menu.STYLE_SELECTED);
		this.timesheetButton.removeStyleName(Menu.STYLE_SELECTED);
		this.leaveManagementButton.removeStyleName(Menu.STYLE_SELECTED);
		this.employeeLeaveRequestButton.removeStyleName(Menu.STYLE_SELECTED);
		this.employeeTimesheetButton.removeStyleName(Menu.STYLE_SELECTED);

		this.hrTimeSheetButton.removeStyleName(Menu.STYLE_SELECTED);
		this.hrLeaveRequestButton.removeStyleName(Menu.STYLE_SELECTED);
		this.hrLeaveRequestFormsButton.removeStyleName(Menu.STYLE_SELECTED);
		this.hrAbsentEmployeesButton.removeStyleName(Menu.STYLE_SELECTED);

		this.adminCreateUser.removeStyleName(Menu.STYLE_SELECTED);
		this.adminViewEmployees.removeStyleName(Menu.STYLE_SELECTED);
		this.adminEditEmployee.removeStyleName(Menu.STYLE_SELECTED);
		this.adminViewDepartments.removeStyleName(Menu.STYLE_SELECTED);
		this.adminCreateDepartment.removeStyleName(Menu.STYLE_SELECTED);
		this.adminEditDepartment.removeStyleName(Menu.STYLE_SELECTED);
		

		this.hrDisengagementFormButton.removeStyleName(Menu.STYLE_SELECTED);
		this.staffDisengagementFormButton.removeStyleName(Menu.STYLE_SELECTED);
		this.logout.removeStyleName(Menu.STYLE_SELECTED);
		
		this.staffAppraisaltFormButton.removeStyleName(Menu.STYLE_SELECTED);
		this.managerAppraisaltFormButton.removeStyleName(Menu.STYLE_SELECTED);
		this.hrAppraisaltFormButton.removeStyleName(Menu.STYLE_SELECTED);
		
		this.staffPerformanceReviewStaffButton.removeStyleName(Menu.STYLE_SELECTED);
		this.staffPerformanceReviewManagerButton.removeStyleName(Menu.STYLE_SELECTED);
		this.staffPerformanceReviewHRButton.removeStyleName(Menu.STYLE_SELECTED);
		this.staffselfServiceButton.removeStyleName(Menu.STYLE_SELECTED);
		this.hrSelfServiceButton.removeStyleName(Menu.STYLE_SELECTED);



	}

	public final class ValoMenuItemButton extends Button {

		private static final long serialVersionUID = 1L;

		public ValoMenuItemButton(final ViewType view) {
			this.setPrimaryStyleName("valo-menu-item");
			this.setIcon(view.getIcon());
			this.setCaption(view.getViewName().substring(0, 1).toUpperCase() + view.getViewName().substring(1));

		}

	}

}
