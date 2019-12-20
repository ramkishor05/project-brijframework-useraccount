package com.brijframework.useraccount.config;

import static com.brijframework.useraccount.constant.Constants.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {
	private Logger logger=Logger.getLogger(TokenProvider.class.getName());

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    	if(token==null) {
    		return null;
    	}
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
    	try{
    		 return Jwts.parser()
    	                .setSigningKey(SIGNING_KEY)
    	                .parseClaimsJws(token)
    	                .getBody();
    	}catch(Exception e){
    		logger.fine("Exception in TokenProvider : getAllClaimsFromToken() :--"+e.getMessage());
    		e.printStackTrace();
    	}
        return null;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    public long getTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        
        long diff = expiration.getTime() - new Date().getTime();
        return diff / (60 * 1000);
    }

    public String generateToken(Authentication authentication) {
    	logger.fine("Inside TokenProvider : generateToken");
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	logger.fine("Inside TokenProvider : validateToken");
    	try {
	        final String username = getUsernameFromToken(token);
	        if(StringUtils.isEmpty(username)) {
	        	return false;
	        }
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    	}catch (Exception e) {
			return false;
		}
    }
    
    public Boolean validateToken(String token) {
    	return !isTokenExpired(token);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
    	logger.fine("Inside TokenProvider : getAuthentication");
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
