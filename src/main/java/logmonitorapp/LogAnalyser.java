package logmonitorapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LogAnalyser {
	
	private static final String COMMA = ",";
	private static final int ERROR_THRESHOLD = 600; // 10 minutes in seconds
	private static final int WARNING_THRESHOLD = 300; // 5 minutes in seconds
	
	
	
	
	public List<String> parseCSV(String filePath) {

		List<String> reportList = new ArrayList<>();

        Map<Integer, LocalTime> processMap = new HashMap<Integer, LocalTime>();
        InputStream inputStream = null;
        BufferedReader fileReader = null;

		try {
			inputStream = LogAnalyser.class.getClassLoader().getResourceAsStream(filePath);
			fileReader= new BufferedReader(new InputStreamReader(inputStream));
			String nextLine;
			
			while((nextLine=fileReader.readLine())!=null)	{
				String[] logsArray= nextLine.split(COMMA);//parsed line as array = logDate, logDescription, logStatus, PID
				
				if(logsArray!=null) {
					
					LocalTime logDate= LocalTime.parse(logsArray[0]);
					String logStatus=logsArray[2].trim();
					int logPid=Integer.parseInt(logsArray[3].trim());
					
					if (logStatus.equals(LogStatus.START.getDescription())) {
						
						processMap.put(logPid, logDate);
						
					}else if(logStatus.equals(LogStatus.END.getDescription())) {
						
						LocalTime startTime= processMap.remove(logPid);// get the start time of the log
						if(startTime==null) {
							System.out.println(String.format("Cannot evaluate this process: %d, it doesn't have a start log",logPid));
							continue;
						}

						long logDuration=Duration.between(startTime, logDate).getSeconds();

						String report=formatReport(logDuration,logPid);
						if(report!=null) {
							reportList.add(report);
						}
					}
					
				}
			}
			//if there are still running processes which means that they don't have an "END" log, 
			//we are checking how long they've been running for and adding them to the reportList if they're above the thresholds
			if (!processMap.isEmpty()) {
				for (Map.Entry<Integer, LocalTime> log : processMap.entrySet()) {
					long logDuration=Duration.between(log.getValue(), LocalTime.now()).getSeconds();
					String report=formatReport(logDuration,log.getKey());
					if(report!=null) {
						reportList.add(report);
					}
					
		        }
			}
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (fileReader!=null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		return reportList;
	}
	
	private String formatReport(long logDuration, int logPid) {
		String report = null;
		if (logDuration > ERROR_THRESHOLD) {
			report=String.format("PID: %d took %d seconds. ERROR: Job took longer than 10 minutes", logPid, logDuration);

		}else if (logDuration> WARNING_THRESHOLD) {
			report=String.format("PID: %d took %d seconds. WARNING: Job took longer than 5 minutes and shorter than 10 minutes", logPid, logDuration);
		}
		return report;
	}
}
