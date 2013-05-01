package jhu.swe645.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import jhu.swe645.ejb.entity.Employee;
import jhu.swe645.ejb.entity.Location;
import jhu.swe645.ejb.entity.Supervisor;
import jhu.swe645.ejb.session.EmployeeManagerRemote;
import jhu.swe645.mvc.models.EmployeeRegistrationForm;
import jhu.swe645.mvc.validators.EmployeeFormValidator;
import jhu.swe645.utils.ApplicationUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This code will be used as one of 2 Spring MVC's controllers for the employee
 * registration application. Specifically, this controller deals with the
 * employee registration form.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */

@Controller
@RequestMapping("/RegisterEmployee")
public class RegistrationController {
	private static String FORMVIEW = "EmployeeForm";
//	private static String LISTVIEW = "Employees";
	private static String emplManagerRef = "EmployeeManagerEjb";
	private final Logger log = Logger.getLogger(RegistrationController.class);
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof EmployeeRegistrationForm) {
			binder.setValidator(new EmployeeFormValidator());
			log.info("setting the validator for EmployeeRegistrationForm");
		}	
	}
	
	/**
	 * to initialize the employee registration form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String intializeForm(Model model) {		
		log.info("GET method is called to initialize the registration form");
		EmployeeManagerRemote employeeManager = null;
		EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm();
		List<Supervisor> supervisors = null;
		try{
			employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
			supervisors = employeeManager.getAllSupervisors();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("supervisors", supervisors);
		model.addAttribute("employeeRegistrationForm", employeeRegistrationForm);
		
		return FORMVIEW;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String saveEmployeeForm(@Valid EmployeeRegistrationForm employeeRegistrationForm, BindingResult result, Model model) {
		EmployeeManagerRemote employeeManager = null;
		Employee employee = null;
		
		try{
			// get a reference to the EJB
			employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!result.hasErrors()) {
			employee = new Employee();
			employee.setFirstName(employeeRegistrationForm.getFirstName());
			employee.setLastName(employeeRegistrationForm.getLastName());
			employee.setMiddleInitial(employeeRegistrationForm.getMiddleInitial());
			employee.setEmail(employeeRegistrationForm.getEmail());
			employee.setJobTitle(employeeRegistrationForm.getJobTitle());
			Location home = new Location();
			home.setStreet(employeeRegistrationForm.getStreet());
			home.setCity(employeeRegistrationForm.getCity());
			home.setState(employeeRegistrationForm.getState());
			home.setZipCode(employeeRegistrationForm.getZipCode());
			home.setPhone(employeeRegistrationForm.getPhoneNumber());
			Location office = new Location();
			office.setStreet(employeeRegistrationForm.getWorkStreet());
			office.setCity(employeeRegistrationForm.getWorkCity());
			office.setState(employeeRegistrationForm.getWorkState());
			office.setZipCode(employeeRegistrationForm.getWorkZipCode());
			office.setPhone(employeeRegistrationForm.getWorkPhoneNumber());
			employee.setHome(home);
			employee.setOffice(office);
			employee.setSupervisor(employeeManager.lookupSupervisor(employeeRegistrationForm.getSupervisor()));
			long empId = employeeManager.saveEmployee(employee);
//				employeeRegistrationForm.setId(empId);
//				model.addAttribute("actionMessage", "Employee registration Successfully Saved");
			model.addAttribute("employeeRegistrationForm", new EmployeeRegistrationForm());	
			return "redirect:/SweMvc/ShowEmployees/view/" + empId;
//			return LISTVIEW;
		} else {
			log.info("the form has errors...");
			
			List<Supervisor> supervisors = employeeManager.getAllSupervisors();
			
			model.addAllAttributes(result.getAllErrors());
			model.addAttribute("supervisors", supervisors);
			model.addAttribute("employeeRegistrationForm", employeeRegistrationForm);
			return FORMVIEW;
		}
	}
}
