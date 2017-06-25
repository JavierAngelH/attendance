/** 
 * HRPerformanceReview.java Created: Mar 13, 2017 Copyright (c) 2013 LEB
 */

package com.app.attendance.component.hr;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.model.PerformanceReview;
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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRPerformanceReview - 
 *
 */
@SpringComponent
@UIScope
public class HRPerformanceReview  extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;
	
	
	PerformanceReview selectedPerformance;

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");


	TextField tfName = new TextField("NAME");
	TextField tfDesignation = new TextField("DEPARTMENT");
	TextField tfLocation = new TextField("LOCATION");
	DateField dfReviewDate = new DateField("REVIEW DATE");
	TextField tfPeriod = new TextField("REVIEW PERIOD");
	TextField tfReviewerName = new TextField("REVIEWER NAME");

	TextField cboxpunctualStaff = new TextField("IS PUNCTUAL TO RESUME WORK");
	TextField cboxinformsLatenessStaff = new TextField("INFORMS SUPERVISOR OF LATENESS OR ABSENCES");
	TextField cboxpersonalAssignmentsStaff = new TextField(
			"DOESN'T TAKE ENGAGE IN PERSONAL ASSIGNMENTS DURING WORK PERIOD");
	TextField cboxlistenInstructionsStaff = new TextField("LISTEN CAREFULLY TO INSTRUCTIONS");
	TextField cboxfollowsInstructionsStaff = new TextField("FOLLOWS GIVEN INSTRUCTIONS");
	TextField cboxwillingToAssistStaff = new TextField("WILLING TO ASSIST WITH VARIOUS TASKS WITHIN AND OUT OF THE TEAM");
	TextField cboxwillingToAcceptStaff = new TextField("WILLING TO ACCEPT SUPERVISION AND FEEDBACK");
	TextField cboxliquidatesFinantialStaff = new TextField("LIQUIDATES FINANTIAL ADVICES TIMELY AND ACURATELLY");
	TextField cboxfinantialRequestsTimelyStaff = new TextField("MAKES FINANTIAL REQUESTS TIMELY AND ACURATELLY");
	TextField cboxprudentFinancesStaff = new TextField("PRUDENTLY USES ORGANIZATIONAL FINANCES AND RESOURCES");
	TextField cboxinitiativeStaff = new TextField("TAKES INITIATIVE AND LEADERSHIP");
	TextField cboxresolvesChallengesStaff = new TextField("RESOLVES CHALLENGES INDEPENDENTLY");
	TextField cboxqualityProductsStaff = new TextField("WORK PRODUCTS ARE OF GOOD QUALITY");
	TextField cboxtimelyProductsStaff = new TextField("WORK PRODUCTS ARE COMPLETED TIMELY");
	TextField cboxintegrityStaff = new TextField("ACTS WITH INTEGRITY");
	TextField cboxrespectStaff = new TextField("TREAT PEOPLE WITH RESPECT");
	TextField cboxoralCommunicationStaff = new TextField("COMMUNICATES ORALLY EFECTIVELLY");
	TextField cboxwrittenCommunicationStaff = new TextField("COMMUNICATES WRITENLLY EFECTIVELLY");
	TextField cboxerrorResponsibilityStaff = new TextField("TAKES RESPONSIBILITY FOR MISTAKES AND ERRORS");
	TextField cboxcommitmentStaff = new TextField("DEMONSTRATES COMMITMENT TO THE ORGANIZATION");
	TextField cboxsupervisesStaff = new TextField("SUPERVISES AND COORDINATES TEAM ADEQUATELY (SUPERVISORS ONLY)");

	TextField cboxpunctualSupervisor = new TextField();
	TextField cboxinformsLatenessSupervisor = new TextField();
	TextField cboxpersonalAssignmentsSupervisor = new TextField();
	TextField cboxlistenInstructionsSupervisor = new TextField();
	TextField cboxfollowsInstructionsSupervisor = new TextField();
	TextField cboxwillingToAssistSupervisor = new TextField();
	TextField cboxwillingToAcceptSupervisor = new TextField();
	TextField cboxliquidatesFinantialSupervisor = new TextField();
	TextField cboxfinantialRequestsTimelySupervisor = new TextField();
	TextField cboxprudentFinancesSupervisor = new TextField();
	TextField cboxinitiativeSupervisor = new TextField();
	TextField cboxresolvesChallengesSupervisor = new TextField();
	TextField cboxqualityProductsSupervisor = new TextField();
	TextField cboxtimelyProductsSupervisor = new TextField();
	TextField cboxintegritySupervisor = new TextField();
	TextField cboxrespectSupervisor = new TextField();
	TextField cboxoralCommunicationSupervisor = new TextField();
	TextField cboxwrittenCommunicationSupervisor = new TextField();
	TextField cboxerrorResponsibilitySupervisor = new TextField();
	TextField cboxcommitmentSupervisor = new TextField();
	TextField cboxsupervisesSupervisor = new TextField();
	
	TextField commentpunctualSupervisor = new TextField();
	TextField commentinformsLatenessSupervisor = new TextField();
	TextField commentpersonalAssignmentsSupervisor = new TextField();
	TextField commentlistenInstructionsSupervisor = new TextField();
	TextField commentfollowsInstructionsSupervisor = new TextField();
	TextField commentwillingToAssistSupervisor = new TextField();
	TextField commentwillingToAcceptSupervisor = new TextField();
	TextField commentliquidatesFinantialSupervisor = new TextField();
	TextField commentfinantialRequestsTimelySupervisor = new TextField();
	TextField commentprudentFinancesSupervisor = new TextField();
	TextField commentinitiativeSupervisor = new TextField();
	TextField commentresolvesChallengesSupervisor = new TextField();
	TextField commentqualityProductsSupervisor = new TextField();
	TextField commenttimelyProductsSupervisor = new TextField();
	TextField commentintegritySupervisor = new TextField();
	TextField commentrespectSupervisor = new TextField();
	TextField commentoralCommunicationSupervisor = new TextField();
	TextField commentwrittenCommunicationSupervisor = new TextField();
	TextField commenterrorResponsibilitySupervisor = new TextField();
	TextField commentcommitmentSupervisor = new TextField();
	TextField commentsupervisesSupervisor = new TextField();
	
	
	TextField tfScore = new TextField("OVERALL SCORE (SUMATION OF SCORE)");
	TextField tfPercentage = new TextField("PERCENTAGE (TOTAL POSSIBLE SCORE FOR NON-SUPERVISORS 60;"
			+ "SUPERVISORS 63)");
	TextField tfRanks = new TextField("ASSESSMENT RANKS (GREEN 71% AND ABOVE; YELLOW 70%-51%; RED 50% AND BELOW)");
	TextArea taConclusions = new TextArea("REVIEWER CONCLUSIONS AND RECOMENDATIONS");

	@PostConstruct
	public void PostConstruct() {

		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setSizeFull();

		root = new VerticalLayout();

		root.setMargin(true);
		root.addStyleName("dashboard-view");
		setContent(root);
		Responsive.makeResponsive(root);
		Component header = buildHeader();
		Component content = buildContent();

		root.addComponent(header);
		root.addComponent(content);
		root.setExpandRatio(content, 3);

	}


	public void setData() {

		titleLabel.setValue("STAFF PERFORMANCE REVIEW");	
		
		this.cbEmployees.removeAllItems();

		BeanItemContainer<PerformanceReview> performanceBeanContainer = new BeanItemContainer<PerformanceReview>(PerformanceReview.class);

		List<PerformanceReview> performanceList = this.employeeService
				.getPerformanceReviewsByHR();

		performanceBeanContainer.addAll(performanceList);

		this.cbEmployees.setContainerDataSource(performanceBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("employeeName");
		
		
		tfName.clear();
		tfDesignation.clear();
		tfLocation.clear();
		dfReviewDate.clear();
		tfPeriod.clear();
		tfReviewerName.clear();
		
		tfScore.clear();
		tfPercentage.clear();
		tfRanks.clear();
		taConclusions.clear();				
		

		cboxpunctualStaff.clear();
		cboxinformsLatenessStaff.clear();
		cboxpersonalAssignmentsStaff.clear();
		cboxlistenInstructionsStaff.clear();
		cboxfollowsInstructionsStaff.clear();
		cboxwillingToAssistStaff.clear();
		cboxwillingToAcceptStaff.clear();
		cboxliquidatesFinantialStaff.clear();
		cboxfinantialRequestsTimelyStaff.clear();
		cboxprudentFinancesStaff.clear();
		cboxinitiativeStaff.clear();
		cboxresolvesChallengesStaff.clear();
		cboxqualityProductsStaff.clear();
		cboxtimelyProductsStaff.clear();
		cboxintegrityStaff.clear();
		cboxrespectStaff.clear();
		cboxoralCommunicationStaff.clear();
		cboxwrittenCommunicationStaff.clear();
		cboxerrorResponsibilityStaff.clear();
		cboxcommitmentStaff.clear();
		cboxsupervisesStaff.clear();

		 cboxpunctualSupervisor.clear();
		 cboxinformsLatenessSupervisor.clear();
		 cboxpersonalAssignmentsSupervisor.clear();
		 cboxlistenInstructionsSupervisor.clear();
		 cboxfollowsInstructionsSupervisor.clear();
		 cboxwillingToAssistSupervisor.clear();
		 cboxwillingToAcceptSupervisor.clear();
		 cboxliquidatesFinantialSupervisor.clear();
		 cboxfinantialRequestsTimelySupervisor.clear();
		 cboxprudentFinancesSupervisor.clear();
		 cboxinitiativeSupervisor.clear();
		 cboxresolvesChallengesSupervisor.clear();
		 cboxqualityProductsSupervisor.clear();
		 cboxtimelyProductsSupervisor.clear();
		 cboxintegritySupervisor.clear();
		 cboxrespectSupervisor.clear();
		 cboxoralCommunicationSupervisor.clear();
		 cboxwrittenCommunicationSupervisor.clear();
		 cboxerrorResponsibilitySupervisor.clear();
		 cboxcommitmentSupervisor.clear();
		 cboxsupervisesSupervisor.clear();
		
		commentpunctualSupervisor.clear();
		commentinformsLatenessSupervisor.clear();
		commentpersonalAssignmentsSupervisor.clear();
		commentlistenInstructionsSupervisor.clear();
		commentfollowsInstructionsSupervisor.clear();
		commentwillingToAssistSupervisor.clear();
		commentwillingToAcceptSupervisor.clear();
		commentliquidatesFinantialSupervisor.clear();
		commentfinantialRequestsTimelySupervisor.clear();
		commentprudentFinancesSupervisor.clear();
		commentinitiativeSupervisor.clear();
		commentresolvesChallengesSupervisor.clear();
		commentqualityProductsSupervisor.clear();
		commenttimelyProductsSupervisor.clear();
		commentintegritySupervisor.clear();
		commentrespectSupervisor.clear();
		commentoralCommunicationSupervisor.clear();
		commentwrittenCommunicationSupervisor.clear();
		commenterrorResponsibilitySupervisor.clear();
		commentcommitmentSupervisor.clear();
		commentsupervisesSupervisor.clear();

	}

	private Component buildContent() {
		
		
		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				selectedPerformance = (PerformanceReview) cbEmployees.getValue();

				if (selectedPerformance != null) {
					Employee selectedEmployee = employeeService.getEmployee(selectedPerformance.getEmployeeId());

					
					tfName.setReadOnly(false);
					tfName
							.setValue(selectedEmployee.getFirstname().toUpperCase() + " " + selectedEmployee.getLastname().toUpperCase());
					tfName.setReadOnly(true);

					tfDesignation.setReadOnly(false);
					tfDesignation.setValue(selectedEmployee.getDepartment().toUpperCase());
					tfDesignation.setReadOnly(true);

					tfLocation.setReadOnly(false);
					tfLocation.setValue(selectedEmployee.getLocation().toUpperCase());
					tfLocation.setReadOnly(true);

					tfReviewerName.setReadOnly(false);
					tfReviewerName.setValue(selectedEmployee.getManager().toUpperCase());
					tfReviewerName.setReadOnly(true);

					dfReviewDate.setReadOnly(false);
					dfReviewDate.setValue(selectedPerformance.getReviewDate());
					dfReviewDate.setReadOnly(true);

					if(selectedPerformance.getPeriod()!=null){
					tfPeriod.setReadOnly(false);
					tfPeriod.setValue(selectedPerformance.getPeriod());
					tfPeriod.setReadOnly(true);					
				}
					
					cboxpunctualStaff.setReadOnly(false);
					cboxinformsLatenessStaff.setReadOnly(false);
					cboxpersonalAssignmentsStaff.setReadOnly(false);
					cboxlistenInstructionsStaff.setReadOnly(false);
					cboxfollowsInstructionsStaff.setReadOnly(false);
					cboxwillingToAssistStaff.setReadOnly(false);
					cboxwillingToAcceptStaff.setReadOnly(false);
					cboxliquidatesFinantialStaff.setReadOnly(false);
					cboxfinantialRequestsTimelyStaff.setReadOnly(false);
					cboxprudentFinancesStaff.setReadOnly(false);
					cboxinitiativeStaff.setReadOnly(false);
					cboxresolvesChallengesStaff.setReadOnly(false);
					cboxqualityProductsStaff.setReadOnly(false);
					cboxtimelyProductsStaff.setReadOnly(false);
					cboxintegrityStaff.setReadOnly(false);
					cboxrespectStaff.setReadOnly(false);
					cboxoralCommunicationStaff.setReadOnly(false);
					cboxwrittenCommunicationStaff.setReadOnly(false);
					cboxerrorResponsibilityStaff.setReadOnly(false);
					cboxcommitmentStaff.setReadOnly(false);
					cboxsupervisesStaff.setReadOnly(false);

					
					cboxpunctualStaff.setValue(selectedPerformance.getPunctualStaff().toString());
					cboxinformsLatenessStaff.setValue(selectedPerformance.getInformsLatenessStaff().toString());
					cboxpersonalAssignmentsStaff.setValue(selectedPerformance.getPersonalAssignmentsStaff().toString());
					cboxlistenInstructionsStaff.setValue(selectedPerformance.getListenInstructionsStaff().toString());
					cboxfollowsInstructionsStaff.setValue(selectedPerformance.getFollowsInstructionsStaff().toString());
					cboxwillingToAssistStaff.setValue(selectedPerformance.getWillingToAssistStaff().toString());
					cboxwillingToAcceptStaff.setValue(selectedPerformance.getWillingToAcceptStaff().toString());
					cboxliquidatesFinantialStaff.setValue(selectedPerformance.getLiquidatesFinantialStaff().toString());
					cboxfinantialRequestsTimelyStaff.setValue(selectedPerformance.getFinantialRequestsTimelyStaff().toString());
					cboxprudentFinancesStaff.setValue(selectedPerformance.getPrudentFinancesStaff().toString());
					cboxinitiativeStaff.setValue(selectedPerformance.getInitiativeStaff().toString());
					cboxresolvesChallengesStaff.setValue(selectedPerformance.getResolvesChallengesStaff().toString());
					cboxqualityProductsStaff.setValue(selectedPerformance.getQualityProductsStaff().toString());
					cboxtimelyProductsStaff.setValue(selectedPerformance.getTimelyProductsStaff().toString());
					cboxintegrityStaff.setValue(selectedPerformance.getIntegrityStaff().toString());
					cboxrespectStaff.setValue(selectedPerformance.getRespectStaff().toString());
					cboxoralCommunicationStaff.setValue(selectedPerformance.getOralCommunicationStaff().toString());
					cboxwrittenCommunicationStaff.setValue(selectedPerformance.getWrittenCommunicationStaff().toString());
					cboxerrorResponsibilityStaff.setValue(selectedPerformance.getErrorResponsibilityStaff().toString());
					cboxcommitmentStaff.setValue(selectedPerformance.getCommitmentStaff().toString());
					
					if(selectedPerformance.getSupervisesStaff()!=null)					
					cboxsupervisesStaff.setValue(selectedPerformance.getQualityProductsStaff().toString());

					
					cboxpunctualStaff.setReadOnly(true);
					cboxinformsLatenessStaff.setReadOnly(true);
					cboxpersonalAssignmentsStaff.setReadOnly(true);
					cboxlistenInstructionsStaff.setReadOnly(true);
					cboxfollowsInstructionsStaff.setReadOnly(true);
					cboxwillingToAssistStaff.setReadOnly(true);
					cboxwillingToAcceptStaff.setReadOnly(true);
					cboxliquidatesFinantialStaff.setReadOnly(true);
					cboxfinantialRequestsTimelyStaff.setReadOnly(true);
					cboxprudentFinancesStaff.setReadOnly(true);
					cboxinitiativeStaff.setReadOnly(true);
					cboxresolvesChallengesStaff.setReadOnly(true);
					cboxqualityProductsStaff.setReadOnly(true);
					cboxtimelyProductsStaff.setReadOnly(true);
					cboxintegrityStaff.setReadOnly(true);
					cboxrespectStaff.setReadOnly(true);
					cboxoralCommunicationStaff.setReadOnly(true);
					cboxwrittenCommunicationStaff.setReadOnly(true);
					cboxerrorResponsibilityStaff.setReadOnly(true);
					cboxcommitmentStaff.setReadOnly(true);
					cboxsupervisesStaff.setReadOnly(true);

			
					
					cboxpunctualSupervisor.setReadOnly(false);
					cboxinformsLatenessSupervisor.setReadOnly(false);
					cboxpersonalAssignmentsSupervisor.setReadOnly(false);
					cboxlistenInstructionsSupervisor.setReadOnly(false);
					cboxfollowsInstructionsSupervisor.setReadOnly(false);
					cboxwillingToAssistSupervisor.setReadOnly(false);
					cboxwillingToAcceptSupervisor.setReadOnly(false);
					cboxliquidatesFinantialSupervisor.setReadOnly(false);
					cboxfinantialRequestsTimelySupervisor.setReadOnly(false);;
					cboxprudentFinancesSupervisor.setReadOnly(false);
					cboxinitiativeSupervisor.setReadOnly(false);
					cboxresolvesChallengesSupervisor.setReadOnly(false);
					cboxqualityProductsSupervisor.setReadOnly(false);
					cboxtimelyProductsSupervisor.setReadOnly(false);
					cboxintegritySupervisor.setReadOnly(false);
					cboxrespectSupervisor.setReadOnly(false);
					cboxoralCommunicationSupervisor.setReadOnly(false);
					cboxwrittenCommunicationSupervisor.setReadOnly(false);
					cboxerrorResponsibilitySupervisor.setReadOnly(false);
					cboxcommitmentSupervisor.setReadOnly(false);					
					commentpunctualSupervisor.setReadOnly(false);
					commentinformsLatenessSupervisor.setReadOnly(false);
					commentpersonalAssignmentsSupervisor.setReadOnly(false);
					commentlistenInstructionsSupervisor.setReadOnly(false);
					commentfollowsInstructionsSupervisor.setReadOnly(false);
					commentwillingToAssistSupervisor.setReadOnly(false);
					commentwillingToAcceptSupervisor.setReadOnly(false);
					commentliquidatesFinantialSupervisor.setReadOnly(false);
					commentfinantialRequestsTimelySupervisor.setReadOnly(false);
					commentprudentFinancesSupervisor.setReadOnly(false);
					commentinitiativeSupervisor.setReadOnly(false);
					commentresolvesChallengesSupervisor.setReadOnly(false);
					commentqualityProductsSupervisor.setReadOnly(false);
					commenttimelyProductsSupervisor.setReadOnly(false);
					commentintegritySupervisor.setReadOnly(false);
					commentrespectSupervisor.setReadOnly(false);
					commentoralCommunicationSupervisor.setReadOnly(false);
					commentwrittenCommunicationSupervisor.setReadOnly(false);
					commenterrorResponsibilitySupervisor.setReadOnly(false);
					commentcommitmentSupervisor.setReadOnly(false);
					commentsupervisesSupervisor.setReadOnly(false);					
					tfScore.setReadOnly(false);
					tfPercentage.setReadOnly(false);
					tfRanks.setReadOnly(false);
					taConclusions.setReadOnly(false);
					
					/////////
					
					cboxpunctualSupervisor.setValue(selectedPerformance.getPunctualSupervisor().toString());
					cboxinformsLatenessSupervisor.setValue(selectedPerformance.getInformsLatenessSupervisor().toString());
					cboxpersonalAssignmentsSupervisor.setValue(selectedPerformance.getPersonalAssignmentsSupervisor().toString());
					cboxlistenInstructionsSupervisor.setValue(selectedPerformance.getListenInstructionsSupervisor().toString());
					cboxfollowsInstructionsSupervisor.setValue(selectedPerformance.getFollowsInstructionsSupervisor().toString());
					cboxwillingToAssistSupervisor.setValue(selectedPerformance.getWillingToAssistSupervisor().toString());
					cboxwillingToAcceptSupervisor.setValue(selectedPerformance.getWillingToAcceptSupervisor().toString());
					cboxliquidatesFinantialSupervisor.setValue(selectedPerformance.getLiquidatesFinantialSupervisor().toString());
					cboxfinantialRequestsTimelySupervisor.setValue(selectedPerformance.getFinantialRequestsTimelySupervisor().toString());
					cboxprudentFinancesSupervisor.setValue(selectedPerformance.getPrudentFinancesSupervisor().toString());
					cboxinitiativeSupervisor.setValue(selectedPerformance.getInitiativeSupervisor().toString());
					cboxresolvesChallengesSupervisor.setValue(selectedPerformance.getResolvesChallengesSupervisor().toString());
					cboxqualityProductsSupervisor.setValue(selectedPerformance.getQualityProductsSupervisor().toString());
					cboxtimelyProductsSupervisor.setValue(selectedPerformance.getTimelyProductsSupervisor().toString());
					cboxintegritySupervisor.setValue(selectedPerformance.getIntegritySupervisor().toString());
					cboxrespectSupervisor.setValue(selectedPerformance.getRespectSupervisor().toString());
					cboxoralCommunicationSupervisor.setValue(selectedPerformance.getOralCommunicationSupervisor().toString());
					cboxwrittenCommunicationSupervisor.setValue(selectedPerformance.getWrittenCommunicationSupervisor().toString());
					cboxerrorResponsibilitySupervisor.setValue(selectedPerformance.getErrorResponsibilitySupervisor().toString());
					cboxcommitmentSupervisor.setValue(selectedPerformance.getCommitmentSupervisor().toString());				
					commentpunctualSupervisor.setValue(selectedPerformance.getPunctualComments());
					commentinformsLatenessSupervisor.setValue(selectedPerformance.getInformsLatenessComments());
					commentpersonalAssignmentsSupervisor.setValue(selectedPerformance.getPersonalAssignmentsComments());
					commentlistenInstructionsSupervisor.setValue(selectedPerformance.getListenInstructionsComments());
					commentfollowsInstructionsSupervisor.setValue(selectedPerformance.getFollowsInstructionsComments());
					commentwillingToAssistSupervisor.setValue(selectedPerformance.getWillingToAssistComments());
					commentwillingToAcceptSupervisor.setValue(selectedPerformance.getWillingToAcceptComments());
					commentliquidatesFinantialSupervisor.setValue(selectedPerformance.getLiquidatesFinantialComments());
					commentfinantialRequestsTimelySupervisor.setValue(selectedPerformance.getFinantialRequestsTimelyComments());
					commentprudentFinancesSupervisor.setValue(selectedPerformance.getPrudentFinancesComments());
					commentinitiativeSupervisor.setValue(selectedPerformance.getInitiativeComments());
					commentresolvesChallengesSupervisor.setValue(selectedPerformance.getResolvesChallengesComments());
					commentqualityProductsSupervisor.setValue(selectedPerformance.getQualityProductsComments());
					commenttimelyProductsSupervisor.setValue(selectedPerformance.getTimelyProductsComments());
					commentintegritySupervisor.setValue(selectedPerformance.getIntegrityComments());
					commentrespectSupervisor.setValue(selectedPerformance.getRespectComments());
					commentoralCommunicationSupervisor.setValue(selectedPerformance.getOralCommunicationComments());
					commentwrittenCommunicationSupervisor.setValue(selectedPerformance.getWrittenCommunicationComments());
					commenterrorResponsibilitySupervisor.setValue(selectedPerformance.getErrorResponsibilityComments());
					commentcommitmentSupervisor.setValue(selectedPerformance.getCommitmentComments());
					commentsupervisesSupervisor.setValue(selectedPerformance.getSupervisesComments());					
					tfScore.setValue(selectedPerformance.getScore().toString());
					tfPercentage.setValue(selectedPerformance.getPercentage().toString());
					tfRanks.setValue(selectedPerformance.getRank().toString());
					taConclusions.setValue(selectedPerformance.getConclusions());
					
					//////
					
					cboxpunctualSupervisor.setReadOnly(true);
					cboxinformsLatenessSupervisor.setReadOnly(true);
					cboxpersonalAssignmentsSupervisor.setReadOnly(true);
					cboxlistenInstructionsSupervisor.setReadOnly(true);
					cboxfollowsInstructionsSupervisor.setReadOnly(true);
					cboxwillingToAssistSupervisor.setReadOnly(true);
					cboxwillingToAcceptSupervisor.setReadOnly(true);
					cboxliquidatesFinantialSupervisor.setReadOnly(true);
					cboxfinantialRequestsTimelySupervisor.setReadOnly(true);;
					cboxprudentFinancesSupervisor.setReadOnly(true);
					cboxinitiativeSupervisor.setReadOnly(true);
					cboxresolvesChallengesSupervisor.setReadOnly(true);
					cboxqualityProductsSupervisor.setReadOnly(true);
					cboxtimelyProductsSupervisor.setReadOnly(true);
					cboxintegritySupervisor.setReadOnly(true);
					cboxrespectSupervisor.setReadOnly(true);
					cboxoralCommunicationSupervisor.setReadOnly(true);
					cboxwrittenCommunicationSupervisor.setReadOnly(true);
					cboxerrorResponsibilitySupervisor.setReadOnly(true);
					cboxcommitmentSupervisor.setReadOnly(true);					
					commentpunctualSupervisor.setReadOnly(true);
					commentinformsLatenessSupervisor.setReadOnly(true);
					commentpersonalAssignmentsSupervisor.setReadOnly(true);
					commentlistenInstructionsSupervisor.setReadOnly(true);
					commentfollowsInstructionsSupervisor.setReadOnly(true);
					commentwillingToAssistSupervisor.setReadOnly(true);
					commentwillingToAcceptSupervisor.setReadOnly(true);
					commentliquidatesFinantialSupervisor.setReadOnly(true);
					commentfinantialRequestsTimelySupervisor.setReadOnly(true);
					commentprudentFinancesSupervisor.setReadOnly(true);
					commentinitiativeSupervisor.setReadOnly(true);
					commentresolvesChallengesSupervisor.setReadOnly(true);
					commentqualityProductsSupervisor.setReadOnly(true);
					commenttimelyProductsSupervisor.setReadOnly(true);
					commentintegritySupervisor.setReadOnly(true);
					commentrespectSupervisor.setReadOnly(true);
					commentoralCommunicationSupervisor.setReadOnly(true);
					commentwrittenCommunicationSupervisor.setReadOnly(true);
					commenterrorResponsibilitySupervisor.setReadOnly(true);
					commentcommitmentSupervisor.setReadOnly(true);
					commentsupervisesSupervisor.setReadOnly(true);					
					tfScore.setReadOnly(true);
					tfPercentage.setReadOnly(true);
					tfRanks.setReadOnly(true);
					taConclusions.setReadOnly(true);
				}

			}
		});
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		

		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfReviewerName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPeriod.addStyleName(ValoTheme.TEXTFIELD_TINY);
		dfReviewDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		
		
		tfScore.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPercentage.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfRanks.addStyleName(ValoTheme.TEXTFIELD_TINY);
		taConclusions.addStyleName(ValoTheme.TEXTAREA_TINY);			
		

		cboxpunctualStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxinformsLatenessStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxpersonalAssignmentsStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxlistenInstructionsStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxfollowsInstructionsStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwillingToAssistStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwillingToAcceptStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxliquidatesFinantialStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxfinantialRequestsTimelyStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxprudentFinancesStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxinitiativeStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxresolvesChallengesStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxqualityProductsStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxtimelyProductsStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxintegrityStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxrespectStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxoralCommunicationStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwrittenCommunicationStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxerrorResponsibilityStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxcommitmentStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxsupervisesStaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		
		cboxpunctualStaff.setWidth("40px");
		cboxinformsLatenessStaff.setWidth("40px");
		cboxpersonalAssignmentsStaff.setWidth("40px");
		cboxlistenInstructionsStaff.setWidth("40px");
		cboxfollowsInstructionsStaff.setWidth("40px");
		cboxwillingToAssistStaff.setWidth("40px");
		cboxwillingToAcceptStaff.setWidth("40px");
		cboxliquidatesFinantialStaff.setWidth("40px");
		cboxfinantialRequestsTimelyStaff.setWidth("40px");
		cboxprudentFinancesStaff.setWidth("40px");
		cboxinitiativeStaff.setWidth("40px");
		cboxresolvesChallengesStaff.setWidth("40px");
		cboxqualityProductsStaff.setWidth("40px");
		cboxtimelyProductsStaff.setWidth("40px");
		cboxintegrityStaff.setWidth("40px");
		cboxrespectStaff.setWidth("40px");
		cboxoralCommunicationStaff.setWidth("40px");
		cboxwrittenCommunicationStaff.setWidth("40px");
		cboxerrorResponsibilityStaff.setWidth("40px");
		cboxcommitmentStaff.setWidth("40px");
		cboxsupervisesStaff.setWidth("40px");
		
		cboxpunctualStaff.setReadOnly(true);
		cboxinformsLatenessStaff.setReadOnly(true);
		cboxpersonalAssignmentsStaff.setReadOnly(true);
		cboxlistenInstructionsStaff.setReadOnly(true);
		cboxfollowsInstructionsStaff.setReadOnly(true);
		cboxwillingToAssistStaff.setReadOnly(true);
		cboxwillingToAcceptStaff.setReadOnly(true);
		cboxliquidatesFinantialStaff.setReadOnly(true);
		cboxfinantialRequestsTimelyStaff.setReadOnly(true);
		cboxprudentFinancesStaff.setReadOnly(true);
		cboxinitiativeStaff.setReadOnly(true);
		cboxresolvesChallengesStaff.setReadOnly(true);
		cboxqualityProductsStaff.setReadOnly(true);
		cboxtimelyProductsStaff.setReadOnly(true);
		cboxintegrityStaff.setReadOnly(true);
		cboxrespectStaff.setReadOnly(true);
		cboxoralCommunicationStaff.setReadOnly(true);
		cboxwrittenCommunicationStaff.setReadOnly(true);
		cboxerrorResponsibilityStaff.setReadOnly(true);
		cboxcommitmentStaff.setReadOnly(true);
		cboxsupervisesStaff.setReadOnly(true);
		
		
		
		cboxpunctualSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxinformsLatenessSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxpersonalAssignmentsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxlistenInstructionsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxfollowsInstructionsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwillingToAssistSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwillingToAcceptSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxliquidatesFinantialSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxfinantialRequestsTimelySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxprudentFinancesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxinitiativeSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxresolvesChallengesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxqualityProductsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxtimelyProductsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxintegritySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxrespectSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxoralCommunicationSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxwrittenCommunicationSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxerrorResponsibilitySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxcommitmentSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cboxsupervisesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
				
		
		cboxpunctualSupervisor.setWidth("40px");
		cboxinformsLatenessSupervisor.setWidth("40px");
		cboxpersonalAssignmentsSupervisor.setWidth("40px");
		cboxlistenInstructionsSupervisor.setWidth("40px");
		cboxfollowsInstructionsSupervisor.setWidth("40px");
		cboxwillingToAssistSupervisor.setWidth("40px");
		cboxwillingToAcceptSupervisor.setWidth("40px");
		cboxliquidatesFinantialSupervisor.setWidth("40px");
		cboxfinantialRequestsTimelySupervisor.setWidth("40px");
		cboxprudentFinancesSupervisor.setWidth("40px");
		cboxinitiativeSupervisor.setWidth("40px");
		cboxresolvesChallengesSupervisor.setWidth("40px");
		cboxqualityProductsSupervisor.setWidth("40px");
		cboxtimelyProductsSupervisor.setWidth("40px");
		cboxintegritySupervisor.setWidth("40px");
		cboxrespectSupervisor.setWidth("40px");
		cboxoralCommunicationSupervisor.setWidth("40px");
		cboxwrittenCommunicationSupervisor.setWidth("40px");
		cboxerrorResponsibilitySupervisor.setWidth("40px");
		cboxcommitmentSupervisor.setWidth("40px");
		cboxsupervisesSupervisor.setWidth("40px");
		

		tfScore.setSizeFull();
		tfPercentage.setSizeFull();
		tfRanks.setSizeFull();
		taConclusions.setSizeFull();
		
		
		tfName.setSizeFull();
		tfDesignation.setSizeFull();
		tfLocation.setSizeFull();
		dfReviewDate.setSizeFull();
		tfPeriod.setSizeFull();
		tfReviewerName.setSizeFull();
		
		
		
		commentpunctualSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentinformsLatenessSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentpersonalAssignmentsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentlistenInstructionsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentfollowsInstructionsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentwillingToAssistSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentwillingToAcceptSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentliquidatesFinantialSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentfinantialRequestsTimelySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentprudentFinancesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentinitiativeSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentresolvesChallengesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentqualityProductsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commenttimelyProductsSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentintegritySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentrespectSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentoralCommunicationSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentwrittenCommunicationSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commenterrorResponsibilitySupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentcommitmentSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
		commentsupervisesSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);

	

		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();
		
		FormLayout formLeft = new FormLayout(tfName, tfDesignation, tfLocation);
				formLeft.setSpacing(true);
				
				FormLayout formRight = new FormLayout(dfReviewDate,
						tfPeriod, tfReviewerName);
				formRight.setSpacing(true);

				HorizontalLayout layoutUpper = new HorizontalLayout(formLeft, formRight);

		layoutUpper.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutUpper.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutUpper.setMargin(new MarginInfo(false, true, true, true));
		layoutUpper.setSpacing(true);
		layoutUpper.setWidth("972px");
		
		FormLayout formConclusions = new FormLayout(tfScore,
				tfPercentage, tfRanks,taConclusions);
		formConclusions.setSpacing(true);
		
		HorizontalLayout layoutLower = new HorizontalLayout(formConclusions);

		layoutLower.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutLower.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutLower.setMargin(new MarginInfo(false, true, true, true));
		layoutLower.setSpacing(true);
		layoutLower.setWidth("972px");

		
		FormLayout form1 = new FormLayout(new Label("Staff"),
				cboxpunctualStaff, cboxinformsLatenessStaff, cboxpersonalAssignmentsStaff,
				cboxlistenInstructionsStaff, cboxfollowsInstructionsStaff, cboxwillingToAssistStaff,
				cboxwillingToAcceptStaff, cboxliquidatesFinantialStaff, cboxfinantialRequestsTimelyStaff,
				cboxprudentFinancesStaff, cboxinitiativeStaff, cboxresolvesChallengesStaff,
				cboxqualityProductsStaff, cboxtimelyProductsStaff, cboxintegrityStaff,
				cboxrespectStaff, cboxoralCommunicationStaff, cboxwrittenCommunicationStaff,
				cboxerrorResponsibilityStaff, cboxcommitmentStaff, cboxsupervisesStaff);
		form1.setSpacing(true);
		
		FormLayout form2 = new FormLayout(new Label("Supervisor"),
				cboxpunctualSupervisor, cboxinformsLatenessSupervisor, cboxpersonalAssignmentsSupervisor,
				cboxlistenInstructionsSupervisor, cboxfollowsInstructionsSupervisor, cboxwillingToAssistSupervisor,
				cboxwillingToAcceptSupervisor, cboxliquidatesFinantialSupervisor, cboxfinantialRequestsTimelySupervisor,
				cboxprudentFinancesSupervisor, cboxinitiativeSupervisor, cboxresolvesChallengesSupervisor,
				cboxqualityProductsSupervisor, cboxtimelyProductsSupervisor, cboxintegritySupervisor,
				cboxrespectSupervisor, cboxoralCommunicationSupervisor, cboxwrittenCommunicationSupervisor,
				cboxerrorResponsibilitySupervisor, cboxcommitmentSupervisor, cboxsupervisesSupervisor);
		form2.setSpacing(true);
		
		FormLayout form3 = new FormLayout(new Label("Comment"),
				commentpunctualSupervisor, commentinformsLatenessSupervisor, commentpersonalAssignmentsSupervisor,
				commentlistenInstructionsSupervisor, commentfollowsInstructionsSupervisor, commentwillingToAssistSupervisor,
				commentwillingToAcceptSupervisor, commentliquidatesFinantialSupervisor, commentfinantialRequestsTimelySupervisor,
				commentprudentFinancesSupervisor, commentinitiativeSupervisor, commentresolvesChallengesSupervisor,
				commentqualityProductsSupervisor, commenttimelyProductsSupervisor, commentintegritySupervisor,
				commentrespectSupervisor, commentoralCommunicationSupervisor, commentwrittenCommunicationSupervisor,
				commenterrorResponsibilitySupervisor, commentcommitmentSupervisor, commentsupervisesSupervisor);
		form3.setSpacing(true);

		
		layoutMain.addComponents(form1, form2, form3);
		layoutMain.setExpandRatio(form1, 3);


		content.addComponents(layoutSearch, layoutUpper, layoutMain, layoutLower);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutUpper, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutLower, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
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
		titleLabel.setSizeUndefined();
		titleLabel.addStyleName(ValoTheme.LABEL_H2);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
		titleLabel.addStyleName(ValoTheme.LABEL_BOLD);

		header.addComponent(titleLabel);
		header.setComponentAlignment(titleLabel, Alignment.MIDDLE_CENTER);
		return header;
	}

}


