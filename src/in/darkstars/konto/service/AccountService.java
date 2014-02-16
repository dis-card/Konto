package in.darkstars.konto.service;

import in.darkstars.konto.dao.AccountDao;
import in.darkstars.konto.dao.CityDao;
import in.darkstars.konto.dao.DaoFactory;
import in.darkstars.konto.dto.City;
import in.darkstars.konto.dto.SavingAccount;
import in.darkstars.konto.dto.Transaction;
import in.darkstars.konto.exception.AccountAlreadyExistException;
import in.darkstars.konto.exception.AccountNotFoundException;
import in.darkstars.konto.exception.CustomerNotFoundException;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.exception.InsufficientDepositException;
import in.darkstars.konto.exception.TransactionNotSupportedException;
import in.darkstars.konto.helper.Constants;

import java.util.List;


/**
 * @author Vikash
 * 
 * Purpose :- Service class for accounts related operations.
 *
 */
public class AccountService implements Service {
	
	/* 
	 * getCityList() :- returns the city list in which bank has branches. 
	 * 
	 */
	
	public List<City> getCityList() throws DataAccessException
	{
		CityDao cityDao = (CityDao) DaoFactory.getDao(Constants.CITY);
		return cityDao.selectAll();
	}

	/**
	 * @throws DataAccessException 
	 * @throws AccountAlreadyExistException 
	 * @throws AccountTypeNotSupportedException 
	 * @throws CustomerIdNotFoundException 
	 * @throws InsufficientAmountException 
	 * 
	 */
	
	/*
	 *  openAccount(account) :- opens account for the customer and returns account id. 
	 *  
	 */
	
	public int openAccount( SavingAccount account ) throws DataAccessException, InsufficientDepositException, CustomerNotFoundException, AccountAlreadyExistException{
		
		if ( account.getAccountType().equals(Constants.REGULARSAVINGACCOUNT) && account.getInitialDeposit()  < 100.0f )
			throw new InsufficientDepositException();
		AccountDao accountDao = (AccountDao) DaoFactory.getDao(Constants.ACCOUNT);
		return accountDao.save(account);
		
	}

	/*
	 *  performTransaction(tansaction) :- performs transactions and returns the current balance to the end user. 
	 *  
	 */
	
	public double performTransaction( Transaction transaction ) throws TransactionNotSupportedException, CustomerNotFoundException, DataAccessException, AccountNotFoundException, InsufficientDepositException {
		
		double balance = 0;
		AccountDao accountDao = (AccountDao) DaoFactory.getDao(Constants.ACCOUNT);
		if (transaction.getTransactionType().equals(Constants.WITHDRAW))
		{
			balance = accountDao.withdraw(transaction);
		}
		else if ( transaction.getTransactionType().equals(Constants.DEPOSIT) )
		{
			balance = accountDao.deposit(transaction);
		}
		else
		{
			throw new TransactionNotSupportedException();
		}
		return balance;
		
	}
	
}
