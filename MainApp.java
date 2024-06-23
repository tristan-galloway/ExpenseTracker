import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    
    // Instance Variables
    private ExpenseHistory _record = new ExpenseHistory();
    private Scanner _optionScanner = new Scanner(System.in);
    
    public void run() {
        System.out.println("Welcome to your expense tracker.");
        displayMenu();
        System.out.println("\nThank you for using the expense tracker today.");
    }
    
    public void displayMenu() {
        // Initialize option tracker.
        int userInput = 0;

        // Display the menu until exit is selected.
        while (userInput != 6) {
            _record.getSummary();
            System.out.println("1. Add expense");
            System.out.println("2. Remove expense");
            System.out.println("3. View breakdown");
            System.out.println("4. Import expense history");
            System.out.println("5. Export expense history");
            System.out.println("6. Exit");
            System.out.println("What would you like to do today? ");
            userInput = _optionScanner.nextInt();
            _optionScanner.nextLine();  // Consume the leftover newline character

            switch(userInput) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    removeExpense();
                    break;
                case 3:
                    viewBreakdown();
                    break;
                case 4:
                    importExpenses();
                    break;
                case 5:
                    exportExpenses();
                    break;
            }   
        }

    }

    public void addExpense(){
        clearConsole(); 

        // Get the name
        System.out.println("What is the name?");
        String name = _optionScanner.nextLine();
        clearConsole(); 

        // Get the cost
        System.out.println("What is the cost?");
        double cost = _optionScanner.nextDouble();
        _optionScanner.nextLine();  // Consume the leftover newline character
        clearConsole();

        // Get the category
        System.out.println("What is the category?");
        String category = _optionScanner.nextLine();
        clearConsole();

        // Build the expense object.
        Expense e = new Expense(name, cost, category);

        // Add the expense to the current history.
        _record.addExpense(e);
    }

    public void removeExpense(){
        clearConsole();

        // Display all expenses
        viewBreakdown();

        // Get the user option
        System.out.println("Enter selection:");
        int choice = _optionScanner.nextInt();
        _optionScanner.nextLine();  // Consume the leftover newline character
        clearConsole(); 

        // Remove expense by index
        choice -= 1;  // Correct for 0 index.
        _record.removeExpense(choice);
    }

    public void viewBreakdown(){
        System.out.println(" ");
        _record.displayExpenses();
        System.out.println(" ");
    }

    public void importExpenses(){
        try (BufferedReader reader = new BufferedReader(new FileReader("expenses.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line on the commas.
                String[] fields = line.split(",");
                // Only read populated strings
                if (fields.length == 3) {
                    String name = fields[0];
                    double cost = Double.valueOf(fields[1]);
                    String category = fields[2];
                    // Build expense
                    Expense expense = new Expense(name, cost, category);
                    // Add the new expense to the working record.
                    _record.addExpense(expense);
                }

                clearConsole();
                System.out.println(ConsoleColors.GREEN + "Succesfully imported data from \"expenses.csv\"" + ConsoleColors.RESET);
            }   
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void exportExpenses(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.csv"))) {
            for (Expense e : _record.getExpenses()) {
                writer.write(e.toCSV());
                writer.newLine();
            }

            clearConsole();
            System.out.println(ConsoleColors.GREEN + "Succesfully exported data to \"expenses.csv\"" + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void clearConsole(){
        // Clear the Terminal.
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}