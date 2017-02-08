/** 
 * TerminationForm.java Created: Dec 13, 2016 JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * TerminationForm - 
 *
 */
public class TerminationForm implements Serializable {
	
	private int id;
	private String employeeId;
	
	private String designation;
	
	private String employeeName;

	private Date employmentDate;
	
	private String leavingReason;
	
	private String possibleReturn;

	private String recommendation;

	private String managementReason;
	
	private String suggestions;
	
	private String comments;
	
	private String rehire;
	
	private String terminationReason;

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return this.designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return this.employmentDate;
	}

	/**
	 * @param employmentDate the employmentDate to set
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * @return the leavingReason
	 */
	public String getLeavingReason() {
		return this.leavingReason;
	}

	/**
	 * @param leavingReason the leavingReason to set
	 */
	public void setLeavingReason(String leavingReason) {
		this.leavingReason = leavingReason;
	}

	/**
	 * @return the possibleReturn
	 */
	public String getPossibleReturn() {
		return this.possibleReturn;
	}

	/**
	 * @param possibleReturn the possibleReturn to set
	 */
	public void setPossibleReturn(String possibleReturn) {
		this.possibleReturn = possibleReturn;
	}

	/**
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return this.recommendation;
	}

	/**
	 * @param recommendation the recommendation to set
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	/**
	 * @return the managementReason
	 */
	public String getManagementReason() {
		return this.managementReason;
	}

	/**
	 * @param managementReason the managementReason to set
	 */
	public void setManagementReason(String managementReason) {
		this.managementReason = managementReason;
	}

	/**
	 * @return the suggestions
	 */
	public String getSuggestions() {
		return this.suggestions;
	}

	/**
	 * @param suggestions the suggestions to set
	 */
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the rehire
	 */
	public String getRehire() {
		return this.rehire;
	}

	/**
	 * @param rehire the rehire to set
	 */
	public void setRehire(String rehire) {
		this.rehire = rehire;
	}

	/**
	 * @return the terminationReason
	 */
	public String getTerminationReason() {
		return this.terminationReason;
	}

	/**
	 * @param terminationReason the terminationReason to set
	 */
	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}
	
	
	

}
