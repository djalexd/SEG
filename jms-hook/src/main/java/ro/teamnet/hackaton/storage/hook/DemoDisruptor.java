package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import ro.teamnet.hackaton.storage.RawEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Take advantage of Disruptor's DSL.
 * @author padawan
 * @since 6/16/12 6:58 PM
 */
public class DemoDisruptor {


	static class DefaultEventFactory implements EventFactory<RawEvent> {
		@Override
		public RawEvent newInstance() {
			return new RawEvent(-1, -1, "");
		}
	}

	static final EventFactory<RawEvent> DEFAULT_EVENT_FACTORY = new DefaultEventFactory();

	static final Executor EXECUTOR = Executors.newFixedThreadPool(4);

	public static Disruptor<RawEvent> buildDisruptor() {

		Disruptor<RawEvent> disruptor = new Disruptor<RawEvent>(DEFAULT_EVENT_FACTORY, 8192, EXECUTOR);
		disruptor.handleEventsWith(new EventHandler<RawEvent>() {
			@Override
			public void onEvent(RawEvent event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println(event.toString());
			}
		});
		disruptor.start();
		return disruptor;
	}

}
