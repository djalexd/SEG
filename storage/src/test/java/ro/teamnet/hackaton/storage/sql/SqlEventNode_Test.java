package ro.teamnet.hackaton.storage.sql;

import org.junit.After;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.teamnet.hackaton.storage.EventNode;

/**
 * @author padawan
 * @since 6/16/12 2:03 PM
 */
public class SqlEventNode_Test {

	EventNode eventNode;

	@Before
	public void setupEventNode() {
		eventNode = new SqlEventNode("some", new JdbcTemplate());
	}

	@After
	public void tearDownEventNode() {
		eventNode = null;
	}

}
