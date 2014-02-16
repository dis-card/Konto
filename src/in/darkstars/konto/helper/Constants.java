package in.darkstars.konto.helper;


/**
 * @author Vikash
 *
 *	Purpose :- All the constants used in the application are present in this class as strings.
 */
public class Constants {
	
	/* Queries */
	public static final String INSERTCUSTOMERQUERY = "INSERT INTO CUSTOMER(FIRSTNAME,LASTNAME,DATEOFBIRTH,STREETNAME,STREETNUMBER,CITY,PIN) VALUES(?,?,?,?,?,?,?)";
	public static final String INSERTREGULARSAVINGACCOUNTQUERY = "INSERT INTO REGULARSAVINGSACCOUNT(CUSTOMERID,CITYACRONYM,INITIALDEPOSIT,OPENINGDATE) VALUES(?,?,?,?)";
	public static final String INSERTSALARYSAVINGACCOUNTQUERY = "INSERT INTO SALARYSAVINGSACCOUNT(CUSTOMERID,CITYACRONYM,INITIALDEPOSIT,OPENINGDATE) VALUES(?,?,?,?)";
	public static final String SELECTREGULARSAVINGACCOUNTQUERY="SELECT CUSTOMERID, ACCOUNTNO, CITYACRONYM, INITIALDEPOSIT, OPENINGDATE FROM REGULARSAVINGSACCOUNT WHERE CUSTOMERID = ?";
	public static final String SELECTSALARYSAVINGACCOUNTQUERY= "SELECT CUSTOMERID, ACCOUNTNO, CITYACRONYM, INITIALDEPOSIT, OPENINGDATE FROM SALARYSAVINGSACCOUNT  WHERE CUSTOMERID = ?";
	public static final String UPDATEREGULARSAVINGACCOUNTQUERY = "UPDATE REGULARSAVINGSACCOUNT SET INITIALDEPOSIT=? WHERE CUSTOMERID=?";
	public static final String UPDATESALARYSAVINGACCOUNTQUERY = "UPDATE SALARYSAVINGSACCOUNT SET INITIALDEPOSIT=? WHERE CUSTOMERID=?";
	public static final String SELECTCUSTOMERQUERY="SELECT FIRSTNAME FROM CUSTOMER WHERE ID = ?";
	public static final String SELECTCITYQUERY = "SELECT ACRONYM, NAME FROM CITY";
	
	/* Exception Messages */
	public static final String DATAACCESSEXCEPTIONMESSAGE = "Problem in connection to the database, please contact the customer care services.";
	public static final String INSUFFICIENTDEPOSITEXCEPTIONMESSAGE = "Deposit is not sufficient";
	public static final String CUSTOMERNOTFOUNDEXCEPTIONMESSAGE = "Customer doesn't exist.";
	public static final String ACCOUNTALREADYEXISTEXCEPTIONMESSAGE = "Customer already have an account";
	public static final String ACCOUNTNOTFOUNDEXCEPTIONMESSAGE = "Customer account doesn't exist.";
	public static final String TRANSACTIONNOTSUPPORTEDEXCEPTIONMESSAGE = "Invalid transaction type.";
	
	/* Success Messages */
	public static final String CUSTOMERREGISTRATIONSUCCESSFULMESSAGE = "Customer registration is successful and customer id is";
	public static final String OPENINGACCOUNTSUCCESSFULMESSAGE = "Savings account successfully opened and account id is ";
	public static final String TRANSACTIONSUCCESSFULMESSAGE = "Transaction is successful, balance after transaction is ";
	
	/* Other Constants */
	public static final String DATASOURCE="java:comp/env/bankDataSource";
	public static final String CUSTOMER = "customer";
	public static final String CITY="city";
	public static final String ACCOUNT = "account";
	public static final String REGULARSAVINGACCOUNT = "RS";
	public static final String SALARYSAVINGACCOUNT = "SS";
	public static final String DEPOSIT = "D";
	public static final String WITHDRAW = "W";
}
