package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Cafeteria {
    private Person customer;
    private HashMap<Person, ArrayList<String>> cafeteriaRecords;
    private HashMap<String, Double> cafeteriaStock;
    private int cafeteriaRevenue = 0;

    public Cafeteria(Person person){
        this.customer = person;
        this.cafeteriaRecords = new HashMap<Person,ArrayList<String>>();
        this.cafeteriaStock = new HashMap<String, Double>();
        this.cafeteriaStock.put("Breakfast",4.00);
        this.cafeteriaStock.put("Lunch",6.00);
        this.cafeteriaStock.put("Snack",3.00);
        this.cafeteriaStock.put("Drink",2.00);
    }

    public String buyItem(String itemToBuy){
        if(this.cafeteriaStock.containsKey(itemToBuy)){
            double itemPrice = cafeteriaStock.get(itemToBuy);
            if(customer instanceof Teacher){
                Teacher tmpTeacher = (Teacher) this.customer;
                if(tmpTeacher.getLessonsTaughtCount() >= 5){
                    itemPrice = itemPrice - (itemPrice * 0.25);
                }
            }
            if(customer.buyFromCafeteria(itemPrice)){
                return "Transaction successful";
            }
            return "Insufficient token balance";
        }
        return "Item not found in stock";

    }



}
