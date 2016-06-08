package br.com.produban.openbus.introscope.sender;

import br.com.produban.openbus.introscope.core.IntroscopeSimpleJob;
import br.com.produban.openbus.introscope.sender.kafka.KafkaSender;
import br.com.produban.openbus.model.avro.Introscope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends IntroscopeSimpleJob {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	public void execute() {

		LinkedBlockingQueue<Introscope> outputResult = queues.getOutputQueue();
		ISender sender = KafkaSender.getInstance();
				//new KafkaSender(queues, resourceUtils);

		try {
			if (!outputResult.isEmpty()) {
				ArrayList<Introscope> Introscopes = new ArrayList<>();
				outputResult.drainTo(Introscopes);
				for (int i = 0; i < Introscopes.size(); i++) {
					sender.send(Introscopes.get(i));
				}
			}

		} catch (Exception e) {
			LOG.error("Error in sender metrics", e);
		}
	}
}
