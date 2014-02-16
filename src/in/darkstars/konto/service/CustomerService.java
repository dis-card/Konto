package in.darkstars.konto.service;

import in.darkstars.konto.dao.CustomerDao;
import in.darkstars.konto.dao.DaoFactory;
import in.darkstars.konto.dto.Customer;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.helper.Constants;

/**
 * @author Vikash
 * 
 * Purpose	:-	Service class for Customer related operations.
 *
 */
public class CustomerService implements Service{
	
	/* 
	 * register(customer) :- register the customer with the bank and returns a customer id. 
	 * 
	 */
	
	public int register(Customer customer) throws DataAccessException
	{
		System.out.println("In customer Service");
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDao(Constants.CUSTOMER);
		return customerDao.save(customer);
	}
	
	
	
	

}
