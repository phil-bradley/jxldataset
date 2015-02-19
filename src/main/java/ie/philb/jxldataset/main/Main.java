/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset.main;

import ie.philb.jxldataset.AbstractDataCell;
import ie.philb.jxldataset.DataSet;
import ie.philb.jxldataset.ExcelExporter;
import ie.philb.jxldataset.ExportException;
import ie.philb.jxldataset.NumberDataCell;
import ie.philb.jxldataset.TextDataCell;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author pbradley
 */
public class Main {

    private static DataSet createSampleDataSet() {
        Random rnd = new Random();

        List<String> headers = new ArrayList<>();
        headers.add("Rank ");
        headers.add("SKU Code");
        headers.add("Name");
        headers.add("Units");
        headers.add("Gross");

        DataSet ds = new DataSet(headers);

        for (int i = 0; i < 100; i++) {
            List<AbstractDataCell> row = new ArrayList<>();
            row.add(new NumberDataCell(i));
            row.add(new NumberDataCell(rnd.nextInt(999999)));
            row.add(new TextDataCell(new BigInteger(128, rnd).toString(32)));
            row.add(new NumberDataCell(rnd.nextInt(9999)));
            row.add(new NumberDataCell(rnd.nextDouble(), 2));

            ds.addRow(row);
        }

        return ds;
    }

    public static void main(String[] args) {

        DataSet ds = createSampleDataSet();
        File exportFile = null;

        try {
            File exportDir = new File("C:\\temp");
            exportFile = File.createTempFile("export", ".xls", exportDir);
        } catch (IOException iox) {
            System.out.println("Failed to create export file: " + iox.getMessage());
            iox.printStackTrace();
            return;
        }

        try {
            ExcelExporter excelExporter = new ExcelExporter();
            excelExporter.export(ds, exportFile);

            System.out.println("Created export file in " + exportFile.getAbsolutePath());

        } catch (ExportException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
