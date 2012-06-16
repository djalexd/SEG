package ro.teamnet.hackaton.storage;

/**
 * @author padawan
 * @since 6/16/12 1:18 PM
 */
public interface EventStorage {

	/**
	 * Store the specified event. The implementation can be asynchronous, and
	 * tries its best to be consistent (however, it is not guaranteed). An exception
	 * should be logged for failure events, along with the original event.
	 * @param event
	 */
	void store(RawEvent event);
}
