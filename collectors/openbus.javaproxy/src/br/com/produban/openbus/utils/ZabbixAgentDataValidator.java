package br.com.produban.openbus.utils;

import br.com.openbus.publisher.MessageValidator;
import br.com.produban.openbus.model.avro.ZabbixAgentData;

public class ZabbixAgentDataValidator implements MessageValidator<ZabbixAgentData> {

    @Override
    public boolean validate(ZabbixAgentData data) {
        return (data.getHost() != null &&
                data.getKey() != null &&
                data.getValue() != null &&
                data.getClock() != null);
    }
}
