package com.example.employee_detail.util;

import com.example.employee_detail.dto.AdminLoginResponse;
import com.example.employee_detail.enums.InOut;
import com.example.employee_detail.enums.LoginResponse;
import com.example.employee_detail.model.Employee;
import com.example.employee_detail.repository.EmployeeRepository;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelFileReader {

    @Setter
    private MultipartFile file;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<?> saveToDB() throws IOException {
        System.out.println("Here's the file love");
        XSSFWorkbook wb=new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet=wb.getSheetAt(0);
        Iterator<Row> itr=sheet.rowIterator();
        List<Employee> employees = new ArrayList<>();
        itr.next();
        while(itr.hasNext()){
            Row row=itr.next();
            Employee data=new Employee();
            Iterator<Cell> cellIterator= row.cellIterator();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            int i=0;
            while(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                switch(i){
                    case 0:
                        try {
                            String dateTime=cell.toString()+" "+cellIterator.next().toString();
                            i++;
                            Date date=dateFormat.parse(dateTime);
                            data.setDateTime(new Timestamp(date.getTime()));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        data.setSiteCode(Integer.parseInt(cell.toString().substring(1)));
                        break;
                    case 3:
                        data.setCardId(Integer.parseInt(cell.toString().substring(1)));
                        break;
                    case 4:
                        data.setEmpId((int) cell.getNumericCellValue());
                        break;
                    case 5:
                        data.setEmpName(cell.toString());
                        break;
                    case 6:
                        data.setCID(Integer.parseInt(cell.toString().substring(1)));
                        break;
                    case 7:
                        data.setGate(cell.toString());
                        break;
                    case 8:
                        data.setInOut(InOut.valueOf(cell.toString().toUpperCase()));
                        break;
                    case 9:
                        data.setRemark(cell.toString());
                        break;
                }
                i++;
            }
            employees.add(data);
        }
        employeeRepository.saveAll(employees);
        return ResponseEntity.ok(new AdminLoginResponse(HttpStatus.ACCEPTED, LoginResponse.SUCCESSFUL_SAVE));
    }
}
