package in.darkstars.service;

import in.darkstars.dao.CustomerDao;
import in.darkstars.dao.DaoFactory;
import in.darkstars.dto.Customer;
import in.darkstars.exception.DataAccessException;
import in.darkstars.helper.Constants;

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
