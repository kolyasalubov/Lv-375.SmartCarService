package ua.ita.smartcarservice.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * This class will be returned by SpringBoot server once an authentication is successful, it contains:
 * 1. JWT Token
 * 2. Schema Type of Token
 * 3. Username
 * 4. Array of User’s Authorities
 */

@Data
public class JwtResponse {

    private String token;
    private String type;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.token = accessToken;
        this.type = SecurityConstant.BEARER;
        this.username = username;
        this.authorities = authorities;
    }

}
