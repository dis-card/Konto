package in.darkstars.presentation.backingbean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import in.darkstars.dto.Transaction;
import in.darkstars.exception.AccountNotFoundException;
import in.darkstars.exception.CustomerNotFoundException;
import in.darkstars.exception.DataAccessException;
import in.darkstars.exception.InsufficientDepositException;
import in.darkstars.exception.TransactionNotSupportedException;
import in.darkstars.helper.Constants;
import in.darkstars.service.AccountService;
import in.darkstars.service.ServiceFactory;


public class TransactionBean {
	
	private String customerId;
	private String accountType;
	private String transactionType;
	private String amount;
	private String outcome = "success";
	
	private String msg;
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

	private void reset()
	{
		this.amount = null;
		this.customerId = null;
	}
	
	public String performTransaction() throws IllegalAccessException, InvocationTargetException
	{
		AccountService accountService = (AccountService) ServiceFactory.getService(Constants.account);
		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transaction, this);
		try {
			double balance = accountService.performTransaction(transaction);
			msg = Constants.transactionSuccessfulMessage+" "+balance;
			reset();
		} catch (TransactionNotSupportedException e) {
			errorMsg = Constants.transactionNotSupportedExceptionMessage;
		} catch (CustomerNotFoundException e) {
			errorMsg = Constants.customerNotFoundExceptionMessage;
		} catch (DataAccessException e) {
			errorMsg = Constants.dataAccessExceptionMessage;
		} catch (AccountNotFoundException e) {
			errorMsg = Constants.accountNotFoundExceptionMessage;
		} catch (InsufficientDepositException e) {
			errorMsg = Constants.insufficientDepositExceptionMessage;
		}
		return outcome;
	}

}
