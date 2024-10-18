package com.example.employee_detail.repository;

import com.example.employee_detail.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByMailId(String mail);
    Boolean existsByMailId(String mail);
}
