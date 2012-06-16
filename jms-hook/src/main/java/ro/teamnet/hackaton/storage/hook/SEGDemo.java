package ro.teamnet.hackaton.storage.hook;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * Entry point class for the demo.
 * @author padawan
 * @since 6/16/12 6:18 PM
 */
public class SEGDemo {
	final static int NUM_EVENTS_PER_THREAD = 700;

	final MessageChannel messageChannel;
	// The executor used to fire events.
	final TaskExecutor eventTaskExecutor;

	public SEGDemo() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("demo.xml");
		messageChannel = appCtx.getBean("raw-event-input-channel", MessageChannel.class);
		eventTaskExecutor = appCtx.getBean("event-executor", TaskExecutor.class);
	}


	void fireEvents() {

		eventTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {

				// each thread writes 100 events to the channel.
				for (int j = 0; j < NUM_EVENTS_PER_THREAD; j++) {
					RawEvent event = RawEventUtils.event();
					messageChannel.send(new GenericMessage<RawEvent>(event));
				}
			}
		});

	}

	public static void main(String[] args) {
		//
		final SEGDemo demo = new SEGDemo();

		// Throw a couple of event and see how it behaves.
		demo.fireEvents();
	}
}
