package in.darkstars.konto.backingbean;

import in.darkstars.konto.dto.City;
import in.darkstars.konto.dto.SavingAccount;
import in.darkstars.konto.exception.AccountAlreadyExistException;
import in.darkstars.konto.exception.CustomerNotFoundException;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.exception.InsufficientDepositException;
import in.darkstars.konto.helper.Constants;
import in.darkstars.konto.service.AccountService;
import in.darkstars.konto.service.ServiceFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Vikash
 * 
 *         Purpose :- Act as the backing bean class for the account opening
 *         page.
 * 
 */
public class OpeningAccountBean {

	/* Store customer's identification number. */
	private String customerId;

	/* Store bank's available branch city list. */
	private List<City> preferredCityList;

	/* Store where the customer wants to open an account. */
	private String preferredCity;

	/* Store customer's initial deposit in the account. */
	private String initialDeposit;

	/* Store customer's account type i.e "Regular Saving" or "Salary Saving". */
	private String accountType;

	/* Store error messages which occur during account opening. */
	private String errorMsg;

	/* Store success messages. */
	private String msg;

	/* Store customer's bank account opening date. */
	private Date openingDate;

	/*
	 * Store customer's account identification number, this will be generated
	 * after customer opened an account.
	 */
	private int accountId = -1;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the openingDate
	 */
	public Date getOpeningDate() {

		if (openingDate == null) {
			openingDate = new java.sql.Date(Calendar.getInstance().getTime()
					.getTime());
		} else if (openingDate.compareTo(new java.sql.Date(Calendar
				.getInstance().getTime().getTime())) != 0) {
			openingDate = new java.sql.Date(Calendar.getInstance().getTime()
					.getTime());
		}
		return openingDate;
	}

	/**
	 * @param openingDate
	 *            the openingDate to set
	 */
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	/**
	 * @return the preferredCity
	 */
	public String getPreferredCity() {
		return preferredCity;
	}

	/**
	 * getPrefferedCityList() :- sets the preferred city list, it fetches the
	 * city list from the database.
	 * 
	 * @return the preferredCityList
	 */
	public List<City> getPreferredCityList() {
		AccountService accountService = (AccountService) ServiceFactory
				.getService(Constants.ACCOUNT);
		try {
			preferredCityList = accountService.getCityList();
		} catch (DataAccessException e) {
			e.printStackTrace();
			errorMsg = Constants.DATAACCESSEXCEPTIONMESSAGE;
		}
		return preferredCityList;
	}

	/**
	 * @param preferredCityList
	 *            the preferredCityList to set
	 */
	public void setPreferredCityList(List<City> preferredCityList) {
		this.preferredCityList = preferredCityList;
	}

	/**
	 * @param preferredCity
	 *            the preferredCity to set
	 */
	public void setPreferredCity(String preferredCity) {
		if (preferredCity != null)
			preferredCity = preferredCity.trim();
		this.preferredCity = preferredCity;
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		if (customerId != null)
			customerId = customerId.trim();
		this.customerId = customerId;
	}

	/**
	 * @return the initialDeposit
	 */
	public String getInitialDeposit() {
		return initialDeposit;
	}

	/**
	 * @param initialDeposit
	 *            the initialDeposit to set
	 */
	public void setInitialDeposit(String initialDeposit) {
		if (initialDeposit != null)
			initialDeposit = initialDeposit.trim();
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
		if (accountType != null)
			accountType = accountType.trim();
		this.accountType = accountType;
	}

	/*
	 * reset() :- this method is called to reset the fields.
	 */

	public String reset() {
		setCustomerId(null);
		setPreferredCity(null);
		setAccountType(null);
		setOpeningDate(getOpeningDate());
		setInitialDeposit(null);
		return Constants.SUCCESS;
	}

	/*
	 * open() :- Workhorse method of this class, it will service layer methods
	 * for opening account.
	 */
	public String open() throws IllegalAccessException,
			InvocationTargetException {

		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingAccount, this);
		savingAccount.setInitialDeposit(Double.parseDouble(initialDeposit));
		AccountService accountService = (AccountService) ServiceFactory
				.getService(Constants.ACCOUNT);
		try {

			accountId = accountService.openAccount(savingAccount);
			msg = Constants.OPENINGACCOUNTSUCCESSFULMESSAGE;
			reset();
		} catch (DataAccessException e) {
			e.printStackTrace();
			errorMsg = Constants.DATAACCESSEXCEPTIONMESSAGE;
		} catch (InsufficientDepositException e) {
			errorMsg = Constants.INSUFFICIENTDEPOSITEXCEPTIONMESSAGE;
		} catch (AccountAlreadyExistException e) {
			errorMsg = Constants.ACCOUNTALREADYEXISTEXCEPTIONMESSAGE;
		} catch (CustomerNotFoundException e) {
			errorMsg = Constants.CUSTOMERNOTFOUNDEXCEPTIONMESSAGE;
		}
		return Constants.SUCCESS;
	}

}
