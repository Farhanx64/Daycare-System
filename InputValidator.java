import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * InputValidator class handles all user input validation
 * Following the Single Responsibility Principle
 */
public class InputValidator {
    private Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getValidCount(String fieldName, int max) {
        while (true) {
            try {
                System.out.printf("Enter number of %s (max %d): ", fieldName, max);
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    throw new InputMismatchException("Please enter a valid integer");
                }
                int count = scanner.nextInt();
                scanner.nextLine();
                if (count < 0) throw new IllegalArgumentException("Count cannot be negative");
                if (count > max) {
                    System.out.println("Warning: Count exceeds " + max + ", using " + max);
                    return max;
                }
                if (count == 0) throw new IllegalArgumentException("Count must be at least 1");
                return count;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public int getValidYear(int minYear, int maxYear) {
        while (true) {
            try {
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    throw new InputMismatchException("Please enter a valid year");
                }
                int year = scanner.nextInt();
                scanner.nextLine();
                if (year < minYear || year > maxYear) 
                    throw new IllegalArgumentException("Year must be between " + minYear + " and " + maxYear);
                return year;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public int getValidPositiveInt(String fieldName) {
        while (true) {
            try {
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    throw new InputMismatchException("Please enter a valid integer");
                }
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value <= 0) throw new IllegalArgumentException(fieldName + " must be positive");
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public double getValidPositiveDouble(String fieldName) {
        while (true) {
            try {
                if (!scanner.hasNextDouble()) {
                    scanner.nextLine();
                    throw new InputMismatchException("Please enter a valid number");
                }
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value <= 0) throw new IllegalArgumentException(fieldName + " must be positive");
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public int getValidRange(String fieldName, int min, int max) {
        while (true) {
            try {
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    throw new InputMismatchException("Please enter a valid integer");
                }
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value < min || value > max) {
                    throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public boolean getValidBoolean() {
        while (true) {
            try {
                if (!scanner.hasNextBoolean()) {
                    String input = scanner.nextLine().trim().toLowerCase();
                    if (input.equals("yes") || input.equals("y")) return true;
                    if (input.equals("no") || input.equals("n")) return false;
                    throw new InputMismatchException("Please enter true/false or yes/no");
                }
                return scanner.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
                scanner.nextLine();
            }
        }
    }

    public String getValidString(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) throw new IllegalArgumentException("Input cannot be empty");
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }
}
