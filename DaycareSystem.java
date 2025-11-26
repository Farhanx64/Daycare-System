/**
 * DaycareSystem class encapsulates all daycare business logic
 * Following OOP principles: Encapsulation, Abstraction, Single Responsibility
 */
public class DaycareSystem {
    private StaffMember[] staff;
    private Child[] children;
    private double[][] ratings;
    private int currentYear;
    private double taxRate;

    public DaycareSystem(int staffCount, int childCount, int parentCount, int currentYear, double taxRate) {
        this.staff = new StaffMember[staffCount];
        this.children = new Child[childCount];
        this.ratings = new double[parentCount][staffCount];
        this.currentYear = currentYear;
        this.taxRate = taxRate;
    }

    // ============ STAFF MANAGEMENT ============

    public void setStaffMember(int index, StaffMember member) {
        if (index >= 0 && index < staff.length) {
            staff[index] = member;
        }
    }

    public StaffMember getStaffMember(int index) {
        if (index >= 0 && index < staff.length) {
            return staff[index];
        }
        return null;
    }

    public StaffMember[] getAllStaff() {
        return staff;
    }

    public int getStaffCount() {
        return staff.length;
    }

    // ============ CHILDREN MANAGEMENT ============

    public void setChild(int index, Child child) {
        if (index >= 0 && index < children.length) {
            children[index] = child;
        }
    }

    public Child getChild(int index) {
        if (index >= 0 && index < children.length) {
            return children[index];
        }
        return null;
    }

    public Child[] getAllChildren() {
        return children;
    }

    public int getChildCount() {
        return children.length;
    }

    // ============ RATINGS MANAGEMENT ============

    public void setRating(int parentIndex, int staffIndex, double rating) {
        if (parentIndex >= 0 && parentIndex < ratings.length &&
            staffIndex >= 0 && staffIndex < ratings[0].length) {
            ratings[parentIndex][staffIndex] = rating;
        }
    }

    public double getRating(int parentIndex, int staffIndex) {
        if (parentIndex >= 0 && parentIndex < ratings.length &&
            staffIndex >= 0 && staffIndex < ratings[0].length) {
            return ratings[parentIndex][staffIndex];
        }
        return 0.0;
    }

    public int getParentCount() {
        return ratings.length;
    }

    // ============ PROCESSING METHODS ============

    public void computeAverageRatings() {
        for (int s = 0; s < staff.length; s++) {
            if (staff[s] == null) continue;
            double sum = 0;
            for (int p = 0; p < ratings.length; p++) {
                sum += ratings[p][s];
            }
            staff[s].setAverageRating(sum / ratings.length);
        }
    }

    public void sortStaffByRating() {
        // Bubble sort by average rating (descending)
        for (int pass = 0; pass < staff.length - 1; pass++) {
            for (int j = 0; j < staff.length - pass - 1; j++) {
                if (staff[j] != null && staff[j + 1] != null) {
                    if (staff[j].getAverageRating() < staff[j + 1].getAverageRating()) {
                        StaffMember tmp = staff[j];
                        staff[j] = staff[j + 1];
                        staff[j + 1] = tmp;
                    }
                }
            }
        }
    }

    // ============ QUERY METHODS ============

    public StaffMember[] getPromotionEligible() {
        return StaffMember.filterByPerfectRating(staff);
    }

    public StaffMember[] getInfantTeachers() {
        return StaffMember.filterByJobTitle(staff, "Infant Teacher");
    }

    public Child[] getChildrenByAgeStatus(String ageStatus) {
        return Child.filterByAgeStatus(children, ageStatus, currentYear);
    }

    public Child[] getChildrenWithAllergies() {
        return Child.filterByAllergies(children, true);
    }

    public int countChildrenWithAllergies() {
        return Child.countWithAllergies(children);
    }

    // ============ REPORT METHODS ============

    public BillingReport generateBillingReport() {
        return new BillingReport(children, currentYear);
    }

    public StaffReportData getStaffReportData() {
        return new StaffReportData(staff, currentYear, taxRate);
    }

    // ============ GETTERS ============

    public int getCurrentYear() {
        return currentYear;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double[][] getRatings() {
        return ratings;
    }
}
