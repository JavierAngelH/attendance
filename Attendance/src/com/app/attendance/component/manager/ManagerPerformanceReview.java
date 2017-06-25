/** 
 * ManagerPerformanceReview.java Created: Mar 12, 2017 Copyright (c) 2013 LEB
 */

package com.app.attendance.component.manager;

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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * ManagerPerformanceReview - 
 *
 */

@SpringComponent
@UIScope
public class ManagerPerformanceReview  extends Panel {
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

	ComboBox cboxpunctualSupervisor = new ComboBox();
	ComboBox cboxinformsLatenessSupervisor = new ComboBox();
	ComboBox cboxpersonalAssignmentsSupervisor = new ComboBox();
	ComboBox cboxlistenInstructionsSupervisor = new ComboBox();
	ComboBox cboxfollowsInstructionsSupervisor = new ComboBox();
	ComboBox cboxwillingToAssistSupervisor = new ComboBox();
	ComboBox cboxwillingToAcceptSupervisor = new ComboBox();
	ComboBox cboxliquidatesFinantialSupervisor = new ComboBox();
	ComboBox cboxfinantialRequestsTimelySupervisor = new ComboBox();
	ComboBox cboxprudentFinancesSupervisor = new ComboBox();
	ComboBox cboxinitiativeSupervisor = new ComboBox();
	ComboBox cboxresolvesChallengesSupervisor = new ComboBox();
	ComboBox cboxqualityProductsSupervisor = new ComboBox();
	ComboBox cboxtimelyProductsSupervisor = new ComboBox();
	ComboBox cboxintegritySupervisor = new ComboBox();
	ComboBox cboxrespectSupervisor = new ComboBox();
	ComboBox cboxoralCommunicationSupervisor = new ComboBox();
	ComboBox cboxwrittenCommunicationSupervisor = new ComboBox();
	ComboBox cboxerrorResponsibilitySupervisor = new ComboBox();
	ComboBox cboxcommitmentSupervisor = new ComboBox();
	ComboBox cboxsupervisesSupervisor = new ComboBox();
	
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
			Employee employee;

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

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try {

				cboxpunctualSupervisor.validate();
				cboxinformsLatenessSupervisor.validate();
				cboxpersonalAssignmentsSupervisor.validate();
				cboxlistenInstructionsSupervisor.validate();
				cboxfollowsInstructionsSupervisor.validate();
				cboxwillingToAssistSupervisor.validate();
				cboxwillingToAcceptSupervisor.validate();
				cboxliquidatesFinantialSupervisor.validate();
				cboxfinantialRequestsTimelySupervisor.validate();
				cboxprudentFinancesSupervisor.validate();
				cboxinitiativeSupervisor.validate();
				cboxresolvesChallengesSupervisor.validate();
				cboxqualityProductsSupervisor.validate();
				cboxtimelyProductsSupervisor.validate();
				cboxintegritySupervisor.validate();
				cboxrespectSupervisor.validate();
				cboxoralCommunicationSupervisor.validate();
				cboxwrittenCommunicationSupervisor.validate();
				cboxerrorResponsibilitySupervisor.validate();
				cboxcommitmentSupervisor.validate();
				
				tfScore.validate();
				tfPercentage.validate();
				tfRanks.validate();
				taConclusions.validate();				
				

				PerformanceReview review = new PerformanceReview();
				review.setId(selectedPerformance.getId());

				review.setEmployeeId(employee.getId());
				review.setReviewDate(dfReviewDate.getValue());
				review.setReviewerName(tfReviewerName.getValue());
				review.setPunctualSupervisor(
						Integer.parseInt(cboxpunctualSupervisor.getValue().toString()));
				review.setInformsLatenessSupervisor(Integer
						.parseInt(cboxinformsLatenessSupervisor.getValue().toString()));
				review.setPersonalAssignmentsSupervisor(Integer.parseInt(
						cboxpersonalAssignmentsSupervisor.getValue().toString()));
				review.setListenInstructionsSupervisor(Integer
						.parseInt(cboxlistenInstructionsSupervisor.getValue().toString()));
				review.setFollowsInstructionsSupervisor(Integer.parseInt(
						cboxfollowsInstructionsSupervisor.getValue().toString()));
				review.setWillingToAssistSupervisor(Integer
						.parseInt(cboxwillingToAssistSupervisor.getValue().toString()));
				review.setWillingToAcceptSupervisor(Integer
						.parseInt(cboxwillingToAcceptSupervisor.getValue().toString()));
				review.setLiquidatesFinantialSupervisor(Integer.parseInt(
						cboxliquidatesFinantialSupervisor.getValue().toString()));
				review.setFinantialRequestsTimelySupervisor(Integer.parseInt(
						cboxfinantialRequestsTimelySupervisor.getValue().toString()));
				review.setPrudentFinancesSupervisor(Integer
						.parseInt(cboxprudentFinancesSupervisor.getValue().toString()));
				review.setInitiativeSupervisor(
						Integer.parseInt(cboxinitiativeSupervisor.getValue().toString()));
				review.setResolvesChallengesSupervisor(Integer
						.parseInt(cboxresolvesChallengesSupervisor.getValue().toString()));
				review.setQualityProductsSupervisor(Integer
						.parseInt(cboxqualityProductsSupervisor.getValue().toString()));
				review.setTimelyProductsSupervisor(Integer
						.parseInt(cboxtimelyProductsSupervisor.getValue().toString()));
				review.setIntegritySupervisor(
						Integer.parseInt(cboxintegritySupervisor.getValue().toString()));
				review.setRespectSupervisor(
						Integer.parseInt(cboxrespectSupervisor.getValue().toString()));
				review.setOralCommunicationSupervisor(Integer
						.parseInt(cboxoralCommunicationSupervisor.getValue().toString()));
				review.setWrittenCommunicationSupervisor(Integer.parseInt(
						cboxwrittenCommunicationSupervisor.getValue().toString()));
				review.setErrorResponsibilitySupervisor(Integer.parseInt(
						cboxerrorResponsibilitySupervisor.getValue().toString()));
				review.setCommitmentSupervisor(
						Integer.parseInt(cboxcommitmentSupervisor.getValue().toString()));
				
				
				review.setPunctualComments(
						commentpunctualSupervisor.getValue());
				review.setInformsLatenessComments(commentinformsLatenessSupervisor.getValue());
				review.setPersonalAssignmentsComments(
						commentpersonalAssignmentsSupervisor.getValue());
				review.setListenInstructionsComments(commentlistenInstructionsSupervisor.getValue());
				review.setFollowsInstructionsComments(
						commentfollowsInstructionsSupervisor.getValue());
				review.setWillingToAssistComments(commentwillingToAssistSupervisor.getValue());
				review.setWillingToAcceptComments(commentwillingToAcceptSupervisor.getValue());
				review.setLiquidatesFinantialComments(
						commentliquidatesFinantialSupervisor.getValue());
				review.setFinantialRequestsTimelyComments(
						commentfinantialRequestsTimelySupervisor.getValue());
				review.setPrudentFinancesComments(commentprudentFinancesSupervisor.getValue());
				review.setInitiativeComments(
						commentinitiativeSupervisor.getValue());
				review.setResolvesChallengesComments(commentresolvesChallengesSupervisor.getValue());
				review.setQualityProductsComments(commentqualityProductsSupervisor.getValue());
				review.setTimelyProductsComments(commenttimelyProductsSupervisor.getValue());
				review.setIntegrityComments(
						commentintegritySupervisor.getValue());
				review.setRespectComments(
						commentrespectSupervisor.getValue());
				review.setOralCommunicationComments(commentoralCommunicationSupervisor.getValue());
				review.setWrittenCommunicationComments(
						commentwrittenCommunicationSupervisor.getValue().toString());
				review.setErrorResponsibilityComments(
						commenterrorResponsibilitySupervisor.getValue().toString());
				review.setCommitmentComments(
						commentcommitmentSupervisor.getValue().toString());
				
				
				review.setScore(Integer.parseInt(
						tfScore.getValue().toString()));
				review.setPercentage(Integer.parseInt(
						tfPercentage.getValue().toString()));
				review.setRank(Integer.parseInt(
						tfRanks.getValue().toString()));
				review.setConclusions(taConclusions.getValue());	
				
				if (cboxsupervisesSupervisor.getValue() != null) {
					review.setSupervisesSupervisor(Integer
							.parseInt(cboxsupervisesSupervisor.getValue().toString()));
				}

				if (tfPeriod.getValue() != null) {
					review.setPeriod(tfPeriod.getValue());
				}

				employeeService.updatePerformanceReview(review);
				
				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				e.printStackTrace();

				Notification.show("You must fill all the required fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		titleLabel.setValue("STAFF PERFORMANCE REVIEW");

	
		
		this.cbEmployees.removeAllItems();

		BeanItemContainer<PerformanceReview> performanceBeanContainer = new BeanItemContainer<PerformanceReview>(PerformanceReview.class);

		List<PerformanceReview> performanceList = this.employeeService
				.getPerformanceReviewsByManager(this.employee.getId());

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



				}

			}
		});
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		cboxpunctualSupervisor.setInvalidAllowed(false);
		cboxinformsLatenessSupervisor.setInvalidAllowed(false);
		cboxpersonalAssignmentsSupervisor.setInvalidAllowed(false);
		cboxlistenInstructionsSupervisor.setInvalidAllowed(false);
		cboxfollowsInstructionsSupervisor.setInvalidAllowed(false);
		cboxwillingToAssistSupervisor.setInvalidAllowed(false);
		cboxwillingToAcceptSupervisor.setInvalidAllowed(false);
		cboxliquidatesFinantialSupervisor.setInvalidAllowed(false);
		cboxfinantialRequestsTimelySupervisor.setInvalidAllowed(false);
		cboxprudentFinancesSupervisor.setInvalidAllowed(false);
		cboxinitiativeSupervisor.setInvalidAllowed(false);
		cboxresolvesChallengesSupervisor.setInvalidAllowed(false);
		cboxqualityProductsSupervisor.setInvalidAllowed(false);
		cboxtimelyProductsSupervisor.setInvalidAllowed(false);
		cboxintegritySupervisor.setInvalidAllowed(false);
		cboxrespectSupervisor.setInvalidAllowed(false);
		cboxoralCommunicationSupervisor.setInvalidAllowed(false);
		cboxwrittenCommunicationSupervisor.setInvalidAllowed(false);
		cboxerrorResponsibilitySupervisor.setInvalidAllowed(false);
		cboxcommitmentSupervisor.setInvalidAllowed(false);
		cboxsupervisesSupervisor.setInvalidAllowed(false);

		cboxpunctualSupervisor.addItem("0");
		cboxinformsLatenessSupervisor.addItem("0");
		cboxpersonalAssignmentsSupervisor.addItem("0");
		cboxlistenInstructionsSupervisor.addItem("0");
		cboxfollowsInstructionsSupervisor.addItem("0");
		cboxwillingToAssistSupervisor.addItem("0");
		cboxwillingToAcceptSupervisor.addItem("0");
		cboxliquidatesFinantialSupervisor.addItem("0");
		cboxfinantialRequestsTimelySupervisor.addItem("0");
		cboxprudentFinancesSupervisor.addItem("0");
		cboxinitiativeSupervisor.addItem("0");
		cboxresolvesChallengesSupervisor.addItem("0");
		cboxqualityProductsSupervisor.addItem("0");
		cboxtimelyProductsSupervisor.addItem("0");
		cboxintegritySupervisor.addItem("0");
		cboxrespectSupervisor.addItem("0");
		cboxoralCommunicationSupervisor.addItem("0");
		cboxwrittenCommunicationSupervisor.addItem("0");
		cboxerrorResponsibilitySupervisor.addItem("0");
		cboxcommitmentSupervisor.addItem("0");
		cboxsupervisesSupervisor.addItem("0");

		cboxpunctualSupervisor.addItem("1");
		cboxinformsLatenessSupervisor.addItem("1");
		cboxpersonalAssignmentsSupervisor.addItem("1");
		cboxlistenInstructionsSupervisor.addItem("1");
		cboxfollowsInstructionsSupervisor.addItem("1");
		cboxwillingToAssistSupervisor.addItem("1");
		cboxwillingToAcceptSupervisor.addItem("1");
		cboxliquidatesFinantialSupervisor.addItem("1");
		cboxfinantialRequestsTimelySupervisor.addItem("1");
		cboxprudentFinancesSupervisor.addItem("1");
		cboxinitiativeSupervisor.addItem("1");
		cboxresolvesChallengesSupervisor.addItem("1");
		cboxqualityProductsSupervisor.addItem("1");
		cboxtimelyProductsSupervisor.addItem("1");
		cboxintegritySupervisor.addItem("1");
		cboxrespectSupervisor.addItem("1");
		cboxoralCommunicationSupervisor.addItem("1");
		cboxwrittenCommunicationSupervisor.addItem("1");
		cboxerrorResponsibilitySupervisor.addItem("1");
		cboxcommitmentSupervisor.addItem("1");
		cboxsupervisesSupervisor.addItem("1");

		cboxpunctualSupervisor.addItem("2");
		cboxinformsLatenessSupervisor.addItem("2");
		cboxpersonalAssignmentsSupervisor.addItem("2");
		cboxlistenInstructionsSupervisor.addItem("2");
		cboxfollowsInstructionsSupervisor.addItem("2");
		cboxwillingToAssistSupervisor.addItem("2");
		cboxwillingToAcceptSupervisor.addItem("2");
		cboxliquidatesFinantialSupervisor.addItem("2");
		cboxfinantialRequestsTimelySupervisor.addItem("2");
		cboxprudentFinancesSupervisor.addItem("2");
		cboxinitiativeSupervisor.addItem("2");
		cboxresolvesChallengesSupervisor.addItem("2");
		cboxqualityProductsSupervisor.addItem("2");
		cboxtimelyProductsSupervisor.addItem("2");
		cboxintegritySupervisor.addItem("2");
		cboxrespectSupervisor.addItem("2");
		cboxoralCommunicationSupervisor.addItem("2");
		cboxwrittenCommunicationSupervisor.addItem("2");
		cboxerrorResponsibilitySupervisor.addItem("2");
		cboxcommitmentSupervisor.addItem("2");
		cboxsupervisesSupervisor.addItem("2");

		cboxpunctualSupervisor.addItem("3");
		cboxinformsLatenessSupervisor.addItem("3");
		cboxpersonalAssignmentsSupervisor.addItem("3");
		cboxlistenInstructionsSupervisor.addItem("3");
		cboxfollowsInstructionsSupervisor.addItem("3");
		cboxwillingToAssistSupervisor.addItem("3");
		cboxwillingToAcceptSupervisor.addItem("3");
		cboxliquidatesFinantialSupervisor.addItem("3");
		cboxfinantialRequestsTimelySupervisor.addItem("3");
		cboxprudentFinancesSupervisor.addItem("3");
		cboxinitiativeSupervisor.addItem("3");
		cboxresolvesChallengesSupervisor.addItem("3");
		cboxqualityProductsSupervisor.addItem("3");
		cboxtimelyProductsSupervisor.addItem("3");
		cboxintegritySupervisor.addItem("3");
		cboxrespectSupervisor.addItem("3");
		cboxoralCommunicationSupervisor.addItem("3");
		cboxwrittenCommunicationSupervisor.addItem("3");
		cboxerrorResponsibilitySupervisor.addItem("3");
		cboxcommitmentSupervisor.addItem("3");
		cboxsupervisesSupervisor.addItem("3");

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
		
		
		
		cboxpunctualSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxinformsLatenessSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxpersonalAssignmentsSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxlistenInstructionsSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxfollowsInstructionsSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxwillingToAssistSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxwillingToAcceptSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxliquidatesFinantialSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxfinantialRequestsTimelySupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxprudentFinancesSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxinitiativeSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxresolvesChallengesSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxqualityProductsSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxtimelyProductsSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxintegritySupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxrespectSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxoralCommunicationSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxwrittenCommunicationSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxerrorResponsibilitySupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxcommitmentSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
		cboxsupervisesSupervisor.addStyleName(ValoTheme.COMBOBOX_TINY);
				
		
		cboxpunctualSupervisor.setWidth("70px");
		cboxinformsLatenessSupervisor.setWidth("70px");
		cboxpersonalAssignmentsSupervisor.setWidth("70px");
		cboxlistenInstructionsSupervisor.setWidth("70px");
		cboxfollowsInstructionsSupervisor.setWidth("70px");
		cboxwillingToAssistSupervisor.setWidth("70px");
		cboxwillingToAcceptSupervisor.setWidth("70px");
		cboxliquidatesFinantialSupervisor.setWidth("70px");
		cboxfinantialRequestsTimelySupervisor.setWidth("70px");
		cboxprudentFinancesSupervisor.setWidth("70px");
		cboxinitiativeSupervisor.setWidth("70px");
		cboxresolvesChallengesSupervisor.setWidth("70px");
		cboxqualityProductsSupervisor.setWidth("70px");
		cboxtimelyProductsSupervisor.setWidth("70px");
		cboxintegritySupervisor.setWidth("70px");
		cboxrespectSupervisor.setWidth("70px");
		cboxoralCommunicationSupervisor.setWidth("70px");
		cboxwrittenCommunicationSupervisor.setWidth("70px");
		cboxerrorResponsibilitySupervisor.setWidth("70px");
		cboxcommitmentSupervisor.setWidth("70px");
		cboxsupervisesSupervisor.setWidth("70px");
		
		
		cboxpunctualSupervisor.setRequired(true);
		cboxinformsLatenessSupervisor.setRequired(true);
		cboxpersonalAssignmentsSupervisor.setRequired(true);
		cboxlistenInstructionsSupervisor.setRequired(true);
		cboxfollowsInstructionsSupervisor.setRequired(true);
		cboxwillingToAssistSupervisor.setRequired(true);
		cboxwillingToAcceptSupervisor.setRequired(true);
		cboxliquidatesFinantialSupervisor.setRequired(true);
		cboxfinantialRequestsTimelySupervisor.setRequired(true);
		cboxprudentFinancesSupervisor.setRequired(true);
		cboxinitiativeSupervisor.setRequired(true);
		cboxresolvesChallengesSupervisor.setRequired(true);
		cboxqualityProductsSupervisor.setRequired(true);
		cboxtimelyProductsSupervisor.setRequired(true);
		cboxintegritySupervisor.setRequired(true);
		cboxrespectSupervisor.setRequired(true);
		cboxoralCommunicationSupervisor.setRequired(true);
		cboxwrittenCommunicationSupervisor.setRequired(true);
		cboxerrorResponsibilitySupervisor.setRequired(true);
		cboxcommitmentSupervisor.setRequired(true);
		cboxsupervisesSupervisor.setRequired(false);
		

		tfScore.setRequired(true);
		tfPercentage.setRequired(true);
		tfRanks.setRequired(true);
		taConclusions.setRequired(true);	
		
		
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

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1, form2, form3);
		layoutMain.setExpandRatio(form1, 3);


		content.addComponents(layoutSearch, layoutUpper, layoutMain, layoutLower, layoutButton);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setComponentAlignment(layoutUpper, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutLower, Alignment.TOP_CENTER);

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

