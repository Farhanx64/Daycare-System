public class StaffMember {
    private String name;
    private int birthYear;
    private String gender;
    private String jobTitle;
    private int weeklyHours;
    private double wageRate;
    private int hiringYear;
    private double averageRating;
    // Default constructor
    public StaffMember() {
    this("", 0, "", "", 0, 0.0, 0);
    }
    // Constructor with parameters
    public StaffMember(String name, int birthYear, String gender,
    String jobTitle, int weeklyHours,
    double wageRate, int hiringYear) {
    this.name = name;
    this.birthYear = birthYear;
    this.gender = gender;
    this.jobTitle = jobTitle;
    this.weeklyHours = weeklyHours;
    this.wageRate = wageRate;
    this.hiringYear = hiringYear;
    this.averageRating = 0.0;
    }
    // Getters and setters
    public String getName() { return name; }
    public int getBirthYear() { return birthYear; }
    public String getGender() { return gender; }
    public String getJobTitle() { return jobTitle; }
    public int getWeeklyHours() { return weeklyHours; }
    public double getWageRate() { return wageRate; }
    public int getHiringYear() { return hiringYear; }
    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double avg) { this.averageRating = avg; }
    // Helper method: calculate years of experience
    public int calculateExperience(int currentYear) {
    return currentYear - hiringYear;
    }
    // Helper method: calculate weekly pay, including overtime
    public double calculateWeeklyPay() {
    if (weeklyHours > 40) {
    return 40 * wageRate + (weeklyHours - 40) * 1.5 * wageRate;
    }
    return weeklyHours * wageRate;
    }
    // Helper method: calculate annual salary
    public double calculateAnnualSalary() {
    return calculateWeeklyPay() * 52;
    }
    // Helper method: calculate net income after tax
    public double calculateNetIncome(double taxRate) {
    return calculateAnnualSalary() * (1 - taxRate);
    }
   }