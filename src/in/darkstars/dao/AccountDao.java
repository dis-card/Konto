package in.darkstars.dao;

import in.darkstars.dto.SavingAccount;
import in.darkstars.dto.Transaction;
import in.darkstars.exception.AccountAlreadyExistException;
import in.darkstars.exception.AccountNotFoundException;
import in.darkstars.exception.CustomerNotFoundException;
import in.darkstars.exception.DataAccessException;
import in.darkstars.exception.DataSourceException;
import in.darkstars.exception.InsufficientDepositException;
import in.darkstars.helper.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Vikash
 * 
 *         Pupose :- Data Access class for the Regular Savings and Salary
 *         Savings table.
 * 
 */
public class AccountDao implements Dao {

	/* save():- Persist account related details to the database. */

	public int save(SavingAccount account) throws DataAccessException,
			CustomerNotFoundException, AccountAlreadyExistException {
		int accountId = -1;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		checkCustomer(account.getCustomerId());
		try {
			getAccount(account.getCustomerId(), account.getAccountType());
			throw new AccountAlreadyExistException();
		} catch (AccountNotFoundException e1) {

		}

		/* If customer doesn't have an account with the bank, it will be opened */
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);

			if (account.getAccountType().equals(Constants.REGULARSAVINGACCOUNT)) {
				ps = con.prepareStatement(
						Constants.INSERTREGULARSAVINGACCOUNTQUERY,
						Statement.RETURN_GENERATED_KEYS);
			}
			else if ( account.getAccountType().equals(Constants.SALARYSAVINGACCOUNT) )
			{
				ps = con.prepareStatement(
						Constants.INSERTSALARYSAVINGACCOUNTQUERY,
						Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, account.getCustomerId());
			ps.setString(2, account.getPreferredCity());
			ps.setDouble(3, account.getInitialDeposit());
			ps.setDate(4, account.getOpeningDate());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				accountId = rs.getInt(1);
			}
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}

		finally {
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return accountId;
	}

	/* withdraw() :- for withdrawal purpose */
	public double withdraw(Transaction transaction)
			throws CustomerNotFoundException, DataAccessException,
			AccountNotFoundException, InsufficientDepositException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SavingAccount savingAccount = null;
		double balance = 0;
		checkCustomer(transaction.getCustomerId());
		savingAccount = getAccount(transaction.getCustomerId(),
				transaction.getAccountType());
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);

			if (transaction.getAccountType().equals(
					Constants.REGULARSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.UPDATEREGULARSAVINGACCOUNTQUERY);
			} else if (transaction.getAccountType().equals(
					Constants.SALARYSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.UPDATESALARYSAVINGACCOUNTQUERY);
			}
			balance = savingAccount.getInitialDeposit()
					- transaction.getAmount();
			if (balance < 0)
				throw new InsufficientDepositException();
			ps.setDouble(1, balance);
			ps.setString(2, savingAccount.getCustomerId());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}

		finally {
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return balance;

	}

	/* deposit() :- for deposit purpose.*/
	public double deposit(Transaction transaction)
			throws CustomerNotFoundException, DataAccessException,
			AccountNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SavingAccount savingAccount = null;
		double balance = 0;
		checkCustomer(transaction.getCustomerId());
		savingAccount = getAccount(transaction.getCustomerId(),
				transaction.getAccountType());
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);

			if (transaction.getAccountType().equals(
					Constants.REGULARSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.UPDATEREGULARSAVINGACCOUNTQUERY);
			} else if (transaction.getAccountType().equals(
					Constants.SALARYSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.UPDATESALARYSAVINGACCOUNTQUERY);
			}
			balance = savingAccount.getInitialDeposit()
					+ transaction.getAmount();
			ps.setDouble(1, balance);
			ps.setString(2, savingAccount.getCustomerId());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}

		finally {
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return balance;
	}

	/* getAccount() :- fetching account details. */
	private SavingAccount getAccount(String customerId, String accountType)
			throws AccountNotFoundException, DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SavingAccount savingAccount = null;
		try {
			con = ConnectionFactory.getConnection();
			if (accountType.equals(Constants.REGULARSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.SELECTREGULARSAVINGACCOUNTQUERY);
			} else if (accountType.equals(Constants.SALARYSAVINGACCOUNT)) {
				ps = con.prepareStatement(Constants.SELECTSALARYSAVINGACCOUNTQUERY);
			} 
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				savingAccount = new SavingAccount();
				savingAccount.setCustomerId(rs.getString(1));
				savingAccount.setAccountNumber(rs.getString(2));
				savingAccount.setPreferredCity(rs.getString(3));
				savingAccount.setInitialDeposit(rs.getDouble(4));
				savingAccount.setOpeningDate(rs.getDate(5));
			} else
				throw new AccountNotFoundException();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		} catch (DataSourceException e) {
			e.printStackTrace();
			throw new DataAccessException();
		} finally {
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DbUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return savingAccount;
	}

	/*
	 * checkCustomer() :- Checks whether the person is customer of the bank or
	 * not
	 */
	private void checkCustomer(String customerId)
			throws CustomerNotFoundException, DataAccessException {
		CustomerDao customerDao = (CustomerDao) DaoFactory
				.getDao(Constants.CUSTOMER);
		customerDao.getCustomer(customerId);
	}

}
