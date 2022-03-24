package com.josephredmond.demoauthapi.applicationuser;

import com.josephredmond.demoauthapi.registration.token.ConfirmationToken;
import com.josephredmond.demoauthapi.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return applicationUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for provided username"));
    }

    public String signUpUser(ApplicationUser applicationUser) {
        boolean userExists = applicationUserRepository.findByEmail(applicationUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());

        applicationUser.setPassword(encodedPassword);

        applicationUserRepository.save(applicationUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusDays(7), applicationUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return applicationUserRepository.enableApplicationUser(email);
    }
}
