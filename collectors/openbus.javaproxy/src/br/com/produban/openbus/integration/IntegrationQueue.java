package br.com.produban.openbus.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weakref.jmx.Managed;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;


public class IntegrationQueue<T> {

	private final static Logger LOGGER = LoggerFactory.getLogger(IntegrationQueue.class);
	
	private LinkedBlockingQueue<T> integrationQueue = new LinkedBlockingQueue<>();

	public void insert(T data) {
		try {
			if (data != null)
				integrationQueue.put(data);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public LinkedList<T> getAll(int size) {
		LinkedList<T> linkedList = new LinkedList<>();
		if (size <= 0)
            integrationQueue.drainTo(linkedList);
        else
            integrationQueue.drainTo(linkedList, size);
		return linkedList;
	}

    public LinkedList<T> getAll() {
        return getAll(-1);
    }

	@Managed(description = "Get the current queue depth")
	public int getSize() {
		return integrationQueue.size();
	}
}
