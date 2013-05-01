package jhu.swe645.ejb.session;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import jhu.swe645.ejb.entity.EmplConciseInfo;

/**
 * This is the service endpoint interface for the employee search web service.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
@Remote
@WebService
public interface EmployeeService {
	public List<EmplConciseInfo> getAllEmployees();

	public List<EmplConciseInfo> searchEmployees(String searchTerm);
}
