package in.darkstars.konto.dao;

import in.darkstars.konto.dto.City;
import in.darkstars.konto.exception.DataAccessException;
import in.darkstars.konto.exception.DataSourceException;
import in.darkstars.konto.helper.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Vikash
 * 
 * Purpose :- City table related operations are implemented in this class.
 * 
 */
public class CityDao implements Dao {
	
	
	/* 
	 * selectAll() :- returns all the city that are present in the database as list. 
	 * 
	 */

	public List<City> selectAll() throws DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<City> cityList = new ArrayList<City>();
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(Constants.SELECTCITYQUERY);
			rs = ps.executeQuery();
			while (rs.next()) {
				City city = new City();
				city.setAcronym(rs.getString(1));
				city.setName(rs.getString(2));
				cityList.add(city);
			}
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
		return cityList;
	}

}
