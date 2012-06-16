package ro.teamnet.hackaton.storage;

/**
 * @author padawan
 * @since 6/16/12 1:25 PM
 */
public interface EventNode {

	String type();

	void store(Event event);
}
