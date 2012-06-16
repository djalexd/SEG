package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * Pushes a new {@link RawEvent} to disruptor.
 * @author padawan
 * @since 6/16/12 5:21 PM
 */
public final class DefaultEventListener {

	final Disruptor<RawEvent> disruptor;
	public DefaultEventListener(Disruptor<RawEvent> disruptor) {
		this.disruptor = disruptor;
	}


	public void waitFor(final RawEvent rawEvent) {

		disruptor.publishEvent(new EventTranslator<RawEvent>() {
			@Override
			public void translateTo(RawEvent event, long sequence) {

				event.setType(rawEvent.getType());
				event.setTimestamp(rawEvent.getTimestamp());
				event.setNumber(rawEvent.getNumber());
			}
		});
	}
}
