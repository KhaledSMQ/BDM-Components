package br.com.produban.openbus.sql2kafka.normalizer;

import java.text.MessageFormat;

import org.apache.avro.generic.GenericRecord;
import org.apache.commons.lang.math.NumberUtils;

import br.com.produban.openbus.rules.base.normalize.INormalizer;
import br.com.produban.openbus.security.exceptions.BusinessException;
import br.com.produban.openbus.security.exceptions.InfraException;

public class SQLNormalizer implements INormalizer {

	private static final long serialVersionUID = 517334962963842658L;

	@Override
	public GenericRecord normalize(GenericRecord input) throws BusinessException, InfraException {
		String originalValue = input.get("value").toString();

		if (!NumberUtils.isNumber(originalValue)) {
			throw new BusinessException(MessageFormat.format("Original value is not number [{0}]!", originalValue));
		}

		return input;
	}

}
