package ro.teamnet.hackaton.storage.sql;

import com.google.common.cache.CacheLoader;
import org.springframework.jdbc.core.JdbcOperations;
import ro.teamnet.hackaton.storage.EventNode;

/**
 * Constructs a new instance of {@link SqlEventNode}, mapped on the key provided by
 * {@link com.google.common.cache.CacheBuilder}.
 *
 * @author padawan
 * @since 6/16/12 4:01 PM
 */
public class SqlCacheLoader extends CacheLoader<String, EventNode> {

	final JdbcOperations jdbcOperations;
	public SqlCacheLoader(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public EventNode load(String key) throws Exception {
		return new SqlEventNode(key, jdbcOperations);
	}
}
