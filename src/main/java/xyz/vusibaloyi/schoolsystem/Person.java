package xyz.vusibaloyi.schoolsystem;

public class Person {
    public  String firstName;
    public  String lastName;
    public  String emailAddress;
    public double tokenCount = 0;


    //getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    public double getTokenCount() {
        return this.tokenCount;
    }

    public void updateTokenCount(double numberOfTokens){
        this.tokenCount += numberOfTokens;
    }

    public boolean buyFromCafeteria(Double productPrice){
        System.out.println("Token count in the person class : "+this.getTokenCount());
        if(productPrice <= this.getTokenCount()){
            this.updateTokenCount(-productPrice);
            return true;
        }
        return true;
    }
}
