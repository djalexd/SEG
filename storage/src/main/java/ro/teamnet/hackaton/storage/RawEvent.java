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
}
