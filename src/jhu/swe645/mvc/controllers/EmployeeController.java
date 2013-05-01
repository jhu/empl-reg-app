package jhu.swe645.mvc.controllers;

import java.util.List;

import javax.naming.NamingException;
import javax.validation.Valid;

import jhu.swe645.ejb.entity.Employee;
import jhu.swe645.ejb.session.EmployeeManagerRemote;
import jhu.swe645.mvc.models.EmployeeSearchForm;
import jhu.swe645.mvc.validators.EmployeeSearchValidator;
import jhu.swe645.utils.ApplicationUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This code will be used as one of 2 Spring MVC's controllers for the employee
 * registration application. Specifically, this controller deals with the
 * displaying all of registered employees that were saved in the database or
 * some form of persistence. Also it will have search function to search for
 * employees by their first name, last name and home phone number
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */

@Controller
@RequestMapping("/ShowEmployees")
public class EmployeeController {
	private static String LISTVIEW = "Employees";
	private static String INDIVIDUALVIEW = "EmployeeProfile";
	private static String emplManagerRef = "EmployeeManagerEjb";
	private final Logger log = Logger.getLogger(EmployeeController.class);
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof EmployeeSearchForm) {
			binder.setValidator(new EmployeeSearchValidator());
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAllEmployees(Model model) {
		EmployeeManagerRemote employeeManager = null;
		try {
			employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		if (employeeManager != null){
			log.info("Getting all employees");
			model.addAttribute("employees", employeeManager.getAllEmployees());
			model.addAttribute("employeeSearchForm", new EmployeeSearchForm());
		}
		return LISTVIEW;	
	}
	
	@RequestMapping(value="view/{emplPk}", method=RequestMethod.GET)
	public String viewEmployee(@PathVariable long emplPk, Model model) throws Exception{
		EmployeeManagerRemote employeeManager = null;
		Employee employee = null;
		try {
			employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
			employee = employeeManager.getEmployee(emplPk);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.info("Getting an employee of the emp_id: " + emplPk);
		
		if(employee==null){
			throw new Exception("Employee " + emplPk + " not found.");
		}
		model.addAttribute("employee", employee);
		
		return INDIVIDUALVIEW;
	}
	
	@RequestMapping(value="delete/{emplPk}", method=RequestMethod.GET)
	public String deleteEmployee(@PathVariable long emplPk, Model model){
		EmployeeManagerRemote employeeManager = null;
		String actionMessage = "";
		try {
			employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		log.info("DELETE command is called for emp_id: " + emplPk);
		boolean status = employeeManager.deleteEmployee(emplPk);
		if(status){
			log.info("Success! Employee with emp_id: " + emplPk + " was deleted.");
		} else {
			log.info("Fail! Employee with emp_id: " + emplPk + " cannot be deleted.");
			actionMessage = "Failed to delete the employee";
		}
		model.addAttribute(actionMessage);
		model.addAttribute("employees", employeeManager.getAllEmployees());
		model.addAttribute("employeeSearchForm", new EmployeeSearchForm());
		return "redirect:/SweMvc/ShowEmployees";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String searchEmployee(@Valid EmployeeSearchForm employeeSearchForm, BindingResult result, Model model){
		EmployeeManagerRemote employeeManager = null;
		List<Employee> employees = null;
		
		if (!result.hasErrors()) {
			log.info("SEARCH command is called for a search term: " + employeeSearchForm.getSearchTerm());
			try {
				employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
				employees = employeeManager.searchEmployees(employeeSearchForm.getSearchTerm());
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			if (employees != null && !employees.isEmpty()) {
				model.addAttribute("employees", employees);
				model.addAttribute("employeeSearchForm", new EmployeeSearchForm());
				
			} else {
				model.addAttribute("actionMessage","No employees were found that matched your search.");
				model.addAttribute("employees", employeeManager.getAllEmployees());
				model.addAttribute("employeeSearchForm", employeeSearchForm);
			}
			return LISTVIEW;	
			
		} else {
			log.info("the search form has errors...");
			try {
				employeeManager = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
				employees = employeeManager.getAllEmployees();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			model.addAllAttributes(result.getAllErrors());
			model.addAttribute("employees", employees);
			model.addAttribute("employeeSearchForm", employeeSearchForm);
			return LISTVIEW;
		}
	}
}
