import java.io.*;
import java.math.BigDecimal;

public class BudgetDatabase {

    private BudgetDatabase() {}

    public static void loadDatabase() throws IOException {
        loadMonthlyBudget();
        loadExpenses();
        loadPurchases();
    }

    public static void loadMonthlyBudget() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("monthlyBudgetData.txt"));
        String monthlyBudgetValue = reader.readLine();
        Budget.getInstance().setMonthlyBudget(new BigDecimal(monthlyBudgetValue));
        reader.close();
        System.out.println("from load:" + monthlyBudgetValue + ":" );
    }

    public static void loadExpenses() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("monthlyExpenseData.txt"));
        String line = reader.readLine();
        while (line != null) {
            int pos = line.indexOf('|');
            String name = line.substring(0,pos);
            String value = line.substring(pos+1);
            System.out.println("From expense load :" + value + ":");
            Entry ent = new Entry(name, new BigDecimal(value));
            Budget.getInstance().addExpense(ent);
            line = reader.readLine();
        }
        reader.close();
    }

    public static void loadPurchases() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("dailyPurchaseData.txt"));

        String line = reader.readLine();

        while (line != null) {
            System.out.println("From load purchase. line :" + line + ":");
            int pos = line.indexOf('|');
            String name = line.substring(0,pos);
            String value = line.substring(pos+1);
            System.out.println("from purchase load :" + value + ":");
            Entry ent = new Entry(name, new BigDecimal(value));
            Budget.getInstance().addPurchase(ent);
            line = reader.readLine();
        }
        reader.close();
    }

    public static void writeMonthlyExpenseToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("e:monthlyExpenseData.txt"));
        for (Entry ent : Budget.getInstance().getExpenseList()) {
            writer.write(ent.getName() + "|" + ent.getValue().toString() + "\n");
        }
        writer.close();

    }

    public static void writeDailyPurchaseToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("e:dailyPurchaseData.txt"));
        for (Entry ent : Budget.getInstance().getPurchaseList()) {
            writer.write(ent.getName() + "|" + ent.getValue().toString() + "\n");
        }
        writer.close();
    }

    public static void writeMonthlyBudgetToFile() throws IOException {
        BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("monthlyBudgetData.txt"));
            writer.write(Budget.getInstance().getMonthlyBudget().toString());
            writer.close();

    }
}
