/**
 * LoginPanel.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.components;

import com.app.attendance.utils.Utilities;
import javax.annotation.PostConstruct;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;

/**
 * LoginPanel -
 *
 */
@SpringComponent
@UIScope
public class LoginPanel extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	final HorizontalLayout fields = new HorizontalLayout();

	public final TextField username = new TextField("Username");
	public final PasswordField password = new PasswordField("Password");
	public final Button signin = new Button("Login");

	@PostConstruct
	public void PostConstruct() {

		Responsive.makeResponsive(this);

		this.setSizeFull();

		this.addStyleName("mainview");
		this.addStyleName("loginpanel");


		VerticalLayout loginForm = this.buildLoginForm();
		HorizontalLayout headerPanel = new HorizontalLayout();
		headerPanel.setHeight("80px");
		headerPanel.setWidth("100%");
		headerPanel.addStyleName("header");
FileResource resource = new FileResource(new File(Utilities.imagesPath + "logo.png"));
    final Image image = new Image(null, resource);
    headerPanel.addComponent(image);
    headerPanel.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		this.addComponent(headerPanel);
		this.addComponent(loginForm);
		this.setComponentAlignment(headerPanel, Alignment.TOP_LEFT);
		this.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		this.setExpandRatio(loginForm, 3);

	}

	private VerticalLayout buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");

		loginPanel.addComponent(this.buildLabels());
		loginPanel.addComponent(this.buildFields());
		return loginPanel;
	}

	private HorizontalLayout buildFields() {

		this.fields.setSpacing(true);
		this.fields.addStyleName("fields");

		this.username.setIcon(FontAwesome.USER);
		this.username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		this.password.setIcon(FontAwesome.LOCK);
		this.password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		this.signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		this.signin.setClickShortcut(KeyCode.ENTER);
		this.signin.setImmediate(true);
		this.signin.focus();

		this.fields.addComponents(this.username, this.password, this.signin);
		this.fields.setComponentAlignment(this.signin, Alignment.BOTTOM_LEFT);

		return this.fields;

	}

	private CssLayout buildLabels() {
		CssLayout labels = new CssLayout();
		labels.addStyleName("labels");

		Label welcome = new Label("");
		welcome.setSizeUndefined();
		welcome.addStyleName(ValoTheme.LABEL_H3);
		welcome.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(welcome);

		Label title = new Label("Welcome");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H4);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(title);
		return labels;
	}

}
