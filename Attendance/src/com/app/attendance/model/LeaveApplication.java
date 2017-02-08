/** 
 * LeaveApplication.java Created: Dec 12, 2016 JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;

/**
 * LeaveApplication - 
 *
 */
public class LeaveApplication implements Serializable {
	
	private int id;
	
	private String type;
	
	private String explanation;
	
	private String backstoping;
	
	private int balance;
	
	private String employeeId;
	
	private String employeeName;
	
	private String designation;
	
	private String location;
	
	
	private int days;
	
	
	
	

	/**
	 * @return the days
	 */
	public int getDays() {
		return this.days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
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
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return this.explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * @return the backstoping
	 */
	public String getBackstoping() {
		return this.backstoping;
	}

	/**
	 * @param backstoping the backstoping to set
	 */
	public void setBackstoping(String backstoping) {
		this.backstoping = backstoping;
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
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
	
	
	

}
