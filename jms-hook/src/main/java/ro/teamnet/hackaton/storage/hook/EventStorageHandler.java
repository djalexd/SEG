package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventHandler;
import com.sun.org.apache.xpath.internal.operations.Or;
import ro.teamnet.hackaton.storage.EventStorage;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * @author padawan
 * @since 6/16/12 8:19 PM
 */
public class EventStorageHandler implements EventHandler<RawEvent> {

	final int numberOfConsumers;
	final int ordinal;

	final EventStorage eventStorage;
	public EventStorageHandler(final EventStorage eventStorage, int numberOfConsumers, int ordinal) {
		this.eventStorage = eventStorage;
		this.numberOfConsumers = numberOfConsumers;
		this.ordinal = ordinal;
	}

	@Override
	public void onEvent(RawEvent event, long sequence, boolean endOfBatch) throws Exception {
		if (sequence % numberOfConsumers == ordinal) {
			eventStorage.store(event);
		}
	}
}
