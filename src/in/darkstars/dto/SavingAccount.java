package in.darkstars.dto;

import java.io.Serializable;
import java.sql.Date;

/*
 *  Author :- Vikash
 *  
 *  Purpose :- data transfer object for Saving Accounts.
 */
public class SavingAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7172375573645136796L;
	private String customerId;
	private String preferredCity;
	private Double initialDeposit;
	private String accountNumber;
	private String accountType;
	private Date openingDate;
	/**
	 * @return the openingDate
	 */
	public Date getOpeningDate() {
		return openingDate;
	}
	/**
	 * @param openingDate the openingDate to set
	 */
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPreferredCity() {
		return preferredCity;
	}
	public void setPreferredCity(String preferredCity) {
		this.preferredCity = preferredCity;
	}
	/**
	 * @return the initialDeposit
	 */
	public Double getInitialDeposit() {
		return initialDeposit;
	}
	/**
	 * @param initialDeposit the initialDeposit to set
	 */
	public void setInitialDeposit(Double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
