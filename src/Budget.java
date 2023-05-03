import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Budget {

    private static final Budget single_instance = new Budget();

    BigDecimal monthlyBudget = new BigDecimal(0);
    BigDecimal currentBalance = new BigDecimal(0);
    BigDecimal monthlyPayments = new BigDecimal(0);
    ArrayList<Entry> expenseList = new ArrayList<Entry>();
    ArrayList<Entry> purchaseList = new ArrayList<Entry>();

    // constructor
    private Budget() {}

    public static Budget getInstance() {
        return single_instance;
    }

    public void addExpense(Entry expense) {
        expenseList.add(expense);
        try {
            BudgetDatabase.writeMonthlyExpenseToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addPurchase(Entry purchase) {
        purchaseList.add(purchase);
        try {
            BudgetDatabase.writeDailyPurchaseToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeExpense(String expense) {
        int pos = expense.indexOf('$');
        String name = expense.substring(0,pos-1);
        for (Entry ent : expenseList) {
            System.out.println("from removeExpense: " + name +" : " + ent.getName().toString());
            if (name.equals(ent.getName().toString())) {
                System.out.println("from remove expense: " + name +" : " + ent.getName().toString() + " TRUE");
                expenseList.remove(ent);
                break;
            }
        }
        try {
            BudgetDatabase.writeMonthlyExpenseToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Entry> getExpenseList() {
        return expenseList;
    }

    public  ArrayList<Entry> getPurchaseList() {
        return purchaseList;
    }

    public void calculateCurrentBalance() {
       currentBalance = monthlyBudget.subtract(monthlyPayments);
       // = currentBalance.subtract();
       //BudgetDatabase currValue = new BudgetDatabase();
    }


    public void setMonthlyBudget(String userInputBudget) {

        monthlyBudget = new BigDecimal(userInputBudget);

        BudgetDatabase.writeMonthlyBudgetToFile();

        String str = monthlyBudget.toString();
        System.out.println(str);

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
