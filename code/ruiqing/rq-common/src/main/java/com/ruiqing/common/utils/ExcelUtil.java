package com.ruiqing.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
/**
 * @ClassName: ExcelUtil
 * @Description: excel文件操作工具类
 * 
 */
public class ExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * 导出Excel
	 * 
	 * @param excelName
	 *            要导出的excel名称
	 * @param list
	 *            要导出的数据集合
	 * @param fieldMap
	 *            中英文字段对应Map,即要导出的excel表头
	 * @param response
	 *            使用response可以导出到浏览器
	 */
	public static <T> void export(String excelName, List<T> list, LinkedHashMap<String, String> fieldMap,
                                  HttpServletResponse response, HttpServletRequest request) throws IOException {
		String fileName = null;
		// 设置默认文件名为当前时间：年月日
		if (excelName == null || excelName == "") {
			fileName = getNoFormatToday() + ".xls";
		}else{
			fileName = excelName + "_" + getNoFormatToday() + ".xls";
		}
		/*String agent = request.getHeader("USER-AGENT").toLowerCase();
		String finalFileName = null;  
		if (StringUtils.contains(agent, "msie")) {// IE浏览器
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(agent, "mozilla")) {// google,火狐浏览器
			finalFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		} else {
			finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
		}*/
		// 设置response头信息
		response.reset();
		response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Content-disposition", "attachment; filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
		try {
			// 创建一个WorkBook,对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			if (CollectionUtils.isEmpty(list)) {
				// 在Workbook中，创建一个sheet，对应Excel中的工作薄（sheet）
				HSSFSheet sheet = wb.createSheet(excelName);
				// 给单子名称一个长度
				sheet.setDefaultColumnWidth(18);
				sheet.setSelected(true);
				// 创建单元格，并设置值表头 设置表头居中
				HSSFCellStyle style = wb.createCellStyle();
				// 创建一个居中格式
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 填充工作表
				fillSheet(wb,sheet, list, fieldMap, style);
			} else {
				List<List<T>> lists = SplitSet.splitList(list, 30000);
				for (int j = 0; j < lists.size(); j++) {
					// 在Workbook中，创建一个sheet，对应Excel中的工作薄（sheet）
					HSSFSheet sheet = wb.createSheet(excelName + "_" + (j + 1));
					// 给单子名称一个长度
					sheet.setDefaultColumnWidth(18);
					sheet.setSelected(true);
					// 创建单元格，并设置值表头 设置表头居中
					HSSFCellStyle style = wb.createCellStyle();
					// 创建一个居中格式
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					// 填充工作表
					fillSheet(wb,sheet, lists.get(j), fieldMap, style);
				}
			}
			// 将文件输出
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			logger.info("导出Excel失败！");
			logger.error(e.getMessage());
		}
	}

	/**
	 * 根据字段名获取字段对象
	 *
	 * @param fieldName
	 *            字段名
	 * @param clazz
	 *            包含该字段的类
	 * @return 字段
	 */
	public static Field getFieldByName(String fieldName, Class<?> clazz) {
		logger.info("根据字段名获取字段对象:getFieldByName()");
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			// 如果本类中存在该字段，则返回
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			// 递归
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}

	/**
	 * 根据字段名获取字段值
	 *
	 * @param fieldName
	 *            字段名
	 * @param o
	 *            对象
	 * @return 字段值
	 * @throws Exception
	 *             异常
	 *
	 */
	public static Object getFieldValueByName(String fieldName, Object o) throws Exception {

		logger.info("根据字段名获取字段值:getFieldValueByName()");
		Object value = null;
		// 根据字段名得到字段对象
		Field field = getFieldByName(fieldName, o.getClass());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 如果该字段存在，则取出该字段的值
		if (field != null) {
			field.setAccessible(true);// 类中的成员变量为private,在类外边使用属性值，故必须进行此操作
			if (field.getGenericType().toString().equals("class java.util.Date")) {
				if(field.get(o) != null){
					value = sdf.format(field.get(o));
				}
			} else {
				value = field.get(o);// 获取当前对象中当前Field的value
			}
		} else {
			throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
		}

		return value;
	}

	/**
	 * 根据带路径或不带路径的属性名获取属性值,即接受简单属性名，
	 * 如userName等，又接受带路径的属性名，如student.department.name等
	 *
	 * @param fieldNameSequence
	 *            带路径的属性名或简单属性名
	 * @param o
	 *            对象
	 * @return 属性值
	 * @throws Exception
	 *             异常
	 *
	 */
	public static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {
		logger.info("根据带路径或不带路径的属性名获取属性值,即接受简单属性名:getFieldValueByNameSequence()");
		Object value = null;

		// 将fieldNameSequence进行拆分
		String[] attributes = fieldNameSequence.split("\\.");
		if (attributes.length == 1) {
			value = getFieldValueByName(fieldNameSequence, o);
		} else {
			// 根据数组中第一个连接属性名获取连接属性对象，如student.department.name
			Object fieldObj = getFieldValueByName(attributes[0], o);
			// 截取除第一个属性名之后的路径
			String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
			// 递归得到最终的属性对象的值
			value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
		}
		return value;

	}

	/**
	 * 向工作表中填充数据
	 *
	 * @param sheet
	 *            excel的工作表名称
	 * @param list
	 *            数据源
	 * @param fieldMap
	 *            中英文字段对应关系的Map
	 * @param style
	 *            表格中的格式
	 * @throws Exception
	 *             异常
	 *
	 */
	public static <T> void fillSheet(HSSFWorkbook wb,HSSFSheet sheet, List<T> list, LinkedHashMap<String, String> fieldMap,
			HSSFCellStyle style) throws Exception {
		logger.info("向工作表中填充数据:fillSheet()");
		// 定义存放英文字段名和中文字段名的数组
		String[] enFields = new String[fieldMap.size()];
		String[] cnFields = new String[fieldMap.size()];

		// 填充数组
		int count = 0;
		for (Entry<String, String> entry : fieldMap.entrySet()) {
			enFields[count] = entry.getKey();
			cnFields[count] = entry.getValue();
			count++;
		}

		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);

		// 填充表头
		for (int i = 0; i < cnFields.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(cnFields[i]);
			cell.setCellStyle(style);
			// sheet.autoSizeColumn(i);
		}
		
		//设置数字展示格式
		HSSFCellStyle numcestyle = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		numcestyle.setDataFormat(format.getBuiltinFormat("#,##0.00"));

		// 填充内容
		for (int index = 0; index < list.size(); index++) {
			row = sheet.createRow(index + 1);
			// 获取单个对象
			T item = list.get(index);
			for (int i = 0; i < enFields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = getFieldByName(enFields[i], item.getClass());
				Object objValue = getFieldValueByNameSequence(enFields[i], item);
				if(field.getType().getName().contains("BigDecimal")||field.getType().getName().contains("Double")||field.getType().getName().contains("Float")) {
					cell.setCellStyle(numcestyle);
					cell.setCellValue(Double.parseDouble(objValue == null ? "" : objValue.toString()));
				}else {
					cell.setCellValue(objValue == null ? "" : objValue.toString());
				}
							
			}
			
		}
	}
	
	/** * 取得当前日期 格式为20090211 * @return */
	public static String getNoFormatToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/** * 取得当前时间 格式为231611 * @return */
	public static String getNoFormatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}
}