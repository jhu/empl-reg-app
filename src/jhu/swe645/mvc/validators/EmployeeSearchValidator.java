package jhu.swe645.mvc.validators;

import jhu.swe645.mvc.models.EmployeeSearchForm;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This will be used to check if the search term is blank or not.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
public class EmployeeSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeSearchForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "searchTerm", "emptySearchTerm");
	}
}
