/** 
 * HRTerminationForm.java Created: Dec 13, 2016 JavierAngelH
 */

package com.app.attendance.component.hr;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.TerminationForm;
import com.app.attendance.service.EmployeeService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
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
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRTerminationForm - 
 *
 */
@SpringComponent
@UIScope
public class HRTerminationForm extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

TextField tfName = new TextField("NAME");
	
	TextField tfDesignation = new TextField("DESIGNATION");
	

	TextField tfEmployeeId = new TextField("EMPLOYEE ID");
	
	DateField dfEmployment = new DateField("DATE OF EMPLOYMENT");
	
	TextField tfReason = new TextField("REASON FOR LEAVING");

	TextField tfReturn = new TextField("CONSIDER FUTURE RETURN");

	TextField tfRecommendation = new TextField("WOULD YOU RECOMMEND TO A FRIEND");

	TextField tfManagement = new TextField("IS THERE ANYTHING MADE BY MANAGEMENT THAT MADE YOU LEAVE?");

	TextField tfSuggestion = new TextField("DO YOU HAVE ANY SUGGESTIONS FOR HOW THE COMPANY CAN IMPORVE SATISFACTION IN YOUR POSITION?");

	TextField tfComments = new TextField("COMMENTS");

	OptionGroup rehireOption = new OptionGroup("IS THIS EMPLOYEE ELEGIBLE FOR REHIRE?");
	
	TextField cbTerminationReason = new TextField("REASON FOR TERMINATION");
	
	OptionGroup managementPreventionOption = new OptionGroup("COULD MANAGEMENT HAVE DONE ANYTHING TO PREVENT YOUR VOLUNTARY RESIGNATION?");
	TextField cbSatisfaction = new TextField("HOW SATISFIED WERE YOU WITH YOUR EMPLOYMENT AT EVA?");
	TextField tfLike = new TextField("WHAT DID YOU LIKE ABOUT YOUR EMPLOYMENT AT EVA?");
	TextField tfDislike = new TextField("WHAT DID YOU DISLIKE ABOUT YOUR EMPLOYMENT AT EVA?");
	OptionGroup reapplyGroup = new OptionGroup("WOULD YOU CONSIDER REAPPLYING FOR A POSITION WITH EVA IN FUTURE?");
	OptionGroup keepContactOption = new OptionGroup("WOULD YOU LIKE EVA TO KEEP IN CONTACT WITH EVA IN THE FUTURE?");
	TextField tfPhoneMail = new TextField("PLEASE GIVE US YOUR PHONE NUMBER AND EMAIL");

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");
	
	OptionGroup formalResignationOption = new OptionGroup("HAS EMPLOYEE SUBMITTED A FORMAL RESIGNATION OR HAS A FORMAL TERMINATION LETTER BEEN GIVEN TO THE EMPLOYEE?");

	OptionGroup handoverNoteOption = new OptionGroup("HAS EMPLOYEE COMPLETED A HANDOVER NOTE, APPROVED BY SUPERVISOR?");

	OptionGroup handoverPropertiesOption = new OptionGroup("HAS EMPLOYEE HANDED OVER ALL ORGANIZATIONAL PROPERTIES AND ASSETS (COMPUTERS, ID CARDS, PHONE, ETC)?");

	OptionGroup medicalCoverageOption = new OptionGroup("HAS EMPLOYEE MEDICAL AND LIFE INSURANCE COVERAGE BEEN TERMINATED?");

	OptionGroup benefitsPaidOption = new OptionGroup("HAS ALL STATUTORY BENEFITS SUCH AS PENSION AND WORKERS COMPENSATION BEEN PAID UP TO DATE?");

	OptionGroup minimunNoticeOption = new OptionGroup("DID EMPLOYEE MEET THE MINIMUM 1 MONTH NOTICE FOR VOLUNTARY RESIGNATION?");

	OptionGroup finalPaymentOption = new OptionGroup("IS THERE ANY FINAL PAYMENTS DUE TO THE EMPLOYEE?");

	OptionGroup possibleRehireOption = new OptionGroup("IS EMPLOYEE ELEGIBLE FOR POSSIBLE REHIRE IN THE FUTURE?");

	Button btnSubmit = new Button("SUBMIT");

	HorizontalLayout layoutButton = new HorizontalLayout();


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
			try{
				
				TerminationForm employee = (TerminationForm) HRTerminationForm.this.cbEmployees.getValue();

employeeService.updateTerminationFormHR(employee.getId(), formalResignationOption.getValue().toString(), handoverNoteOption.getValue().toString(), 
		handoverPropertiesOption.getValue().toString(), medicalCoverageOption.getValue().toString(), benefitsPaidOption.getValue().toString(), 
		minimunNoticeOption.getValue().toString(), finalPaymentOption.getValue().toString(), possibleRehireOption.getValue().toString());

			Notification.show("Information saved succesfully.");
			}catch(Exception e){
String error = e.getMessage();

	Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.titleLabel.setValue("EXIT FORMS");
		
		this.tfName.setReadOnly(false);
		this.tfDesignation.setReadOnly(false);
		this.tfEmployeeId.setReadOnly(false);
		this.dfEmployment.setReadOnly(false);	
		this.tfReturn.setReadOnly(false);		
		this.tfRecommendation.setReadOnly(false);
		this.tfManagement.setReadOnly(false);
		this.tfSuggestion.setReadOnly(false);
		this.tfComments.setReadOnly(false);
		this.tfReason.setReadOnly(false);
		this.cbTerminationReason.setReadOnly(false);
		this.rehireOption.setReadOnly(false);		
		
		rehireOption.setReadOnly(false);		
		cbTerminationReason.setReadOnly(false);		
		managementPreventionOption.setReadOnly(false);
		tfLike.setReadOnly(false);
		tfDislike.setReadOnly(false);
		reapplyGroup.setReadOnly(false);
		keepContactOption.setReadOnly(false);
		tfPhoneMail.setReadOnly(false);
		cbSatisfaction.setReadOnly(false);		
		
		this.formalResignationOption.setReadOnly(false);
		this.handoverNoteOption.setReadOnly(false);
		this.handoverPropertiesOption.setReadOnly(false);
		this.medicalCoverageOption.setReadOnly(false);
		this.benefitsPaidOption.setReadOnly(false);
		this.minimunNoticeOption.setReadOnly(false);
		this.finalPaymentOption.setReadOnly(false);
		this.possibleRehireOption.setReadOnly(false);	

		this.tfName.clear();
		this.tfDesignation.clear();
		this.tfEmployeeId.clear();
		this.dfEmployment.clear();		
		this.tfReturn.clear();		
		this.tfRecommendation.clear();
		this.tfManagement.clear();
		this.tfSuggestion.clear();
		this.tfComments.clear();
		this.tfReason.clear();
		this.cbTerminationReason.clear();
		this.rehireOption.clear();

		this.formalResignationOption.clear();
		this.handoverNoteOption.clear();
		this.handoverPropertiesOption.clear();
		this.medicalCoverageOption.clear();
		this.benefitsPaidOption.clear();
		this.minimunNoticeOption.clear();
		this.finalPaymentOption.clear();
		this.possibleRehireOption.clear();
		
		cbTerminationReason.clear();	
		managementPreventionOption.clear();
		tfLike.clear();
		tfDislike.clear();
		reapplyGroup.clear();
		keepContactOption.clear();
		tfPhoneMail.clear();
		cbSatisfaction.clear();


		this.tfName.setReadOnly(true);
		this.tfDesignation.setReadOnly(true);
		this.tfEmployeeId.setReadOnly(true);
		this.dfEmployment.setReadOnly(true);	
		this.tfReturn.setReadOnly(true);		
		this.tfRecommendation.setReadOnly(true);
		this.tfManagement.setReadOnly(true);
		this.tfSuggestion.setReadOnly(true);
		this.tfComments.setReadOnly(true);
		this.tfReason.setReadOnly(true);
		this.cbTerminationReason.setReadOnly(true);
		this.rehireOption.setReadOnly(true);
		
		managementPreventionOption.setReadOnly(true);
		reapplyGroup.setReadOnly(true);
		keepContactOption.setReadOnly(true);
		
		tfLike.setReadOnly(true);
		tfDislike.setReadOnly(true);
		tfPhoneMail.setReadOnly(true);

		
		cbSatisfaction.setReadOnly(true);	
		

		this.cbEmployees.removeAllItems();

		BeanItemContainer<TerminationForm> terminationBeanContainer = new BeanItemContainer<TerminationForm>(TerminationForm.class);

		List<TerminationForm> terminationList = this.employeeService.getTerminationForms();

		terminationBeanContainer.addAll(terminationList);

		this.cbEmployees.setContainerDataSource(terminationBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("employeeName");
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		VerticalLayout layoutMain = new VerticalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfName.setSizeFull();

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfDesignation.setSizeFull();

		this.tfEmployeeId.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfEmployeeId.setSizeFull();


		this.tfReason.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfReason.setSizeFull();

		this.tfReturn.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfReturn.setSizeFull();

		this.tfRecommendation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfRecommendation.setSizeFull();

		this.tfManagement.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfManagement.setSizeFull();

		this.tfSuggestion.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfSuggestion.setSizeFull();

		this.tfComments.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfComments.setSizeFull();
		
		tfLike.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfLike.setSizeFull();
		
		tfDislike.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfDislike.setSizeFull();
		
		tfPhoneMail.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfPhoneMail.setSizeFull();

		this.rehireOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.rehireOption.addItem("YES");
		this.rehireOption.addItem("NO");
		this.rehireOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);
		rehireOption.setSizeFull();
		
		this.managementPreventionOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.managementPreventionOption.addItem("YES");
		this.managementPreventionOption.addItem("NO");
		this.managementPreventionOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);
		managementPreventionOption.setSizeFull();
		
		this.reapplyGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.reapplyGroup.addItem("YES");
		this.reapplyGroup.addItem("NO");
		this.reapplyGroup.addStyleName(ValoTheme.OPTIONGROUP_SMALL);
		reapplyGroup.setSizeFull();
		
		this.keepContactOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.keepContactOption.addItem("YES");
		this.keepContactOption.addItem("NO");
		this.keepContactOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);
		keepContactOption.setSizeFull();		
		

		this.formalResignationOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.formalResignationOption.addItem("YES");
		this.formalResignationOption.addItem("NO");
		this.formalResignationOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.handoverNoteOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.handoverNoteOption.addItem("YES");
		this.handoverNoteOption.addItem("NO");
		this.handoverNoteOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.handoverPropertiesOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.handoverPropertiesOption.addItem("YES");
		this.handoverPropertiesOption.addItem("NO");
		this.handoverPropertiesOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.medicalCoverageOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.medicalCoverageOption.addItem("YES");
		this.medicalCoverageOption.addItem("NO");
		this.medicalCoverageOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.benefitsPaidOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.benefitsPaidOption.addItem("YES");
		this.benefitsPaidOption.addItem("NO");
		this.benefitsPaidOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.minimunNoticeOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.minimunNoticeOption.addItem("YES");
		this.minimunNoticeOption.addItem("NO");
		this.minimunNoticeOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.finalPaymentOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.finalPaymentOption.addItem("YES");
		this.finalPaymentOption.addItem("NO");
		this.finalPaymentOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.possibleRehireOption.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.possibleRehireOption.addItem("YES");
		this.possibleRehireOption.addItem("NO");
		this.possibleRehireOption.addStyleName(ValoTheme.OPTIONGROUP_SMALL);

		this.cbTerminationReason.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cbTerminationReason.setSizeFull();
		
		this.cbSatisfaction.addStyleName(ValoTheme.TEXTFIELD_TINY);
		cbSatisfaction.setSizeFull();
		

		this.dfEmployment.addStyleName(ValoTheme.DATEFIELD_TINY);
		dfEmployment.setSizeFull();

	
		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbEmployees.setNullSelectionAllowed(false);
		cbEmployees.setSizeFull();

		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				TerminationForm employee = (TerminationForm) HRTerminationForm.this.cbEmployees.getValue();

				if (employee != null) {

					HRTerminationForm.this.tfName.setReadOnly(false);
					HRTerminationForm.this.tfDesignation.setReadOnly(false);
					HRTerminationForm.this.tfEmployeeId.setReadOnly(false);
					HRTerminationForm.this.dfEmployment.setReadOnly(false);	
					HRTerminationForm.this.tfReturn.setReadOnly(false);		
					HRTerminationForm.this.tfRecommendation.setReadOnly(false);
					HRTerminationForm.this.tfManagement.setReadOnly(false);
					HRTerminationForm.this.tfSuggestion.setReadOnly(false);
					HRTerminationForm.this.tfComments.setReadOnly(false);
					HRTerminationForm.this.tfReason.setReadOnly(false);
					HRTerminationForm.this.rehireOption.setReadOnly(false);
					HRTerminationForm.this.cbTerminationReason.setReadOnly(false);

					HRTerminationForm.this.managementPreventionOption.setReadOnly(false);
					HRTerminationForm.this.tfLike.setReadOnly(false);
					HRTerminationForm.this.tfDislike.setReadOnly(false);
					HRTerminationForm.this.reapplyGroup.setReadOnly(false);
					HRTerminationForm.this.keepContactOption.setReadOnly(false);
					HRTerminationForm.this.tfPhoneMail.setReadOnly(false);
					HRTerminationForm.this.cbSatisfaction.setReadOnly(false);

				
					
					HRTerminationForm.this.tfName.setValue(employee.getEmployeeName());
					HRTerminationForm.this.tfReason.setValue(employee.getLeavingReason());
					HRTerminationForm.this.rehireOption.setValue(employee.getRehire());
					HRTerminationForm.this.tfDesignation.setValue(employee.getDesignation());
					HRTerminationForm.this.tfEmployeeId.setValue(employee.getEmployeeId());
					HRTerminationForm.this.dfEmployment.setValue(employee.getEmploymentDate());
					HRTerminationForm.this.tfReturn.setValue(employee.getPossibleReturn());		
					HRTerminationForm.this.tfRecommendation.setValue(employee.getRecommendation());
					HRTerminationForm.this.tfManagement.setValue(employee.getManagementReason());
					HRTerminationForm.this.tfSuggestion.setValue(employee.getSuggestions());
					HRTerminationForm.this.tfComments.setValue(employee.getComments());
					HRTerminationForm.this.cbTerminationReason.setValue(employee.getTerminationReason());
					
					
					HRTerminationForm.this.managementPreventionOption.setValue(employee.getManagementPrevention());
					HRTerminationForm.this.tfLike.setValue(employee.getLikeEmployment());
					HRTerminationForm.this.tfDislike.setValue(employee.getDislikeEmployment());
					HRTerminationForm.this.reapplyGroup.setValue(employee.getConsiderReapply());
					HRTerminationForm.this.keepContactOption.setValue(employee.getKeepContact());
					HRTerminationForm.this.tfPhoneMail.setValue(employee.getPhoneNumber());
					HRTerminationForm.this.cbSatisfaction.setValue(employee.getSatisfaction());

					if(employee.getHrReviewed()== 1){
						
					
					HRTerminationForm.this.formalResignationOption.setValue(employee.getFormalResignation());
					HRTerminationForm.this.handoverNoteOption.setValue(employee.getHandoverNote());
					HRTerminationForm.this.handoverPropertiesOption.setValue(employee.getHandoverProperties());
					HRTerminationForm.this.medicalCoverageOption.setValue(employee.getMedicalCoverage());
					HRTerminationForm.this.benefitsPaidOption.setValue(employee.getBenefitsPaid());
					HRTerminationForm.this.minimunNoticeOption.setValue(employee.getMinimumNotice());
					HRTerminationForm.this.finalPaymentOption.setValue(employee.getFinalPayment());
					HRTerminationForm.this.possibleRehireOption.setValue(employee.getElegibleRehire());
					layoutButton.setVisible(false);


					HRTerminationForm.this.formalResignationOption.setReadOnly(true);
					HRTerminationForm.this.handoverNoteOption.setReadOnly(true);
					HRTerminationForm.this.handoverPropertiesOption.setReadOnly(true);
					HRTerminationForm.this.medicalCoverageOption.setReadOnly(true);
					HRTerminationForm.this.benefitsPaidOption.setReadOnly(true);
					HRTerminationForm.this.minimunNoticeOption.setReadOnly(true);
					HRTerminationForm.this.finalPaymentOption.setReadOnly(true);
					HRTerminationForm.this.possibleRehireOption.setReadOnly(true);
					}else{
						layoutButton.setVisible(true);

					}
					
					HRTerminationForm.this.tfName.setReadOnly(true);
					HRTerminationForm.this.tfDesignation.setReadOnly(true);
					HRTerminationForm.this.tfEmployeeId.setReadOnly(true);
					HRTerminationForm.this.dfEmployment.setReadOnly(true);	
					HRTerminationForm.this.tfReturn.setReadOnly(true);		
					HRTerminationForm.this.tfRecommendation.setReadOnly(true);
					HRTerminationForm.this.tfManagement.setReadOnly(true);
					HRTerminationForm.this.tfSuggestion.setReadOnly(true);
					HRTerminationForm.this.tfComments.setReadOnly(true);
					HRTerminationForm.this.tfReason.setReadOnly(true);
					HRTerminationForm.this.cbTerminationReason.setReadOnly(true);
					HRTerminationForm.this.rehireOption.setReadOnly(true);
					

					HRTerminationForm.this.managementPreventionOption.setReadOnly(true);
					HRTerminationForm.this.tfLike.setReadOnly(true);
					HRTerminationForm.this.tfDislike.setReadOnly(true);
					HRTerminationForm.this.reapplyGroup.setReadOnly(true);
					HRTerminationForm.this.keepContactOption.setReadOnly(true);
					HRTerminationForm.this.tfPhoneMail.setReadOnly(true);
					HRTerminationForm.this.cbSatisfaction.setReadOnly(true);

				}

			}
		});

		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		Label hrLabel = new Label("HR OBSERVATIONS");
		hrLabel.setStyleName(ValoTheme.LABEL_BOLD);
		hrLabel.setStyleName(ValoTheme.LABEL_COLORED);
		hrLabel.setSizeUndefined();

		FormLayout form1 = new FormLayout(this.tfName, this.tfDesignation, this.tfEmployeeId, this.dfEmployment,
				this.tfReason, this.tfReturn, this.tfRecommendation, this.tfManagement, this.tfSuggestion, this.tfComments,
				this.rehireOption, this.cbTerminationReason, this.managementPreventionOption, this.cbSatisfaction,
				this.tfLike, this.tfDislike, this.reapplyGroup, this.keepContactOption, this.tfPhoneMail);

		form1.setWidth("100%");

		FormLayout form2 = new FormLayout(this.formalResignationOption, this.handoverNoteOption,
				this.handoverPropertiesOption, this.medicalCoverageOption, this.benefitsPaidOption, this.minimunNoticeOption,
				this.finalPaymentOption, this.possibleRehireOption);
		
		form2.setWidth("100%");

		form1.setSpacing(true);
		form2.setSpacing(true);

		layoutButton.setWidth("100%");
		this.btnSubmit.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.btnSubmit.addStyleName(ValoTheme.BUTTON_SMALL);

	this.btnSubmit.addClickListener(this.sendInfoListener);

		layoutButton.addComponent(this.btnSubmit);
		layoutButton.setComponentAlignment(this.btnSubmit, Alignment.TOP_CENTER);

		
		layoutMain.addComponents(form1,hrLabel, form2);
		layoutMain.setComponentAlignment(hrLabel, Alignment.MIDDLE_CENTER);

		content.addComponents(layoutSearch, layoutMain, layoutButton);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

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
