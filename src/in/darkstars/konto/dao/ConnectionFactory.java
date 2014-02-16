package in.darkstars.konto.dao;

import in.darkstars.konto.exception.DataSourceException;
import in.darkstars.konto.helper.Constants;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Vikash
 * 
 *         Purpose :- Factory class for getting connections in a centralized manner. 
 * 
 */
public class ConnectionFactory {

	private static DataSource ds;
	private static Context ctx;

	/* getConnection() :- returns Connection object. */
	
	public static Connection getConnection() throws SQLException,
			DataSourceException {

		Connection con = null;
		try {
			if (ctx == null) {
				synchronized (Context.class) {
					if (ctx == null)
						ctx = new InitialContext();
				}
			}
			if (ds == null)
				ds = (DataSource) ctx.lookup(Constants.DATASOURCE);
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DataSourceException();
		}
		return con;
	}

}