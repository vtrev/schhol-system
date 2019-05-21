package xyz.vusibaloyi.schoolsystem.person;

public class Person {
    public String firstName;
    public  String lastName;
    public  String emailAddress;
    public double tokenCount = 0;
    
    public void updateTokenCount(double numberOfTokens){
        this.tokenCount += numberOfTokens;
    }
    public boolean hasSufficientTokens(Double productPrice){
        return productPrice <= getTokenCount();
    }
    
    //getters
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }
    public double getTokenCount() {
        return tokenCount;
    }
    
    @Override
    public String toString() {
        return "\t\tStudent info\n"+"==================================\n"+"First Name\t: "+getFirstName()+"\n"+
                "Last Name\t: "+getLastName()+"\n"+
                "email\t\t: "+getEmailAddress()+"\n";
    }
}
