package br.com.produban.openbus.introscope.sender;

import br.com.produban.openbus.model.avro.Introscope;

import java.util.List;

public abstract class ISender {

	protected ISender() {}

	protected abstract void send(Introscope introscope);

}
