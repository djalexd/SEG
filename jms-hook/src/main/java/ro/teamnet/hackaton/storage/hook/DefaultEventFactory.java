package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventFactory;
import ro.teamnet.hackaton.storage.RawEvent;

/**
* @author padawan
* @since 6/16/12 8:26 PM
*/
class DefaultEventFactory implements EventFactory<RawEvent> {
	@Override
	public RawEvent newInstance() {
		return new RawEvent(-1, -1, "");
	}
}
