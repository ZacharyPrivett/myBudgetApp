import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Budget {

    private static final Budget single_instance = new Budget();

    BigDecimal monthlyBudget = new BigDecimal(0);
    BigDecimal currentBalance = new BigDecimal(0);
    BigDecimal expenseTotal = new BigDecimal(0);
    BigDecimal purchaseTotal = new BigDecimal(0);
    ArrayList<Entry> expenseList = new ArrayList<Entry>();
    ArrayList<Entry> purchaseList = new ArrayList<Entry>();

    // constructor
    private Budget() {}
    // Returns single instance of Budget Class
    public static Budget getInstance() {
        return single_instance;
    }

    public void addExpense(Entry expense) throws IOException {
        expenseList.add(expense);
        BudgetDatabase.writeMonthlyExpenseToFile();


    }

    public void addPurchase(Entry purchase) throws IOException {
        purchaseList.add(purchase);
        BudgetDatabase.writeDailyPurchaseToFile();
    }

    public void removeExpense(String expense) throws IOException {
        int pos = expense.indexOf('$');
        String name = expense.substring(0,pos-1);
        for (Entry ent : expenseList) {
            System.out.println("from removeExpense: " + name +" : " + ent.getName());
            if (name.equals(ent.getName())) {
                System.out.println("from removeExpense: " + name +" : " + ent.getName() + " TRUE");
                expenseList.remove(ent);
                break;
            }
        }
        BudgetDatabase.writeMonthlyExpenseToFile();
    }

    public void removePurchase(String purchase) throws IOException {
        int pos = purchase.indexOf('$');
        String name = purchase.substring(0,pos-1);
        // loops through purchaseList to remove selected purchase
        for (Entry ent : purchaseList) {
            //System.out.println("from removePurchase: " + name +" : " + ent.getName());
            if (name.equals(ent.getName())) {
                //System.out.println("from removePurchase: " + name +" : " + ent.getName() + " TRUE");
                purchaseList.remove(ent); // remove selected item
                break;
            }
        }
        BudgetDatabase.writeDailyPurchaseToFile();
    }

    public void newMonth() throws IOException {
        purchaseList.clear();
        BudgetDatabase.writeDailyPurchaseToFile();
    }

    public void calculateExpenseTotal() {
        expenseTotal = expenseTotal.subtract(expenseTotal);
        for (Entry ent : expenseList) {
            expenseTotal = expenseTotal.add(ent.getValue());
        }
    }

    public void calculatePurchaseTotal() {
        purchaseTotal = purchaseTotal.subtract(purchaseTotal);
        for (Entry ent : purchaseList) {
            purchaseTotal = purchaseTotal.add(ent.getValue());
        }
    }

    public ArrayList<Entry> getExpenseList() {
        return expenseList;
    }

    public  ArrayList<Entry> getPurchaseList() {
        return purchaseList;
    }

    public void calculateCurrentBalance() {
        calculateExpenseTotal();
        calculatePurchaseTotal();
        currentBalance = currentBalance.subtract(currentBalance); // Zeros out current balance
        currentBalance = currentBalance.add(monthlyBudget);
        currentBalance = currentBalance.subtract(expenseTotal).subtract(purchaseTotal);
    }

    public void setMonthlyBudget(BigDecimal userInputBudget) throws IOException {
        monthlyBudget = monthlyBudget.subtract(monthlyBudget);
        monthlyBudget = monthlyBudget.add(userInputBudget);
        BudgetDatabase.writeMonthlyBudgetToFile();
    }

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public BigDecimal getCurrentBalance() {
        calculateCurrentBalance();
        return currentBalance;
    }

    // Get sum of expense. Not used in current build. Here for later update
    public BigDecimal getExpenseTotal() {
        calculateExpenseTotal();
        return expenseTotal;
    }

    // Get sum of purchase. Not used in current build. Here for later update
    public BigDecimal getPurchaseTotal() {
        calculatePurchaseTotal();
        return purchaseTotal;
    }
}
