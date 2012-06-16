package ro.teamnet.hackaton.storage;

/**
 * @author padawan
 * @since 6/16/12 1:21 PM
 */
public class RawEvent extends Event {
	final String type;

	public RawEvent(long timestamp, int number, String type) {
		super(timestamp, number);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("RawEvent");
		sb.append("{type='").append(type).append('\'');
		sb.append(", timestamp=").append(timestamp);
		sb.append(", value=").append(number);
		sb.append('}');
		return sb.toString();
	}
}
