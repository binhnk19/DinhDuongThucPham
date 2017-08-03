package zerbi.com.dinhduong.thucpham.models;

/**
 * Created by binhnk on 6/14/2017.
 */

public class Disposal { // phần trăm thải bỏ
    private int value;
    private String unit;

    public Disposal(int value) {
        this.value = value;
        this.unit = "percent";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return "percent";
    }

    public void setUnit() {
        this.unit = "percent";
    }
}
