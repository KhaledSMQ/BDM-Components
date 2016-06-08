package br.com.openbus.social.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TokenAuthenticationProvider implements AuthenticationProvider{
	
	private String tokenAcess;
	
	public TokenAuthenticationProvider(String tokenAccess) {
		this.tokenAcess = tokenAccess;
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String)authentication.getPrincipal();
		
		if (tokenAcess.equals(token)){
			authentication.setAuthenticated(true);
		}
		
		return authentication;
	}

    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }

}
