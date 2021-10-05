package com.sprint1.hcsapi.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * This enumeration is used to define different roles.
 * @author devendra 
 */
public enum Role implements GrantedAuthority{
	ROLE_ADMIN,ROLE_USER;
	
    /**
     * This getAuthority will returns role name for token.
     */
	@Override
	public String getAuthority() {
		
		return name();
	}

}
