package com.ssoyoo.blogExperienceSite.service.implement;

import com.ssoyoo.blogExperienceSite.entity.AdminEntity;
import com.ssoyoo.blogExperienceSite.repository.AdminRepository;
import com.ssoyoo.blogExperienceSite.security.AdminPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminEntity admin = adminRepository.findByAdminEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with email : " + email);
        }
        return new AdminPrincipal(admin);
    }
}