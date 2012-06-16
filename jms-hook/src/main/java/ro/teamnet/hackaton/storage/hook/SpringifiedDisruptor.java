package ro.teamnet.hackaton.storage.hook;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

/**
 * @author padawan
 * @since 6/16/12 8:24 PM
 */
public class SpringifiedDisruptor<T> extends Disruptor<T> {

	@Autowired
	public SpringifiedDisruptor(EventFactory<T> tEventFactory, int ringBufferSize, Executor executor) {
		super(tEventFactory, ringBufferSize, executor);
	}

	@Autowired
	public void setEventHandlers(EventHandler<T> ... handlers) {
		this.handleEventsWith(handlers);
	}
}
