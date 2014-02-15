package in.darkstars.helper;


/**
 * @author Vikash
 *
 *	Purpose :- All the constants used in the application are present in this class as strings.
 */
public class Constants {
	
	/* Queries */
	public static final String insertCustomerQuery = "INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,DATEOFBIRTH,STREETNAME,STREETNUMBER,CITY,PIN) VALUES(?,?,?,?,?,?,?)";
	public static final String insertRegularSavingAccountQuery = "INSERT INTO REGULARSAVINGSACCOUNT(CUSTOMERID,CITYACRONYM,INITIALDEPOSIT,OPENINGDATE) VALUES(?,?,?,?)";
	public static final String insertSalarySavingAccountQuery = "INSERT INTO SALARYSAVINGSACCOUNT(CUSTOMERID,CITYACRONYM,INITIALDEPOSIT,OPENINGDATE) VALUES(?,?,?,?)";
	public static final String selectRegularSavingAccountQuery="SELECT CUSTOMERID, ACCOUNTNO, CITYACRONYM, INITIALDEPOSIT, OPENINGDATE FROM REGULARSAVINGSACCOUNT WHERE CUSTOMERID = ?";
	public static final String selectSalarySavingAccountQuery= "SELECT CUSTOMERID, ACCOUNTNO, CITYACRONYM, INITIALDEPOSIT, OPENINGDATE FROM SALARYSAVINGSACCOUNT  WHERE CUSTOMERID = ?";
	public static final String updateRegularSavingAccountQuery = "UPDATE REGULARSAVINGSACCOUNT SET INITIALDEPOSIT=? WHERE CUSTOMERID=?";
	public static final String updateSalarySavingAccountQuery = "UPDATE SALARYSAVINGSACCOUNT SET INITIALDEPOSIT=? WHERE CUSTOMERID=?";
	public static final String selectCustomerQuery="SELECT FIRSTNAME FROM CUSTOMER WHERE ID = ?";
	public static final String selectCityQuery = "SELECT ACRONYM, NAME FROM CITY";
	
	/* Exception Messages */
	public static final String dataAccessExceptionMessage = "Problem in connection to the database, please contact the customer care services.";
	public static final String insufficientDepositExceptionMessage = "Deposit is not sufficient";
	public static final String customerNotFoundExceptionMessage = "Customer doesn't exist.";
	public static final String accountAlreadyExistExceptionMessage = "Customer already have an account";
	public static final String accountNotFoundExceptionMessage = "Customer account doesn't exist.";
	public static final String transactionNotSupportedExceptionMessage = "Invalid transaction type.";
	public static final String accountTypeNotSupportedExceptionMessage = "Chosen account type is not supported.";
	
	/* Success Messages */
	public static final String customerRegistrationSuccessfulMessage = "Customer registration is successful and customer id is";
	public static final String openingAccountSuccessfulMessage = "Savings account successfully opened and account id is ";
	public static final String transactionSuccessfulMessage = "Transaction is successful, balance after transaction is ";
	
	/* Other Constants */
	public static final String dataSource="java:comp/env/bankDataSource";
	public static final String customer = "customer";
	public static final String city="city";
	public static final String account = "account";
	public static final String regularSavingAccount = "RS";
	public static final String salarySavingAccount = "SS";
	public static final String deposit = "D";
	public static final String withdraw = "W";
}
