package jhu.swe645.ejb.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity for employee's personal information and referenced locations.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="mi")
	private String middleInitial;
	
	@Column(name="email_address")
	private String email;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="home_id")
	private Location home;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="office_id")
	private Location office;
	
	@ManyToOne
	@JoinColumn(name="sup_id")
	private Supervisor supervisor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Location getHome() {
		return home;
	}

	public void setHome(Location home) {
		this.home = home;
	}

	public Location getOffice() {
		return office;
	}

	public void setOffice(Location office) {
		this.office = office;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
		
		if(!supervisor.getEmployees().contains(this)){
			supervisor.getEmployees().add(this);
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleInitial=" + middleInitial
				+ ", email=" + email + ", jobTitle=" + jobTitle + ", home="
				+ home + ", office=" + office + ", supervisor=" + supervisor
				+ "]";
	}
	
	
	
}
