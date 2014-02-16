package in.darkstars.konto.service;

import in.darkstars.konto.helper.Constants;

/**
 * @author Vikash
 * 
 */
public class ServiceFactory {

	private static CustomerService customerService;
	private static AccountService accountService;

	public static Service getService(String serviceType) {
		Service service = null;
		if (serviceType.equals(Constants.CUSTOMER)) {
			
			if (customerService == null )
			{
				synchronized (CustomerService.class) {
					if (customerService == null)
						customerService = new CustomerService();
				}
			}
			service = customerService;
		} else if (serviceType.equals(Constants.ACCOUNT)) {
			
			if (accountService == null )
			{
				synchronized (AccountService.class) {
					if (accountService == null)
						accountService = new AccountService();
				}
			}
			service = accountService;
		}
		return service;
	}
}
