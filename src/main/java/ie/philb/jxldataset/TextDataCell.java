/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset;

import java.math.BigDecimal;

/**
 *
 * @author pbradley
 */
public class TextDataCell extends AbstractDataCell {

    private String value;

    public TextDataCell(String value) {
        this.value = value;
    }

    public TextDataCell(Long value) {
        this.value = Long.toString(value);
    }

    public TextDataCell(BigDecimal value) {
        this.value = value.toPlainString();
    }

    @Override
    public DataCellType getType() {
        return DataCellType.Text;
    }

    public String getValue() {
        return value;
    }
}
