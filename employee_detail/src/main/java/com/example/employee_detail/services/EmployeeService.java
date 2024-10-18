package com.example.employee_detail.services;

import com.example.employee_detail.dto.AdminLoginResponse;
import com.example.employee_detail.dto.DateFilter;
import com.example.employee_detail.dto.DateFilterWithEmployee;
import com.example.employee_detail.dto.EmployeeListTransfer;
import com.example.employee_detail.enums.LoginResponse;
import com.example.employee_detail.model.Admin;
import com.example.employee_detail.model.Employee;
import com.example.employee_detail.repository.AdminRepository;
import com.example.employee_detail.repository.EmployeeRepository;
import com.example.employee_detail.util.ExcelFileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final ExcelFileReader excelFileReader;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<?> saveToDB(MultipartFile file) throws FileNotFoundException, IOException {
        System.out.println("Hello love in service");
        excelFileReader.setFile(file);
        return excelFileReader.saveToDB();
    }

    public ResponseEntity<?> allData(int page) {
        System.out.println(employeeRepository.count());
        long count=employeeRepository.count();
        long pageCount=count%10==0?count/10:count/10 + 1;
        if(page>pageCount){
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"The Page number exceeds the limit"));
        }
        Page<Employee> employeePage=employeeRepository.findAll(PageRequest.of(page-1,10));
        Boolean isEnd=employeePage.isLast();
        return ResponseEntity.ok(new EmployeeListTransfer(HttpStatus.ACCEPTED,"Successfully retrieved data", isEnd,employeePage.getContent()));
    }

    public ResponseEntity<?> deleteAll(Admin admin){
        String encodedPassword=adminRepository.findByMailId(admin.getMailId()).getPassword();
        if(passwordEncoder.matches(admin.getPassword(),encodedPassword))
        {
            try {
                employeeRepository.deleteAll();
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body(e);
            }
            return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.ACCEPTED, LoginResponse.SUCCESSFUL_DELETION));
        }
        return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,LoginResponse.DELETION_FAILURE));
    }

    public ResponseEntity<?> deleteByEmployeeId(int id){
        if(!employeeRepository.existsByEmpId(id)){
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"Employee Id Not found"));
        }
       try{
           employeeRepository.deleteAllByEmpId(id);
           return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.ACCEPTED,LoginResponse.SUCCESSFUL_DELETION));
       }
       catch (Exception e){
           return  ResponseEntity.internalServerError().body(new AdminLoginResponse(HttpStatus.BAD_GATEWAY,e.toString()));
       }
    }

    public ResponseEntity<?> deleteByCardId(int id){
        System.out.println(" res "+employeeRepository.existsByCardId(id));
        if(!employeeRepository.existsByCardId(id)){
            System.out.println("inside the not found block");
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"CID Not found"));
        }
            employeeRepository.deleteAllByCardId(id);
            return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.ACCEPTED,LoginResponse.SUCCESSFUL_DELETION));
    }

    public ResponseEntity<?> allDataWithEmpId(int id){
        if(!employeeRepository.existsByEmpId(id)){
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"Employee Id Not found"));
        }
        try{
            return ResponseEntity.ok(employeeRepository.findAllByEmpId(id));
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    public ResponseEntity<?> allDataWithCardId(int id){
        System.out.println(" res "+employeeRepository.existsByCardId(id));
        if(!employeeRepository.existsByCardId(id)){
            return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"CID Not found"));
        }
        try{
            return ResponseEntity.ok(employeeRepository.findAllByCardId(id));
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    public ResponseEntity<?> dateFilter(DateFilter dateObj){
        String endDate=dateObj.getEndDate()!=null ? dateObj.getEndDate():"";
       List<Timestamp> timestamps=convertToTimeStamp(dateObj.getStartDate(),endDate);
       List<Employee> result=employeeRepository.getDataWithDate(timestamps.get(0),timestamps.get(1));
       if(!result.isEmpty()) {
           return ResponseEntity.ok(result);
       }
       else{
           return ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"No Data found on that data"));
       }
    }

    public ResponseEntity<?> dateFilterWithEmployee(DateFilterWithEmployee data){
        System.out.println(data);
        String endDate=data.getDateFilter().getEndDate()!=null?data.getDateFilter().getEndDate():"";
        List<Timestamp> timestamps=convertToTimeStamp(data.getDateFilter().getStartDate(),endDate);
        List<Employee> result=employeeRepository.getDataWithDateAndEmployee(
                timestamps.get(0),
                timestamps.get(1),
                data.getId());
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        else{
            return  ResponseEntity.badRequest().body(new AdminLoginResponse(HttpStatus.BAD_REQUEST,"No Data Found"));
        }
    }

    private static List<Timestamp> convertToTimeStamp(String startDatePassed,String endDatePassed){
        DateTimeFormatter inputFormat=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate startDate=LocalDate.parse(startDatePassed,inputFormat);
        LocalDateTime startDateTime=LocalDateTime.of(startDate,LocalTime.MIN);

        LocalDateTime endDateTime;
        if(!endDatePassed.isEmpty()){
            LocalDate endDate=LocalDate.parse(endDatePassed,inputFormat);
            endDateTime=LocalDateTime.of(endDate, LocalTime.MAX);
        }
        else{
            endDateTime=LocalDateTime.of(startDate,LocalTime.MAX);
        }

        String formattedStartDateTime=startDateTime.format(outputFormat);
        String formattedEndDateTime=endDateTime.format(outputFormat);

        List<Timestamp> timestamps=new ArrayList<>();
        timestamps.add(Timestamp.valueOf(formattedStartDateTime));
        timestamps.add(Timestamp.valueOf(formattedEndDateTime));

        return timestamps;
    }
}
