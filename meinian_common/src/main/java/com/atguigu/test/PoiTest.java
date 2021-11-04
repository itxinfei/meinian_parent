package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lbstart
 * @create 2021-06-04 10:02
 */
public class PoiTest {
    /*

    */
    @Test
    public void test() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook("D:\\document\\SGG\\06.EveryyearTour\\2021-05-28_美年旅游\\课件\\atguigu.xlsx");
        XSSFSheet sheet = sheets.getSheetAt(0);
        for (Row cells : sheet) {
            for (Cell cell : cells) {
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        sheets.close();
    }

    /*

    */
    @Test
    public void testGet() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\document\\SGG\\06.EveryyearTour\\2021-05-28_美年旅游\\课件\\atguigu.xlsx");
        XSSFSheet sheetAt = workbook.getSheet("大数据");
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0;i<= lastRowNum;i++){
            XSSFRow row = sheetAt.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 0;j<lastCellNum;j++){
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }
        workbook.close();
    }

    /*

    */
    @Test
    public void testImportData() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("大数据");
        Row row = sheet.createRow(4);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("地址");

        Row row1 = sheet.createRow(5);
        row1.createCell(0).setCellValue("张三");
        row1.createCell(1).setCellValue("23");
        row1.createCell(2).setCellValue("北京");

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\document\\SGG\\06.EveryyearTour\\2021-05-28_美年旅游\\课件\\atguigu.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        workbook.close();
    }
}
