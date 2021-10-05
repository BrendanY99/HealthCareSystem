package com.sprint1.hcsapi.configuration;

import java.util.Date;
import java.util.Objects;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sprint1.hcsapi.domain.Role;
import com.sprint1.hcsapi.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class is used to create Jwt token and other methods related to token.
 * 
 */
@Component
public class JwtTokenProvider {

	@Value("${security.jet.token.secret-key:secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:172800000}")
	private long validityInMilliseconds = 172800000;
	
    @Autowired
	private UserDetailConfiguration userDetails;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	/**
	 * This method is used to create token by providing username and list of roles.
	 * @return created token.
	 */
	public String createToken(String username, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(
				s-> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList())
			);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	/**
	 * This method returns the authority of particular user entity from the token.
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		UserDetails userDetail = userDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetail,"",userDetail.getAuthorities());
	}
	/**
	 * This method is used to return the username from the token.
	 * @param token
	 * @return
	 */
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	/**
	 * This method return token with Bearer part removed.
	 * @param 
	 * @return
	 */
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if(bearerToken!=null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
	/**
	 * This method is for validation of token, like token is expired.
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or Invalid Jwt Token",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
