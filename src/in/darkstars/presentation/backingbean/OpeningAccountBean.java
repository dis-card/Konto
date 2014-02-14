package in.darkstars.presentation.backingbean;

import in.darkstars.dto.City;
import in.darkstars.dto.SavingAccount;
import in.darkstars.exception.AccountAlreadyExistException;
import in.darkstars.exception.CustomerNotFoundException;
import in.darkstars.exception.DataAccessException;
import in.darkstars.exception.InsufficientDepositException;
import in.darkstars.helper.Constants;
import in.darkstars.service.AccountService;
import in.darkstars.service.ServiceFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Vikash
 * 
 * Purpose :- Act as the backing bean class for the account opening page.
 * 
 */
public class OpeningAccountBean {

	/* Store customer's identification number.*/
	private String customerId;
	
	/* Store bank's available branch city list.*/
	private List<City> preferredCityList;
	
	/* Store where the customer wants to open an account. */
	private String preferredCity;
	
	/* Store customer's initial deposit in the account.*/
	private double initialDeposit;
	
	/* Store customer's account type i.e "Regular Saving" or "Salary Saving".*/
	private String accountType;
	
	/* Store customer's account identification number, this will be generated after customer opened an account.*/
	private int accountId;
	
	/* Store error messages which occur during account opening. */
	private String errorMsg;
	
	/* Store success messages. */
	private String msg;
	
	/* Store customer's bank account opening date. */
	private Date openingDate;
	
	/**
	 * @return the openingDate
	 */
	public Date getOpeningDate() {
		
		if (openingDate == null)
		{
			openingDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		}
		return openingDate;
	}
	/**
	 * @param openingDate the openingDate to set
	 */
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	private String outcome="success";

	/**
	 * @return the preferredCity
	 */
	public String getPreferredCity() {
		return preferredCity;
	}
	/**
	 * getPrefferedCityList() :- sets the preferred city list, it fetches the city list from the database.
	 * @return the preferredCityList
	 */
	public List<City> getPreferredCityList() {
		AccountService accountService = (AccountService) ServiceFactory
				.getService(Constants.account);
		try {
			preferredCityList = accountService.getCityList();
		} catch (DataAccessException e) {
			e.printStackTrace();
			errorMsg = Constants.dataAccessExceptionMessage;
		}
		return preferredCityList;
	}

	/**
	 * @param preferredCityList the preferredCityList to set
	 */
	public void setPreferredCityList(List<City> preferredCityList) {
		this.preferredCityList = preferredCityList;
	}

	/**
	 * @param preferredCity the preferredCity to set
	 */
	public void setPreferredCity(String preferredCity) {
		this.preferredCity = preferredCity;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the initialDeposit
	 */
	public double getInitialDeposit() {
		return initialDeposit;
	}

	/**
	 * @param initialDeposit
	 *            the initialDeposit to set
	 */
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

		
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
	public String reset()
	{
		this.customerId = null;
		this.preferredCity = null;
		this.initialDeposit = 0;
		this.accountType = null;
		this.openingDate = null;
		this.preferredCity = null;
		this.accountType = null;
		return outcome;
	}
	
	/* open() :- Workhorse method of this class, it will service layer methods for opening account. */
	public String open() throws IllegalAccessException, InvocationTargetException
	{
		
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingAccount, this);
		AccountService accountService = (AccountService)ServiceFactory.getService(Constants.account);
			try {
				accountId = accountService.openAccount(savingAccount);
				msg = Constants.openingAccountSuccessfulMessage;
				reset();
			} catch (DataAccessException e) {
				e.printStackTrace();
				errorMsg = Constants.dataAccessExceptionMessage;
			}
			catch (InsufficientDepositException e )
			{
				errorMsg = Constants.insufficientDepositExceptionMessage;
			}
			catch (AccountAlreadyExistException e )
			{
				errorMsg = Constants.accountAlreadyExistExceptionMessage;
			}
			catch (CustomerNotFoundException e )
			{
				errorMsg = Constants.customerNotFoundExceptionMessage;
			}
		return outcome;
	}

}
