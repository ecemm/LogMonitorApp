# LogMonitorApp
 This repository contains a log analysis tool that processes CSV log files to track job execution times and generate reports with warnings and errors based on duration thresholds.

Features
- Parses CSV log files to extract job details.
- Tracks job start and end times using process IDs (PID).
- Calculates the duration of each job.
- Logs a warning if a job exceeds 5 minutes.
- Logs an error if a job exceeds 10 minutes.

Log Format
- Timestamp (HH:MM:SS)
- Job description
- Log entry status (START or END)
- Process ID (PID)

How to run?

1) Make sure you have the following installed on your system:

- Java 11+ (JDK)
- Apache Maven
- Eclipse IDE

2) Clone the repository
```sh
git clone https://github.com/ecemm/LogMonitorApp.git
```
3) Import the project into Eclipse

Open Eclipse.
Go to File → Import → Maven → Existing Maven Projects.
Select the cloned repository folder and click Finish.

4) Build the project

Open the Terminal in Eclipse 
Run the following command to ensure all dependencies are downloaded:
```sh
mvn clean install
```

5) Run the application

Locate the Main.java file inside the logmonitorapp package.
Right-click on the file and select Run As → Java Application.

6) The application will process the log file and generate a report.
Warnings and errors will be displayed in the console.
