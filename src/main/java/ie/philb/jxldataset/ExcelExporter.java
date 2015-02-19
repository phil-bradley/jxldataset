/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author pbradley
 */
public class ExcelExporter {

    private WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    private WritableFont standardFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
    private DisplayFormat moneyDisplayFormat = NumberFormats.THOUSANDS_FLOAT;

    public void export(DataSet dataSet, File exportFile) throws ExportException {

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(exportFile);
            WritableSheet sheet = workbook.createSheet(dataSet.getSheetName(), 0);
            sheet.getSettings().setHorizontalFreeze(dataSet.getHorizontalFreeze());
            sheet.getSettings().setVerticalFreeze(dataSet.getVerticalFreeze());

            int i = 0;
            for (String header : dataSet.getHeaders()) {
                addHeader(sheet, i, header, Alignment.LEFT);
                i++;
            }

            for (int rowIdx = 0; rowIdx < dataSet.getRowCount(); rowIdx++) {
                List<AbstractDataCell> row = dataSet.getRow(rowIdx);

                for (int colIdx = 0; colIdx < row.size(); colIdx++) {
                    AbstractDataCell cell = row.get(colIdx);

                    switch (cell.getType()) {
                        case Currency:
                            BigDecimal currencyValue = ((CurrencyDataCell) cell).getValue();
                            addCurrency(sheet, colIdx, rowIdx + 1, currencyValue);
                            break;
                        case Number:
                            BigDecimal numberValue = ((NumberDataCell) cell).getValue();
                            addNumber(sheet, colIdx, rowIdx + 1, numberValue);
                            break;
                        case Text:
                            String textValue = ((TextDataCell) cell).getValue();
                            addString(sheet, colIdx, rowIdx + 1, textValue, Alignment.LEFT);
                            break;
                    }
                }
            }

            autoFitColumns(sheet);

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException x) {
            throw new ExportException(x);
        }

    }

    private void addHeader(WritableSheet sheet, int col, String value, Alignment aligment) throws WriteException {
        WritableCellFormat format = new WritableCellFormat(headerFont);
        format.setAlignment(aligment);

        Label label0 = new Label(col, 0, value, format);
        sheet.addCell(label0);
    }

    private void addString(WritableSheet sheet, int col, int row, String value, Alignment aligment) throws WriteException {
        WritableCellFormat format = new WritableCellFormat(standardFont);
        format.setAlignment(aligment);

        Label label0 = new Label(col, row, value, format);
        sheet.addCell(label0);
    }

    private void addCurrency(WritableSheet sheet, int col, int row, BigDecimal value) throws WriteException {

        WritableFont font = new WritableFont(standardFont);

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            font.setColour(Colour.RED);
        }

        WritableCellFormat format = new WritableCellFormat(font, moneyDisplayFormat);
        jxl.write.Number numberCell = new jxl.write.Number(col, row, value.doubleValue(), format);
        sheet.addCell(numberCell);
    }

    private void addNumber(WritableSheet sheet, int col, int row, BigDecimal value) throws WriteException {
        CellFormat format = new WritableCellFormat(standardFont);
        jxl.write.Number numberCell = new jxl.write.Number(col, row, value.doubleValue(), format);
        sheet.addCell(numberCell);
    }

    private void autoFitColumns(WritableSheet sheet) {
        for (int i = 0; i < sheet.getColumns(); i++) {
            Cell[] cells = sheet.getColumn(i);
            int longestStrLen = -1;

            if (cells.length == 0) {
                continue;
            }

            /* Find the widest cell in the column. */
            for (int j = 0; j < cells.length; j++) {
                if (cells[j].getContents().length() > longestStrLen) {
                    String str = cells[j].getContents();
                    if (str == null || str.isEmpty()) {
                        continue;
                    }
                    longestStrLen = str.trim().length();
                }
            }

            /* If not found, skip the column. */
            if (longestStrLen == -1) {
                continue;
            }

            /* If wider than the max width, crop width */
            if (longestStrLen > 255) {
                longestStrLen = 255;
            }

            CellView cv = sheet.getColumnView(i);
            cv.setSize(longestStrLen * 256 + 512); /* Every character is 256 units wide, so scale it. */

            sheet.setColumnView(i, cv);
        }
    }

    public WritableFont getHeaderFont() {
        return new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    }

    public void setHeaderFont(WritableFont font) {
        this.headerFont = font;
    }

    public WritableFont getStandardFont() {
        return standardFont;
    }

    public void setStandardFont(WritableFont font) {
        this.standardFont = font;
    }

    public DisplayFormat getMoneyDisplayFormat() {
        return moneyDisplayFormat;
    }

    public void setMoneyDisplayFormat(DisplayFormat moneyDisplayFormat) {
        this.moneyDisplayFormat = moneyDisplayFormat;
    }

}
