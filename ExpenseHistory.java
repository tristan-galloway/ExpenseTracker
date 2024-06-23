import java.util.ArrayList;
import java.util.List;

public class ExpenseHistory {
    // Instance variables
    private List<Expense> _expenses = new ArrayList<Expense>();
    private int _transactionCount = 0;

    // Getters
    public int getCount() {
        return _transactionCount;
    }

    public List<Expense> getExpenses() {
        return _expenses;
    }

    public double getTotal() {
        // Initialize variable to hold a running total
        double total = 0;

        // Iterate through each expense in the expenses adding to the total.
        for (Expense expense : _expenses) {
            total += expense.getCost();
        }

        return total;
    }

    public void getSummary() {
        System.out.println("You have spent $" + getTotal() + " in " + getCount() + " transactions.");
    }

    public void displayExpenses() {
        for (int i = 0; i < _expenses.size(); i++) {
            int itemNum = i + 1;  // Correct the index to line up with what the user sees.
            Expense currentExpense = _expenses.get(i);
            System.out.println("[" + itemNum + "] " + currentExpense.getDisplayString());
        }
    }

    // Setters
    public void addExpense(Expense expense) {
        _expenses.add(expense);
        _transactionCount += 1;
    }

    // Methods
    public void removeExpense(int index) {
        _expenses.remove(index);
        _transactionCount -= 1;
    }
}