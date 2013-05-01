package jhu.swe645.mvc.models;

/**
 * This is a model to hold the search term that is to be passed between view and
 * controller in Spring MVC architecture.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
public class EmployeeSearchForm {
//	@NotNull(message = "Search term must not be null.")
//	@Size(min=1, max=50)
	private String searchTerm;
	
	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@Override
	public String toString() {
		return "EmployeeSearchForm [searchTerm=" + searchTerm + "]";
	}
	
	
}
