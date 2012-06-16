package ro.teamnet.hackaton.storage.hook;

import ro.teamnet.hackaton.storage.RawEvent;

import java.util.Random;

/**
 * @author padawan
 * @since 6/16/12 6:37 PM
 */
public class RawEventUtils {

	private static String[] types = new String[] {
		"eventA", "eventB", "eventD"
	};

	private static final Random random = new Random();

	static RawEvent event() {
		return new RawEvent(System.currentTimeMillis(), 1, types[random.nextInt(types.length)]);
	}
}
