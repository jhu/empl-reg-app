package jhu.swe645.mvc.validators;

import javax.naming.NamingException;

import jhu.swe645.ejb.session.EmployeeManagerRemote;
import jhu.swe645.mvc.models.EmployeeRegistrationForm;
import jhu.swe645.utils.ApplicationUtil;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This will be used to validate the employee registration form. This will check
 * for required fields (first and last name, email address) and also checks if
 * the email address is unique or not.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
public class EmployeeFormValidator implements Validator {
	private static String emplManagerRef = "EmployeeManagerEjb";
	private final Logger log = Logger.getLogger(EmployeeFormValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeRegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		log.info("validating the form...");
		EmployeeRegistrationForm employee = (EmployeeRegistrationForm) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lastName", "requiredvalue");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "firstName", "requiredvalue");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "requiredvalue");

		// Need to get ejb
		EmployeeManagerRemote dao = null;

		try {
			dao = (EmployeeManagerRemote) ApplicationUtil.getEjbReference(emplManagerRef);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

		if (dao.hasEmail(employee.getEmail())) {
			e.rejectValue("email", "emailexists", "Email already exists");
		}
	}

}
