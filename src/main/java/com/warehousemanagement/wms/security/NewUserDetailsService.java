package com.warehousemanagement.wms.security;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewUserDetailsService implements UserDetailsService {
    @Autowired
    AdministratorRepository administratorRepository;
    @Override
    public NewUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Administrator> optionalAdministrator= administratorRepository.findByNickname(username);
     optionalAdministrator.orElseThrow(()->new UsernameNotFoundException("Not found: " + username) );
        return optionalAdministrator.map(NewUserDetails::new).get();
    }
}
