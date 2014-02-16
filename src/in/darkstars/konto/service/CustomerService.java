package in.darkstars.konto.service;

import in.darkstars.konto.dao.CustomerDao;
import in.darkstars.konto.dao.DaoFactory;
import in.darkstars.konto.dto.Customer;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.helper.Constants;

/**
 * @author Vikash
 *
 */
public class CustomerService implements Service{
	
	public int register(Customer customer) throws DataAccessException
	{
		System.out.println("In customer Service");
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDao(Constants.CUSTOMER);
		return customerDao.save(customer);
	}
	
	
	
	

}
