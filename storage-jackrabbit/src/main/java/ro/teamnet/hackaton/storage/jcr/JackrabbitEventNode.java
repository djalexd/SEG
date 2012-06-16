package ro.teamnet.hackaton.storage.jcr;

import ro.teamnet.hackaton.storage.AbstractEventNode;
import ro.teamnet.hackaton.storage.Event;

import javax.jcr.*;

/**
 * @author padawan
 * @since 6/16/12 3:31 PM
 */
public class JackrabbitEventNode extends AbstractEventNode {
	private static final String TIMESTAMP = "timestamp";
	private static final String VALUE = "value";

	Session session;
	Node root;

	public JackrabbitEventNode(String type, final Repository repository) {
		super(type);
		try {
			session = repository.login(new SimpleCredentials("username", "password".toCharArray()));
			root = session.getRootNode().getNode(type());
		} catch (PathNotFoundException e) {
			try {
				root = session.getRootNode().addNode(type());
			} catch (RepositoryException e1) {
				throw new IllegalStateException("Cannot create node " + type(), e1);
			}
		} catch (RepositoryException e) {
			throw new IllegalStateException("cannot login to repo", e);
		}
	}

	public void store(Event event) {
		try {
			Node evtNode = root.addNode("event");
			evtNode.setProperty(TIMESTAMP, event.getTimestamp());
			evtNode.setProperty(VALUE, event.getNumber());

			session.save();
		} catch (RepositoryException e) {
			throw new IllegalStateException("unable to retrieve node: event", e);
		}
	}
}
