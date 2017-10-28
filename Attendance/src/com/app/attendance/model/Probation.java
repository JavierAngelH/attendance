/** 
 * Created by JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Probation - 
 *
 */
public class Probation implements Serializable{
	private int id;
	private String nameOfStaff;
	private String location;
	private String position;
	private Date dateOfHire;
	private String performanceConducted;
	private String scorePerfromance;
	private String recommendation;
	private String enrollPension;
	private String enrollMedical;
	private String enrollInsurance;
	private String enrollNsitf;
	private String confrimLetter;
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
	 * @return the staffName
	 */
	public String getNameOfStaff() {
		return this.nameOfStaff;
	}
	/**
	 * @param staffName the staffName to set
	 */
	public void setNameOfStaff(String staffName) {
		this.nameOfStaff = staffName;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return this.position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the hireDate
	 */
	public Date getDateOfHire() {
		return this.dateOfHire;
	}
	/**
	 * @param hireDate the hireDate to set
	 */
	public void setDateOfHire(Date hireDate) {
		this.dateOfHire = hireDate;
	}
	/**
	 * @return the performanceConducted
	 */
	public String getPerformanceConducted() {
		return this.performanceConducted;
	}
	/**
	 * @param performanceConducted the performanceConducted to set
	 */
	public void setPerformanceConducted(String performanceConducted) {
		this.performanceConducted = performanceConducted;
	}
	/**
	 * @return the scorePerformance
	 */
	public String getScorePerfromance() {
		return this.scorePerfromance;
	}
	/**
	 * @param scorePerformance the scorePerformance to set
	 */
	public void setScorePerfromance(String scorePerformance) {
		this.scorePerfromance = scorePerformance;
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
	 * @return the enrollPension
	 */
	public String getEnrollPension() {
		return this.enrollPension;
	}
	/**
	 * @param enrollPension the enrollPension to set
	 */
	public void setEnrollPension(String enrollPension) {
		this.enrollPension = enrollPension;
	}
	/**
	 * @return the enrollMedical
	 */
	public String getEnrollMedical() {
		return this.enrollMedical;
	}
	/**
	 * @param enrollMedical the enrollMedical to set
	 */
	public void setEnrollMedical(String enrollMedical) {
		this.enrollMedical = enrollMedical;
	}
	/**
	 * @return the enrollInsurance
	 */
	public String getEnrollInsurance() {
		return this.enrollInsurance;
	}
	/**
	 * @param enrollInsurance the enrollInsurance to set
	 */
	public void setEnrollInsurance(String enrollInsurance) {
		this.enrollInsurance = enrollInsurance;
	}
	/**
	 * @return the enrollNsitf
	 */
	public String getEnrollNsitf() {
		return this.enrollNsitf;
	}
	/**
	 * @param enrollNsitf the enrollNsitf to set
	 */
	public void setEnrollNsitf(String enrollNsitf) {
		this.enrollNsitf = enrollNsitf;
	}
	/**
	 * @return the confrimLetter
	 */
	public String getConfrimLetter() {
		return this.confrimLetter;
	}
	/**
	 * @param confrimLetter the confrimLetter to set
	 */
	public void setConfrimLetter(String confrimLetter) {
		this.confrimLetter = confrimLetter;
	}
	
}	
