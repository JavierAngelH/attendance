/**
 * Created by JavierAngelH
 */

package com.app.attendance.component.hr;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.attendance.model.Advert;
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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * HRAdvertLayout -
 *
 */
@SpringComponent
@UIScope
public class HRAdvertLayout extends Panel {
	private static final long serialVersionUID = 1L;
	private VerticalLayout root;
	Label titleLabel = new Label();

	TextField tfPosition = new TextField("POSITION TO BE FILLED");
	DateField dfOpenedDate = new DateField("DATE OPENED");
	TextField tfLocation = new TextField("LOCATION");
	TextField tfUnit = new TextField("UNIT");
	TextField tfWebsite = new TextField("WEBSITE");
	ComboBox cbAdvertisementMode = new ComboBox("MODE OF ADVERTISEMENT");

	TextField tfSite = new TextField("SITE");
	TextField tfSite2 = new TextField("SITE 2");
	TextField tfSite3 = new TextField("SITE 3");

	AdvertUploader receiver = new AdvertUploader();
	Upload btnUpload = new Upload("SHORTLIST UPLOAD", this.receiver);
	Upload btnUpload2 = new Upload("UPLOAD", this.receiver);

	String shortlistUrl = "";
	String uploadUrl = "";

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

		this.titleLabel.setValue("RECRUITMENT - ADVERT SECTION");

		this.tfPosition.clear();
		this.dfOpenedDate.clear();
		this.tfLocation.clear();
		this.tfUnit.clear();
		this.tfWebsite.clear();
		this.cbAdvertisementMode.clear();
		this.tfSite.clear();
		this.tfSite2.clear();
		this.tfSite3.clear();

	}

	private Component buildContent() {
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		HorizontalLayout layoutMain = new HorizontalLayout();
		layoutMain.addStyleName(ValoTheme.LAYOUT_CARD);
		layoutMain.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		layoutMain.setMargin(new MarginInfo(false, true, true, true));
		layoutMain.setSpacing(true);

		this.tfPosition.setNullRepresentation("");
		this.tfLocation.setNullRepresentation("");
		this.tfUnit.setNullRepresentation("");
		this.tfWebsite.setNullRepresentation("");
		this.tfSite.setNullRepresentation("");
		this.tfSite2.setNullRepresentation("");
		this.tfSite3.setNullRepresentation("");

		this.tfPosition.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfPosition.setWidth("498px");

		this.dfOpenedDate.addStyleName(ValoTheme.DATEFIELD_TINY);
		this.dfOpenedDate.setDateFormat("dd/MM/yyyy");
		this.dfOpenedDate.setWidth("498px");

		this.tfLocation.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfLocation.setWidth("498px");

		this.tfWebsite.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfWebsite.setWidth("498px");
		this.tfWebsite.setInputPrompt("http://");

		this.tfUnit.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfUnit.setWidth("498px");

		this.cbAdvertisementMode.addStyleName(ValoTheme.COMBOBOX_TINY);
		this.cbAdvertisementMode.setWidth("498px");

		this.tfUnit.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfUnit.setWidth("498px");

		this.tfSite.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSite.setWidth("498px");

		this.tfSite2.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSite2.setWidth("498px");

		this.tfSite3.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.tfSite3.setWidth("498px");

		Label labelform1 = new Label("ADVERT LIST SECTION");
		labelform1.addStyleName(ValoTheme.LABEL_H3);
		labelform1.addStyleName(ValoTheme.LABEL_COLORED);

		Label labelform2 = new Label("ATTACH LINK");
		labelform2.addStyleName(ValoTheme.LABEL_H3);
		labelform2.addStyleName(ValoTheme.LABEL_COLORED);

		Label labelform3 = new Label("VIRTUAL PLACEMENT");
		labelform3.addStyleName(ValoTheme.LABEL_H3);
		labelform3.addStyleName(ValoTheme.LABEL_COLORED);

		Label labelform4 = new Label("DETAILS OF INDIVIDUAL SHORTLISTED");
		labelform4.addStyleName(ValoTheme.LABEL_H3);
		labelform4.addStyleName(ValoTheme.LABEL_COLORED);

		Label labelform5 = new Label("UPLOAD GOOGLE FORM");
		labelform5.addStyleName(ValoTheme.LABEL_H3);
		labelform5.addStyleName(ValoTheme.LABEL_COLORED);

		this.cbAdvertisementMode.setTextInputAllowed(false);
		this.cbAdvertisementMode.addItem("Physical Placement");
		this.cbAdvertisementMode.addItem("Vitual Placement");
		this.cbAdvertisementMode.setNullSelectionAllowed(false);
		this.cbAdvertisementMode.setValue("Physical Placement");

		this.btnUpload.addStyleName(ValoTheme.BUTTON_TINY);
		this.btnUpload.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.btnUpload.setImmediate(true);
		this.btnUpload.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				HRAdvertLayout.this.shortlistUrl = event.getFilename();
				Notification.show("Document uploaded");
			}
		});

		this.btnUpload2.addStyleName(ValoTheme.BUTTON_TINY);
		this.btnUpload2.addStyleName(ValoTheme.TEXTFIELD_TINY);
		this.btnUpload2.setImmediate(true);
		this.btnUpload2.addSucceededListener(new SucceededListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				HRAdvertLayout.this.uploadUrl = event.getFilename();
				Notification.show("Document Uploaded");
			}
		});

		FormLayout form1 = new FormLayout(labelform1, this.tfPosition, this.dfOpenedDate, this.tfLocation, this.tfUnit,
				labelform2, this.tfWebsite, this.cbAdvertisementMode, labelform3, this.tfSite, this.tfSite2,
				this.tfSite3, labelform4, this.btnUpload, labelform5, this.btnUpload2);

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

				Advert advert = new Advert();
				advert.setAdvertisement(HRAdvertLayout.this.cbAdvertisementMode.getValue().toString());
				advert.setAdvertUrl(HRAdvertLayout.this.uploadUrl);
				advert.setLocation(HRAdvertLayout.this.tfLocation.getValue());
				advert.setOpenedDate(HRAdvertLayout.this.dfOpenedDate.getValue());
				advert.setPosition(HRAdvertLayout.this.tfPosition.getValue());
				advert.setShortlistUrl(HRAdvertLayout.this.shortlistUrl);
				advert.setSite1(HRAdvertLayout.this.tfSite.getValue());
				advert.setSite2(HRAdvertLayout.this.tfSite2.getValue());
				advert.setSite3(HRAdvertLayout.this.tfSite3.getValue());
				advert.setUnit(HRAdvertLayout.this.tfUnit.getValue());
				advert.setWebsite(HRAdvertLayout.this.tfWebsite.getValue());
				HRAdvertLayout.this.employeeService.saveAdvert(advert);
				Notification.show("Information saved succesfully.");
			} catch (Exception e) {
				String error = e.getMessage();
				System.out.println(error);
				Notification.show("You must fill all the fields", Notification.Type.ERROR_MESSAGE);

			}
		}
	};
}