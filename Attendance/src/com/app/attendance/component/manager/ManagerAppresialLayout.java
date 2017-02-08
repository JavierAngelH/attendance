/**
 * ManagerAppresialLayout.java Created: Jan 4, 2017 JavierAngelH
 */

package com.app.attendance.component.manager;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Employee;
import com.app.attendance.model.Performance;
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
 * ManagerAppresialLayout -
 *
 */

@SpringComponent
@UIScope
public class ManagerAppresialLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	@Autowired
	EmployeeService employeeService;

	TextField tfName = new TextField("NAME");

	TextField tfDesignation = new TextField("DEPARTMENT");

	TextField tfLocation = new TextField("LOCATION");

	TextArea tfObjective1 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 1");

	TextArea tfObjective2 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 2");

	TextArea tfObjective3 = new TextArea("ACHIEVEMENT RESULTS OF OBJECTIVE 3");

	TextArea tfManagerObjective1 = new TextArea("MANAGER'S COMMENTS ON OBJECTIVE 1");

	TextArea tfManagerObjective2 = new TextArea("MANAGER'S COMMENTS ON OBJECTIVE 2");

	TextArea tfManagerObjective3 = new TextArea("MANAGER'S COMMENTS ON OBJECTIVE 3");

	ComboBox cbEmployees = new ComboBox("SELECT EMPLOYEE");

	TextField tfTechnicalCompetencies = new TextField("TECHNICAL COMPETENCIES (Maximun Score 60%)");

	TextField tfNonTechnicalCompetencies = new TextField("NON-TECHNICAL COMPETENCIES (Maximun Score 40%)");

	TextField tfTotalScore = new TextField("TOTAL SCORE (Maximun Score 100%)");

	ComboBox cbRating = new ComboBox("FINAL RATING CATEGORY");

	TextArea tfManagerComment = new TextArea();

	Employee employee;
	
	Performance selectedPerformance;

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
				ManagerAppresialLayout.this.tfManagerObjective1.validate();
				ManagerAppresialLayout.this.tfManagerObjective2.validate();
				ManagerAppresialLayout.this.tfManagerObjective3.validate();
				tfNonTechnicalCompetencies.validate();
				tfTechnicalCompetencies.validate();
				tfTotalScore.validate();
				tfManagerComment.validate();
				cbRating.validate();
				
				
				selectedPerformance.setManagerComment1(tfManagerObjective1.getValue());
				selectedPerformance.setManagerComment2(tfManagerObjective2.getValue());
				selectedPerformance.setManagerComment3(tfManagerObjective3.getValue());
				selectedPerformance.setNonTechnicalScore(Double.parseDouble(tfNonTechnicalCompetencies.getValue()));
				selectedPerformance.setTechnicalScore(Double.parseDouble(tfTechnicalCompetencies.getValue()));
				selectedPerformance.setTotalScore(Double.parseDouble(tfTotalScore.getValue()));
				selectedPerformance.setSupervisorComment(tfManagerComment.getValue());
				selectedPerformance.setRatingCategory(cbRating.getValue().toString());
				employeeService.updatePerformance(selectedPerformance);
				Notification.show("Information saved succesfully.");
				
			} catch (Exception e) {
				System.out.println(e.getMessage());

				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

	public void setData() {

		this.employee = (Employee) VaadinSession.getCurrent().getAttribute("employee");
		this.titleLabel.setValue("STAFF PERFORMANCE APPRESIAL");

		this.tfName.setReadOnly(false);
		this.tfDesignation.setReadOnly(false);
		this.tfLocation.setReadOnly(false);
		this.tfObjective1.setReadOnly(false);
		this.tfObjective2.setReadOnly(false);
		this.tfObjective3.setReadOnly(false);
		this.tfTotalScore.setReadOnly(false);
		
		this.tfName.clear();
		this.tfDesignation.clear();
		this.tfLocation.clear();
		this.tfObjective1.clear();
		this.tfObjective2.clear();
		this.tfObjective3.clear();
		this.tfManagerComment.clear();
		this.tfManagerObjective1.clear();
		this.tfManagerObjective2.clear();
		this.tfManagerObjective3.clear();
		this.tfTechnicalCompetencies.clear();
		this.tfTotalScore.clear();
		this.tfNonTechnicalCompetencies.clear();

		this.tfName.setReadOnly(true);
		this.tfDesignation.setReadOnly(true);
		this.tfLocation.setReadOnly(true);

		this.tfObjective1.setReadOnly(true);
		this.tfObjective2.setReadOnly(true);
		this.tfObjective3.setReadOnly(true);
		this.tfTotalScore.setReadOnly(true);

		this.cbRating.setValue("");

		this.cbEmployees.removeAllItems();

		BeanItemContainer<Performance> performanceBeanContainer = new BeanItemContainer<Performance>(Performance.class);

		List<Performance> performanceList = this.employeeService
				.getEmployeePerformancesByManager(this.employee.getId());

		performanceBeanContainer.addAll(performanceList);

		this.cbEmployees.setContainerDataSource(performanceBeanContainer);
		this.cbEmployees.setItemCaptionPropertyId("name");

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);

		VerticalLayout layoutMain = new VerticalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

		layoutMain.setMargin(new MarginInfo(true, true, true, true));
		layoutMain.setSpacing(true);

		layoutMain.setWidth("80%");
		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfDesignation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);

		this.tfObjective1.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfObjective2.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfObjective3.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfManagerObjective1.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfManagerObjective2.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.tfManagerObjective3.addStyleName(ValoTheme.TEXTAREA_TINY);

		this.cbEmployees.addStyleName(ValoTheme.COMBOBOX_TINY);

		this.tfManagerComment.setNullRepresentation("");
		this.tfManagerObjective1.setNullRepresentation("");
		this.tfManagerObjective2.setNullRepresentation("");
		this.tfManagerObjective3.setNullRepresentation("");
		this.tfTechnicalCompetencies.setNullRepresentation("");
		this.tfTotalScore.setNullRepresentation("");
		this.tfNonTechnicalCompetencies.setNullRepresentation("");
		
	tfTechnicalCompetencies.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				String technical = tfTechnicalCompetencies.getValue();
				if(!technical.isEmpty())
				try{
					Double technicalDouble = Double.parseDouble(technical);
					if(technicalDouble > 60){
						Notification.show("The max. value is 60", Notification.Type.WARNING_MESSAGE);	
						tfTechnicalCompetencies.setValue("");

					}
					
				}catch(Exception e){
					tfTechnicalCompetencies.setValue("");
Notification.show("Invalid Number", Notification.Type.WARNING_MESSAGE);	
}
				
			}
		});

		tfNonTechnicalCompetencies.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				String technical = tfTechnicalCompetencies.getValue();
				String nonTechnical = tfNonTechnicalCompetencies.getValue();
				try{
					Double technicalDouble = Double.parseDouble(technical);
					Double nonTechnicalDouble = Double.parseDouble(nonTechnical);
					if(nonTechnicalDouble > 40){
						Notification.show("The max. value is 40", Notification.Type.WARNING_MESSAGE);	
						tfNonTechnicalCompetencies.setValue("");

					}
					
					
					tfTotalScore.setReadOnly(false);
					Double score = technicalDouble + nonTechnicalDouble;
					tfTotalScore.setValue(score.toString());
					tfTotalScore.setReadOnly(true);

				}catch(Exception e){

					tfNonTechnicalCompetencies.setValue("");
Notification.show("Invalid Number", Notification.Type.WARNING_MESSAGE);	
				}
				
			}
		});
		
		this.cbEmployees.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				selectedPerformance = (Performance) ManagerAppresialLayout.this.cbEmployees.getValue();

				if (selectedPerformance != null) {

					ManagerAppresialLayout.this.tfName.setReadOnly(false);
					ManagerAppresialLayout.this.tfDesignation.setReadOnly(false);
					ManagerAppresialLayout.this.tfLocation.setReadOnly(false);
					ManagerAppresialLayout.this.tfObjective1.setReadOnly(false);
					ManagerAppresialLayout.this.tfObjective2.setReadOnly(false);
					ManagerAppresialLayout.this.tfObjective3.setReadOnly(false);
					ManagerAppresialLayout.this.tfTotalScore.setReadOnly(false);


					ManagerAppresialLayout.this.tfName.setValue(selectedPerformance.getName());
					ManagerAppresialLayout.this.tfDesignation.setValue(selectedPerformance.getDepartment());
					ManagerAppresialLayout.this.tfLocation.setValue(selectedPerformance.getLocation());
					ManagerAppresialLayout.this.tfObjective1.setValue(selectedPerformance.getObjective1());
					ManagerAppresialLayout.this.tfObjective2.setValue(selectedPerformance.getObjective2());
					ManagerAppresialLayout.this.tfObjective3.setValue(selectedPerformance.getObjective3());
					ManagerAppresialLayout.this.tfManagerComment.setValue(selectedPerformance.getSupervisorComment());
					ManagerAppresialLayout.this.tfManagerObjective1.setValue(selectedPerformance.getManagerComment1());
					ManagerAppresialLayout.this.tfManagerObjective2.setValue(selectedPerformance.getManagerComment2());
					ManagerAppresialLayout.this.tfManagerObjective3.setValue(selectedPerformance.getManagerComment3());
					
					if(selectedPerformance.getTechnicalScore()!=null)
					ManagerAppresialLayout.this.tfTechnicalCompetencies
							.setValue(selectedPerformance.getTechnicalScore().toString());
					
					if(selectedPerformance.getTotalScore()!=null)
					ManagerAppresialLayout.this.tfTotalScore.setValue(selectedPerformance.getTotalScore().toString());
					
					if(selectedPerformance.getNonTechnicalScore()!=null)
						ManagerAppresialLayout.this.tfNonTechnicalCompetencies
							.setValue(selectedPerformance.getNonTechnicalScore().toString());
					ManagerAppresialLayout.this.cbRating.setValue(selectedPerformance.getRatingCategory());
					ManagerAppresialLayout.this.tfName.setReadOnly(true);
					ManagerAppresialLayout.this.tfDesignation.setReadOnly(true);
					ManagerAppresialLayout.this.tfLocation.setReadOnly(true);
					ManagerAppresialLayout.this.tfObjective1.setReadOnly(true);
					ManagerAppresialLayout.this.tfObjective2.setReadOnly(true);
					ManagerAppresialLayout.this.tfObjective3.setReadOnly(true);
					ManagerAppresialLayout.this.tfTotalScore.setReadOnly(true);


				}

			}
		});

		this.cbRating.setWidth("300px");

		this.tfTechnicalCompetencies.setWidth("300px");
		this.tfNonTechnicalCompetencies.setWidth("300px");
		this.tfTotalScore.setWidth("300px");

		this.cbRating.setInvalidAllowed(false);
		this.cbRating.addItem("Did not Meet Minimal Performance Expectation");
		this.cbRating.addItem("Significantly Missed Performance Objectives");
		this.cbRating.addItem("Met Performance Objectives");
		this.cbRating.addItem("Fully Met Performance Objectives");
		this.cbRating.addItem("Exceeded Performance Objectives");

		this.tfManagerObjective1.setRequired(true);
		this.tfManagerObjective2.setRequired(true);
		this.tfManagerObjective3.setRequired(true);
		cbRating.setRequired(true);
		this.tfTechnicalCompetencies.setRequired(true);
		this.tfNonTechnicalCompetencies.setRequired(true);
		this.tfTotalScore.setRequired(true);
		this.tfManagerComment.setRequired(true);


		this.tfObjective1.setWidth("300px");
		this.tfObjective2.setWidth("300px");
		this.tfObjective3.setWidth("300px");

		this.tfManagerObjective1.setWidth("300px");
		this.tfManagerObjective2.setWidth("300px");
		this.tfManagerObjective3.setWidth("300px");

		HorizontalLayout layoutSearch = new HorizontalLayout(this.cbEmployees);
		layoutSearch.setSizeUndefined();

		HorizontalLayout identificationLayout = new HorizontalLayout(this.tfName, this.tfDesignation, this.tfLocation);
		identificationLayout.setSizeFull();

		identificationLayout.setComponentAlignment(this.tfName, Alignment.MIDDLE_CENTER);
		identificationLayout.setComponentAlignment(this.tfDesignation, Alignment.MIDDLE_CENTER);
		identificationLayout.setComponentAlignment(this.tfLocation, Alignment.MIDDLE_CENTER);

		HorizontalLayout layout1 = new HorizontalLayout(this.tfObjective1, this.tfManagerObjective1);
		HorizontalLayout layout2 = new HorizontalLayout(this.tfObjective2, this.tfManagerObjective2);
		HorizontalLayout layout3 = new HorizontalLayout(this.tfObjective3, this.tfManagerObjective3);

		layout1.setComponentAlignment(this.tfObjective1, Alignment.MIDDLE_CENTER);
		layout1.setComponentAlignment(this.tfManagerObjective1, Alignment.MIDDLE_CENTER);

		layout2.setComponentAlignment(this.tfObjective2, Alignment.MIDDLE_CENTER);
		layout2.setComponentAlignment(this.tfManagerObjective2, Alignment.MIDDLE_CENTER);

		layout3.setComponentAlignment(this.tfObjective3, Alignment.MIDDLE_CENTER);
		layout3.setComponentAlignment(this.tfManagerObjective3, Alignment.MIDDLE_CENTER);

		layout1.setSizeFull();
		layout1.setSpacing(true);

		layout2.setSizeFull();
		layout2.setSpacing(true);

		layout3.setSizeFull();
		layout3.setSpacing(true);

		VerticalLayout layoutComment = new VerticalLayout(this.tfManagerComment);
		layoutComment.setCaption("SUPERVISOR / MANAGER'S COMMENT");
		layoutComment.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutComment.addStyleName(ValoTheme.LAYOUT_CARD);
		this.tfManagerComment.setSizeFull();
		layoutComment.setWidth("80%");

		FormLayout form = new FormLayout(this.cbRating, this.tfTechnicalCompetencies, this.tfNonTechnicalCompetencies,
				this.tfTotalScore);

		VerticalLayout layoutSummary = new VerticalLayout(form);

		layoutSummary.setCaption("SUPERVISOR’S OVERALL SUMMARY");
		layoutSummary.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutSummary.addStyleName(ValoTheme.LAYOUT_CARD);
		form.setMargin(new MarginInfo(true, true, true, true));
		form.setSpacing(true);
		form.setSizeFull();
		layoutSummary.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		layoutSummary.setWidth("80%");

		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setWidth("100%");
		Button btnSave = new Button("Save");
		btnSave.addClickListener(this.sendInfoListener);
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addStyleName(ValoTheme.BUTTON_SMALL);

		layoutButton.addComponent(btnSave);
		layoutButton.setComponentAlignment(btnSave, Alignment.TOP_CENTER);

		layoutMain.addComponents(identificationLayout, layout1, layout2, layout3);
		content.addComponents(layoutSearch, layoutMain, layoutSummary, layoutComment, layoutButton);
		content.setComponentAlignment(layoutSearch, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutMain, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutSummary, Alignment.TOP_CENTER);
		content.setComponentAlignment(layoutComment, Alignment.TOP_CENTER);
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
