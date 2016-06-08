package br.com.produban.openbus.introscope.util.queue;

import java.sql.ResultSet;
import java.util.concurrent.LinkedBlockingQueue;

import br.com.produban.openbus.model.avro.Introscope;

public class Queues {

	private LinkedBlockingQueue<ResultSet> resultSets;
	private LinkedBlockingQueue<Introscope> outputQueue;

	public Queues() {
		this.resultSets = new LinkedBlockingQueue<ResultSet>();
		this.outputQueue = new LinkedBlockingQueue<Introscope>();
	}

	public LinkedBlockingQueue<ResultSet> getResultSets() {
		return resultSets;
	}

	public void setResultSets(LinkedBlockingQueue<ResultSet> resultSets) {
		this.resultSets = resultSets;
	}

	public LinkedBlockingQueue<Introscope> getOutputQueue() {
		return outputQueue;
	}

	public void setOutputQueue(LinkedBlockingQueue<Introscope> outputQueue) {
		this.outputQueue = outputQueue;
	}

}
