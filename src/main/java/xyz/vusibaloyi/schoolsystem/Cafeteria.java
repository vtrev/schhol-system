package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cafeteria {
    private Person customer;
    private HashMap<Person, ArrayList<String>> cafeteriaRecords;
    private HashMap<String, Double> cafeteriaStock;

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
        if(cafeteriaStock.containsKey(itemToBuy)){
            double itemPrice = cafeteriaStock.get(itemToBuy);
            if(customer instanceof Teacher){
                Teacher tmpTeacher = (Teacher) customer;
                if(tmpTeacher.getLessonsTaughtCount() >= 5){
                    itemPrice = itemPrice - (itemPrice * 0.25);
                }
            }
            if(customer.buyFromCafeteria(itemPrice)){
                updateCafeteriaRecords(customer,itemToBuy);
                return "Transaction successful";
            }
            return "Insufficient token balance";
        }
        return "Item not found in stock";

    }

    private void updateCafeteriaRecords(Person customer,String item){
        ArrayList<String> tmpItems;
        if(!cafeteriaRecords.containsKey(customer)){
            tmpItems = new ArrayList<String>();
            tmpItems.add(item);
            cafeteriaRecords.put(customer,tmpItems);
        }else {
            tmpItems = cafeteriaRecords.get(customer);
            tmpItems.add(item);
        }
    }

    public double getCafeteriaRevenue() {
        double revenue = 0.00;
        ArrayList<ArrayList> itemsBoughtLists = new ArrayList<ArrayList>(cafeteriaRecords.values());

        for (ArrayList<String> customerList : itemsBoughtLists) {

            for (String item : customerList) {
                revenue += cafeteriaStock.get(item);
            }
        }
       return revenue;
    }



}


