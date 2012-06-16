package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventTranslator;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * @author padawan
 * @since 6/16/12 5:13 PM
 */
public class JmsEventTranslator implements EventTranslator<RawEvent> {

	final RawEvent rawEvent;
	public JmsEventTranslator(RawEvent rawEvent) {
		this.rawEvent = rawEvent;
	}

	public void translateTo(RawEvent event, long sequence) {
		event = this.rawEvent;
	}
}
