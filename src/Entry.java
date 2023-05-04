import java.math.BigDecimal;

public class Entry {

    private String name;
    private BigDecimal value;

    public Entry(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }
}
