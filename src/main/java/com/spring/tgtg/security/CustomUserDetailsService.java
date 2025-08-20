package com.spring.tgtg.security;

import com.spring.tgtg.common.exception.ApiException;
import com.spring.tgtg.common.exception.GlobalErrorCode;
import com.spring.tgtg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(GlobalErrorCode.NOT_FOUND_USER));
        return new CustomUserDetails(user);
    }
}
