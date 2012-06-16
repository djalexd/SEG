package ro.teamnet.hackaton.storage.jcr;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.*;
import org.springframework.util.StopWatch;
import ro.teamnet.hackaton.storage.Event;
import ro.teamnet.hackaton.storage.EventNode;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.SimpleCredentials;

/**
 * @author padawan
 * @since 6/16/12 3:40 PM
 */
public class JackrabbitEventNode_Test {

	static Repository repository;

	@BeforeClass
	public static void setupRepository() {
		repository = new TransientRepository();
	}

	EventNode eventNode;

	@Before
	public void setupEventNode() {
		eventNode = new JackrabbitEventNode("login", repository);
	}

	@After
	public void tearDownEventNode() {
		eventNode = null;
	}

	@Test
	public void should_insert_into_storage() throws RepositoryException {
		// then
		eventNode.store(new Event(System.currentTimeMillis() / 1000, 1));
		// assert
		Assert.assertEquals("should have 1 event stored", 1, rootNode().getNode("login").getNodes().getSize());
	}

	@Test
	public void timed_test() throws RepositoryException {
		// when
		final long numObjects = 100 * 1000;
		// then
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for (long l = 0; l < numObjects; l++) {
			eventNode.store(new Event(System.currentTimeMillis() / 1000, 5));
		}
		// display time
		stopWatch.stop();
		System.out.println("Inserted " + numObjects + " events in " + stopWatch.getTotalTimeMillis() + " milliseconds");
		Assert.assertEquals("should have " + numObjects + " events stored", numObjects, rootNode().getNode("login").getNodes().getSize());
	}

	private Node rootNode() throws RepositoryException {
		return repository.login(new SimpleCredentials("username", "password".toCharArray())).getRootNode();
	}
}
