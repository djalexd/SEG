package ro.teamnet.hackaton.storage.hook;

import org.springframework.integration.annotation.Gateway;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * Listens to a specified input channel for incoming
 * {@link ro.teamnet.hackaton.storage.RawEvent raw events}.
 * @author padawan
 * @since 6/16/12 5:18 PM
 */
public interface EventGateway {

	@Gateway(requestChannel = "raw-event-input-channel")
	void waitFor(RawEvent rawEvent);
}
