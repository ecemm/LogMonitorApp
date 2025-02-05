package logmonitorapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

  
public class LogAnalyserTest {

	@Test
	public void testLogMonitoring_isEmpty() {
		String logFile="isEmpty.log";
		LogAnalyser analyser = new LogAnalyser();
		List<String> resultList = analyser.parseCSV(logFile);
		assertTrue(resultList.isEmpty(),"Results list should be empty");
	}
	  
	@Test
	public void testLogMonitoring_warningTask() {
		String logFile="warningTask.log";
		  
		LogAnalyser analyser = new LogAnalyser();
		List<String> resultList = analyser.parseCSV(logFile);
		assertFalse(resultList.isEmpty(),"Result list should not be empty");
		assertTrue(resultList.get(0).contains("WARNING"),"Results list should only contain warning message");
	}
	
	@Test
	public void testLogMonitoring_errorTask() {
		String logFile="errorTask.log";
		
		LogAnalyser analyser = new LogAnalyser();
		List<String> resultList = analyser.parseCSV(logFile);
		assertFalse(resultList.isEmpty(),"Result list should not be empty");

		assertTrue(resultList.get(0).contains("ERROR"),"Results list should only contain error message");
	}
	
	@Test
	public void testLogMonitoring_withoutStartTask() {
		String logFile="withoutStartTask.log";
		
		LogAnalyser analyser = new LogAnalyser();
		List<String> resultList = analyser.parseCSV(logFile);
		assertTrue(resultList.isEmpty(),"Result list should be empty");

	}
	
	@Test
	public void testLogMonitoring_withoutEndTask() {
		String logFile="withoutEndTask.log";
		
		LogAnalyser analyser = new LogAnalyser();
		List<String> resultList = analyser.parseCSV(logFile);
		assertFalse(resultList.isEmpty(),"Result list should not be empty");

		assertTrue(resultList.get(0).contains("ERROR"),"Results list should only contain error message");
		assertFalse(resultList.get(0).contains("WARNING"),"Results list should not contain warning message");
	}
}
