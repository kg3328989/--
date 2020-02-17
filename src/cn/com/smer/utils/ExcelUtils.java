package cn.com.smer.utils;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

//import net.sf.jxls.transformer.XLSTransformer;

/**
 * ��Excel�ļ����е���ʱ���õ��Ĺ����ࡣ
 * 
 * @author gyz
 *
 */
public class ExcelUtils {
	private static DecimalFormat df = new DecimalFormat("0%");

	public static Object getValue(HSSFCell cell) {
		Object obj = null;
		if (null != cell) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // ����
				obj = (int) cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING: // �ַ���
				obj = cell.getStringCellValue();
				break;
			default: // ����
				break;
			}
		}
		return obj;
	}

	public static Object getValue(XSSFCell cell, String type) {
		if (null != cell) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC: // ����
				if ("int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type)) {
					return (int) cell.getNumericCellValue();
				} else if ("float".equalsIgnoreCase(type)) {
					return (float) cell.getNumericCellValue();
				} else if ("string".equalsIgnoreCase(type)) {
					return String.valueOf((int) cell.getNumericCellValue());
				} else if ("date".equalsIgnoreCase(type)) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						return cell.getDateCellValue();
					}
				} else if ("percent".equalsIgnoreCase(type)) {
					return df.format(cell.getNumericCellValue());
					// return cell.getNumericCellValue() * 100 + "%";
				}
				break;
			case XSSFCell.CELL_TYPE_STRING: // �ַ���
				if ("int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type)) {
					try {
						return Integer.parseInt(cell.getStringCellValue().trim());
					} catch (NumberFormatException e) {
						return null;
					}
				} else if ("float".equalsIgnoreCase(type)) {
					try {
						return Float.parseFloat(cell.getStringCellValue().trim());
					} catch (NumberFormatException e) {
						return null;
					}
				} else if ("string".equalsIgnoreCase(type)) {
					String obj = cell.getStringCellValue().trim();
					return "".equals(obj) ? null : obj;
				} else if ("date".equalsIgnoreCase(type)) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						return cell.getDateCellValue();
					}
				}
				break;
			default: // ����
				break;
			}
		}

		return null;
	}
/*	public static void exportToExcel(HttpServletRequest request, HttpServletResponse response, String outFileName,
			String tplFileName, Map<String, Object> bean) throws IOException {
		String outFile;
		String tplFile;

		outFile = ExcelUtils.processFileName(request, outFileName);
		tplFile = request.getServletContext().getRealPath("/") + CONTANTS.TEMPLATEFILES + tplFileName;

		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "private");
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Type", "application/force-download");

		response.setHeader("Content-disposition", "attachment;filename=\"" + outFile + "\"");
		XLSTransformer transformer = new XLSTransformer();

		InputStream is = new BufferedInputStream(new FileInputStream(tplFile));
		Workbook workbook = null;
		try {
			workbook = transformer.transformXLS(is, bean);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		is.close();
		out.flush();
		out.close();
	}

	private static String processFileName(HttpServletRequest request, String fileNames) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
				String name = java.net.URLEncoder.encode(fileNames, "UTF8");
				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// ff,chrome
				codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}*/
}
