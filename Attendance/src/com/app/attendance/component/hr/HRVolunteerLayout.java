/** 
 * Created by JavierAngelH
 */

package com.app.attendance.component.hr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Probation;
import com.app.attendance.model.Volunteer;
import com.app.attendance.service.EmployeeService;
import com.app.attendance.utils.AdvertUploader;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRVolunteerLayout - 
 *
 */
@SpringComponent
@UIScope
public class HRVolunteerLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	AdvertUploader receiver = new AdvertUploader();
	Upload btnUpload = new Upload("UPLOAD GOOGLE FORM", this.receiver);
	TextField tfName = new TextField("NAME");
	TextField tfSocioDemographics = new TextField("SOCIO-DEMOGRAPHICS");
	TextField tfEducationalBackground = new TextField("EDUCATIONAL BACKGROUND");
	TextField tfAreaOfInterest = new TextField("AREA OF INTEREST");
	TextField tfOrganizationPlacement = new TextField("ORGANIZATION PLACEMENT");
	TextField tfObjectives = new TextField("OBJECTIVES");
	TextField tfDurations = new TextField("DURATIONS");
	TextField tfWorkDays = new TextField("WORK DAYS");
	TextField tfLocation = new TextField("LOCATION");
	TextField tfUnitOfInterest = new TextField("UNIT OF INTEREST");
	TextArea taSupervisorComment = new TextArea("SUPERVISOR COMMENT");
	TextArea taEdComment = new TextArea("ED COMMENT");
	
	String upload = "";

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
		this.titleLabel.setValue("VOLUNTEER SUMMARY");

		this.tfSocioDemographics.clear();
		this.tfEducationalBackground.clear();
		this.tfName.clear();
		this.tfAreaOfInterest.clear();
		this.tfOrganizationPlacement.clear();
		this.tfObjectives.clear();
		this.tfDurations.clear();
		this.tfWorkDays.clear();
		this.tfLocation.clear();
		this.tfUnitOfInterest.clear();
		taSupervisorComment.clear();
		taEdComment.clear();
	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		this.tfSocioDemographics.setNullRepresentation("");
		this.tfEducationalBackground.setNullRepresentation("");
		this.tfName.setNullRepresentation("");
		this.tfAreaOfInterest.setNullRepresentation("");
		this.tfOrganizationPlacement.setNullRepresentation("");
		this.tfObjectives.setNullRepresentation("");
		this.tfDurations.setNullRepresentation("");
		this.tfWorkDays.setNullRepresentation("");
		this.tfLocation.setNullRepresentation("");
		this.tfUnitOfInterest.setNullRepresentation("");
		taSupervisorComment.setNullRepresentation("");
		taEdComment.setNullRepresentation("");
		
		this.tfSocioDemographics.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSocioDemographics.setWidth("300px");
		this.tfEducationalBackground.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfEducationalBackground.setWidth("300px");
		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfName.setWidth("300px");
		this.tfAreaOfInterest.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfAreaOfInterest.setWidth("300px");
		this.tfOrganizationPlacement.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfOrganizationPlacement.setWidth("300px");
		this.tfObjectives.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfObjectives.setWidth("300px");
		this.tfDurations.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfDurations.setWidth("300px");
		this.tfWorkDays.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfWorkDays.setWidth("300px");
		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfLocation.setWidth("300px");
		this.tfUnitOfInterest.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfUnitOfInterest.setWidth("300px");

		this.taSupervisorComment.addStyleName(ValoTheme.TEXTAREA_TINY);
		this.taSupervisorComment.setWidth("300px");		

		this.taEdComment.addStyleName(ValoTheme.TEXTAREA_TINY);
		this.taEdComment.setWidth("300px");
		taEdComment.setImmediate(true);
		this.btnUpload.addStyleName(ValoTheme.BUTTON_TINY);
		this.btnUpload.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.btnUpload.setImmediate(true);
		this.btnUpload.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				upload = event.getFilename();
				Notification.show("Document uploaded");
			}
		});
		FormLayout form1 = new FormLayout(this.tfName, this.btnUpload, this.tfSocioDemographics,this.tfEducationalBackground, 
				this.tfAreaOfInterest, this.tfOrganizationPlacement, this.tfObjectives, this.tfDurations, this.tfWorkDays,
				this.tfLocation, this.tfUnitOfInterest, this.taSupervisorComment, this.taEdComment);

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
				Volunteer volunteer = new Volunteer();
				volunteer.setDuration(tfDurations.getValue());
				volunteer.setEducationalBackground(tfEducationalBackground.getValue());
				volunteer.setInterestArea(tfAreaOfInterest.getValue());
				volunteer.setInterestUnit(tfUnitOfInterest.getValue());
				volunteer.setLocation(tfLocation.getValue());
				volunteer.setManagerComment(taSupervisorComment.getValue());
				volunteer.setName(tfName.getValue());
				volunteer.setObjectives(tfObjectives.getValue());
				volunteer.setOrganizationPlacement(tfOrganizationPlacement.getValue());
				volunteer.setSocioDemographics(tfSocioDemographics.getValue());
				volunteer.setUploadUrl(upload);
				volunteer.setWorkDays(tfWorkDays.getValue());
				volunteer.setEdComment(taEdComment.getValue());

				
				employeeService.saveVolunteer(volunteer);
				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				String error = e.getMessage();
				System.out.println(error);
				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};

}
