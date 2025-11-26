/**
 * InputManager class handles all user input operations
 * Follows Single Responsibility Principle - manages input workflows
 */
public class InputManager {
    private InputValidator validator;

    public InputManager(InputValidator validator) {
        this.validator = validator;
    }

    public void inputStaffData(DaycareSystem system, int currentYear) {
        System.out.println("\n--- Enter Staff Information ---");
        for (int i = 0; i < system.getStaffCount(); i++) {
            try {
                String name = validator.getValidString(String.format("Staff #%d Name: ", i+1));
                int birthYear = validator.getValidYear(1900, currentYear);
                String gender = validator.getValidString("Gender: ");
                String jobTitle = validator.getValidString("Job Title: ");
                
                System.out.print("Weekly Hours: ");
                int weeklyHours = validator.getValidPositiveInt("Weekly Hours");
                if (weeklyHours > 168) throw new IllegalArgumentException("Weekly Hours cannot exceed 168");
                
                System.out.print("Wage Rate: ");
                double wageRate = validator.getValidPositiveDouble("Wage Rate");
                
                int hiringYear = validator.getValidYear(1900, currentYear);
                if (hiringYear > currentYear) throw new IllegalArgumentException("Hiring year cannot be in the future");

                StaffMember staff = new StaffMember(name, birthYear, gender, jobTitle, weeklyHours, wageRate, hiringYear);
                system.setStaffMember(i, staff);
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                i--;
            }
        }
    }

    public void inputChildrenData(DaycareSystem system, int currentYear) {
        System.out.println("--- Enter Children Information ---");
        for (int i = 0; i < system.getChildCount(); i++) {
            try {
                String name = validator.getValidString(String.format("Child #%d Name: ", i+1));
                int birthYear = validator.getValidYear(1900, currentYear);
                String gender = validator.getValidString("Gender: ");
                String parentName = validator.getValidString("Parent Name: ");
                String parentPhone = validator.getValidString("Parent Phone: ");
                String parentLanguage = validator.getValidString("Parent Language: ");
                
                System.out.print("Allergies (true/false): ");
                boolean hasAllergies = validator.getValidBoolean();
                
                System.out.print("Days/Week: ");
                int daysInDaycare = validator.getValidRange("Days/Week", 1, 7);
                
                System.out.print("Shift (1=AM,2=PM,3=Full): ");
                int shiftChoice = validator.getValidRange("Shift", 1, 3);
                
                String dropOffTime = validator.getValidString("Drop Off Time: ");
                String pickUpTime = validator.getValidString("Pick Up Time: ");

                Child child = new Child(name, birthYear, gender, parentName, parentPhone, parentLanguage,
                                       hasAllergies, daysInDaycare, shiftChoice, dropOffTime, pickUpTime);
                system.setChild(i, child);
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                i--;
            }
        }
    }

    public void inputParentRatings(DaycareSystem system) {
        System.out.println("--- Parent Ratings for Staff ---");
        for (int p = 0; p < system.getParentCount(); p++) {
            try {
                System.out.printf("Parent #%d:%n", p+1);
                for (int s = 0; s < system.getStaffCount(); s++) {
                    System.out.printf("  %s (1-5): ", system.getStaffMember(s).getName());
                    double rating = validator.getValidRange("Rating", 1, 5);
                    system.setRating(p, s, rating);
                }
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                p--;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                p--;
            }
        }
    }
}
