package jhu.swe645.ejb.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jhu.swe645.ejb.entity.Employee;
import jhu.swe645.ejb.entity.Supervisor;

import org.apache.log4j.Logger;

/**
 * This implementation has business logic to interact get or set object data
 * which are persisted in the db (ORM). 
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Stateless
//@WebService(endpointInterface = "jhu.swe645.ejb.session.EmployeeManagerRemote", serviceName = "EmployeeManagerWS")
public class EmployeeManagerImpl implements EmployeeManagerRemote {

	@PersistenceContext(unitName = "swe645Unit")
	private EntityManager em;

	private final Logger log = Logger.getLogger(EmployeeManagerImpl.class);

	@SuppressWarnings("unchecked")
	@Override
//	@WebMethod(exclude=true)
	public boolean hasEmail(String email) {
		log.info("Checking to see if " + email + " is unique. ");
		String sql = "SELECT e FROM Employee AS e WHERE e.email LIKE '" + email
				+ "'";
		List<Employee> employees = em.createQuery(sql).getResultList();
		return !employees.isEmpty() ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
//	@WebMethod(exclude=true)
	public List<Supervisor> getAllSupervisors() {
		log.info("Getting all supervisors.");
		List<Supervisor> supes = new ArrayList<Supervisor>();
		String sql = "SELECT s FROM Supervisor AS s ORDER BY s.fullName";
		supes = em.createQuery(sql).getResultList();
		return supes;
	}

	@SuppressWarnings("unchecked")
	@Override
//	@WebMethod(exclude=true)
	public List<Employee> getAllEmployees() {
		log.info("Getting all employees.");
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "SELECT e FROM Employee AS e ORDER BY e.lastName";
		employees = em.createQuery(sql).getResultList();
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
//	@WebMethod(exclude=true)
	public List<Employee> searchEmployees(String searchTerm) {
		log.info("Getting all employees by this search term: " + searchTerm);
		StringBuffer sbQL = new StringBuffer(" from Employee e ");
		if (searchTerm != null && !searchTerm.isEmpty()) {
			sbQL.append(" WHERE ");
			sbQL.append(" firstName LIKE ?" + 1);
			sbQL.append(" OR ");
			sbQL.append(" lastName LIKE ?" + 2);
			sbQL.append(" OR ");
			sbQL.append(" home.phone LIKE ?" + 3);
		}
		log.info("SQL command is: " + sbQL);
		Query q = em.createQuery(sbQL.toString());
		q.setParameter(1, "%" + searchTerm + "%");
		q.setParameter(2, "%" + searchTerm + "%");
		q.setParameter(3, "%" + searchTerm + "%");

		return q.getResultList();
	}

	@Override
//	@WebMethod(exclude=true)
	public long saveEmployee(Employee employee) {
		log.info("Saving new employee: " + employee.toString());

		Supervisor supa = em.find(Supervisor.class, employee.getSupervisor()
				.getId());
		supa.addEmployee(employee);
		// em.persist(employee.getHome());
		// em.persist(employee.getOffice());
		em.persist(employee);
		return employee.getId();
	}

	@Override
//	@WebMethod(exclude=true)
	public boolean deleteEmployee(long id) {
		log.info("Deleting an employee id: " + id);
		Employee employee = em.find(Employee.class, id);
		em.remove(employee);
		return !em.contains(employee);
	}

	@Override
//	@WebMethod(exclude=true)
	public Employee getEmployee(long id) {
		log.info("Getting the employee with id: " + id);
		return em.find(Employee.class, id);
	}

	@Override
//	@WebMethod(exclude=true)
	public Supervisor lookupSupervisor(String supervisor) {
		log.info("Getting the supervisor with id: " + supervisor);
		return em.find(Supervisor.class, Long.parseLong(supervisor));
	}
}