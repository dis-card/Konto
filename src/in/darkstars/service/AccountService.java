package in.darkstars.service;

import in.darkstars.dao.AccountDao;
import in.darkstars.dao.CityDao;
import in.darkstars.dao.DaoFactory;
import in.darkstars.dto.City;
import in.darkstars.dto.SavingAccount;
import in.darkstars.dto.Transaction;
import in.darkstars.exception.AccountAlreadyExistException;
import in.darkstars.exception.AccountNotFoundException;
import in.darkstars.exception.CustomerNotFoundException;
import in.darkstars.exception.DataAccessException;
import in.darkstars.exception.InsufficientDepositException;
import in.darkstars.exception.TransactionNotSupportedException;
import in.darkstars.helper.Constants;

import java.util.List;


/**
 * @author Vikash
 * 
 * Purpose :- Service class for accounts related operations.
 *
 */
public class AccountService implements Service {
	
	public List<City> getCityList() throws DataAccessException
	{
		CityDao cityDao = (CityDao) DaoFactory.getDao(Constants.city);
		return cityDao.selectAll();
	}

	/**
	 * @throws DataAccessException 
	 * @throws AccountAlreadyExistException 
	 * @throws CustomerIdNotFoundException 
	 * @throws InsufficientAmountException 
	 * 
	 */
	public int openAccount( SavingAccount account ) throws DataAccessException, InsufficientDepositException, CustomerNotFoundException, AccountAlreadyExistException {
		
		if ( account.getAccountType().equals(Constants.regularSavingAccount) && account.getInitialDeposit() < 100.0f )
			throw new InsufficientDepositException();
		AccountDao accountDao = (AccountDao) DaoFactory.getDao(Constants.account);
		return accountDao.save(account);
		
	}

	public double performTransaction( Transaction transaction ) throws TransactionNotSupportedException, CustomerNotFoundException, DataAccessException, AccountNotFoundException, InsufficientDepositException {
		
		double balance = 0;
		AccountDao accountDao = (AccountDao) DaoFactory.getDao(Constants.account);
		if (transaction.getTransactionType().equals(Constants.withdraw))
		{
			balance = accountDao.withdraw(transaction);
		}
		else if ( transaction.getTransactionType().equals(Constants.deposit) )
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
