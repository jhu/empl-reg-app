package jhu.swe645.mvc.models;


/**
 * This is a model to hold the employee registration form values that is to be
 * passed between view and controller in Spring MVC architecture.
 * 
 * @author johnson hu (00324459)
 * @date november 25, 2012
 * @course swe 645
 * 
 */
public class EmployeeRegistrationForm {
	
	private long id;
	
//	@NotNull(message="First name is required")
	private String firstName;
	
//	@NotNull(message="Last name is required")
	private String lastName;
	
	private String middleInitial;
	
//	@NotNull(message="Email address is required")
	private String email;
	
	private String jobTitle;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	
	private String workStreet;
	private String workCity;
	private String workState;
	private String workZipCode;
	private String workPhoneNumber;
	
	private String supervisor;
	
	public EmployeeRegistrationForm() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkStreet() {
		return workStreet;
	}

	public void setWorkStreet(String workStreet) {
		this.workStreet = workStreet;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getWorkZipCode() {
		return workZipCode;
	}

	public void setWorkZipCode(String workZipCode) {
		this.workZipCode = workZipCode;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "EmployeeRegistrationForm [id=" + id + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleInitial="
				+ middleInitial + ", email=" + email + ", jobTitle=" + jobTitle
				+ ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber
				+ ", workStreet=" + workStreet + ", workCity=" + workCity
				+ ", workState=" + workState + ", workZipCode=" + workZipCode
				+ ", workPhoneNumber=" + workPhoneNumber + ", supervisor="
				+ supervisor + "]";
	}
	
	
}
