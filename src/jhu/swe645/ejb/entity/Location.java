package jhu.swe645.ejb.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for employee's location (street, city, state, zip code and phone
 * number).
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Entity
@Table(name="location")
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="location_id")
	private long id;
	
	private String street;
	private String city;
	private String state;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@Column(name="phone_number")
	private String phone;

	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getStreet() {return street;}

	public void setStreet(String street) {this.street = street;}

	public String getCity() {return city;}

	public void setCity(String city) {this.city = city;}

	public String getState() {return state;}

	public void setState(String state) {this.state = state;}

	public String getZipCode() {return zipCode;}

	public void setZipCode(String zipCode) {this.zipCode = zipCode;}

	public String getPhone() {return phone;}

	public void setPhone(String phone) {this.phone = phone;}

	@Override
	public String toString() {
		return "Location [id=" + id + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", phone="
				+ phone + "]";
	}
}
