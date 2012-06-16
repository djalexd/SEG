package ro.teamnet.hackaton.storage;

/**
 *
 * @author padawan
 * @since 6/16/12 1:18 PM
 */
public class Event {
	final long timestamp; // ISO8601 (no of seconds passed since 1970 0:00:000 am)
	final int number;

	public Event(long timestamp, int number) {
		this.timestamp = timestamp;
		this.number = number;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getNumber() {
		return number;
	}
}
