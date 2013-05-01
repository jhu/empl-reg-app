package jhu.swe645.ejb.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jhu.swe645.ejb.entity.EmplConciseInfo;
import jhu.swe645.ejb.entity.Employee;

import org.apache.log4j.Logger;

/**
 * This is an implementation of the SOAP-based web service and stateless session
 * bean to provide the business logic of getting all employees or subset of
 * employees based on the search query.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Stateless
@WebService(endpointInterface = "jhu.swe645.ejb.session.EmployeeService", serviceName = "EmployeeWS")
public class EmployeeServiceImpl implements EmployeeService {

	@PersistenceContext(unitName = "swe645Unit")
	private EntityManager em;

	private final Logger log = Logger.getLogger(EmployeeManagerImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmplConciseInfo> getAllEmployees() {
		log.info("Getting all employees.");
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "SELECT e FROM Employee AS e ORDER BY e.lastName";
		employees = em.createQuery(sql).getResultList();

		List<EmplConciseInfo> emps = new ArrayList<EmplConciseInfo>();

		for (Employee e : employees) {
			EmplConciseInfo temp = new EmplConciseInfo();
			temp.setId(e.getId());
			temp.setFirstName(e.getFirstName());
			temp.setLastName(e.getLastName());
			temp.setEmail(e.getEmail());
			temp.setPhone(e.getHome().getPhone());
			emps.add(temp);
		}
		return emps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmplConciseInfo> searchEmployees(String searchTerm) {
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

		List<Employee> employees = q.getResultList();
		List<EmplConciseInfo> emps = new ArrayList<EmplConciseInfo>();

		for (Employee e : employees) {
			EmplConciseInfo temp = new EmplConciseInfo();
			temp.setId(e.getId());
			temp.setFirstName(e.getFirstName());
			temp.setLastName(e.getLastName());
			temp.setEmail(e.getEmail());
			temp.setPhone(e.getHome().getPhone());
			emps.add(temp);
		}
		return emps;
	}
}
