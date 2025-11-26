/*
 * DayCareApp.java
 * Main driver to run the daycare system using OOP principles
 * Demonstrates: Encapsulation, Abstraction, Single Responsibility, Dependency Injection
 */
import java.util.Scanner;

public class DayCareApp {
    private static final int MAX_CAPACITY = 100;
    private static final int CURRENT_YEAR = 2024;
    private static final double TAX_RATE = 0.05;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Initialize components
            InputValidator validator = new InputValidator(scanner);
            
            // Get system dimensions
            int parentCount = validator.getValidCount("parents providing ratings", MAX_CAPACITY);
            int staffCount = validator.getValidCount("staff members", MAX_CAPACITY);
            int childCount = validator.getValidCount("children", MAX_CAPACITY);
            
            // Create daycare system with dependency injection
            DaycareSystem system = new DaycareSystem(staffCount, childCount, parentCount, CURRENT_YEAR, TAX_RATE);
            InputManager inputManager = new InputManager(validator);
            
            // Execute happy path
            executeHappyPath(system, inputManager);
            
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Happy path: shows the clear workflow without any clutter
     */
    private static void executeHappyPath(DaycareSystem system, InputManager inputManager) {
        // Step 1: Collect Data
        inputManager.inputStaffData(system, CURRENT_YEAR);
        inputManager.inputChildrenData(system, CURRENT_YEAR);
        inputManager.inputParentRatings(system);
        
        // Step 2: Process Data
        system.computeAverageRatings();
        system.sortStaffByRating();
        
        // Step 3: Generate Reports
        StaffReportData staffReport = system.getStaffReportData();
        BillingReport billingReport = system.generateBillingReport();
        
        // Step 4: Display Results
        staffReport.displayAll(system.getRatings());
        billingReport.display();
    }
}
