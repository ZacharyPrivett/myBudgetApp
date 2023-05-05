import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Budget {

    private static final Budget single_instance = new Budget();

    BigDecimal monthlyBudget = new BigDecimal(0);
    BigDecimal currentBalance = new BigDecimal(0);
    BigDecimal expenseTotal = new BigDecimal(0);
    BigDecimal purchaseTotal = new BigDecimal(0);
    ArrayList<Entry> expenseList = new ArrayList<>();
    ArrayList<Entry> purchaseList = new ArrayList<>();

    // constructor
    private Budget() {}
    // Returns single instance of Budget Class
    public static Budget getInstance() {
        return single_instance;
    }
    // Adds expense to expenseList and calls method to write list to file
    public void addExpense(Entry expense) throws IOException {
        expenseList.add(expense);
        BudgetDatabase.writeMonthlyExpenseToFile();
    }
    // Adds purchase to purchaseList and calls method to write list to file
    public void addPurchase(Entry purchase) throws IOException {
        purchaseList.add(purchase);
        BudgetDatabase.writeDailyPurchaseToFile();
    }
    // Removes expense from expenseList and calls method to write list to file
    public void removeExpense(String expense) throws IOException {
        // Grabs the string that in front of '$' to compare
        int pos = expense.indexOf('$');
        String name = expense.substring(0,pos-1);
        // loops through expenseList to remove selected expense
        for (Entry ent : expenseList) {
            if (name.equals(ent.getName())) {
                expenseList.remove(ent);    // remove selected item
                break;
            }
        }
        BudgetDatabase.writeMonthlyExpenseToFile();
    }
    // Removes purchase from purchaseList and calls method to write list to file
    public void removePurchase(String purchase) throws IOException {
        // Grabs the string that in front of '$' to compare
        int pos = purchase.indexOf('$');
        String name = purchase.substring(0,pos-1);
        // loops through purchaseList to remove selected purchase
        for (Entry ent : purchaseList) {
            if (name.equals(ent.getName())) {
                purchaseList.remove(ent);   // remove selected item
                break;
            }
        }
        BudgetDatabase.writeDailyPurchaseToFile();
    }
    // Called from MainFrame. Clears purchase list and writes list to file
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
    // Sets new monthly budget and writes value to file
    public void setMonthlyBudget(BigDecimal userInputBudget) throws IOException {
        monthlyBudget = monthlyBudget.subtract(monthlyBudget); // Zeros out current balance
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

    // Get sum of expense. Not used in current build. Here for future update
    public BigDecimal getExpenseTotal() {
        calculateExpenseTotal();
        return expenseTotal;
    }

    // Get sum of purchase. Not used in current build. Here for future update
    public BigDecimal getPurchaseTotal() {
        calculatePurchaseTotal();
        return purchaseTotal;
    }
}
