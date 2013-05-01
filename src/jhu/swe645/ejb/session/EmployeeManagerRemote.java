package jhu.swe645.ejb.session;

import java.util.List;

import javax.ejb.Remote;

import jhu.swe645.ejb.entity.Employee;
import jhu.swe645.ejb.entity.Supervisor;

/**
 * This is the interface for the Employee Registration SLSB
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Remote
//@WebService
public interface EmployeeManagerRemote {
	/**
	 * returns all of supervisors
	 * 
	 * @return list of supervisors
	 */
	public List<Supervisor> getAllSupervisors();
	
	/**
	 * returns all of registered employees
	 * 
	 * @return list of employees
	 */
	public List<Employee> getAllEmployees();
	
	/**
	 * returns registered employee(s) that meets the search term criteria
	 * 
	 * @param searchTerm
	 * @return list of employees if not empty
	 */
	public List<Employee> searchEmployees(String searchTerm);
	
	/**
	 * saves the newly registered employee into the system
	 * 
	 * @param employee
	 * @return employee's generated id
	 */
	public long saveEmployee(Employee employee);
	
	/**
	 * removes the employee with id from the system
	 * 
	 * @param id
	 * @return true if delete is successful, otherwise false
	 */
	public boolean deleteEmployee(long id);
	
	/**
	 * retrieve a registered employee with the given id
	 * 
	 * @param id
	 * @return employee
	 */
	public Employee getEmployee(long id);
	
	/**
	 * checks to see if the given email is unique
	 * 
	 * @param email
	 * @return true if email exists, otherwise false
	 */
	public boolean hasEmail(String email);
	
	/**
	 * gets the supervisor of the given id
	 * 
	 * @param supervisor id
	 * @return supervisor
	 */
	public Supervisor lookupSupervisor(String supervisor);
}
