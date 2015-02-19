/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pbradley
 */
public class DataSet {

    private List<List<AbstractDataCell>> rows;
    private List<String> headers;
    private int verticalFreeze = 1;
    private int horizontalFreeze = 1;
    private String sheetName = "Sheet 1";

    public DataSet(List<String> headers) {
        this.headers = new ArrayList<String>();
        this.headers.addAll(headers);
        rows = new ArrayList<List<AbstractDataCell>>();
    }

    public int getRowCount() {
        return rows.size();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<AbstractDataCell> getRow(int rowIdx) {
        if (rowIdx < 0 || rowIdx > getRowCount()) {
            throw new IllegalArgumentException("Row parameter " + rowIdx + " outside range [0, " + getRowCount() + "]");
        }

        List<AbstractDataCell> row = rows.get(rowIdx);
        return row;
    }

    public void addRow(List<AbstractDataCell> row) {
        rows.add(row);
    }

    public int getVerticalFreeze() {
        return verticalFreeze;
    }

    public void setVerticalFreeze(int verticalFreeze) {
        this.verticalFreeze = verticalFreeze;
    }

    public int getHorizontalFreeze() {
        return horizontalFreeze;
    }

    public void setHorizontalFreeze(int horizontalFreeze) {
        this.horizontalFreeze = horizontalFreeze;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

}
