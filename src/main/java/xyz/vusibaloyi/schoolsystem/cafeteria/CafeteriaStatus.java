package xyz.vusibaloyi.schoolsystem.cafeteria;

public enum CafeteriaStatus {
    LOW_BALANCE("Insufficient token balance"),
    SUCCESS("Transaction successful"),
    NO_STOCK("Item not found in stock"),
    NO_SALES_RECORD("No items have been purchased yet");
    
    private String message;
    
    private CafeteriaStatus(String message) {
    this.message = message;
    
    }
    
    public String getMessage(){
        return message;
    }
}
