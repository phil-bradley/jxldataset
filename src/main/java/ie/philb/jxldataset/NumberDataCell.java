/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.jxldataset;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author pbradley
 */
public class NumberDataCell extends AbstractDataCell {

    private BigDecimal value;

    public NumberDataCell(BigDecimal value) {
        this.value = value;
    }

    public NumberDataCell(Long value) {
        this(new BigDecimal(value));
    }

    public NumberDataCell(Integer value) {
        this(new BigDecimal(value));
    }

    public NumberDataCell(double value) {
        this(new BigDecimal(value));
    }

    public NumberDataCell(double value, int scale) {
        this(new BigDecimal(value).setScale(scale, RoundingMode.HALF_EVEN));
    }

    @Override
    public DataCellType getType() {
        return DataCellType.Number;
    }

    public BigDecimal getValue() {
        return value;
    }
}
