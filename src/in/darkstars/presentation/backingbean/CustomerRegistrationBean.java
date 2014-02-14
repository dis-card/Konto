package in.darkstars.presentation.backingbean;

import in.darkstars.dto.Customer;
import in.darkstars.exception.DataAccessException;
import in.darkstars.helper.Constants;
import in.darkstars.service.CustomerService;
import in.darkstars.service.ServiceFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Vikash
 * 
 * Purpose :- Backing bean class for the customer registration page.
 * 
 */
public class CustomerRegistrationBean {
	
	/* Store customer's first name.*/
	private String firstName;
	/* Store customer's last name. */
	private String lastName;
	/* Store customer date of birth. */
	private Date dateOfBirth;
	/* Store customer's street name on which he resides. */
	private String streetName;
	/* Store customer's residence street number. */
	private String streetNumber;
	/* Stores customer's city of residence. */
	private String city;
	/* Store customer's residence pin code. */
	private String pin;
	/* Store customer's state of residence. */
	private String state;
	/* Stores customer's id, it will be generated after he is registered with the bank. */
	private int customerId;

	/* Stores error messages */
	private String errorMsg = null;
	
	/* Stores success messages */
	private String msg = null;

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city.trim();
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private String outcome = "success";

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state.trim();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName.trim();
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber.trim();
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin.trim();
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		
		if (dateOfBirth == null)
		{
			dateOfBirth = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		}
		else if ( dateOfBirth.compareTo( new java.sql.Date(Calendar.getInstance().getTime().getTime())) != 0 )
		{
			dateOfBirth = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		}
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	public String reset()
	{
		this.firstName = "";
		this.lastName = "";
		this.dateOfBirth = null;
		this.streetName = "";
		this.streetNumber = "";
		this.city = "";
		this.pin = "";
		this.state = ""; 
		return outcome;
	}

	/* register() :- calls service layer for customer registration */
	
	public String register() throws IllegalAccessException,
			InvocationTargetException {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customer, this);
		CustomerService customerService = (CustomerService) ServiceFactory
				.getService(Constants.customer);
		try {
			customerId = customerService.register(customer);
			msg = Constants.customerRegistrationSuccessfulMessage;
			reset();
		} catch (DataAccessException e) {
			errorMsg = Constants.dataAccessExceptionMessage;
		}
		return outcome;
	}
}
