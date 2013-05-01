package jhu.swe645.ejb.entity;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity for supervisor information (full name and email address).
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Entity
@Table(name="supervisor")
public class Supervisor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sup_id")
	private long id;
	
	@Column(name="full_name")
	private String fullName;

	private String email;
	
	@OneToMany(mappedBy="supervisor",fetch=FetchType.EAGER)
//	@OneToMany(mappedBy="supervisor",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Employee> employees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee){
		this.employees.add(employee);
		if(employee.getSupervisor()!=this){
			employee.setSupervisor(this);
		}
	}

	@Override
	public String toString() {
		return "Supervisor [id=" + id + ", fullName=" + fullName + ", email="
				+ email + "]";
	}
	
	public String getEmployeesString(){
		StringBuffer str = new StringBuffer(this.fullName + "'s employees are: ");
		
		for(Employee e : this.employees){
			str.append(e.getId()+", ");
		}
		return str.toString();
	}
	
}
