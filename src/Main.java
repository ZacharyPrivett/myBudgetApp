import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        BudgetDatabase.loadDatabase();
        MainFrame myFrame = new MainFrame();
        //BudgetDatabase data = new BudgetDatabase();main

    /*
        String addExpense = "";

        Budget myBudget = Budget.getInstance();

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Budget Helper, an app that makes monthly budgeting easy");

        System.out.print("What is your monthly budget: $");
        myBudget.setMonthlyBudget(scan.nextBigDecimal());

        System.out.print("how much do you spend a month on recurring payments: $");
        myBudget.setMonthlyPayments(scan.nextBigDecimal());

        do {
            System.out.print("Would you like an expense? (Y/N): ");
            addExpense = scan.next();

            if (addExpense.equalsIgnoreCase("y")) {
                System.out.print("Add expense: ");
                myBudget.setExpenseList(scan.next());
            }

        } while(addExpense.equalsIgnoreCase("Y"));

        myBudget.calculateCurrentBalance();

        System.out.println("Monthly Budget: $" + myBudget.getMonthlyBudget());
        System.out.println("Monthly Payment: $" + myBudget.getMonthlyPayments());
        System.out.println("Current Balance: $" + myBudget.getCurrentBalance());

        for (String str : myBudget.getExpenseList()) {
            System.out.println(str);
        }

    */
    }
}