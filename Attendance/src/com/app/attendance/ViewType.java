package com.app.attendance;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;

public enum ViewType {
	VIEW_TIMESHEET("VIEW TIMESHEET", FontAwesome.EYE, true), TIMESHEET("TIMESHEET", FontAwesome.FOLDER_OPEN,
			true), LEAVE_MANAGEMENT("LEAVE MANAGEMENT", FontAwesome.HOURGLASS, true), MANAGER_TIMESHEET("TIMESHEETS",
					FontAwesome.USER, true), MANAGER_LEAVE_REQUEST("LEAVE REQUESTS", FontAwesome.HOURGLASS_END,
							true), HR_DISENGAGEMENT_FORM("DISENGAGEMENT FORMS", FontAwesome.TIMES,
									true), STAFF_DISENGAGEMENT_FORM("DISENGAGEMENT FORM", FontAwesome.TIMES, true),

	HR_TIMESHEET("TIMESHEETS", FontAwesome.CALENDAR, true), HR_LEAVE_REQUEST("LEAVE REQUESTS",
			FontAwesome.HOURGLASS_END, true), HR_LEAVE_REQUEST_FORMS("LEAVE REQUEST FORMS", FontAwesome.HOURGLASS_2,
					true), HR_ABSENT_EMPLOYEES("ABSENT EMPLOYEES", FontAwesome.CREDIT_CARD, true),

	ADMIN_CREATE_USER("CREATE EMPLOYEE", FontAwesome.PLUS, true), ADMIN_VIEW_USER("VIEW EMPLOYEES", FontAwesome.EYE,
			true), ADMIN_EDIT_USER("EDIT/DELETE EMPLOYEE", FontAwesome.TRASH_O, true), ADMIN_VIEW_DEPARTMENT(
					"VIEW DEPARTMENTS", FontAwesome.STREET_VIEW, true), ADMIN_CREATE_DEPARTMENT("CREATE DEPARTMENT",
							FontAwesome.CREATIVE_COMMONS,
							true), ADMIN_EDIT_DEPARTMENT("EDIT/DELETE DEPT.", FontAwesome.PENCIL_SQUARE, true),

	STAFF_APPRAISAL("APPRAISAL FORM", FontAwesome.ENVELOPE_O, true),
	MANAGER_APPRAISAL("APPRAISAL FORMS", FontAwesome.ENVELOPE_O, true),
	HR_APPRAISAL("APPRAISAL FORMS", FontAwesome.ENVELOPE_O, true),
	STAFF_PERFORMANCE_REVIEW_STAFF("STAFF PERFORMANCE REVIEW", FontAwesome.FILE, true),
	STAFF_PERFORMANCE_REVIEW_HR("STAFF PERFORMANCE REVIEW", FontAwesome.FILE, true),
	STAFF_PERFORMANCE_REVIEW_MANAGER("STAFF PERFORMANCE REVIEW", FontAwesome.FILE, true),

	LOGOUT("LOG OUT", FontAwesome.SIGN_OUT, true);

	private final String viewName;
	private final Resource icon;
	private final boolean stateful;

	private ViewType(final String viewName, final Resource icon, final boolean stateful) {
		this.viewName = viewName;
		this.icon = icon;
		this.stateful = stateful;
	}

	public boolean isStateful() {
		return this.stateful;
	}

	public String getViewName() {
		return this.viewName;
	}

	public Resource getIcon() {
		return this.icon;
	}

	public static ViewType getByViewName(final String viewName) {
		ViewType result = null;
		for (ViewType viewType : ViewType.values()) {
			if (viewType.getViewName().equals(viewName)) {
				result = viewType;
				break;
			}
		}
		return result;
	}

}
