package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.dsl.Disruptor;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * Pushes a new {@link RawEvent} to disruptor.
 * @author padawan
 * @since 6/16/12 5:21 PM
 */
public final class DefaultJmsEventListener implements EventGateway {

	final Disruptor<RawEvent> disruptor;
	public DefaultJmsEventListener(Disruptor<RawEvent> disruptor) {
		this.disruptor = disruptor;
	}

	public void waitFor(RawEvent rawEvent) {
		disruptor.publishEvent(new JmsEventTranslator(rawEvent));
	}
}
