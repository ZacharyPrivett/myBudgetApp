import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;



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
        monthlyExpenseList = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            monthlyExpenseList.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    private static void loadPurchases() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("dailyPurchaseData.txt"));
        dailyPurchaseList = new ArrayList<>();
        String line = reader.readLine();
        while(line != null) {
            dailyPurchaseList.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    public static void addMonthlyExpense(String monthlyExpense) throws IOException {
        monthlyExpenseList.add(monthlyExpense);
        writeMonthlyExpenseToFile();
    }

    public static void removeMonthlyExpense(String monthlyExpense) throws IOException {
        monthlyExpenseList.remove(monthlyExpense);
        writeMonthlyExpenseToFile();
    }

    public static void addDailyPurchase(String dailyPurchase) throws IOException {
        dailyPurchaseList.add(dailyPurchase);
        writeDailyPurchaseToFile();
    }

    public static void removeDailyPurchase(String dailyPurchase) throws IOException {
        dailyPurchaseList.remove(dailyPurchase);
        writeDailyPurchaseToFile();
    }

    public static void writeMonthlyExpenseToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("monthlyExpenseData.txt"));
        for (int i = 0; i < monthlyExpenseList.size()-1; i++) {
            writer.write(monthlyExpenseList.get(i) + "\n");

        }
        writer.close();

    }

    public static void writeDailyPurchaseToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("dailyPurchaseData.txt"));
        for (int i = 0; i < dailyPurchaseList.size()-1; i++) {
            writer.write(dailyPurchaseList.get(i) + "\n");
        }
        writer.close();

    }

    public void writeMonthlyBudgetToFile() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("monthlyBudgetData.txt"));
            writer.write(String.valueOf(monthlyBudget));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadMonthlyBudget();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
