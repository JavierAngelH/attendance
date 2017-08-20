/** 
 * Created by JavierAngelH
 */

package com.app.attendance.component.hr;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.model.EmployeeSS;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.DocumentUploader;
import com.app.attendance.utils.Utilities;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
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
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRSelfServiceLayout - 
 *
 */
@SpringComponent
@UIScope
public class HRSelfServiceLayout extends Panel {
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
    TextField cbManager = new TextField("MANAGER");
    TextField tfIdentification = new TextField("IDENTIFICATION NUMBER");
    TextField tfPhoneContact = new TextField("EMERGENCY NUMBER");
    TextField tfNext = new TextField("NEXT OF KIN");
    TextField tfTaxNumber = new TextField("TAX ID NUMBER");
    TextField tfPension = new TextField("PENSION NUMBER");
    TextField tfExperience = new TextField("YEARS OF EXPERIENCE");
    TextField tfSalaryLevel = new TextField("SALARY LEVEL");
    TextField tfPhoneNumber = new TextField("PHONE NUMBER");

    DocumentUploader receiver = new DocumentUploader();
	Button btnCV = new Button("CV/BIO");
	
    TextArea taTeamMembers = new TextArea("TEAM MEMBERS");
    RichTextArea taBio = new RichTextArea("BIO");
    TextField tfSupervisor = new TextField("SUPERVISOR");
    TextField tfEd = new TextField("ED");

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");

	Button btnJobDescription = new Button("JOB DESCRIPTION");
    
    String cvUrl = "";	
	String jobDescription = "";
	
	HorizontalLayout layoutPicture = new HorizontalLayout();


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
		

		tfAge.setReadOnly(false);
	    dfBirthday.setReadOnly(false);
	    dfEmploymentDate.setReadOnly(false);
	    ogSex.setReadOnly(false);
	    tfEmail.setReadOnly(false);
	    cbManager.setReadOnly(false);
	    tfPosition.setReadOnly(false);
	    tfIdentification.setReadOnly(false);
	    tfPhoneContact.setReadOnly(false);
	    tfNext.setReadOnly(false);
	    tfTaxNumber.setReadOnly(false);
	    tfPension.setReadOnly(false);
	    tfExperience.setReadOnly(false);
	    tfSalaryLevel.setReadOnly(false);
	    taTeamMembers.setReadOnly(false);
	    taBio.setReadOnly(false);
	    tfSupervisor.setReadOnly(false);
	    tfEd.setReadOnly(false);
	    tfPhoneNumber.setReadOnly(false); 
		btnCV.setEnabled(true);
	    btnJobDescription.setEnabled(true);
		tfAge.clear();
	    dfBirthday.clear();
	    dfEmploymentDate.clear();
	    ogSex.clear();
	    tfEmail.clear();
	    cbManager.clear();
	    tfPosition.clear();
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
	    
	    
		tfAge.setReadOnly(true);
	    dfBirthday.setReadOnly(true);
	    dfEmploymentDate.setReadOnly(true);
	    ogSex.setReadOnly(true);
	    tfEmail.setReadOnly(true);
	    cbManager.setReadOnly(true);
	    tfPosition.setReadOnly(true);
	    tfIdentification.setReadOnly(true);
	    tfPhoneContact.setReadOnly(true);
	    tfNext.setReadOnly(true);
	    tfTaxNumber.setReadOnly(true);
	    tfPension.setReadOnly(true);
	    tfExperience.setReadOnly(true);
	    tfSalaryLevel.setReadOnly(true);
	    taTeamMembers.setReadOnly(true);
	    taBio.setReadOnly(true);
	    tfSupervisor.setReadOnly(true);
	    tfEd.setReadOnly(true);
	    tfPhoneNumber.setReadOnly(true); 

		btnCV.setEnabled(false);
	    btnJobDescription.setEnabled(false);
	    
	
		this.cbEmployees.removeAllItems();

		BeanItemContainer<EmployeeSS> essBeanContainer = new BeanItemContainer<EmployeeSS>(EmployeeSS.class);

		List<EmployeeSS> employeeList = this.employeeService.getESSList();

		essBeanContainer.addAll(employeeList);

		this.cbEmployees.setContainerDataSource(essBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("name");
	}
	
	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);
		tfAge.setNullRepresentation("");
	    tfEmail.setNullRepresentation("");
	    cbManager.setNullRepresentation("");
	    tfPosition.setNullRepresentation("");
	    tfIdentification.setNullRepresentation("");
	    tfPhoneContact.setNullRepresentation("");
	    tfNext.setNullRepresentation("");
	    tfTaxNumber.setNullRepresentation("");
	    tfPension.setNullRepresentation("");
	    tfExperience.setNullRepresentation("");
	    tfSalaryLevel.setNullRepresentation("");
	    taTeamMembers.setNullRepresentation("");
	    taBio.setNullRepresentation("");
	    tfSupervisor.setNullRepresentation("");
	    tfEd.setNullRepresentation("");
	    tfPhoneNumber.setNullRepresentation("");
	    
		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				ess = (EmployeeSS) cbEmployees.getValue();

				if (ess != null) {
					employee = employeeService.getEmployee(ess.getId());

					tfAge.setReadOnly(false);
				    dfBirthday.setReadOnly(false);
				    dfEmploymentDate.setReadOnly(false);
				    ogSex.setReadOnly(false);
				    tfEmail.setReadOnly(false);
				    cbManager.setReadOnly(false);
				    tfPosition.setReadOnly(false);
				    tfIdentification.setReadOnly(false);
				    tfPhoneContact.setReadOnly(false);
				    tfNext.setReadOnly(false);
				    tfTaxNumber.setReadOnly(false);
				    tfPension.setReadOnly(false);
				    tfExperience.setReadOnly(false);
				    tfSalaryLevel.setReadOnly(false);
				    taTeamMembers.setReadOnly(false);
				    taBio.setReadOnly(false);
				    tfSupervisor.setReadOnly(false);
				    tfEd.setReadOnly(false);
				    tfPhoneNumber.setReadOnly(false); 
			    	btnJobDescription.setEnabled(false);
			    	btnCV.setEnabled(false);

					
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
				    dfEmploymentDate.setValue(employee.getEmpdate());
				    cbManager.setValue(employee.getManager());
			
					tfAge.setReadOnly(true);
				    dfBirthday.setReadOnly(true);
				    dfEmploymentDate.setReadOnly(true);
				    ogSex.setReadOnly(true);
				    tfEmail.setReadOnly(true);
				    cbManager.setReadOnly(true);
				    tfPosition.setReadOnly(true);
				    tfIdentification.setReadOnly(true);
				    tfPhoneContact.setReadOnly(true);
				    tfNext.setReadOnly(true);
				    tfTaxNumber.setReadOnly(true);
				    tfPension.setReadOnly(true);
				    tfExperience.setReadOnly(true);
				    tfSalaryLevel.setReadOnly(true);
				    taTeamMembers.setReadOnly(true);
				    taBio.setReadOnly(true);
				    tfSupervisor.setReadOnly(true);
				    tfEd.setReadOnly(true);
				    tfPhoneNumber.setReadOnly(true); 
				    
				    if(!ess.getCvUrl().isEmpty())
				    	btnCV.setEnabled(true);
				 
				    if(!ess.getJobDescriptionUrl().isEmpty())
				    	btnJobDescription.setEnabled(true);
				    layoutPicture.removeAllComponents();
				    if(employee.getPictureURL()!=null){ 	
				 
					FileResource resource = new FileResource(new File(Utilities.imagesPath + employee.getPictureURL()));
				    final Image image = new Image(null, resource);
				    layoutPicture.addComponent(image);
				    layoutPicture.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
				    }
				}

			}
		});

		
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

	
		this.btnCV.addStyleName(ValoTheme.BUTTON_TINY);
		this.btnCV.setImmediate(true);
		this.btnCV.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Embedded object = new Embedded("", new FileResource(new File(Utilities.documentsPath + ess.getCvUrl())));
				Window subWindow = new Window();
				subWindow.setSizeFull();
				subWindow.setModal(true);
				subWindow.setCaption(null);
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(false);
				subContent.setSizeFull();
				subWindow.setContent(subContent);
				object.setSizeFull();
				object.setMimeType("application/pdf");
				object.setType(Embedded.TYPE_BROWSER);
				subContent.addComponent(object);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		});
		
		this.btnJobDescription.addStyleName(ValoTheme.BUTTON_TINY);
		this.btnJobDescription.setImmediate(true);
		this.btnJobDescription.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Embedded object = new Embedded("", new FileResource(new File(Utilities.documentsPath + ess.getJobDescriptionUrl())));
				Window subWindow = new Window();
				subWindow.setSizeFull();
				subWindow.setModal(true);
				subWindow.setCaption(null);
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(false);
				subContent.setSizeFull();
				subWindow.setContent(subContent);
				object.setSizeFull();
				object.setMimeType("application/pdf");
				object.setType(Embedded.TYPE_BROWSER);
				subContent.addComponent(object);

				// Center it in the browser window
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);
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

		   HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
			layoutSearch.setSizeUndefined();
			
			layoutPicture.setSizeUndefined();
			layoutPicture.setWidth("100%");
		
	FormLayout form1 = new FormLayout(layoutPicture, labelform1, this.tfAge, this.dfBirthday, this.dfEmploymentDate, this.ogSex,
			labelform2, this.tfEmail, this.tfPhoneNumber, this.tfPosition, this.cbManager, this.tfPhoneContact, tfNext, tfTaxNumber,
			tfPension, tfExperience, tfSalaryLevel, btnCV, 
			labelform3, this.taTeamMembers, this.taBio, labelform4, this.tfSupervisor, tfEd, this.btnJobDescription);

		form1.setSpacing(true);
		

	
		layoutMain.addComponents(form1);
		content.addComponents(layoutSearch, layoutMain);
		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setExpandRatio(layoutMain, 3);



		return content;

	}

}
