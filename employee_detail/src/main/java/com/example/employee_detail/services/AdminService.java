package com.example.employee_detail.services;

import com.example.employee_detail.model.Admin;
import com.example.employee_detail.dto.AdminLoginResponse;
import com.example.employee_detail.enums.LoginResponse;
import com.example.employee_detail.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository,PasswordEncoder passwordEncoder){
        this.adminRepository=adminRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByMailId(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(admin.getMailId())
                .password(admin.getPassword())
                .roles("ADMIN") // You can customize roles as needed
                .build();
    }
    /** SignUp
     *
     * @param admin
     * @return
     */
    public ResponseEntity<?> saveUser(Admin admin){
        try {
            if (adminRepository.existsByMailId(admin.getMailId())) {
                return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.FORBIDDEN, LoginResponse.SIGNUP_FAILURE));
            }
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            try {
                adminRepository.save(admin);
                return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.OK, LoginResponse.SIGNUP_SUCCESS));
            }
            catch (Exception e){
                System.out.println(e);
                return ResponseEntity.badRequest().body(e);
            }
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    /**    SignIN
     *
     * @param admin
     * @return
     */
    public ResponseEntity<AdminLoginResponse> login(Admin admin){
        Admin data=adminRepository.findByMailId(admin.getMailId());
        if(data!=null){
            if(passwordEncoder.matches(admin.getPassword(),data.getPassword())) {
                return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.OK, LoginResponse.SIGNIN_SUCCESS));
            }
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,LoginResponse.SIGNIN_FAILURE));
        }
        return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.NOT_FOUND,LoginResponse.MAIL_NOT_FOUND));
    }

}