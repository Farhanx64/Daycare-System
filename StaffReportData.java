/**
 * StaffReportData class encapsulates staff report data and calculations
 * Follows Single Responsibility Principle
 */
public class StaffReportData {
    private StaffMember[] staff;
    private int currentYear;
    private double taxRate;

    public StaffReportData(StaffMember[] staff, int currentYear, double taxRate) {
        this.staff = staff;
        this.currentYear = currentYear;
        this.taxRate = taxRate;
    }

    // ============ GETTERS ============

    public StaffMember[] getStaff() {
        return staff;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public double getTaxRate() {
        return taxRate;
    }

    // ============ DISPLAY METHODS ============

    public void displayByRating() {
        System.out.println("\n--- Staff by Avg Rating (High→Low) ---");
        for (StaffMember sm : staff) {
            if (sm == null) continue;
            double avg = sm.getAverageRating();
            String level = avg >= 4.5 ? "Excellent" : avg >= 3.0 ? "Good" : "Poor";
            System.out.printf("%s — Avg: %.2f (%s)%n", sm.getName(), avg, level);
        }
    }

    public void displayPromotionEligible() {
        System.out.println("\n--- Staff Eligible for Promotion (5.0) ---");
        StaffMember[] promotionEligible = StaffMember.filterByPerfectRating(staff);
        if (promotionEligible.length == 0) {
            System.out.println("No staff members are eligible for promotion at this time.");
        } else {
            for (StaffMember sm : promotionEligible) {
                System.out.println(sm.getName());
            }
        }
    }

    public void displayPayDetails() {
        System.out.println("\n--- Staff Experience, Pay, Salary & Net Income ---");
        for (StaffMember sm : staff) {
            if (sm == null) continue;
            int exp = sm.calculateExperience(currentYear);
            double weekly = sm.calculateWeeklyPay();
            double annual = sm.calculateAnnualSalary();
            double net = sm.calculateNetIncome(taxRate);
            System.out.printf("%s: %d yrs, Weekly $%.2f, Annual $%.2f, Net $%.2f%n",
                              sm.getName(), exp, weekly, annual, net);
        }
    }

    public void displayInfantTeachers() {
        System.out.println("\n--- Infant Teachers ---");
        StaffMember[] infantTeachers = StaffMember.filterByJobTitle(staff, "Infant Teacher");
        if (infantTeachers.length == 0) {
            System.out.println("No Infant Teachers on staff.");
        } else {
            for (StaffMember sm : infantTeachers) {
                System.out.println(sm.getName());
            }
        }
    }

    public void displayRatingsMatrix(double[][] ratings) {
        System.out.println("\n--- Parent Ratings ---");
        for (int p = 0; p < ratings.length; p++) {
            System.out.printf("Parent #%d: ", p+1);
            for (int s = 0; s < staff.length; s++) {
                System.out.printf("%s=%.1f  ", staff[s].getName(), ratings[p][s]);
            }
            System.out.println();
        }
    }

    public void displayAll(double[][] ratings) {
        displayByRating();
        displayPromotionEligible();
        displayPayDetails();
        displayInfantTeachers();
        displayRatingsMatrix(ratings);
    }
}
