package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.util.StopWatch;
import ro.teamnet.hackaton.storage.RawEvent;

import java.util.concurrent.CountDownLatch;

/**
 * Entry point class for the demo.
 * @author padawan
 * @since 6/16/12 6:18 PM
 */
public class SEGDemo {
	final static int NUM_TOTAL_THREADS = 100;
	final static int NUM_EVENTS_PER_THREAD = 100;

	final MessageChannel messageChannel;
	// The executor used to fire events.
	final TaskExecutor eventTaskExecutor;
	final Disruptor disruptor;

	public SEGDemo() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("demo.xml");
		messageChannel = appCtx.getBean("raw-event-input-channel", MessageChannel.class);
		eventTaskExecutor = appCtx.getBean("event-executor", TaskExecutor.class);
		disruptor = appCtx.getBean(Disruptor.class);
	}


	void fireEvents() throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(NUM_TOTAL_THREADS);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		// Execute a couple of threads.
		for (int i = 0; i < NUM_TOTAL_THREADS; i++) {

			eventTaskExecutor.execute(new Runnable() {
				@Override
				public void run() {

					// each thread writes 100 events to the channel.
					for (int j = 0; j < NUM_EVENTS_PER_THREAD; j++) {
						RawEvent event = RawEventUtils.event();
						messageChannel.send(new GenericMessage<RawEvent>(event));
					}

					countDownLatch.countDown();
				}
			});

		}

		countDownLatch.await();
		stopWatch.stop();

		System.out.println("Dispatched " + (NUM_TOTAL_THREADS * NUM_EVENTS_PER_THREAD) + " events in " + stopWatch.getTotalTimeMillis() + " milliseconds");

		// Stop the disruptor
		disruptor.shutdown();
	}


	public static void main(String[] args) throws InterruptedException {
		//
		final SEGDemo demo = new SEGDemo();

		// Throw a couple of event and see how it behaves.
		demo.fireEvents();
	}
}
