package in.darkstars.dao;

import in.darkstars.helper.Constants;

/**
 * @author Vikash
 * 
 * Purpose :- factory class for the data access objects.
 * 
 */
public class DaoFactory {

	private static CustomerDao customerDao;
	private static CityDao cityDao;
	private static AccountDao accountDao;

	/* getDao() :- returns the dao class of the respective type ( as supplied in the argument ) */
	public static Dao getDao(String daoType) {
		Dao dao = null;
		if (daoType.equals(Constants.customer)) {
			if (customerDao == null)
			{
				synchronized (CustomerDao.class) {
					if (customerDao == null)
						customerDao = new CustomerDao();
				}
				
			}			
			dao = customerDao;
		}
		else if (daoType.equals(Constants.city)) {

			if (cityDao == null)
			{
				synchronized (CityDao.class) {
					if (cityDao == null)
						cityDao = new CityDao();
				}
				
			}		
			dao = cityDao;
		}
		else if (daoType.equals(Constants.account)) {
			
			if (accountDao == null)
			{
				synchronized(AccountDao.class)
				{
					if (accountDao == null)
					{
						accountDao = new AccountDao();
					}
				}
			}
			dao = accountDao;
		}
		return dao;
	}
}
