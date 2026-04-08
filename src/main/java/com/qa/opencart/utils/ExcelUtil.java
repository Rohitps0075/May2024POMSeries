package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH="./scr/test/resources/testdata/openCartTestData.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;

	// static method so i can call using class name
	public static Object[][] getTestData(String sheetName) {

		System.out.println("Reading data from the sheet" + sheetName);
		Object[][] data = null;

		try {
			// to create the connect
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			// to load the file, this create method will return workbook
			workbook = WorkbookFactory.create(ip);
			// we are in work so we have to tell the which sheet we are looking for by using
			// getSheet(), this will return the sheet
			sheet = workbook.getSheet(sheetName);

			// lets initialize an 2d object array
			// i dont know row are filled with data so i am telling system that get last row
			// num using sheet.getLastRowNum(), this will get the no of rows filled in the
			// sheet.
			// similarly i know my coloumn value will be always fixed, so i am telling the
			// system that go to zeroth row from there get the last cell value means this
			// will give the no of coloumns filled with data.

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			// by using for loop we need to featch the data from the excel
			// outer for loop for rows, inner for loop for colomns

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
					// sheet.getRow(i+0)-->because row zero we have the headers details like
					// firstname lastname etc.so we are statring from row 1 so that we used i+0

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
