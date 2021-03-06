package sunnyxiaobai5.core.base;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.security.util.FieldUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class BaseExcelView extends AbstractExcelView {

    /**
     * Subclasses must implement this method to create an Excel HSSFWorkbook document,
     * given the model.
     *
     * @param model    the model Map
     * @param workbook the Excel workbook to complete
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = (String) model.get("title");
        Map<String, String> headerMap = (Map<String, String>) model.get("headerMap");
        String[] fieldArray = headerMap.keySet().toArray(new String[0]);
        String[] headerArray = headerMap.values().toArray(new String[0]);
        List dataList = (List) model.get("dataList");

        HSSFSheet sheet = buildSheet(workbook, title);

        int row = buildTitle(workbook, sheet, title, fieldArray.length);

        row = buildHeader(workbook, sheet, headerArray, row);

        buildData(sheet, fieldArray, dataList, row);
    }

    public void buildExcelDocument(String title, Map<String, String> headerMap, List<?> dataList, HSSFWorkbook workbook) throws Exception {
        String[] fieldArray = headerMap.keySet().toArray(new String[0]);
        String[] headerArray = headerMap.values().toArray(new String[0]);

        HSSFSheet sheet = buildSheet(workbook, title);

        int row = buildTitle(workbook, sheet, title, fieldArray.length);

        row = buildHeader(workbook, sheet, headerArray, row);

        buildData(sheet, fieldArray, dataList, row);
    }

    protected HSSFSheet buildSheet(HSSFWorkbook workbook, String title) {
        return workbook.createSheet(title);
    }

    /**
     * 创建title
     *
     * @param workbook    workbook
     * @param sheet       sheet
     * @param title       sheet标题
     * @param mergeColNum 标题所占列数量
     * @return 已使用行数
     */
    protected int buildTitle(HSSFWorkbook workbook, HSSFSheet sheet, String title, int mergeColNum) {
        HSSFCell cell = getCell(sheet, 0, 0);
        setText(cell, title);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, mergeColNum - 1));

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 24);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font);
        cell.setCellStyle(style);
        return 1;
    }

    /**
     * 创建表头
     *
     * @param workbook    workbook
     * @param sheet       sheet
     * @param headerArray 表头数据数组
     * @param row         表头起始行下标
     * @return 已使用行数
     */
    protected int buildHeader(HSSFWorkbook workbook, HSSFSheet sheet, String[] headerArray, int row) {
        Font font = workbook.createFont();
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);

        for (int i = 0; i < headerArray.length; i++) {
            HSSFCell cell = getCell(sheet, row, i);
            setText(cell, headerArray[i]);
            cell.setCellStyle(style);
        }
        return row + 1;
    }

    /**
     * 填充数据
     *
     * @param sheet      sheet
     * @param fieldArray fieldArray
     * @param dataList   数据集合
     * @param row        数据起始行下标
     * @return 已使用行数
     * @throws IllegalAccessException
     */
    protected <T> int buildData(HSSFSheet sheet, String[] fieldArray, List<T> dataList, int row) throws IllegalAccessException {
        for (int rowIndex = row; rowIndex < dataList.size() + row; rowIndex++) {
            for (int col = 0; col < fieldArray.length; col++) {
                HSSFCell cell = getCell(sheet, rowIndex, col);
                Object value = FieldUtils.getFieldValue(dataList.get(rowIndex - row), fieldArray[col]);
                setText(cell, String.valueOf(value));
            }
        }
        return row + dataList.size();
    }
}
