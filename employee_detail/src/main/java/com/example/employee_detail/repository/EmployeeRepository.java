package com.example.employee_detail.repository;

import com.example.employee_detail.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Transactional
    void deleteAllByCardId(int id);
    @Transactional
    void deleteAllByEmpId(int id);
    List<Employee> findAllByEmpId(int id);
    List<Employee> findAllByCardId(int id);
    Boolean existsByCardId(int id);
    Boolean existsByEmpId(int id);
    Page<Employee> findAll(Pageable page);

    @Query("SELECT e FROM Employee e where e.dateTime between :start and :end")
    List<Employee> getDataWithDate(
            @RequestParam("start") Timestamp start,
            @RequestParam("end") Timestamp end
    );

    @Query("SELECT e FROM Employee e WHERE e.dateTime between :start and :end and e.empId=:empId")
    List<Employee> getDataWithDateAndEmployee(
            @RequestParam("start") Timestamp start,
            @RequestParam("end") Timestamp end,
            @RequestParam("empId") int empId
    );

}
