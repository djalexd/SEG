package ro.teamnet.hackaton.storage;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @author padawan
 * @since 6/16/12 1:26 PM
 */
public class SimpleEventStorage implements EventStorage {

	LoadingCache<String, EventNode> nodes;
	public SimpleEventStorage(CacheLoader<String, EventNode> cacheLoader) {
		this.nodes = CacheBuilder
				.newBuilder()
				.build(cacheLoader);
	}

	public void store(final RawEvent event) {
		Preconditions.checkNotNull(event);
		Preconditions.checkNotNull(event.getType());

		try {
			nodes.get(event.getType()).store(event);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
