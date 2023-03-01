import java.math.BigDecimal;
import java.util.ArrayList;

public class Budget {

    private static final Budget single_instance = new Budget();

    BigDecimal monthlyBudget = new BigDecimal(0);
    BigDecimal currentBalance = new BigDecimal(0);
    BigDecimal monthlyPayments = new BigDecimal(0);
    ArrayList<String> expenseList = new ArrayList<String>();

    // constructor
    private Budget() {}

    public static Budget getInstance() {
        return single_instance;
    }

    public void setExpenseList(String expense) {
        expenseList.add(expense);
    }

    public ArrayList<String> getExpenseList() {
        return expenseList;
    }

    public void calculateCurrentBalance() {
       currentBalance = monthlyBudget.subtract(monthlyPayments);
    }

    public void setMonthlyBudget(BigDecimal userInputBudget) {
        monthlyBudget = monthlyBudget.add(userInputBudget);
    }

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setMonthlyPayments(BigDecimal userInputPayments ) {
        monthlyPayments = monthlyPayments.add(userInputPayments);
    }

    public BigDecimal getMonthlyPayments() {
        return monthlyPayments;
    }

    public String toString() {
        return "Month " + monthlyBudget;
    }

}
