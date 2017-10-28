/** 
 * Created by JavierAngelH
 */

package com.app.attendance.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Advert - 
 *
 */
public class Advert implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String position;
	private Date openedDate;
	private String location;
	private String unit;
	private String website;
	private String advertisement;
	private String site1;
	private String site2;
	private String site3;
	private String shortlistUrl;
	private String advertUrl;
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
	 * @return the openedDate
	 */
	public Date getOpenedDate() {
		return this.openedDate;
	}
	/**
	 * @param openedDate the openedDate to set
	 */
	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
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
	 * @return the unit
	 */
	public String getUnit() {
		return this.unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return this.website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the advertisement
	 */
	public String getAdvertisement() {
		return this.advertisement;
	}
	/**
	 * @param advertisement the advertisement to set
	 */
	public void setAdvertisement(String advertisement) {
		this.advertisement = advertisement;
	}
	/**
	 * @return the site1
	 */
	public String getSite1() {
		return this.site1;
	}
	/**
	 * @param site1 the site1 to set
	 */
	public void setSite1(String site1) {
		this.site1 = site1;
	}
	/**
	 * @return the site2
	 */
	public String getSite2() {
		return this.site2;
	}
	/**
	 * @param site2 the site2 to set
	 */
	public void setSite2(String site2) {
		this.site2 = site2;
	}
	/**
	 * @return the site3
	 */
	public String getSite3() {
		return this.site3;
	}
	/**
	 * @param site3 the site3 to set
	 */
	public void setSite3(String site3) {
		this.site3 = site3;
	}
	/**
	 * @return the shortlistUrl
	 */
	public String getShortlistUrl() {
		return this.shortlistUrl;
	}
	/**
	 * @param shortlistUrl the shortlistUrl to set
	 */
	public void setShortlistUrl(String shortlistUrl) {
		this.shortlistUrl = shortlistUrl;
	}
	/**
	 * @return the advertUrl
	 */
	public String getAdvertUrl() {
		return this.advertUrl;
	}
	/**
	 * @param advertUrl the advertUrl to set
	 */
	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}
	
	
}
