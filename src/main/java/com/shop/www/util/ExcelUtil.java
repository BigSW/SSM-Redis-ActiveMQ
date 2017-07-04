package com.shop.www.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private Logger log = Logger.getLogger(ExcelUtil.class);

	private Workbook wb;
	private XSSFSheet xsheet;
	private String templateFileName;
	private String downloadFileName;

	public ExcelUtil() {

	}

	public ExcelUtil(String templateFileName) throws FileNotFoundException, IOException {
		this();
		this.setTemplateFileName(templateFileName);
	}

	public void setTemplateFileName(String templateFileName) throws FileNotFoundException, IOException {
		this.templateFileName = templateFileName;
		try {
			wb = new XSSFWorkbook(new FileInputStream(templateFileName));
		} catch (Exception e) {
			wb = new HSSFWorkbook(new FileInputStream(templateFileName));
		}
	}

	/**
	 * 读取excel表中的信息
	 * @return	
	 */
	public List<List<String>> readExcel() {
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			ArrayList<String> list = new ArrayList<String>();
			xsheet = (XSSFSheet) wb.getSheetAt(i);
			if (xsheet == null) {
				continue;
			}
			for (int j = 0; j <= xsheet.getLastRowNum(); j++) {
				XSSFRow row = xsheet.getRow(j);
				for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
					XSSFCell cell = row.getCell(k);
					if (cell == null)
						continue;
					else
						list.add(getCellValue(cell));
				}
			}
			result.add(list);
		}
		return result;
	}

	/**
	 * 生成excek表格
	 * @param title 表格标题
	 * @param data	表格数据
	 * @param out	输出流
	 */
	public static void writeExcel(String title, List<String> data, final OutputStream out) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("导出的例子");

		XSSFRow headRow = sheet.createRow(0); // 标题行
		XSSFCell cell = null;
		ExcelUtil.setCellValue(headRow, 0, 2, title, ExcelUtil.setHeadStyle(workbook));

		if (data != null && data.size() > 0) { // 内容行
			for (int j = 0; j < data.size(); j++) {
				XSSFRow bodyRow = sheet.createRow(j + 1);
				cell = bodyRow.createCell(0);
				cell.setCellValue("ssss");
				cell.setCellStyle(ExcelUtil.setBodyStyle(workbook));

				cell = bodyRow.createCell(1);
				cell.setCellValue("ssss");
				cell.setCellStyle(ExcelUtil.setBodyStyle(workbook));

				cell = bodyRow.createCell(2);
				cell.setCellValue("ssss");
				cell.setCellStyle(ExcelUtil.setBodyStyle(workbook));
			}
		}
		try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getCellValue(Cell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}

	/** 设置表格-表头样式
	 * @param wb 	XSSFWorkbook
	 * @return
	 */
	public static XSSFCellStyle setHeadStyle(XSSFWorkbook wb) {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;

	}

	/**
	 * 设置表格内容具体样式
	 * @param wb XSSFWorkbook
	 * @return
	 */
	public static XSSFCellStyle setBodyStyle(XSSFWorkbook wb) {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * 某行中，第x1-x2列的合并
	 * @param row  当前的行
	 * @param x1
	 * @param x2
	 * @param obj	单元格中的内容
	 * @param cellStyle	样式
	 */
	public static void setCellValue(XSSFRow row, int x1,int x2, Object obj,XSSFCellStyle cellStyle) {
		XSSFCell createCell = row.createCell(x1);
		createCell.setCellStyle(cellStyle);
		createCell.setCellValue((String)obj);
		if(x1<x2){
			row.getSheet().addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), x1, x2));
		}
	}
	
	public String getTemplateFileName() {
		return templateFileName;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * String templateFileName = "E:\\workspace\\aa.xlsx";
		 * ExcelUtil util = new ExcelUtil(templateFileName);
		 * util.readExcel(); 
		 * System.exit(0);
		 */

		OutputStream out = new FileOutputStream("E:\\workspace\\bb.xlsx");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i, "gg");
		}

		ExcelUtil.writeExcel("demo", list, out);

	}

}
