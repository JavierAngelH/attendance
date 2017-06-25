/**
 * StaffPerformanceReviewLayout.java Created: Mar 11, 2017 Copyright (c) 2013 LEB
 */

package com.app.attendance.components.staff;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.model.PerformanceReview;
import com.app.attendance.service.EmployeeService;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffPerformanceReviewLayout -
 *
 */

@SpringComponent
@UIScope
public class StaffPerformanceReviewLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfName = new TextField("NAME");
	TextField tfDesignation = new TextField("DEPARTMENT");
	TextField tfLocation = new TextField("LOCATION");
	DateField dfReviewDate = new DateField("REVIEW DATE");
	TextField tfPeriod = new TextField("REVIEW PERIOD");
	TextField tfReviewerName = new TextField("REVIEWER NAME");

	ComboBox cboxpunctualStaff = new ComboBox("IS PUNCTUAL TO RESUME WORK");
	ComboBox cboxinformsLatenessStaff = new ComboBox("INFORMS SUPERVISOR OF LATENESS OR ABSENCES");
	ComboBox cboxpersonalAssignmentsStaff = new ComboBox(
			"DOESN'T TAKE ENGAGE IN PERSONAL ASSIGNMENTS DURING WORK PERIOD");
	ComboBox cboxlistenInstructionsStaff = new ComboBox("LISTEN CAREFULLY TO INSTRUCTIONS");
	ComboBox cboxfollowsInstructionsStaff = new ComboBox("FOLLOWS GIVEN INSTRUCTIONS");
	ComboBox cboxwillingToAssistStaff = new ComboBox("WILLING TO ASSIST WITH VARIOUS TASKS WITHIN AND OUT OF THE TEAM");
	ComboBox cboxwillingToAcceptStaff = new ComboBox("WILLING TO ACCEPT SUPERVISION AND FEEDBACK");
	ComboBox cboxliquidatesFinantialStaff = new ComboBox("LIQUIDATES FINANTIAL ADVICES TIMELY AND ACURATELLY");
	ComboBox cboxfinantialRequestsTimelyStaff = new ComboBox("MAKES FINANTIAL REQUESTS TIMELY AND ACURATELLY");
	ComboBox cboxprudentFinancesStaff = new ComboBox("PRUDENTLY USES ORGANIZATIONAL FINANCES AND RESOURCES");
	ComboBox cboxinitiativeStaff = new ComboBox("TAKES INITIATIVE AND LEADERSHIP");
	ComboBox cboxresolvesChallengesStaff = new ComboBox("RESOLVES CHALLENGES INDEPENDENTLY");
	ComboBox cboxqualityProductsStaff = new ComboBox("WORK PRODUCTS ARE OF GOOD QUALITY");
	ComboBox cboxtimelyProductsStaff = new ComboBox("WORK PRODUCTS ARE COMPLETED TIMELY");
	ComboBox cboxintegrityStaff = new ComboBox("ACTS WITH INTEGRITY");
	ComboBox cboxrespectStaff = new ComboBox("TREAT PEOPLE WITH RESPECT");
	ComboBox cboxoralCommunicationStaff = new ComboBox("COMMUNICATES ORALLY EFECTIVELLY");
	ComboBox cboxwrittenCommunicationStaff = new ComboBox("COMMUNICATES WRITENLLY EFECTIVELLY");
	ComboBox cboxerrorResponsibilityStaff = new ComboBox("TAKES RESPONSIBILITY FOR MISTAKES AND ERRORS");
	ComboBox cboxcommitmentStaff = new ComboBox("DEMONSTRATES COMMITMENT TO THE ORGANIZATION");
	ComboBox cboxsupervisesStaff = new ComboBox("SUPERVISES AND COORDINATES TEAM ADEQUATELY (SUPERVISORS ONLY)");

	Employee employee;

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

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try {
				StaffPerformanceReviewLayout.this.cboxpunctualStaff.validate();
				StaffPerformanceReviewLayout.this.cboxinformsLatenessStaff.validate();
				StaffPerformanceReviewLayout.this.cboxpersonalAssignmentsStaff.validate();
				StaffPerformanceReviewLayout.this.cboxlistenInstructionsStaff.validate();
				StaffPerformanceReviewLayout.this.cboxfollowsInstructionsStaff.validate();
				StaffPerformanceReviewLayout.this.cboxwillingToAssistStaff.validate();
				StaffPerformanceReviewLayout.this.cboxwillingToAcceptStaff.validate();
				StaffPerformanceReviewLayout.this.cboxliquidatesFinantialStaff.validate();
				StaffPerformanceReviewLayout.this.cboxfinantialRequestsTimelyStaff.validate();
				StaffPerformanceReviewLayout.this.cboxprudentFinancesStaff.validate();
				StaffPerformanceReviewLayout.this.cboxinitiativeStaff.validate();
				StaffPerformanceReviewLayout.this.cboxresolvesChallengesStaff.validate();
				StaffPerformanceReviewLayout.this.cboxqualityProductsStaff.validate();
				StaffPerformanceReviewLayout.this.cboxtimelyProductsStaff.validate();
				StaffPerformanceReviewLayout.this.cboxintegrityStaff.validate();
				StaffPerformanceReviewLayout.this.cboxrespectStaff.validate();
				StaffPerformanceReviewLayout.this.cboxoralCommunicationStaff.validate();
				StaffPerformanceReviewLayout.this.cboxwrittenCommunicationStaff.validate();
				StaffPerformanceReviewLayout.this.cboxerrorResponsibilityStaff.validate();
				StaffPerformanceReviewLayout.this.cboxcommitmentStaff.validate();
				
				PerformanceReview review = new PerformanceReview();
				review.setEmployeeId(employee.getId());
				review.setReviewDate(dfReviewDate.getValue());
				review.setReviewerName(tfReviewerName.getValue());
				review.setPunctualStaff(
						Integer.parseInt(StaffPerformanceReviewLayout.this.cboxpunctualStaff.getValue().toString()));
				review.setInformsLatenessStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxinformsLatenessStaff.getValue().toString()));
				review.setPersonalAssignmentsStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxpersonalAssignmentsStaff.getValue().toString()));
				review.setListenInstructionsStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxlistenInstructionsStaff.getValue().toString()));
				review.setFollowsInstructionsStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxfollowsInstructionsStaff.getValue().toString()));
				review.setWillingToAssistStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxwillingToAssistStaff.getValue().toString()));
				review.setWillingToAcceptStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxwillingToAcceptStaff.getValue().toString()));
				review.setLiquidatesFinantialStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxliquidatesFinantialStaff.getValue().toString()));
				review.setFinantialRequestsTimelyStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxfinantialRequestsTimelyStaff.getValue().toString()));
				review.setPrudentFinancesStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxprudentFinancesStaff.getValue().toString()));
				review.setInitiativeStaff(
						Integer.parseInt(StaffPerformanceReviewLayout.this.cboxinitiativeStaff.getValue().toString()));
				review.setResolvesChallengesStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxresolvesChallengesStaff.getValue().toString()));
				review.setQualityProductsStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxqualityProductsStaff.getValue().toString()));
				review.setTimelyProductsStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxtimelyProductsStaff.getValue().toString()));
				review.setIntegrityStaff(
						Integer.parseInt(StaffPerformanceReviewLayout.this.cboxintegrityStaff.getValue().toString()));
				review.setRespectStaff(
						Integer.parseInt(StaffPerformanceReviewLayout.this.cboxrespectStaff.getValue().toString()));
				review.setOralCommunicationStaff(Integer
						.parseInt(StaffPerformanceReviewLayout.this.cboxoralCommunicationStaff.getValue().toString()));
				review.setWrittenCommunicationStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxwrittenCommunicationStaff.getValue().toString()));
				review.setErrorResponsibilityStaff(Integer.parseInt(
						StaffPerformanceReviewLayout.this.cboxerrorResponsibilityStaff.getValue().toString()));
				review.setCommitmentStaff(
						Integer.parseInt(StaffPerformanceReviewLayout.this.cboxcommitmentStaff.getValue().toString()));
				if (StaffPerformanceReviewLayout.this.cboxsupervisesStaff.getValue() != null) {
					review.setSupervisesStaff(Integer
							.parseInt(StaffPerformanceReviewLayout.this.cboxsupervisesStaff.getValue().toString()));
				}

				if (StaffPerformanceReviewLayout.this.tfPeriod.getValue() != null) {
					review.setPeriod(StaffPerformanceReviewLayout.this.tfPeriod.getValue());
				}

				employeeService.savePerformanceReview(review);
				
				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				e.printStackTrace();
				String error = e.getMessage();

				Notification.show("You must fill all the required fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("STAFF PERFORMANCE REVIEW");

		this.tfName.setReadOnly(false);
		this.tfName
				.setValue(this.employee.getFirstname().toUpperCase() + " " + this.employee.getLastname().toUpperCase());
		this.tfName.setReadOnly(true);

		this.tfDesignation.setReadOnly(false);
		this.tfDesignation.setValue(this.employee.getDepartment().toUpperCase());
		this.tfDesignation.setReadOnly(true);

		this.tfLocation.setReadOnly(false);
		this.tfLocation.setValue(this.employee.getLocation().toUpperCase());
		this.tfLocation.setReadOnly(true);

		this.tfReviewerName.setReadOnly(false);
		this.tfReviewerName.setValue(this.employee.getManager().toUpperCase());
		this.tfReviewerName.setReadOnly(true);

		this.dfReviewDate.setValue(new Date());

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		this.cboxpunctualStaff.setInvalidAllowed(false);
		this.cboxinformsLatenessStaff.setInvalidAllowed(false);
		this.cboxpersonalAssignmentsStaff.setInvalidAllowed(false);
		this.cboxlistenInstructionsStaff.setInvalidAllowed(false);
		this.cboxfollowsInstructionsStaff.setInvalidAllowed(false);
		this.cboxwillingToAssistStaff.setInvalidAllowed(false);
		this.cboxwillingToAcceptStaff.setInvalidAllowed(false);
		this.cboxliquidatesFinantialStaff.setInvalidAllowed(false);
		this.cboxfinantialRequestsTimelyStaff.setInvalidAllowed(false);
		this.cboxprudentFinancesStaff.setInvalidAllowed(false);
		this.cboxinitiativeStaff.setInvalidAllowed(false);
		this.cboxresolvesChallengesStaff.setInvalidAllowed(false);
		this.cboxqualityProductsStaff.setInvalidAllowed(false);
		this.cboxtimelyProductsStaff.setInvalidAllowed(false);
		this.cboxintegrityStaff.setInvalidAllowed(false);
		this.cboxrespectStaff.setInvalidAllowed(false);
		this.cboxoralCommunicationStaff.setInvalidAllowed(false);
		this.cboxwrittenCommunicationStaff.setInvalidAllowed(false);
		this.cboxerrorResponsibilityStaff.setInvalidAllowed(false);
		this.cboxcommitmentStaff.setInvalidAllowed(false);
		this.cboxsupervisesStaff.setInvalidAllowed(false);

		this.cboxpunctualStaff.addItem("0");
		this.cboxinformsLatenessStaff.addItem("0");
		this.cboxpersonalAssignmentsStaff.addItem("0");
		this.cboxlistenInstructionsStaff.addItem("0");
		this.cboxfollowsInstructionsStaff.addItem("0");
		this.cboxwillingToAssistStaff.addItem("0");
		this.cboxwillingToAcceptStaff.addItem("0");
		this.cboxliquidatesFinantialStaff.addItem("0");
		this.cboxfinantialRequestsTimelyStaff.addItem("0");
		this.cboxprudentFinancesStaff.addItem("0");
		this.cboxinitiativeStaff.addItem("0");
		this.cboxresolvesChallengesStaff.addItem("0");
		this.cboxqualityProductsStaff.addItem("0");
		this.cboxtimelyProductsStaff.addItem("0");
		this.cboxintegrityStaff.addItem("0");
		this.cboxrespectStaff.addItem("0");
		this.cboxoralCommunicationStaff.addItem("0");
		this.cboxwrittenCommunicationStaff.addItem("0");
		this.cboxerrorResponsibilityStaff.addItem("0");
		this.cboxcommitmentStaff.addItem("0");
		this.cboxsupervisesStaff.addItem("0");

		this.cboxpunctualStaff.addItem("1");
		this.cboxinformsLatenessStaff.addItem("1");
		this.cboxpersonalAssignmentsStaff.addItem("1");
		this.cboxlistenInstructionsStaff.addItem("1");
		this.cboxfollowsInstructionsStaff.addItem("1");
		this.cboxwillingToAssistStaff.addItem("1");
		this.cboxwillingToAcceptStaff.addItem("1");
		this.cboxliquidatesFinantialStaff.addItem("1");
		this.cboxfinantialRequestsTimelyStaff.addItem("1");
		this.cboxprudentFinancesStaff.addItem("1");
		this.cboxinitiativeStaff.addItem("1");
		this.cboxresolvesChallengesStaff.addItem("1");
		this.cboxqualityProductsStaff.addItem("1");
		this.cboxtimelyProductsStaff.addItem("1");
		this.cboxintegrityStaff.addItem("1");
		this.cboxrespectStaff.addItem("1");
		this.cboxoralCommunicationStaff.addItem("1");
		this.cboxwrittenCommunicationStaff.addItem("1");
		this.cboxerrorResponsibilityStaff.addItem("1");
		this.cboxcommitmentStaff.addItem("1");
		this.cboxsupervisesStaff.addItem("1");

		this.cboxpunctualStaff.addItem("2");
		this.cboxinformsLatenessStaff.addItem("2");
		this.cboxpersonalAssignmentsStaff.addItem("2");
		this.cboxlistenInstructionsStaff.addItem("2");
		this.cboxfollowsInstructionsStaff.addItem("2");
		this.cboxwillingToAssistStaff.addItem("2");
		this.cboxwillingToAcceptStaff.addItem("2");
		this.cboxliquidatesFinantialStaff.addItem("2");
		this.cboxfinantialRequestsTimelyStaff.addItem("2");
		this.cboxprudentFinancesStaff.addItem("2");
		this.cboxinitiativeStaff.addItem("2");
		this.cboxresolvesChallengesStaff.addItem("2");
		this.cboxqualityProductsStaff.addItem("2");
		this.cboxtimelyProductsStaff.addItem("2");
		this.cboxintegrityStaff.addItem("2");
		this.cboxrespectStaff.addItem("2");
		this.cboxoralCommunicationStaff.addItem("2");
		this.cboxwrittenCommunicationStaff.addItem("2");
		this.cboxerrorResponsibilityStaff.addItem("2");
		this.cboxcommitmentStaff.addItem("2");
		this.cboxsupervisesStaff.addItem("2");

		this.cboxpunctualStaff.addItem("3");
		this.cboxinformsLatenessStaff.addItem("3");
		this.cboxpersonalAssignmentsStaff.addItem("3");
		this.cboxlistenInstructionsStaff.addItem("3");
		this.cboxfollowsInstructionsStaff.addItem("3");
		this.cboxwillingToAssistStaff.addItem("3");
		this.cboxwillingToAcceptStaff.addItem("3");
		this.cboxliquidatesFinantialStaff.addItem("3");
		this.cboxfinantialRequestsTimelyStaff.addItem("3");
		this.cboxprudentFinancesStaff.addItem("3");
		this.cboxinitiativeStaff.addItem("3");
		this.cboxresolvesChallengesStaff.addItem("3");
		this.cboxqualityProductsStaff.addItem("3");
		this.cboxtimelyProductsStaff.addItem("3");
		this.cboxintegrityStaff.addItem("3");
		this.cboxrespectStaff.addItem("3");
		this.cboxoralCommunicationStaff.addItem("3");
		this.cboxwrittenCommunicationStaff.addItem("3");
		this.cboxerrorResponsibilityStaff.addItem("3");
		this.cboxcommitmentStaff.addItem("3");
		this.cboxsupervisesStaff.addItem("3");

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfReviewerName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfPeriod.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.dfReviewDate.addStyleName(ValoTheme.DATEFIELD_TINY);

		this.cboxpunctualStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxinformsLatenessStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxpersonalAssignmentsStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxlistenInstructionsStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxfollowsInstructionsStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxwillingToAssistStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxwillingToAcceptStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxliquidatesFinantialStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxfinantialRequestsTimelyStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxprudentFinancesStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxinitiativeStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxresolvesChallengesStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxqualityProductsStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxtimelyProductsStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxintegrityStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxrespectStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxoralCommunicationStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxwrittenCommunicationStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxerrorResponsibilityStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxcommitmentStaff.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxsupervisesStaff.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cboxpunctualStaff.setRequired(true);
		this.cboxinformsLatenessStaff.setRequired(true);
		this.cboxpersonalAssignmentsStaff.setRequired(true);
		this.cboxlistenInstructionsStaff.setRequired(true);
		this.cboxfollowsInstructionsStaff.setRequired(true);
		this.cboxwillingToAssistStaff.setRequired(true);
		this.cboxwillingToAcceptStaff.setRequired(true);
		this.cboxliquidatesFinantialStaff.setRequired(true);
		this.cboxfinantialRequestsTimelyStaff.setRequired(true);
		this.cboxprudentFinancesStaff.setRequired(true);
		this.cboxinitiativeStaff.setRequired(true);
		this.cboxresolvesChallengesStaff.setRequired(true);
		this.cboxqualityProductsStaff.setRequired(true);
		this.cboxtimelyProductsStaff.setRequired(true);
		this.cboxintegrityStaff.setRequired(true);
		this.cboxrespectStaff.setRequired(true);
		this.cboxoralCommunicationStaff.setRequired(true);
		this.cboxwrittenCommunicationStaff.setRequired(true);
		this.cboxerrorResponsibilityStaff.setRequired(true);
		this.cboxcommitmentStaff.setRequired(true);
		this.cboxsupervisesStaff.setRequired(false);

		this.tfName.setWidth("300px");
		this.tfDesignation.setWidth("300px");
		this.tfLocation.setWidth("300px");
		this.dfReviewDate.setWidth("300px");
		this.tfPeriod.setWidth("300px");
		this.tfReviewerName.setWidth("300px");

		this.cboxpunctualStaff.setWidth("300px");
		this.cboxinformsLatenessStaff.setWidth("300px");
		this.cboxpersonalAssignmentsStaff.setWidth("300px");
		this.cboxlistenInstructionsStaff.setWidth("300px");
		this.cboxfollowsInstructionsStaff.setWidth("300px");
		this.cboxwillingToAssistStaff.setWidth("300px");
		this.cboxwillingToAcceptStaff.setWidth("300px");
		this.cboxliquidatesFinantialStaff.setWidth("300px");
		this.cboxfinantialRequestsTimelyStaff.setWidth("300px");
		this.cboxprudentFinancesStaff.setWidth("300px");
		this.cboxinitiativeStaff.setWidth("300px");
		this.cboxresolvesChallengesStaff.setWidth("300px");
		this.cboxqualityProductsStaff.setWidth("300px");
		this.cboxtimelyProductsStaff.setWidth("300px");
		this.cboxintegrityStaff.setWidth("300px");
		this.cboxrespectStaff.setWidth("300px");
		this.cboxoralCommunicationStaff.setWidth("300px");
		this.cboxwrittenCommunicationStaff.setWidth("300px");
		this.cboxerrorResponsibilityStaff.setWidth("300px");
		this.cboxcommitmentStaff.setWidth("300px");
		this.cboxsupervisesStaff.setWidth("300px");

		FormLayout form1 = new FormLayout(this.tfName, this.tfDesignation, this.tfLocation, this.dfReviewDate,
				this.tfPeriod, this.tfReviewerName,

				this.cboxpunctualStaff, this.cboxinformsLatenessStaff, this.cboxpersonalAssignmentsStaff,
				this.cboxlistenInstructionsStaff, this.cboxfollowsInstructionsStaff, this.cboxwillingToAssistStaff,
				this.cboxwillingToAcceptStaff, this.cboxliquidatesFinantialStaff, this.cboxfinantialRequestsTimelyStaff,
				this.cboxprudentFinancesStaff, this.cboxinitiativeStaff, this.cboxresolvesChallengesStaff,
				this.cboxqualityProductsStaff, this.cboxtimelyProductsStaff, this.cboxintegrityStaff,
				this.cboxrespectStaff, this.cboxoralCommunicationStaff, this.cboxwrittenCommunicationStaff,
				this.cboxerrorResponsibilityStaff, this.cboxcommitmentStaff, this.cboxsupervisesStaff);

		form1.setWidth("80%");

		form1.setSpacing(true);

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(this.sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1);
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
