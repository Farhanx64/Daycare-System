# DayCare System - OOP Architecture

## Overview
The DayCare System now fully complies with Object-Oriented Programming (OOP) principles.

## OOP Principles Implemented

### 1. **Encapsulation**
- Each class encapsulates related data and behavior
- Private fields with public getter/setter methods
- Data hiding: Internal state is protected from external modification

**Examples:**
- `DaycareSystem`: Encapsulates all business data (staff, children, ratings)
- `BillingReport`: Encapsulates billing calculations and display logic
- `StaffReportData`: Encapsulates staff reporting functionality

### 2. **Abstraction**
- Complex logic is hidden behind simple interfaces
- Classes expose only what's necessary through public methods
- Implementation details are hidden from users

**Examples:**
- `DaycareSystem.computeAverageRatings()` - hides loop logic
- `BillingReport` - hides billing metric calculations
- `InputValidator` - abstracts all input validation logic

### 3. **Inheritance**
- Base classes provide common functionality
- `Child` and `StaffMember` provide foundational models
- Code reuse through inheritance hierarchy

**Examples:**
- `Child` and `StaffMember` both inherit common patterns
- Filtering methods in both classes use similar patterns

### 4. **Single Responsibility Principle**
Each class has one clear responsibility:

| Class | Responsibility |
|-------|-----------------|
| `DaycareSystem` | Central data manager & business logic |
| `Child` | Represents a child and child operations |
| `StaffMember` | Represents staff member and staff operations |
| `InputValidator` | Validates all user input |
| `InputManager` | Manages all input workflows |
| `DaycareApp` | Orchestrates the application flow |
| `BillingReport` | Generates and displays billing reports |
| `StaffReportData` | Generates and displays staff reports |

### 5. **Dependency Injection**
- Objects receive dependencies through constructors
- Loosely coupled components
- Easier testing and maintenance

**Examples:**
```java
InputManager(InputValidator validator)        // Injected validator
DaycareSystem(int staffCount, ...)            // Injected parameters
```

## Architecture Pattern

### Model-View-Controller (MVC) Inspired
```
┌─────────────────┐
│  DayCareApp     │ (Controller - Orchestrator)
│  (main method)  │
└────────┬────────┘
         │
    ┌────┴────────────────────┐
    │                         │
┌───┴──────────┐      ┌──────┴──────┐
│ InputManager │      │DaycareSystem │ (Model)
└───┬──────────┘      │  (Business   │
    │                 │   Logic)     │
┌───┴──────────┐      │              │
│InputValidator│      └──────┬───────┘
└──────────────┘             │
                    ┌────────┴──────────────┐
                    │                       │
            ┌───────┴────────┐    ┌────────┴─────┐
            │BillingReport   │    │StaffReportData│ (View)
            │(Presentation)  │    │(Presentation) │
            └────────────────┘    └───────────────┘
```

## Class Responsibilities

### DayCareApp
- **Responsibility**: Application orchestration
- **Methods**: `main()`, `executeHappyPath()`
- **OOP Focus**: Coordinator pattern, high-level flow control

### DaycareSystem
- **Responsibility**: Central business logic and data management
- **Methods**: `computeAverageRatings()`, `sortStaffByRating()`, queries
- **OOP Focus**: Encapsulation of domain logic

### InputValidator
- **Responsibility**: Input validation
- **Methods**: `getValidCount()`, `getValidYear()`, `getValidRange()`, etc.
- **OOP Focus**: Single Responsibility Principle

### InputManager  
- **Responsibility**: Input workflow orchestration
- **Methods**: `inputStaffData()`, `inputChildrenData()`, `inputParentRatings()`
- **OOP Focus**: Manages user input process flow

### BillingReport
- **Responsibility**: Billing calculations and display
- **Methods**: `calculateBillingMetrics()`, `display()`
- **OOP Focus**: Encapsulation of reporting logic

### StaffReportData
- **Responsibility**: Staff reporting and display
- **Methods**: `displayByRating()`, `displayPayDetails()`, `displayAll()`
- **OOP Focus**: Separation of concerns (data vs. presentation)

### Child & StaffMember
- **Responsibility**: Domain models representing entities
- **Methods**: Getters, setters, calculations, filtering
- **OOP Focus**: Entity encapsulation

## Benefits of This OOP Architecture

✅ **Maintainability**: Each class has single, clear purpose
✅ **Reusability**: Classes can be used independently
✅ **Testability**: Loose coupling makes unit testing easier
✅ **Scalability**: Easy to add new features without affecting existing code
✅ **Readability**: Clear separation of concerns
✅ **Extensibility**: Easy to extend with new functionality

## Example: Happy Path Flow

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
