public class Main {
    public static void main(String[] args){
        BudgetDatabase.loadDatabase();  // On startup loads data fields with stored values from txt files
        new MainFrame();                // On startup create GUI for user
    }
}