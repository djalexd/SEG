package ro.teamnet.hackaton.storage.hook;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import ro.teamnet.hackaton.storage.RawEvent;

/**
 * Entry point class for the demo.
 * @author padawan
 * @since 6/16/12 6:18 PM
 */
public class SEGDemo {

	MessageChannel messageChannel;

	public SEGDemo() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("demo.xml");
		messageChannel = appCtx.getBean("raw-event-input-channel", MessageChannel.class);
	}


	void fireEvents() {

		// Spawn a couple of threads and shoot them at channel.
		for (int i = 0; i < 1; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// each thread writes 100 events to the channel.
					for (int j = 0; j < 1; j++) {
						messageChannel.send(new GenericMessage<RawEvent>(RawEventUtils.event()));
					}

				}
			}).start();
		}
	}

	public static void main(String[] args) {
		//
		final SEGDemo demo = new SEGDemo();

		// Throw a couple of event and see how it behaves.
		demo.fireEvents();
	}
}
