package br.com.openbus.social.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Reading;
import facebook4j.User;
import facebook4j.conf.ConfigurationBuilder;

@Service
public class FacebookIntegrationService {

	@Value("${social.facebook.appid}")
	private String appId;

	@Value("${social.facebook.secret}")
	private String secret;

	@Value("${social.facebook.fields}")
	private String[] facebookFields;
	
	@Value("${social.facebook.proxy.enabled}")
	private boolean isProxyEnabled;
	
	@Value("${social.facebook.proxy.host}")
	private String httpProxyHost;
	
	@Value("${social.facebook.proxy.port}")
	private int httpProxyPort;

	public User getUserByToken(String token) throws FacebookException {
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthAccessToken(token);
		builder.setOAuthAppId(appId);
		builder.setOAuthAppSecret(secret);
		builder.setOAuthPermissions("public_profile, email");
		builder.setDebugEnabled(true);
		
		if(isProxyEnabled){
			builder.setHttpProxyHost(httpProxyHost);
			builder.setHttpProxyPort(httpProxyPort);
		}
		
		Facebook facebook = new FacebookFactory(builder.build()).getInstance();

		return facebook.getMe(new Reading().fields(facebookFields));
	}
	
	
}
