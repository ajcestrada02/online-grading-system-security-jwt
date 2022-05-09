package org.blc.security.jwt;

import java.util.Date;

import org.blc.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${blc.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${blc.app.jwtExpirationMs}")
	private int jwtExpirations;
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrinciapl = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrinciapl.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public Boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT Signature {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token expired {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty {}", e.getMessage());
		}
		return false;
	}
}
