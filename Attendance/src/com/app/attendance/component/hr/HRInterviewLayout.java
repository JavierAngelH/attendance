/**
 * Created by JavierAngelH
 */

package com.app.attendance.component.hr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Interview;
import com.app.attendance.service.EmployeeService;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRInterviewLayout -
 *
 */
@SpringComponent
@UIScope
public class HRInterviewLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	TextField tfName = new TextField("NAME");
	TextField tfage = new TextField("AGE");
	TextField tfsex = new TextField("SEX");
	TextField tfTotal = new TextField("TOTAL");
	TextField tfSalary = new TextField("SALARY EXPECTATION");
	TextField tfComments = new TextField("COMMENTS");

	ComboBox cboxeducation = new ComboBox("EDUCATION ABOVE QUALIFICATION");
	ComboBox cboxwork = new ComboBox("WORK EXPERIENCE RELATED TO THE JOB");
	ComboBox cboxknowledge = new ComboBox("SRHR KNOWLEDGE");
	ComboBox cboxteam = new ComboBox("ABILITY TO WORK AS A TEAM");
	ComboBox cboxconfidence = new ComboBox("CONFIDENCE");
	ComboBox cboxwrite = new ComboBox("STRONG WRITING ABILITIES");
	ComboBox cboxtech = new ComboBox("TECHNICAL ABILITY (DEPENDS ON POSITION)");
	ComboBox cboxinter = new ComboBox("INTERPERSONAL COMMUNICATION");
	ComboBox cboxidea = new ComboBox("OPEN TO NEW IDEAS");
	ComboBox cboxmicrosoft = new ComboBox("MICROSOFT PACKAGES");

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

		this.titleLabel.setValue("RECRUITMENT - INTERVIEW SECTION");

		this.tfName.clear();
		this.tfage.clear();
		this.tfsex.clear();
		this.tfTotal.clear();
		this.tfSalary.clear();
		this.tfComments.clear();

		this.cboxeducation.clear();
		this.cboxwork.clear();
		this.cboxknowledge.clear();
		this.cboxteam.clear();
		this.cboxconfidence.clear();
		this.cboxwrite.clear();
		this.cboxtech.clear();
		this.cboxinter.clear();
		this.cboxidea.clear();
		this.cboxmicrosoft.clear();

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
		this.tfage.setNullRepresentation("");
		this.tfsex.setNullRepresentation("");
		this.tfTotal.setNullRepresentation("");
		this.tfSalary.setNullRepresentation("");
		this.tfComments.setNullRepresentation("");

		this.tfName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfName.setWidth("300px");

		this.tfage.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfage.setWidth("300px");

		this.tfsex.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfsex.setWidth("300px");

		this.tfTotal.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfTotal.setWidth("300px");

		this.tfSalary.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSalary.setWidth("300px");

		this.tfComments.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfComments.setWidth("300px");

		this.cboxeducation.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxeducation.setWidth("300px");

		this.cboxwork.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxwork.setWidth("300px");

		this.cboxknowledge.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxknowledge.setWidth("300px");

		this.cboxteam.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxteam.setWidth("300px");

		this.cboxconfidence.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxconfidence.setWidth("300px");

		this.cboxwrite.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxwrite.setWidth("300px");

		this.cboxtech.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxtech.setWidth("300px");

		this.cboxinter.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxinter.setWidth("300px");

		this.cboxidea.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxidea.setWidth("300px");

		this.cboxmicrosoft.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cboxmicrosoft.setWidth("300px");

		this.cboxeducation.addItem("1");
		this.cboxwork.addItem("1");
		this.cboxknowledge.addItem("1");
		this.cboxteam.addItem("1");
		this.cboxconfidence.addItem("1");
		this.cboxwrite.addItem("1");
		this.cboxtech.addItem("1");
		this.cboxinter.addItem("1");
		this.cboxmicrosoft.addItem("1");
		this.cboxidea.addItem("1");

		this.cboxeducation.addItem("2");
		this.cboxwork.addItem("2");
		this.cboxknowledge.addItem("2");
		this.cboxteam.addItem("2");
		this.cboxconfidence.addItem("2");
		this.cboxwrite.addItem("2");
		this.cboxtech.addItem("2");
		this.cboxinter.addItem("2");
		this.cboxmicrosoft.addItem("2");
		this.cboxidea.addItem("2");

		this.cboxeducation.addItem("3");
		this.cboxwork.addItem("3");
		this.cboxknowledge.addItem("3");
		this.cboxteam.addItem("3");
		this.cboxconfidence.addItem("3");
		this.cboxwrite.addItem("3");
		this.cboxtech.addItem("3");
		this.cboxinter.addItem("3");
		this.cboxmicrosoft.addItem("3");
		this.cboxidea.addItem("3");

		FormLayout form1 = new FormLayout(this.tfName, this.tfage, this.tfsex, this.cboxeducation, this.cboxwork,
				this.cboxknowledge, this.cboxteam, this.cboxconfidence, this.cboxwrite, this.cboxtech, this.cboxinter,
				this.cboxidea, this.cboxmicrosoft, this.tfTotal, this.tfSalary, this.tfComments);

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
				Interview interview = new Interview();
				interview.setAge(Integer.parseInt(HRInterviewLayout.this.tfage.getValue()));
				interview.setComments(HRInterviewLayout.this.tfComments.getValue());
				interview.setConfidence(Integer.parseInt(HRInterviewLayout.this.cboxconfidence.getValue().toString()));
				interview.setEducationAboveQ(
						Integer.parseInt(HRInterviewLayout.this.cboxeducation.getValue().toString()));
				interview.setInterpersonalCommunication(
						Integer.parseInt(HRInterviewLayout.this.cboxinter.getValue().toString()));
				interview.setMicrosoftPackages(
						Integer.parseInt(HRInterviewLayout.this.cboxmicrosoft.getValue().toString()));
				interview.setName(HRInterviewLayout.this.tfName.getValue());
				interview.setOpenIdeas(Integer.parseInt(HRInterviewLayout.this.cboxidea.getValue().toString()));
				interview.setSalary(HRInterviewLayout.this.tfSalary.getValue());
				interview.setSex(HRInterviewLayout.this.tfsex.getValue());
				interview
						.setSrhrKnowledge(Integer.parseInt(HRInterviewLayout.this.cboxknowledge.getValue().toString()));
				interview.setTeamWork(Integer.parseInt(HRInterviewLayout.this.cboxteam.getValue().toString()));
				interview.setTechnicalAbility(Integer.parseInt(HRInterviewLayout.this.cboxtech.getValue().toString()));
				interview.setTotal(Integer.parseInt(HRInterviewLayout.this.tfTotal.getValue()));
				interview.setWorkExperience(Integer.parseInt(HRInterviewLayout.this.cboxwork.getValue().toString()));
				interview.setWritingAbilities(Integer.parseInt(HRInterviewLayout.this.cboxwrite.getValue().toString()));
				HRInterviewLayout.this.employeeService.saveInterview(interview);
				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				String error = e.getMessage();
				System.out.println(error);
				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};
}