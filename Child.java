public class Child {
    private String name;
    private int birthYear;
    private String gender;
    private String parentName;
    private String parentPhone;
    private String parentLanguage;
    private boolean hasAllergies;
    private int daysInDaycare;
    private int shiftChoice;        // 1: half morning, 2: half afternoon, 3: full day
    private String dropOffTime;
    private String pickUpTime;

    // Default constructor
    public Child() {
        this("", 0, "", "", "", "", false, 0, 0, "", "");
    }

    // Constructor with parameters
    public Child(String name, int birthYear, String gender,
                 String parentName, String parentPhone,
                 String parentLanguage, boolean hasAllergies,
                 int daysInDaycare, int shiftChoice,
                 String dropOffTime, String pickUpTime) {
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.parentLanguage = parentLanguage;
        this.hasAllergies = hasAllergies;
        this.daysInDaycare = daysInDaycare;
        this.shiftChoice = shiftChoice;
        this.dropOffTime = dropOffTime;
        this.pickUpTime = pickUpTime;
    }

    // Getters
    public String getName() { return name; }
    public int getBirthYear() { return birthYear; }
    public String getGender() { return gender; }
    public String getParentName() { return parentName; }
    public String getParentPhone() { return parentPhone; }
    public String getParentLanguage() { return parentLanguage; }
    public boolean hasAllergies() { return hasAllergies; }
    public int getDaysInDaycare() { return daysInDaycare; }
    public int getShiftChoice() { return shiftChoice; }
    public String getDropOffTime() { return dropOffTime; }
    public String getPickUpTime() { return pickUpTime; }

    // Helper method: calculate age
    public int calculateAge(int currentYear) {
        return currentYear - birthYear;
    }

    // Helper method: determine base rate by age
    public double calculateBaseRate(int age) {
        if (age <= 1) return 20.0;
        if (age <= 3) return 15.0;
        return 10.0;
    }

    // Helper method: hours per day by shift choice
    public int calculateHoursPerDay() {
        return (shiftChoice == 1 || shiftChoice == 2) ? 4 : 8;
    }

    // Helper method: base weekly bill before discounts/fees
    public double calculateWeeklyBill(int age) {
        return daysInDaycare * calculateBaseRate(age) * calculateHoursPerDay();
    }

    // Helper method: discount percent based on bill amount
    public double calculateDiscountPercent(double bill) {
        if (bill >= 100) return 0.20;
        if (bill >= 75)  return 0.15;
        if (bill >= 50)  return 0.10;
        return 0.05;
    }

    // Helper method: time fee percent based on drop-off/pick-up times
    public double calculateTimeFeePercent() {
        if (dropOffTime.contains("5") || dropOffTime.contains("6") || dropOffTime.contains("7")) return 0.02;
        if (pickUpTime.contains("5") || pickUpTime.contains("6") || pickUpTime.contains("7")) return 0.02;
        return 0.0;
    }

    // Helper method: age status
    public String getAgeStatus(int age) {
        if (age <= 1) return "Infant";
        if (age <= 3) return "Toddler";
        return "Preschool";
    }

    // Helper method: discount label (e.g. "20%")
    public String getDiscountLabel(double pct) {
        return (int)(pct * 100) + "%";
    }

    // Helper method: time fee message
    public String getTimeFeeMessage(double pct) {
        if (pct == 0) return "No fees";
        return dropOffTime.contains("5") || dropOffTime.contains("6") || dropOffTime.contains("7")
            ? "Early drop-off fee 2%"
            : "Late pick-up fee 2%";
    }

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
    public void setGender(String gender) { this.gender = gender; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    public void setParentPhone(String parentPhone) { this.parentPhone = parentPhone; }
    public void setParentLanguage(String parentLanguage) { this.parentLanguage = parentLanguage; }
    public void setHasAllergies(boolean hasAllergies) { this.hasAllergies = hasAllergies; }
    public void setDaysInDaycare(int daysInDaycare) { this.daysInDaycare = daysInDaycare; }
    public void setShiftChoice(int shiftChoice) { this.shiftChoice = shiftChoice; }
    public void setDropOffTime(String dropOffTime) { this.dropOffTime = dropOffTime; }
    public void setPickUpTime(String pickUpTime) { this.pickUpTime = pickUpTime; }

    // Display method
    public void displayInfo(int currentYear) {
        int age = calculateAge(currentYear);
        double bill = calculateWeeklyBill(age);
        double discPct = calculateDiscountPercent(bill);
        double afterDisc = bill * (1 - discPct);
        double timePct = calculateTimeFeePercent();
        double total = afterDisc * (1 + timePct);

        System.out.println("\n--- " + name + " ---");
        System.out.println("Age: " + age + " (" + getAgeStatus(age) + ")");
        System.out.println("Gender: " + gender);
        System.out.println("Parent: " + parentName + " | Phone: " + parentPhone);
        System.out.println("Language: " + parentLanguage);
        System.out.println("Allergies: " + (hasAllergies ? "Yes" : "No"));
        System.out.println("Days/Week: " + daysInDaycare);
        System.out.println("Shift: " + (shiftChoice == 1 ? "Morning" : shiftChoice == 2 ? "Afternoon" : "Full Day"));
        System.out.println("Drop-off: " + dropOffTime + " | Pick-up: " + pickUpTime);
        System.out.printf("Weekly Bill: $%.2f → After Discount: $%.2f → Total: $%.2f%n", bill, afterDisc, total);
    }

    // Static filtering methods
    public static Child[] filterByAgeStatus(Child[] children, String ageStatus, int currentYear) {
        java.util.ArrayList<Child> result = new java.util.ArrayList<>();
        for (Child c : children) {
            if (c.getAgeStatus(c.calculateAge(currentYear)).equalsIgnoreCase(ageStatus)) {
                result.add(c);
            }
        }
        return result.toArray(new Child[0]);
    }

    public static Child[] filterByAllergies(Child[] children, boolean hasAllergies) {
        java.util.ArrayList<Child> result = new java.util.ArrayList<>();
        for (Child c : children) {
            if (c.hasAllergies() == hasAllergies) {
                result.add(c);
            }
        }
        return result.toArray(new Child[0]);
    }

    public static Child[] filterByParentLanguage(Child[] children, String language) {
        java.util.ArrayList<Child> result = new java.util.ArrayList<>();
        for (Child c : children) {
            if (c.getParentLanguage().equalsIgnoreCase(language)) {
                result.add(c);
            }
        }
        return result.toArray(new Child[0]);
    }

    public static int countByAgeStatus(Child[] children, String ageStatus, int currentYear) {
        int count = 0;
        for (Child c : children) {
            if (c.getAgeStatus(c.calculateAge(currentYear)).equalsIgnoreCase(ageStatus)) {
                count++;
            }
        }
        return count;
    }

    public static int countWithAllergies(Child[] children) {
        int count = 0;
        for (Child c : children) {
            if (c.hasAllergies()) {
                count++;
            }
        }
        return count;
    }
}
