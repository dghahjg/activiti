package com.example.activiti.config;

import com.example.activiti.domain.LoginUser;
import com.example.activiti.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserMapper.selectLoginUserByUserName(username);
        if (!Objects.isNull(loginUser)) {
            return new LoginUser(username, passwordEncoder.encode(loginUser.getPassword()),
                    loginUser.getAuthorities());
        }
        return null;
    }
}
