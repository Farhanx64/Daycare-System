
## Project Overview
A comprehensive Object-Oriented Java application for managing daycare operations including staff, children, billing, and performance tracking.

## Final Project Structure

### Java Classes (8 files)
```
DayCareApp.java          - Main application entry point & orchestrator
DaycareSystem.java       - Core business logic & data management
InputValidator.java      - Input validation & error handling
InputManager.java        - Input workflow management
BillingReport.java       - Billing calculations & reporting
StaffReportData.java     - Staff reporting & analytics
Child.java               - Child domain model
StaffMember.java         - Staff domain model
```

### Documentation (3 files)
```
OOP_ARCHITECTURE.md      - Complete OOP architecture documentation
TEST_REPORT.md           - Comprehensive test results
README.md                - Project overview
```

### Testing (1 file)
```
test_input.txt           - Sample test data
```

---

## Key Features Implemented

### **Core Functionality**
- Staff member management and tracking
- Child enrollment and monitoring
- Billing system with discounts and fees
- Parent rating system for staff evaluation
- Performance analytics and reporting

### **Advanced Features**
- Average rating calculation and sorting
- Promotion eligibility tracking (5.0 rating)
- Overtime pay calculation (1.5x after 40 hours)
- Age-based billing rates
- Automatic discount application
- Early/late fee calculation
- Tax withholding calculations

### **OOP Principles**
1. **Encapsulation** - Data hiding with proper getters/setters
2. **Abstraction** - Complex logic behind simple interfaces
3. **Single Responsibility** - Each class has one clear purpose
4. **Dependency Injection** - Loose coupling of components
5. **Error Handling** - Comprehensive input validation

### **Input Validation**
- Type validation (integers, doubles, booleans, strings)
- Range validation (years, ratings, shifts, days)
- Empty string checking
- Overflow/underflow protection
- Retry mechanism for invalid inputs

### **Error Handling**
- InputMismatchException handling
- IllegalArgumentException handling
- Null pointer checks
- Boundary validation
- User-friendly error messages

---

## Application Flow
```
1. Create InputValidator (dependency for InputManager)
2. Get counts using validator
3. Create DaycareSystem with capacity
4. Create InputManager with validator
5. Call executeHappyPath() which:
   - inputManager.inputStaffData()
   - inputManager.inputChildrenData()
   - inputManager.inputParentRatings()
   - system.computeAverageRatings()
   - system.sortStaffByRating()
   - system.getStaffReportData()
   - system.generateBillingReport()
   - staffReport.displayAll()
   - billingReport.display()
```

All objects collaborate without violating encapsulation!


---

## How to Run the Application

### Prerequisites
```bash
Java JDK 8 or higher installed
```

### Compilation
```bash
cd c:\Users\"location"
javac *.java
```

### Execution
```bash
java DayCareApp
```

### With Test Data
```bash
Get-Content test_input.txt | java DayCareApp
```

---

## Usage Instructions

### When Running the Application

1. **Enter System Dimensions:**
   - Number of parents providing ratings (max 100)
   - Number of staff members (max 100)
   - Number of children (max 100)

2. **Enter Staff Information** (for each staff member):
   - Name
   - Year of Birth (1900-2024)
   - Gender
   - Job Title
   - Weekly Hours (max 168)
   - Wage Rate (positive number)
   - Hiring Year (1900-2024, not future)

3. **Enter Children Information** (for each child):
   - Name
   - Year of Birth (1900-2024)
   - Gender
   - Parent Name
   - Parent Phone
   - Parent Language
   - Allergies (yes/no or true/false)
   - Days per Week (1-7)
   - Shift (1=AM, 2=PM, 3=Full)
   - Drop Off Time
   - Pick Up Time

4. **Enter Parent Ratings** (for each parent):
   - Rating for each staff member (1-5)

### Output Generated
- Staff sorted by average rating
- Promotion-eligible staff (5.0 rating)
- Staff experience and pay details
- Infant Teachers list
- Children billing information
- Age statistics (youngest/oldest)
- Billing statistics (highest/lowest)
- Parent ratings matrix

---

## Project Statistics

- **Total Lines of Code:** ~2,500+
- **Classes:** 8
- **Methods:** 50+
