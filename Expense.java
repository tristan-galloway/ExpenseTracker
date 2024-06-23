public class Expense {
    // Instance variables
    private String _name;
    private double _cost;
    private String _category;

    // Constructor
    Expense(String name, double cost) {
        _name = name;
        _cost = cost;
        _category = "Other";
    }

    Expense(String name, double cost, String category) {
        _name = name;
        _cost = cost;
        _category = category;
    }

    // Getters
     String getName() {
        return _name;
    }

    public double getCost() {
        return _cost;
    }

    public String getCategory() {
        return _category;
    }

    // Get the details in a csv format
    public String toCSV() {
        return _name + "," + _cost + "," + _category;
    }

    // Get the information in a format to display to the user.
    public String getDisplayString() {
        return _name + " - $" + _cost + " - " + _category;
    }

    // Setters
    public void setName(String newName) {
        _name = newName;
    }

    public void setCost(double newCost) {
        _cost = newCost;
    }

    public void setCategory(String newCategory) {
        _category = newCategory;
    }
}