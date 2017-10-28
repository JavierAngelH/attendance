/** 
 * Created by JavierAngelH
 */

package com.app.attendance.component.ed;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.component.hr.HRProbationLayout;
import com.app.attendance.model.Employee;
import com.app.attendance.model.PerformanceReview;
import com.app.attendance.model.Probation;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

/**
 * EDProbationLayout - 
 *
 */
@SpringComponent
@UIScope
public class EDProbationLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	TextField tfnamestaff = new TextField("NAME OF STAFF");
	DateField dfhire = new DateField("DATE OF HIRE");
	TextField tflocation = new TextField("LOCATION");
	TextField tfPosition = new TextField("POSITION");
	ComboBox cbrecommendation = new ComboBox("RECOMMENDATION");
	TextField tfperfromance = new TextField("PERFROMANCE APPERSIAL HAS BEEN CONDUCTED");
	TextField tfscore = new TextField("SCORE ON PERFROMANCE APPRISAL");
	TextField tfpension = new TextField("HAS EMPLOYEE BEEN ENROOLED FOR PENSION ");
	TextField tfmedical = new TextField("HAS EMPLOYEE BEEN ENROLED FOR MEDICAL COVERAGE");
	TextField tfinsure = new TextField("HAS EMPLOYEE BEEN ENROLLED FOR INSURANCE COVERAGE");
	TextField tfnsitf = new TextField("HAS EMPLOYEE BEEN ENROLLED FOR NSITF");
	TextField tfletter = new TextField("HAS A CONFRIMATION LETTER BEEN ISSUED TO STAFF.");
	
	ComboBox cbEmployees = new ComboBox("SELECT PROBATION STAFF");
	Probation selectedProbation;

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
		this.titleLabel.setValue("PROBATION SECTION");

		this.tfnamestaff.setReadOnly(false);
		this.dfhire.setReadOnly(false);
		this.tflocation.setReadOnly(false);
		this.tfPosition.setReadOnly(false);
		this.cbrecommendation.setReadOnly(false);
		this.tfperfromance.setReadOnly(false);
		this.tfscore.setReadOnly(false);
		this.tfpension.setReadOnly(false);
		this.tfmedical.setReadOnly(false);
		this.tfinsure.setReadOnly(false);
		this.tfnsitf.setReadOnly(false);
		this.tfletter.setReadOnly(false);
		
		this.tfnamestaff.clear();
		this.dfhire.clear();
		this.tflocation.clear();
		this.tfPosition.clear();
		this.cbrecommendation.clear();
		this.tfperfromance.clear();
		this.tfscore.clear();
		this.tfpension.clear();
		this.tfmedical.clear();
		this.tfinsure.clear();
		this.tfnsitf.clear();
		this.tfletter.clear();
		this.cbEmployees.removeAllItems();
		BeanItemContainer<Probation> probationBeanContainer = new BeanItemContainer<Probation>(Probation.class);

		List<Probation> probationList = this.employeeService.getPendingProbations();

		probationBeanContainer.addAll(probationList);

		this.cbEmployees.setContainerDataSource(probationBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("nameOfStaff");
		
		this.tfnamestaff.setReadOnly(true);
		this.dfhire.setReadOnly(true);
		this.tflocation.setReadOnly(true);
		this.tfPosition.setReadOnly(true);
		this.cbrecommendation.setReadOnly(true);
		this.tfperfromance.setReadOnly(true);
		this.tfscore.setReadOnly(true);
		this.tfpension.setReadOnly(true);
		this.tfmedical.setReadOnly(true);
		this.tfinsure.setReadOnly(true);
		this.tfnsitf.setReadOnly(true);
		this.tfletter.setReadOnly(true);
		
		
	}

	private Component buildContent() {
		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				selectedProbation = (Probation) cbEmployees.getValue();

				if (selectedProbation != null) {
					tfnamestaff.setReadOnly(false);
					dfhire.setReadOnly(false);
					tflocation.setReadOnly(false);
					tfPosition.setReadOnly(false);
					cbrecommendation.setReadOnly(false);
					tfperfromance.setReadOnly(false);
					tfscore.setReadOnly(false);
					tfpension.setReadOnly(false);
					tfmedical.setReadOnly(false);
					tfinsure.setReadOnly(false);
					tfnsitf.setReadOnly(false);
					tfletter.setReadOnly(false);

					tfnamestaff.setValue(selectedProbation.getNameOfStaff());
					dfhire.setValue(selectedProbation.getDateOfHire());
					tflocation.setValue(selectedProbation.getLocation());
					tfPosition.setValue(selectedProbation.getPosition());
					cbrecommendation.setValue(selectedProbation.getRecommendation());
					tfperfromance.setValue(selectedProbation.getPerformanceConducted());
					tfscore.setValue(selectedProbation.getScorePerfromance());
					tfpension.setValue(selectedProbation.getEnrollPension());
					tfmedical.setValue(selectedProbation.getEnrollMedical());
					tfinsure.setValue(selectedProbation.getEnrollInsurance());
					tfnsitf.setValue(selectedProbation.getEnrollNsitf());
					tfletter.setValue(selectedProbation.getConfrimLetter());
					
					tfnamestaff.setReadOnly(true);
					dfhire.setReadOnly(true);
					tflocation.setReadOnly(true);
					tfPosition.setReadOnly(true);
					cbrecommendation.setReadOnly(true);
					tfperfromance.setReadOnly(true);
					tfscore.setReadOnly(true);
					tfpension.setReadOnly(true);
					tfmedical.setReadOnly(true);
					tfinsure.setReadOnly(true);
					tfnsitf.setReadOnly(true);
					tfletter.setReadOnly(true);					
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

		this.tfnamestaff.setNullRepresentation("");
		this.tflocation.setNullRepresentation("");
		this.tfPosition.setNullRepresentation("");
		this.tfperfromance.setNullRepresentation("");
		this.tfscore.setNullRepresentation("");
		this.tfpension.setNullRepresentation("");
		this.tfmedical.setNullRepresentation("");
		this.tfinsure.setNullRepresentation("");
		this.tfnsitf.setNullRepresentation("");
		this.tfletter.setNullRepresentation("");

		this.tfnamestaff.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfnamestaff.setWidth("300px");
		this.tflocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tflocation.setWidth("300px");
		this.tfPosition.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfPosition.setWidth("300px");
		this.tfperfromance.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfperfromance.setWidth("300px");
		this.tfscore.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfscore.setWidth("300px");
		this.tfpension.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfpension.setWidth("300px");
		this.tfmedical.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfmedical.setWidth("300px");
		this.tfinsure.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfinsure.setWidth("300px");
		this.tfnsitf.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfnsitf.setWidth("300px");
		this.tfletter.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfletter.setWidth("300px");
		this.dfhire.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfhire.setWidth("300px");

		this.cbrecommendation.addItem("confirm employment");
		this.cbrecommendation.addItem("Extend Probation");
		this.cbrecommendation.select("Disengagement");
		this.cbrecommendation.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbrecommendation.setWidth("300px");
		
		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		FormLayout form1 = new FormLayout(this.tfnamestaff, this.dfhire, this.tflocation, this.tfPosition,
				this.cbrecommendation, this.tfperfromance, this.tfscore, this.tfpension, this.tfmedical, this.tfinsure,
				this.tfnsitf, this.tfletter);

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
		content.addComponent(layoutSearch);
		content.addComponent(layoutMain);
		content.addComponent(layoutButton);

		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);

		content.setExpandRatio(layoutMain, 3);

		return content;

	}

	ClickListener sendInfoListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			try {
				employeeService.approveProbation(selectedProbation.getId());

				Notification.show("Probation Approved");
				setData();
			} catch (Exception e) {
				String error = e.getMessage();
				System.out.println(error);
				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

}