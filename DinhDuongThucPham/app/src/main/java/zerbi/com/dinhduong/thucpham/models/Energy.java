package zerbi.com.dinhduong.thucpham.models;

/**
 * Created by binhnk on 6/14/2017.
 */

public class Energy { // năng lượng
    private int value;
    private String unit;

    public Energy(int value) {
        this.value = value;
        this.unit = "kcal";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return "kcal";
    }

    public void setUnit() {
        this.unit = "kcal";
    }
}
