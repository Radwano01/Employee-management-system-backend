package com.example.employeemanagement.Security;

import com.example.employeemanagement.Entity.AdminEntity;
import com.example.employeemanagement.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsServiceAdmin implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public CustomUserDetailsServiceAdmin(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity admin = adminRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
        return new User(admin.getUsername(), admin.getPassword(), mapRole(admin.getRole()));

    }

    private Collection<GrantedAuthority> mapRole(String role){
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
