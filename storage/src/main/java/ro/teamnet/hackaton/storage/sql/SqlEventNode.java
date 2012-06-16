package ro.teamnet.hackaton.storage.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ro.teamnet.hackaton.storage.AbstractEventNode;
import ro.teamnet.hackaton.storage.Event;

/**
 * @author padawan
 * @since 6/16/12 1:45 PM
 */
public class SqlEventNode extends AbstractEventNode {
	final static String DEFAULT_PREFIX = "t_event_";
	final static String CREATE_TABLE_SQL = "create table %s(created TIMESTAMP(),value INT(10)) if not exists";
	final static String INSERT_SQL = "insert into %s(created,value) VALUES(%s, %s)";

	final String tableName;
	final JdbcOperations jdbcOperations;

	public SqlEventNode(final String type, final JdbcOperations jdbcOperations) {
		super(type);
		this.jdbcOperations = jdbcOperations;
		this.tableName = DEFAULT_PREFIX + type;
		// Ensure table exists
		this.jdbcOperations.execute(String.format(CREATE_TABLE_SQL, tableName));
		// Ensure type exists in types table.
	}

	public void store(Event event) {
		// Just insert.
		try {
			jdbcOperations.execute(
					String.format(INSERT_SQL, tableName, event.getTimestamp(), event.getNumber())
			);
		} catch (DataAccessException e) {
			// TODO handle
		}
	}
}
