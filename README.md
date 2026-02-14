# Digital Government Service Management System

A Java console application that allows citizens to register, apply for government services, track applications, and generate revenue reports.

## Project Structure

```
src/
├── Main.java                    - Entry point with menu-driven interface
├── Citizen.java                 - Citizen model with 16-digit ID validation
├── CitizenCollection.java       - HashMap-based citizen storage
├── Menu.java                    - Static utility for displaying menu options
├── GovermrntService.java        - Abstract base class for government services
├── DrivingLicenseService.java   - Driving licence service (10,000 Rwf)
├── CriminalRecordService.java   - Criminal record service (1,500 Rwf)
├── DivorceService.java          - Divorce certificate service (50,000 Rwf)
├── PassportRequest.java         - Passport request service (30,000 Rwf)
├── ServiseApplication.java      - Application model with UUID
├── ApplicationManager.java      - Application management and file persistence
└── Exceptions/
    ├── ApplicationNotFound.java - Custom exception for missing applications
    └── InvalidStatus.java       - Custom exception for invalid status changes
```

## Features

1. **Citizen Registration** - Dynamic user registration with 16-digit national ID validation
2. **Service Application** - Apply for driving licence, criminal record, divorce certificate, or passport
3. **Fee Payment Validation** - Full payment auto-approves, partial payment stays pending
4. **View Profile** - Look up citizen information by national ID
5. **Search Application** - Find application by UUID
6. **View All Applications** - Display all submitted applications
7. **Revenue Reports** - Generate revenue breakdown by service type

## How to Run

```bash
javac -d bin src/*.java src/Exceptions/*.java
java -cp bin Main
```
