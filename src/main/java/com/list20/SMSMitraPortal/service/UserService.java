package com.list20.SMSMitraPortal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import com.list20.SMSMitraPortal.pojo.BankMaster;
import com.list20.SMSMitraPortal.pojo.BranchMaster;
import com.list20.SMSMitraPortal.pojo.User;
import com.list20.SMSMitraPortal.repository.BankMasterRepository;
import com.list20.SMSMitraPortal.repository.BranchMasterRepository;
import com.list20.SMSMitraPortal.repository.UserRepository;
import com.list20.SMSMitraPortal.request.SignInRequest;
import com.list20.SMSMitraPortal.request.UserRequest;
import com.list20.SMSMitraPortal.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class UserService {
    UserRepository userRepository;
    BankMasterRepository bankMasterRepository;
    BranchMasterRepository branchMasterRepository;
    PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, BankMasterRepository bankMasterRepository, BranchMasterRepository branchMasterRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.bankMasterRepository = bankMasterRepository;
        this.branchMasterRepository = branchMasterRepository;
    }

    public User signUp(UserRequest userRequest) {
        BankMaster bankMaster = this.bankMasterRepository.findByBankName(userRequest.getBankName());
        BranchMaster branchMaster = this.branchMasterRepository.findByBranchNameAndBankCode(userRequest.getBranchName(), bankMaster.getBankCode());
        User user = User.fromRequest(userRequest, bankMaster, branchMaster);
        user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        return (User)this.userRepository.saveAndFlush(user);
    }

    public String changePassword(Map<String, String> changePasswordRequest) {
        Optional<User> user = this.userRepository.findByName((String)changePasswordRequest.get("name"));
        if (user.isEmpty()) {
            return "User does not exists...";
        } else {
            User user1 = (User)user.get();
            if (this.passwordEncoder.matches((CharSequence)changePasswordRequest.get("oldPassword"), user1.getPassword())) {
                user1.setPassword(this.passwordEncoder.encode((CharSequence)changePasswordRequest.get("newPassword")));
                this.userRepository.saveAndFlush(user1);
                return "Password updated successfully...";
            } else {
                return "Invalid Password...";
            }
        }
    }

    public JwtResponse signIn(SignInRequest signInRequest) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        User user = (User)this.userRepository.findByName(signInRequest.getUserName()).orElseThrow();
        String jwt = this.jwtService.generateToken(user);
        ClassPathResource imgFile = new ClassPathResource("images/" + user.getBankMaster().getBankName() + ".png");

        byte[] bytes;
        try {
            InputStream inputStream = imgFile.getInputStream();

            try {
                bytes = StreamUtils.copyToByteArray(inputStream);
            } catch (Throwable var10) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException var11) {
            throw new RuntimeException(var11);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new JwtResponse(jwt, user.getBankMaster().getBankName(), user.getBranchMaster().getBranchName(), bytes);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return this.jwtService.isTokenValid(jwt, userDetails);
    }

    public String extractUserName(String jwt) {
        return this.jwtService.extractUserName(jwt);
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return (UserDetails)UserService.this.userRepository.findByName(username).orElseThrow();
            }
        };
    }

    public boolean expireJWT(String token) {
        return this.jwtService.expireJWT(token);
    }
}

