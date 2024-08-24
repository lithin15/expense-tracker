
//expense Tracker//



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Expense {
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Expense(LocalDate date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return date + " - " + category + ": $" + amount + " (" + description + ")";
    }
}

class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public List<Expense> searchByCategory(String category) {
        List<Expense> result = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                result.add(e);
            }
        }
        return result;
    }

    public double calculateTotalExpenses() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }
}

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Search by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.next());
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    tracker.addExpense(new Expense(date, amount, category, description));
                    break;
                case 2:
                    for (Expense e : tracker.getAllExpenses()) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    System.out.print("Enter category to search: ");
                    category = scanner.next();
                    List<Expense> found = tracker.searchByCategory(category);
                    if (found.isEmpty()) {
                        System.out.println("No expenses found in this category.");
                    } else {
                        for (Expense e : found) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Total Expenses: $" + tracker.calculateTotalExpenses());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}