import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


public class BudgetDatabase {

    BigDecimal monthlyBudget = new BigDecimal(0);

    public BudgetDatabase(BigDecimal x) {
        this.monthlyBudget = x;

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

        {
            try {
                writer = new BufferedWriter(new FileWriter("monthlyBudgetData.txt"));
                writer.write(String.valueOf(monthlyBudget));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void LoadDatabase () {

    }
}
