package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.AuthenticationRequest;
import com.warehousemanagement.wms.dto.AuthenticationResponse;
import com.warehousemanagement.wms.security.JwtUtil;
import com.warehousemanagement.wms.security.NewUserDetails;
import com.warehousemanagement.wms.security.NewUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private NewUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Nickname-ul sau parola este incorectă");
        }catch (Exception ex){
            System.out.println("Incorrect "+ ex);
        }
       final NewUserDetails userDetails=userDetailsService
               .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails.getUsername(),
                userDetails.getAvatar(),userDetails.getId(),
                userDetails.getAuthorities().stream().findFirst().get().toString()));
    }
}