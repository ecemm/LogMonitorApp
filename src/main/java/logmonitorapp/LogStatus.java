package logmonitorapp;

public enum LogStatus {

	START("START"),
	END("END");
	
    private final String description;

    LogStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

}
