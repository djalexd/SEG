package ro.teamnet.hackaton.storage;

/**
 *
 * @author padawan
 * @since 6/16/12 1:18 PM
 */
public class Event {
	long timestamp; // ISO8601 (no of seconds passed since 1970 0:00:000 am)
	int number;

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

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Event");
		sb.append("{timestamp=").append(timestamp);
		sb.append(", number=").append(number);
		sb.append('}');
		return sb.toString();
	}
}
