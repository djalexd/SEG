package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventHandler;
import ro.teamnet.hackaton.storage.EventStorage;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * @author padawan
 * @since 6/16/12 8:19 PM
 */
public class EventStorageHandler implements EventHandler<RawEvent> {

	final EventStorage eventStorage;
	public EventStorageHandler(EventStorage eventStorage) {
		this.eventStorage = eventStorage;
	}

	@Override
	public void onEvent(RawEvent event, long sequence, boolean endOfBatch) throws Exception {
		eventStorage.store(event);
	}
}
