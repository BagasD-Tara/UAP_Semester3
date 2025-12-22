package com.tiketbioskop.Utill;

import com.tiketbioskop.Model.User;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtill {

    public static User login(String username, String password) {
        try {
            FileInputStream fis = new FileInputStream(new File("data/users.xlsx"));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String excelUsername = row.getCell(0).getStringCellValue();
                String excelPassword = row.getCell(1).getStringCellValue();
                String role = row.getCell(2).getStringCellValue();
                String nama = row.getCell(3).getStringCellValue();

                if (excelUsername.equals(username) && excelPassword.equals(password)) {
                    workbook.close();
                    return new User(username, password, role, nama);
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
