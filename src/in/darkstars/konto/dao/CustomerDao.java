package in.darkstars.konto.dao;

import in.darkstars.konto.dto.Customer;
import in.darkstars.konto.exception.CustomerNotFoundException;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.exception.DataSourceException;
import in.darkstars.konto.helper.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Vikash
 * 
 * Puprose:-Data Access class for the Customer table.
 * 
 */
public class CustomerDao implements Dao {

	
	
	/* 
	 * save(customer):-Saves the customer details to the database 
	 * 
	 */

	public int save(Customer customer) throws DataAccessException {
		int customerId = -1;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Constants.INSERTCUSTOMERQUERY,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setDate(3, customer.getDateOfBirth());
			ps.setString(4, customer.getStreetName());
			ps.setString(5, customer.getStreetNumber());
			ps.setString(6, customer.getCity());
			ps.setString(7, customer.getPin());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				customerId = rs.getInt(1);
			}
			con.commit();
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
		return customerId;
	}

	
	/*
	 *  getCustomer(customerId) :- This method checks whether the customer detail is in database or not. 
	 *  
	 */
	
	public void getCustomer(String customerId) throws CustomerNotFoundException, DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(Constants.SELECTCUSTOMERQUERY);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			if (!rs.next())
				throw new CustomerNotFoundException();
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
	}

}