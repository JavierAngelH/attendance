/**
 * Department.java Created: Nov 7, 2016 JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;

/**
 * Department -
 *
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String manageirid;
	private String description;

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
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the manageirid
	 */
	public String getManageirid() {
		return this.manageirid;
	}

	/**
	 * @param manageirid
	 *            the manageirid to set
	 */
	public void setManageirid(String manageirid) {
		this.manageirid = manageirid;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
