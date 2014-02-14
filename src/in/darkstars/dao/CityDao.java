package in.darkstars.dao;

import in.darkstars.exception.DataAccessException;
import in.darkstars.exception.DataSourceException;
import in.darkstars.helper.Constants;
import in.darkstars.dto.City;

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
 */
public class CityDao implements Dao {

	public List<City> selectAll() throws DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<City> cityList = new ArrayList<City>();
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(Constants.selectCityQuery);
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
