package in.darkstars.konto.backingbean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import in.darkstars.konto.dto.Transaction;
import in.darkstars.konto.exception.AccountNotFoundException;
import in.darkstars.konto.exception.CustomerNotFoundException;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.exception.InsufficientDepositException;
import in.darkstars.konto.exception.TransactionNotSupportedException;
import in.darkstars.konto.helper.Constants;
import in.darkstars.konto.service.AccountService;
import in.darkstars.konto.service.ServiceFactory;


public class TransactionBean {
	
	/* customerId :- stores the customer identification number.*/
	private String customerId;
	
	/* accountType :- stores the account type of the customer. */
	private String accountType;
	
	/* transactionType :- stores the type transaction customer is willing to perform. */
	private String transactionType;
	
	/* amount :- stores the amount for the current transaction. */
	private String amount;
	
	/* outcome :- stores the outcome which will be used by the controller to switch to proper view. */
	private String outcome = "success";
	
	/* msg :- stores success message if any. */
	private String msg;
	
	/* errorMsg :- stores error message, if any. */
	private String errorMsg;
	


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/*
	 *  reset() :- used to reset the fields.
	 * 
	 */

	private void reset()
	{
		this.amount = null;
		this.customerId = null;
	}
	
	/*
	 *  performTransaction() :- calls the appropriate service layer method for doing transactions.
	 *  
	 */
	public String performTransaction() throws IllegalAccessException, InvocationTargetException
	{
		AccountService accountService = (AccountService) ServiceFactory.getService(Constants.ACCOUNT);
		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transaction, this);
		try {
			double balance = accountService.performTransaction(transaction);
			msg = Constants.TRANSACTIONSUCCESSFULMESSAGE+" "+balance;
			reset();
		} catch (TransactionNotSupportedException e) {
			errorMsg = Constants.TRANSACTIONNOTSUPPORTEDEXCEPTIONMESSAGE;
		} catch (CustomerNotFoundException e) {
			errorMsg = Constants.CUSTOMERNOTFOUNDEXCEPTIONMESSAGE;
		} catch (DataAccessException e) {
			errorMsg = Constants.DATAACCESSEXCEPTIONMESSAGE;
		} catch (AccountNotFoundException e) {
			errorMsg = Constants.ACCOUNTNOTFOUNDEXCEPTIONMESSAGE;
		} catch (InsufficientDepositException e) {
			errorMsg = Constants.INSUFFICIENTDEPOSITEXCEPTIONMESSAGE;
		}
		return outcome;
	}

}
