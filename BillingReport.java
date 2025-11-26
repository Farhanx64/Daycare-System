/**
 * BillingReport class encapsulates billing data and calculations
 * Follows Single Responsibility Principle
 */
public class BillingReport {
    private Child[] children;
    private int currentYear;
    private double highestBill;
    private double lowestBill;
    private int youngestAge;
    private int oldestAge;

    public BillingReport(Child[] children, int currentYear) {
        this.children = children;
        this.currentYear = currentYear;
        this.highestBill = -1;
        this.lowestBill = Double.MAX_VALUE;
        this.youngestAge = Integer.MAX_VALUE;
        this.oldestAge = -1;
        calculateBillingMetrics();
    }

    private void calculateBillingMetrics() {
        for (Child c : children) {
            if (c == null) continue;
            int age = c.calculateAge(currentYear);
            double bill0 = c.calculateWeeklyBill(age);
            double discPct = c.calculateDiscountPercent(bill0);
            double afterDisc = bill0 * (1 - discPct);
            double timePct = c.calculateTimeFeePercent();
            double total = afterDisc * (1 + timePct);

            if (age < youngestAge) youngestAge = age;
            if (age > oldestAge) oldestAge = age;
            if (total > highestBill) highestBill = total;
            if (total < lowestBill) lowestBill = total;
        }
    }

    // ============ GETTERS ============

    public Child[] getChildren() {
        return children;
    }

    public double getHighestBill() {
        return highestBill;
    }

    public double getLowestBill() {
        return lowestBill;
    }

    public int getYoungestAge() {
        return youngestAge;
    }

    public int getOldestAge() {
        return oldestAge;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    // ============ DISPLAY METHOD ============

    public void display() {
        System.out.println("\n--- Children Status & Billing ---");
        for (Child c : children) {
            if (c == null) continue;
            int age = c.calculateAge(currentYear);
            double bill0 = c.calculateWeeklyBill(age);
            double discPct = c.calculateDiscountPercent(bill0);
            double afterDisc = bill0 * (1 - discPct);
            double timePct = c.calculateTimeFeePercent();
            double total = afterDisc * (1 + timePct);
            String ageSt = c.getAgeStatus(age);
            String discLab = c.getDiscountLabel(discPct);
            String timeMsg = c.getTimeFeeMessage(timePct);

            System.out.printf("%s (%s, Age %d): $%.2f/week, %s off, %s, Total $%.2f%n",
                              c.getName(), ageSt, age, bill0, discLab, timeMsg, total);
        }
        System.out.printf("Youngest: %d, Oldest: %d%n", youngestAge, oldestAge);
        System.out.printf("Highest Bill: $%.2f, Lowest Bill: $%.2f%n", highestBill, lowestBill);
    }
}
