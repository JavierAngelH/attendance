/**
 * Created by JavierAngelH
 */

package com.app.attendance.component.hr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Orientation;
import com.app.attendance.service.EmployeeService;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HROrientationLayout -
 *
 */
@SpringComponent
@UIScope
public class HROrientationLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	TextField tfName = new TextField("NAME");
	DateField dfDate = new DateField("DATE OF ORIENTATION");
	TextField tfwork = new TextField("WORK BUDDY ASIGNED TO ME");
	TextField tfJobContract = new TextField("JOB CONTRACT HAS BEEN REVIEWED WITH ME");
	TextField tfPolicy = new TextField("I HAVE BEEN ORIENTED ON THE HR POLICY");
	TextField tfYouthProtection = new TextField("I HAVE BEEN ORIENTED ON THE CHILD PROTECTION POLICY");
	TextField tfAdminPolicy = new TextField("I HAVE BEEN ORIENTED ON THE ADMIN POLICY");
	TextField tfCopyAdminPolicy = new TextField("I HAVE A COPY OF THE ADMIN POLICY");
	TextField tfFinancePolicy = new TextField("I HAVE BEEN ORIENTED ON THE FINANACE POLICY");
	TextField tfFinanceTools = new TextField("I HAVE COPIES OF THE FINANCE TOOLS");
	TextField tfIdCard = new TextField("I HAVE BEEN GIVEN AN ID CARD");
	TextField tfSalaryAccount = new TextField("MY SALARY ACCOUNT HAS BEEN OPENED");
	TextField tfOfficeTour = new TextField("I HAVE BEEN ON AN OFFICE TOUR AND ALLOCATED WORKING SPACE");
	TextField tfIntroducedSupervisor = new TextField("I HAVE BEEN INTRODUCED TO MY SUPERVISOR");
	TextField tfIntroducedTeamMembers = new TextField("I HAVE BEEN INTRODUCED TO OTHER TEAM MEMBERS");
	TextField tfMeeting = new TextField("I HAVE HAD AN EXPECTATION MEETING WITH MY SUPERVISOR/TEAM MEMEBER");

	@Autowired
	EmployeeService employeeService;

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

	public void setData() {
		this.titleLabel.setValue("ORIENTATION CHECKLIST SECTION");

		this.tfName.clear();
		this.dfDate.clear();
		this.tfwork.clear();
		this.tfJobContract.clear();
		this.tfPolicy.clear();
		this.tfYouthProtection.clear();
		this.tfAdminPolicy.clear();
		this.tfCopyAdminPolicy.clear();
		this.tfFinancePolicy.clear();
		this.tfFinanceTools.clear();
		this.tfIdCard.clear();
		this.tfSalaryAccount.clear();
		this.tfOfficeTour.clear();
		this.tfIntroducedSupervisor.clear();
		this.tfIntroducedTeamMembers.clear();
		this.tfMeeting.clear();
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		this.tfName.setNullRepresentation("");
		this.tfwork.setNullRepresentation("");
		this.tfJobContract.setNullRepresentation("");
		this.tfPolicy.setNullRepresentation("");
		this.tfYouthProtection.setNullRepresentation("");
		this.tfAdminPolicy.setNullRepresentation("");
		this.tfCopyAdminPolicy.setNullRepresentation("");
		this.tfFinancePolicy.setNullRepresentation("");
		this.tfFinanceTools.setNullRepresentation("");
		this.tfIdCard.setNullRepresentation("");
		this.tfSalaryAccount.setNullRepresentation("");
		this.tfOfficeTour.setNullRepresentation("");
		this.tfIntroducedSupervisor.setNullRepresentation("");
		this.tfIntroducedTeamMembers.setNullRepresentation("");
		this.tfMeeting.setNullRepresentation("");
		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfName.setWidth("300px");
		this.tfwork.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfwork.setWidth("300px");
		this.tfJobContract.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfJobContract.setWidth("300px");
		this.tfPolicy.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfPolicy.setWidth("300px");
		this.tfYouthProtection.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfYouthProtection.setWidth("300px");
		this.tfAdminPolicy.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfAdminPolicy.setWidth("300px");
		this.tfCopyAdminPolicy.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfCopyAdminPolicy.setWidth("300px");
		this.tfFinancePolicy.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfFinancePolicy.setWidth("300px");
		this.tfFinanceTools.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfFinanceTools.setWidth("300px");
		this.tfIdCard.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfIdCard.setWidth("300px");
		this.tfSalaryAccount.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSalaryAccount.setWidth("300px");
		this.tfOfficeTour.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfOfficeTour.setWidth("300px");
		this.tfIntroducedSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfIntroducedSupervisor.setWidth("300px");
		this.tfIntroducedTeamMembers.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfIntroducedTeamMembers.setWidth("300px");
		this.tfMeeting.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfMeeting.setWidth("300px");
		this.dfDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfDate.setWidth("300px");


		FormLayout form1 = new FormLayout(this.tfName, this.dfDate, this.tfwork, this.tfJobContract, this.tfPolicy,
				this.tfYouthProtection, this.tfAdminPolicy, this.tfCopyAdminPolicy, this.tfFinancePolicy,
				this.tfFinanceTools, this.tfIdCard, this.tfSalaryAccount, this.tfOfficeTour,
				this.tfIntroducedSupervisor, this.tfIntroducedTeamMembers, this.tfMeeting);

		form1.setSpacing(true);

		layoutMain.addComponents(form1);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);

		btnSave.addClickListener(this.sendInfoListener);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		content.addComponent(layoutMain);
		content.addComponent(layoutButton);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);

		content.setExpandRatio(layoutMain, 3);

		return content;

	}

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try {
				Orientation orientation = new Orientation();
				orientation.setAdminPolicy(HROrientationLayout.this.tfAdminPolicy.getValue());
				orientation.setCopyAdminPolicy(HROrientationLayout.this.tfCopyAdminPolicy.getValue());
				orientation.setFinancePolicy(HROrientationLayout.this.tfFinancePolicy.getValue());
				orientation.setFinanceTools(HROrientationLayout.this.tfFinanceTools.getValue());
				orientation.setIdCard(HROrientationLayout.this.tfIdCard.getValue());
				orientation.setIntroducedSupervisor(HROrientationLayout.this.tfIntroducedSupervisor.getValue());
				orientation.setJobContract(HROrientationLayout.this.tfJobContract.getValue());
				orientation.setMeetingSupervisor(HROrientationLayout.this.tfMeeting.getValue());
				orientation.setName(HROrientationLayout.this.tfName.getValue());
				orientation.setOfficeTour(HROrientationLayout.this.tfOfficeTour.getValue());
				orientation.setOrientationDate(HROrientationLayout.this.dfDate.getValue());
				orientation.setPolicy(HROrientationLayout.this.tfPolicy.getValue());
				orientation.setSalaryAccount(HROrientationLayout.this.tfSalaryAccount.getValue());
				orientation.setTeamMembers(HROrientationLayout.this.tfIntroducedTeamMembers.getValue());
				orientation.setWorkBuddy(HROrientationLayout.this.tfwork.getValue());
				orientation.setYouthProtection(HROrientationLayout.this.tfYouthProtection.getValue());
				HROrientationLayout.this.employeeService.saveOrientation(orientation);

				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				String error = e.getMessage();
				System.out.println(error);
				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

}