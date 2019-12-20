package com.brijframework.useraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.useraccount.beans.TokenDTO;
import com.brijframework.useraccount.beans.rqrs.LoginRequest;
import com.brijframework.useraccount.config.TokenProvider;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/token/generate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginUser) throws AuthenticationException {
    	 Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()) 
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/token/validate", method = RequestMethod.POST)
    public ResponseEntity<?> validateToken(@RequestBody TokenDTO tokenDTO) throws AuthenticationException {
    	return ResponseEntity.ok(jwtTokenUtil.validateToken(tokenDTO.getToken()));
    }
    
    @RequestMapping(value = "/token/expired", method = RequestMethod.POST)
    public ResponseEntity<?> expiredToken(@RequestBody TokenDTO tokenDTO) throws AuthenticationException {
    	return ResponseEntity.ok(jwtTokenUtil.getTokenExpired(tokenDTO.getToken()));
    }
}
