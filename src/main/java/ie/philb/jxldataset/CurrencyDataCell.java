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
public class CurrencyDataCell extends AbstractDataCell {

    private BigDecimal value;

    public CurrencyDataCell(BigDecimal value) {
        this.value = value;
    }

    public CurrencyDataCell(Long value) {
        this(new BigDecimal(value));
    }

    public CurrencyDataCell(Integer value) {
        this(new BigDecimal(value));
    }

    public CurrencyDataCell(double value) {
        this(new BigDecimal(value));
    }

    public CurrencyDataCell(double value, int scale) {
        this(new BigDecimal(value).setScale(scale, RoundingMode.HALF_EVEN));
    }

    @Override
    public DataCellType getType() {
        return DataCellType.Currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
