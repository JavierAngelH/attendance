/**
 * Employee.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.components.staff;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.components.admin.EditEmployeeLayout;
import com.app.attendance.model.Employee;
import com.app.attendance.model.EmployeeSS;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.DocumentUploader;
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
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * StaffSelfServiceLayout - 
 *
 */
@SpringComponent
@UIScope
public class StaffSelfServiceLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();
	Employee employee;
	EmployeeSS ess;
    TextField tfAge = new TextField("AGE");
    DateField dfBirthday = new DateField("BIRTHDAY");
    DateField dfEmploymentDate = new DateField("EMPLOYMENT DATE");
    OptionGroup ogSex = new OptionGroup("SEX");
    TextField tfEmail = new TextField("EMAIL");
    TextField tfPosition = new TextField("POSITION");
    ComboBox cbManager = new ComboBox("MANAGER");
    TextField tfIdentification = new TextField("IDENTIFICATION NUMBER");
    TextField tfPhoneContact = new TextField("EMERGENCY NUMBER");
    TextField tfNext = new TextField("NEXT OF KIN");
    TextField tfTaxNumber = new TextField("TAX ID NUMBER");
    TextField tfPension = new TextField("PENSION NUMBER");
    TextField tfExperience = new TextField("YEARS OF EXPERIENCE");
    TextField tfSalaryLevel = new TextField("SALARY LEVEL");
    TextField tfPhoneNumber = new TextField("PHONE NUMBER");

    DocumentUploader receiver = new DocumentUploader();
	Upload uploadCV = new Upload("UPLOAD CV/BIO", this.receiver);
	
    TextArea taTeamMembers = new TextArea("TEAM MEMBERS");
    RichTextArea taBio = new RichTextArea("BIO");
    TextField tfSupervisor = new TextField("SUPERVISOR");
    TextField tfEd = new TextField("ED");


    Upload uploadJobDescription = new Upload("JOB DESCRIPTION", this.receiver);
    
    String cvUrl = "";	
	String jobDescription = "";
	


	BeanItemContainer<Employee> managerBeanContainer = new BeanItemContainer<Employee>(Employee.class);

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

		this.titleLabel.setValue("EMPLOYEE SELF SERVICE");
		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		tfAge.clear();
	    dfBirthday.clear();
	    dfEmploymentDate.clear();
	    ogSex.clear();
	    tfEmail.clear();
	    tfPosition.clear();
	    cbManager.setValue(null);
	    tfIdentification.clear();
	    tfPhoneContact.clear();
	    tfNext.clear();
	    tfTaxNumber.clear();
	    tfPension.clear();
	    tfExperience.clear();
	    tfSalaryLevel.clear();
	    taTeamMembers.clear();
	    taBio.clear();
	    tfSupervisor.clear();
	    tfEd.clear();
	    tfPhoneNumber.clear();
	    
		List<Employee> managerList = this.employeeService.managerList();
	    dfEmploymentDate.setValue(employee.getEmpdate());
	
	    cbManager.removeAllItems();
	    managerBeanContainer.removeAllItems();
		managerBeanContainer.addAll(managerList);

		this.cbManager.setContainerDataSource(managerBeanContainer);
		this.cbManager.setItemCaptionPropertyId("username");

	    for (Employee manager : managerList) {
	    	if(manager.getUsername().equals(employee.getManager())){
			    cbManager.setValue(manager);

	    	}

		}
	    
	    EmployeeSS ess = employeeService.getEss(employee.getId());
	    if(ess!=null){
	    	tfAge.setValue(ess.getAge().toString());
		    dfBirthday.setValue(ess.getBirthday());
		    ogSex.setValue(ess.getSex());
		    tfEmail.setValue(ess.getEmail());
		    tfPosition.setValue(ess.getPosition());
		    tfIdentification.setValue(ess.getIdNumber());
		    tfPhoneContact.setValue(ess.getEmergencyContact());
		    tfNext.setValue(ess.getNok());
		    tfTaxNumber.setValue(ess.getTaxidNumber());
		    tfPension.setValue(ess.getPensionNumber());
		    tfExperience.setValue(ess.getExperienceYears().toString());
		    tfSalaryLevel.setValue(ess.getSalaryLevel());
		    taTeamMembers.setValue(ess.getTeamMembers());
		    taBio.setValue(ess.getBio());
		    tfSupervisor.setValue(ess.getSupervisor());
		    tfEd.setValue(ess.getEd());
		    tfPhoneNumber.setValue(ess.getPhoneNumber());
	    }



	}
	
	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		
		tfAge.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfAge.setWidth("498px");
		this.dfBirthday.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfBirthday.setDateFormat("dd/MM/yyyy");
		dfBirthday.setWidth("498px");

		this.dfEmploymentDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfEmploymentDate.setDateFormat("dd/MM/yyyy");
		dfEmploymentDate.setWidth("498px");

	    ogSex.addStyleName(ValoTheme.OPTIONGROUP_SMALL);
	    ogSex.addItem("Male");
	    ogSex.addItem("Female");
	    ogSex.select("Male");
	    ogSex.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
	    ogSex.setWidth("498px");

	    tfEmail.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfEmail.setWidth("498px");

	    tfPhoneNumber.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfPhoneNumber.setWidth("498px");

	    tfPosition.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfPosition.setWidth("498px");

	    cbManager.setValue(ValoTheme.COMBOBOX_TINY);
	    cbManager.setWidth("498px");

	    tfIdentification.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfIdentification.setWidth("498px");

	    tfPhoneContact.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfPhoneContact.setWidth("498px");

	    tfNext.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfNext.setWidth("498px");

	    tfTaxNumber.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfTaxNumber.setWidth("498px");

	    tfPension.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfPension.setWidth("498px");

	    tfExperience.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfExperience.setWidth("498px");

	    tfSalaryLevel.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfSalaryLevel.setWidth("498px");


	    taTeamMembers.addStyleName(ValoTheme.TEXTAREA_TINY);
	    taTeamMembers.setWidth("498px");

	    taBio.addStyleName(ValoTheme.TEXTAREA_TINY);
	    taBio.setWidth("498px");

	    tfSupervisor.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfSupervisor.setWidth("498px");

	    tfEd.addStyleName(ValoTheme.TEXTFIELD_TINY);
	    tfEd.setWidth("498px");

	
		this.uploadCV.addStyleName(ValoTheme.BUTTON_TINY);
		this.uploadCV.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.uploadCV.setImmediate(true);
		this.uploadCV.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				cvUrl = event.getFilename();
				Notification.show("CV uploaded");
			}
		});
		
		this.uploadJobDescription.addStyleName(ValoTheme.BUTTON_TINY);
		this.uploadJobDescription.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.uploadJobDescription.setImmediate(true);
		this.uploadJobDescription.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				jobDescription = event.getFilename();
				Notification.show("Job Description Uploaded");
			}
		});
		

		

        Label labelform1 = new Label("PERSONAL INFO");
        labelform1.addStyleName(ValoTheme.LABEL_H3);
        labelform1.addStyleName(ValoTheme.LABEL_COLORED);
		
		
	    
     
		

        Label labelform2 = new Label("CONTACT INFO");
        labelform2.addStyleName(ValoTheme.LABEL_H3);
        labelform2.addStyleName(ValoTheme.LABEL_COLORED);

		
		   Label labelform3 = new Label("ADDITIONAL INFO");
		   labelform3.addStyleName(ValoTheme.LABEL_H3);
		   labelform3.addStyleName(ValoTheme.LABEL_COLORED);
		
		   Label labelform4 = new Label("ORGANIZATIONAL CONTACT");
		   labelform4.addStyleName(ValoTheme.LABEL_H3);
		   labelform4.addStyleName(ValoTheme.LABEL_COLORED);


	FormLayout form1 = new FormLayout(labelform1, this.tfAge, this.dfBirthday, this.dfEmploymentDate, this.ogSex,
			labelform2, this.tfEmail, this.tfPhoneNumber, this.tfPosition, this.cbManager, this.tfPhoneContact, tfNext, tfTaxNumber,
			tfPension, tfExperience, tfSalaryLevel, uploadCV, 
			labelform3, this.taTeamMembers, this.taBio, labelform4, this.tfSupervisor, tfEd, this.uploadJobDescription);

		form1.setSpacing(true);
		

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);

		btnSave.addClickListener(this.sendInfoListener);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(form1);
		content.addComponents(layoutMain, layoutButton);
		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutButton, Alignment.TOP_CENTER);
		content.setExpandRatio(layoutMain, 3);



		return content;

	}
	
	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try{
				
				EmployeeSS es1 = new EmployeeSS();
				es1.setAge(Integer.parseInt(tfAge.getValue()));
				es1.setBio(taBio.getValue());
				es1.setBirthday(dfBirthday.getValue());
				es1.setCvUrl(cvUrl);
				es1.setEd(tfEd.getValue());
				es1.setEmail(tfEmail.getValue());
				es1.setEmergencyContact(tfPhoneContact.getValue());
				es1.setExperienceYears(Integer.parseInt(tfExperience.getValue()));
				es1.setId(employee.getId());
				es1.setJobDescriptionUrl(jobDescription);
				es1.setNok(tfNext.getValue());
				es1.setPensionNumber(tfPension.getValue());
				es1.setPhoneNumber(tfPhoneNumber.getValue());
				es1.setPosition(tfPosition.getValue());
				es1.setSalaryLevel(tfSalaryLevel.getValue());
				es1.setSex(ogSex.getValue().toString());
				es1.setSupervisor(tfSupervisor.getValue());
				es1.setTaxidNumber(tfTaxNumber.getValue());
				es1.setTeamMembers(taTeamMembers.getValue());
				es1.setIdNumber(tfIdentification.getValue());
				
				if(ess == null)
					employeeService.insertEss(es1);
				else
					employeeService.updateEss(es1);
				
				employee.setEmpdate(dfEmploymentDate.getValue());
				Employee manager = (Employee) cbManager.getValue();
				if (manager != null) {
					employee.setManager(manager.getId());
				}
				
				employeeService.updateEmployee(employee);
			Notification.show("Information saved succesfully.");
			}catch(Exception e){
String error = e.getMessage();
System.out.println(error);
	Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

	

			}
		}
	};


}
