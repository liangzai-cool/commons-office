package org.xueliang.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;

import vms.common.util.ValidateUtil;

public class ExcelUtils {
	
	/** 用来记录日志 */
	private final static Log log = LogFactory.getLog(ExcelUtils.class);
	
	/**
	 * 将Excel转成JSONArray
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public JSONArray excelToJSONArray(InputStream inputStream) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
		hssfWorkbook.close();
		return excelToJSONArray(hssfWorkbook);
	}
	
	/**
	 * 将Excel转成JSONArray
	 * @param hssfWorkbook
	 * @return
	 */
	public JSONArray excelToJSONArray(HSSFWorkbook hssfWorkbook) {
		JSONArray jsonWorkbookArray = new JSONArray();
		for (int i = 0, len = hssfWorkbook.getNumberOfSheets(); i < len; i++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
			JSONArray jsonSheetArray = sheetToJSONArray(hssfSheet);
			jsonWorkbookArray.put(jsonSheetArray);
		}
		return jsonWorkbookArray;
	}
	
	/**
	 * 将指定的表格，转成JSONArray
	 * @param hssfSheet
	 * @return
	 */
	public JSONArray sheetToJSONArray(HSSFSheet hssfSheet) {
		JSONArray jsonRowArray = new JSONArray();
		for (int i = 0, len = hssfSheet.getLastRowNum(); i < len; i++) {
			HSSFRow hssfRow = hssfSheet.getRow(i);
			if (ValidateUtil.isNull(hssfRow)) {
				continue;
			}
			JSONArray jsonRow = rowToJSONArray(hssfRow);
			jsonRowArray.put(jsonRow);
		}
		return jsonRowArray;
	}
	
	/**
	 * 将指定的行转成JSONArray
	 * @param hssfRow
	 * @return
	 */
	public JSONArray rowToJSONArray(HSSFRow hssfRow) {
		JSONArray jsonCellArray = new JSONArray();
		for (int i = 0, len = hssfRow.getLastCellNum(); i < len; i++) {
			Object object = getValue(hssfRow, i);
			jsonCellArray.put(object);
		}
		return jsonCellArray;
	}
	
	/**
	 * 根据坐标获取单元格的值
	 * 
	 * @param rowIndex 行索引(从0开始)
	 * @param colIndex 列索引(从0开始)
	 * @return 值
	 */
	public Object getValue(HSSFSheet sheet, int rowIndex, int colIndex) {
		HSSFCell hssfCell = sheet.getRow(rowIndex).getCell(colIndex);
		return getValue(hssfCell);
	}
	
	public Object getValue(HSSFRow row, int colIndex) {
		HSSFCell hssfCell = row.getCell(colIndex);
		return getValue(hssfCell);
	}
	
	/**
	 * 获取指定单元格的值
	 * @param hssfCell
	 * @return
	 */
	public Object getValue(HSSFCell hssfCell) {
		Object cellValue = "";
		int type = typeOfCell(hssfCell);
		switch (type) {
			case HSSFCell.CELL_TYPE_STRING:								//数
				cellValue = hssfCell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:							//布尔类型
				DecimalFormat df = new DecimalFormat("00");
				cellValue = df.format(hssfCell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:							//错误类型
				cellValue = hssfCell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_ERROR:								//公式
				cellValue = hssfCell.getErrorCellValue();
				break;
			case HSSFCell.CELL_TYPE_FORMULA:							//空白
				cellValue = hssfCell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			default:
				try {
					cellValue = hssfCell.getDateCellValue();
				} catch (NullPointerException e) {
					log.debug("null on getDateCellValue");
				} catch (Exception e) {
					
				}
				break;
		}
		return cellValue;
	}
	
	/**
	 * 根据单元格的坐标，获取其类型
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public int typeOfCell(HSSFSheet sheet, int rowIndex, int colIndex) {
		return typeOfCell(sheet.getRow(rowIndex).getCell(colIndex));
	}
	
	/**
	 * 获取单元格的类型
	 * @param hssfCell
	 * @return
	 */
	public int typeOfCell(HSSFCell hssfCell) {
		if (ValidateUtil.isNull(hssfCell)) {
			return -1;
		}
		return hssfCell.getCellType();
	}
}
