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

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
    public void setGender(String gender) { this.gender = gender; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setWeeklyHours(int weeklyHours) { this.weeklyHours = weeklyHours; }
    public void setWageRate(double wageRate) { this.wageRate = wageRate; }
    public void setHiringYear(int hiringYear) { this.hiringYear = hiringYear; }

    // Display method
    public void displayInfo(int currentYear) {
        int exp = calculateExperience(currentYear);
        double weekly = calculateWeeklyPay();
        double annual = calculateAnnualSalary();
        double net = calculateNetIncome(0.05);

        System.out.println("\n--- " + name + " ---");
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Gender: " + gender);
        System.out.println("Birth Year: " + birthYear);
        System.out.println("Hiring Year: " + hiringYear);
        System.out.println("Experience: " + exp + " years");
        System.out.println("Weekly Hours: " + weeklyHours);
        System.out.println("Wage Rate: $" + wageRate);
        System.out.printf("Weekly Pay: $%.2f%n", weekly);
        System.out.printf("Annual Salary: $%.2f%n", annual);
        System.out.printf("Net Income (5%% tax): $%.2f%n", net);
        System.out.printf("Average Rating: %.2f%n", averageRating);
    }

    // Static filtering methods
    public static StaffMember[] filterByJobTitle(StaffMember[] staff, String jobTitle) {
        java.util.ArrayList<StaffMember> result = new java.util.ArrayList<>();
        for (StaffMember sm : staff) {
            if (sm.getJobTitle().equalsIgnoreCase(jobTitle)) {
                result.add(sm);
            }
        }
        return result.toArray(new StaffMember[0]);
    }

    public static StaffMember[] filterByMinRating(StaffMember[] staff, double minRating) {
        java.util.ArrayList<StaffMember> result = new java.util.ArrayList<>();
        for (StaffMember sm : staff) {
            if (sm.getAverageRating() >= minRating) {
                result.add(sm);
            }
        }
        return result.toArray(new StaffMember[0]);
    }

    public static StaffMember[] filterByPerfectRating(StaffMember[] staff) {
        java.util.ArrayList<StaffMember> result = new java.util.ArrayList<>();
        for (StaffMember sm : staff) {
            if (sm.getAverageRating() == 5.0) {
                result.add(sm);
            }
        }
        return result.toArray(new StaffMember[0]);
    }

    public static int countByJobTitle(StaffMember[] staff, String jobTitle) {
        int count = 0;
        for (StaffMember sm : staff) {
            if (sm.getJobTitle().equalsIgnoreCase(jobTitle)) {
                count++;
            }
        }
        return count;
    }

    public static double getAverageRating(StaffMember[] staff) {
        if (staff.length == 0) return 0.0;
        double sum = 0;
        for (StaffMember sm : staff) {
            sum += sm.getAverageRating();
        }
        return sum / staff.length;
    }

    public static double getHighestRating(StaffMember[] staff) {
        if (staff.length == 0) return 0.0;
        double highest = staff[0].getAverageRating();
        for (StaffMember sm : staff) {
            if (sm.getAverageRating() > highest) {
                highest = sm.getAverageRating();
            }
        }
        return highest;
    }

    public static double getLowestRating(StaffMember[] staff) {
        if (staff.length == 0) return 0.0;
        double lowest = staff[0].getAverageRating();
        for (StaffMember sm : staff) {
            if (sm.getAverageRating() < lowest) {
                lowest = sm.getAverageRating();
            }
        }
        return lowest;
    }
   }