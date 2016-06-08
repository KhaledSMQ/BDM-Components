package br.com.produban.openbus.sql2kafka.util.security;

public class IllegalConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -4870270361981085328L;

	public IllegalConfigurationException() {
	}

	public IllegalConfigurationException(String paramString) {
		super(paramString);
	}

	public IllegalConfigurationException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public IllegalConfigurationException(Throwable paramThrowable) {
		super(paramThrowable);
	}
}
