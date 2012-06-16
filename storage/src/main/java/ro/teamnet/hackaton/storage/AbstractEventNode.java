package ro.teamnet.hackaton.storage;

/**
 * @author padawan
 * @since 6/16/12 1:46 PM
 */
public abstract class AbstractEventNode implements EventNode {
	final String type;

	protected AbstractEventNode(String type) {
		this.type = type;
	}

	public final String type() {
		return this.type;
	}
}
