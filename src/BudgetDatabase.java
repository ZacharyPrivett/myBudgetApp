import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BudgetDatabase {

    BigDecimal monthlyBudget = new BigDecimal(0);

    public static String monthlyBudgetValue;
    // public static String currentBudgetBalance;
    public static ArrayList<String> dailyPurchaseList;
    public static ArrayList<String> monthlyExpenseList;


    public BudgetDatabase(BigDecimal x) {
        this.monthlyBudget = x;

    }

    public static void loadDatabase() throws IOException {

        loadMonthlyBudget();
        loadExpenses();
        loadPurchases();

    }

    private static void loadMonthlyBudget() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("monthlyBudgetData.txt"));
        monthlyBudgetValue = reader.readLine();
        reader.close();
    }

    private static void loadExpenses() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("monthlyExpenseData.txt"));
        monthlyExpenseList = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            monthlyExpenseList.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    /*

    BigDecimal currentBalance = new BigDecimal(0);
    BigDecimal monthlyPayments = new BigDecimal(0);
    ArrayList<String> expenseNameList = new ArrayList<String>();
    ArrayList<Double> expensePriceList = new ArrayList<Double>();

    static String mb;
    */

    public void createMonthlyBudgetDatabase() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("monthlyBudgetData.txt"));
            writer.write(String.valueOf(monthlyBudget));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
    public void LoadDatabase () {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("monthlyBudgetData.txt"));
            monthlyBudgetValue = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

     */
}
