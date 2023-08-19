package com.warehousemanagement.wms.security;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.repository.AdministratorRepository;
import com.warehousemanagement.wms.repository.OperatorRepository;
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
    @Autowired
    OperatorRepository operatorRepository;
    @Override
    public NewUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Administrator> optionalAdministrator= administratorRepository.findByNickname(username);
      if(optionalAdministrator.isPresent())
          return new NewUserDetails(optionalAdministrator.get());
      Optional<Operator> optionalOperator=operatorRepository.findByNickname(username);
     optionalOperator.orElseThrow(()->new UsernameNotFoundException("Not found: " + username) );
        return optionalOperator.map(NewUserDetails::new).get();
    }
}
