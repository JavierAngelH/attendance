/**
 * Employee.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Employee -
 *
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstname;
	private String lastname;
	private String username;
	private String location;
	private String department;
	private String manager;
	private Date empdate;
	private String employment_status;
	private String role;
	private Bean project;
	private Bean funder;
	private String projectName;
	private String funderName;
	private String hoursList;
	private String daysList;
	private List<String> daysArray;
	private List<String> hoursArray;
	private Date startDate;
	private Date endDate;
	private String status;
	private String pictureURL;
	private String email;
	private String password;
	private Double workedHours;
	private String signatureUrl;

	/**
	 * @return the workedHours
	 */
	public Double getWorkedHours() {
		return this.workedHours;
	}

	/**
	 * @param workedHours
	 *            the workedHours to set
	 */
	public void setWorkedHours(Double workedHours) {
		this.workedHours = workedHours;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pictureURL
	 */
	public String getPictureURL() {
		return this.pictureURL;
	}

	/**
	 * @param pictureURL
	 *            the pictureURL to set
	 */
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		if (firstname != null) {
			this.firstname = firstname;
		} else {
			this.firstname = "";
		}
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		if (lastname != null) {
			this.lastname = lastname;
		} else {
			this.lastname = "";
		}
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		if (username != null) {
			this.username = username;
		} else {
			this.username = "";
		}
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		if (location != null) {
			this.location = location;
		} else {
			this.location = "";
		}
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		if (department != null) {
			this.department = department;
		} else {
			this.department = "";
		}
	}

	/**
	 * @return the manager
	 */
	public String getManager() {
		return this.manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(String manager) {
		if (manager != null) {
			this.manager = manager;
		} else {
			this.manager = "";
		}
	}

	/**
	 * @return the empdate
	 */
	public Date getEmpdate() {
		return this.empdate;
	}

	/**
	 * @param empdate
	 *            the empdate to set
	 */
	public void setEmpdate(Date empdate) {
		this.empdate = empdate;
	}

	/**
	 * @return the employment_status
	 */
	public String getEmployment_status() {
		return this.employment_status;
	}

	/**
	 * @param employment_status
	 *            the employment_status to set
	 */
	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the Project
	 */
	public Bean getProject() {
		return this.project;
	}

	/**
	 * @param Project
	 *            the Project to set
	 */
	public void setProject(Bean project) {
		this.project = project;
	}

	/**
	 * @return the Funder
	 */
	public Bean getFunder() {
		return this.funder;
	}

	
	public void setFunder(Bean funder) {
		this.funder = funder;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the funderName
	 */
	public String getFunderName() {
		return this.funderName;
	}

	/**
	 * @param funderName
	 *            the funderName to set
	 */
	public void setFunderName(String funderName) {
		this.funderName = funderName;
	}

	/**
	 * @return the hoursList
	 */
	public String getHoursList() {
		return this.hoursList;
	}

	/**
	 * @param hoursList
	 *            the hoursList to set
	 */
	public void setHoursList(String hoursList) {
		this.hoursList = hoursList;

		this.hoursArray = new ArrayList<>(Arrays.asList(hoursList.split(",")));
	}

	/**
	 * @return the daysList
	 */
	public String getDaysList() {
		return this.daysList;
	}

	/**
	 * @param daysList
	 *            the daysList to set
	 */
	public void setDaysList(String daysList) {
		this.daysList = daysList;
		this.daysArray = new ArrayList<>(Arrays.asList(daysList.split(",")));
	}

	/**
	 * @return the daysArray
	 */
	public List<String> getDaysArray() {
		return this.daysArray;
	}

	/**
	 * @param daysArray
	 *            the daysArray to set
	 */
	public void setDaysArray(List<String> daysArray) {
		this.daysArray = daysArray;

	}

	/**
	 * @return the hoursArray
	 */
	public List<String> getHoursArray() {
		return this.hoursArray;
	}

	/**
	 * @param hoursArray
	 *            the hoursArray to set
	 */
	public void setHoursArray(List<String> hoursArray) {
		this.hoursArray = hoursArray;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the signatureUrl
	 */
	public String getSignatureUrl() {
		return this.signatureUrl;
	}

	/**
	 * @param signatureUrl the signatureUrl to set
	 */
	public void setSignatureUrl(String signatureUrl) {
		this.signatureUrl = signatureUrl;
	}

}
