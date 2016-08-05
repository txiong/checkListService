package thorxiong.demo.checklist.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Util class to handle db connection.
 * @author thor
 *
 */
@Configuration
public class ConnectionUtil {

	@Autowired
	private DataSource dataSource;

	/**
	 * Get the JdbcTemplate
	 * @return JdbcTemplate
	 */
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Get the connection.
	 * @return The connection.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return getJdbcTemplate().getDataSource().getConnection();
	}

	/**
	 * Get the data source.
	 * @return The data source.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Set the data source.
	 * @param dataSource The data source.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Helper method to close the connection.
	 * @param connection The connection to close.
	 * @throws SQLException
	 */
	public void close(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * Helper method to close the connection and prepared statement.
	 * @param connection The connection.
	 * @param preparedStatement The statement.
	 * @throws SQLException
	 */
	public void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		close(connection);
	}

	/**
	 * Helper method to close the connection, statement and result set.
	 * @param connection The connection.
	 * @param preparedStatement The statemet.
	 * @param result The result set.
	 * @throws SQLException
	 */
	public void close(Connection connection, PreparedStatement preparedStatement, ResultSet result) throws SQLException {
		if (result != null) {
			result.close();
		}
		close(connection, preparedStatement);
	}
}
