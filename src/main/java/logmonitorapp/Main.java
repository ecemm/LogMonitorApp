package logmonitorapp;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		  String filePath = "logs.log";
		  LogAnalyser analyser=new LogAnalyser();
	        List<String> resultList = analyser.parseCSV(filePath);
	        if (resultList!=null && resultList.size()>0) {
	        	for (String result : resultList) {
	    			System.out.println(result);
	    		}
	        }else {
	        	System.out.println("Result list is empty");
	        }
	}

}
