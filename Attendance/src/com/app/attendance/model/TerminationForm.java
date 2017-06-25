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
	
	private String formalResignation;
	private String handoverNote;
	private String handoverProperties;
	private String medicalCoverage;
	private String benefitsPaid;
	private String minimumNotice;
	private String finalPayment;
	private String elegibleRehire;
	
	private int hrReviewed;
	
	private String managementPrevention;
	private String satisfaction;
	private String likeEmployment;
	private String dislikeEmployment;
	private String considerReapply;
	private String keepContact;
	private String phoneNumber;

	
	
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

	/**
	 * @return the formalResignation
	 */
	public String getFormalResignation() {
		return this.formalResignation;
	}

	/**
	 * @param formalResignation the formalResignation to set
	 */
	public void setFormalResignation(String formalResignation) {
		this.formalResignation = formalResignation;
		
	}

	/**
	 * @return the handoverNote
	 */
	public String getHandoverNote() {
		return this.handoverNote;
	}

	/**
	 * @param handoverNote the handoverNote to set
	 */
	public void setHandoverNote(String handoverNote) {
		this.handoverNote = handoverNote;
		
	}

	/**
	 * @return the handoverProperties
	 */
	public String getHandoverProperties() {
		return this.handoverProperties;
	}

	/**
	 * @param handoverProperties the handoverProperties to set
	 */
	public void setHandoverProperties(String handoverProperties) {
		this.handoverProperties = handoverProperties;
		
	}

	/**
	 * @return the medicalCoverage
	 */
	public String getMedicalCoverage() {
		return this.medicalCoverage;
	}

	/**
	 * @param medicalCoverage the medicalCoverage to set
	 */
	public void setMedicalCoverage(String medicalCoverage) {
		this.medicalCoverage = medicalCoverage;
		
	}

	/**
	 * @return the benefitsPaid
	 */
	public String getBenefitsPaid() {
		return this.benefitsPaid;
	}

	/**
	 * @param benefitsPaid the benefitsPaid to set
	 */
	public void setBenefitsPaid(String benefitsPaid) {
		this.benefitsPaid = benefitsPaid;
		
	}

	/**
	 * @return the minimumNotice
	 */
	public String getMinimumNotice() {
		return this.minimumNotice;
	}

	/**
	 * @param minimumNotice the minimumNotice to set
	 */
	public void setMinimumNotice(String minimumNotice) {
		this.minimumNotice = minimumNotice;
		
	}

	/**
	 * @return the finalPayment
	 */
	public String getFinalPayment() {
		return this.finalPayment;
	}

	/**
	 * @param finalPayment the finalPayment to set
	 */
	public void setFinalPayment(String finalPayment) {
		this.finalPayment = finalPayment;
		
	}

	/**
	 * @return the elegibleRehire
	 */
	public String getElegibleRehire() {
		return this.elegibleRehire;
	}

	/**
	 * @param elegibleRehire the elegibleRehire to set
	 */
	public void setElegibleRehire(String elegibleRehire) {
		this.elegibleRehire = elegibleRehire;
		
	}

	/**
	 * @return the hrReviewed
	 */
	public int getHrReviewed() {
		return this.hrReviewed;
	}

	/**
	 * @param hrReviewed the hrReviewed to set
	 */
	public void setHrReviewed(int hrReviewed) {
		this.hrReviewed = hrReviewed;
	}

	/**
	 * @return the managementPrevention
	 */
	public String getManagementPrevention() {
		return this.managementPrevention;
	}

	/**
	 * @param managementPrevention the managementPrevention to set
	 */
	public void setManagementPrevention(String managementPrevention) {
		this.managementPrevention = managementPrevention;
	}

	/**
	 * @return the satisfaction
	 */
	public String getSatisfaction() {
		return this.satisfaction;
	}

	/**
	 * @param satisfaction the satisfaction to set
	 */
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	/**
	 * @return the likeEmployment
	 */
	public String getLikeEmployment() {
		return this.likeEmployment;
	}

	/**
	 * @param likeEmployment the likeEmployment to set
	 */
	public void setLikeEmployment(String likeEmployment) {
		this.likeEmployment = likeEmployment;
	}

	/**
	 * @return the dislikeEmployment
	 */
	public String getDislikeEmployment() {
		return this.dislikeEmployment;
	}

	/**
	 * @param dislikeEmployment the dislikeEmployment to set
	 */
	public void setDislikeEmployment(String dislikeEmployment) {
		this.dislikeEmployment = dislikeEmployment;
	}

	/**
	 * @return the considerReapply
	 */
	public String getConsiderReapply() {
		return this.considerReapply;
	}

	/**
	 * @param considerReapply the considerReapply to set
	 */
	public void setConsiderReapply(String considerReapply) {
		this.considerReapply = considerReapply;
	}

	/**
	 * @return the keepContact
	 */
	public String getKeepContact() {
		return this.keepContact;
	}

	/**
	 * @param keepContact the keepContact to set
	 */
	public void setKeepContact(String keepContact) {
		this.keepContact = keepContact;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	

}
