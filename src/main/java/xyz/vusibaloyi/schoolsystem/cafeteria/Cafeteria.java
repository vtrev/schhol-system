package xyz.vusibaloyi.schoolsystem.cafeteria;

import xyz.vusibaloyi.schoolsystem.person.Person;
import xyz.vusibaloyi.schoolsystem.person.Teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class Cafeteria {

    private HashMap<String, ArrayList<String>> cafeteriaRecords;
    private HashMap<String, Double> cafeteriaStock;

    public Cafeteria(HashMap<String, Double> cafeteriaStockIn){
        this.cafeteriaStock = cafeteriaStockIn;
        this.cafeteriaRecords = new HashMap<String,ArrayList<String>>();
    }

    public String buyItem(Person customer, String itemToBuy){
        if(cafeteriaStock.containsKey(itemToBuy)){
            double itemPrice = cafeteriaStock.get(itemToBuy);
            if(customer instanceof Teacher){
                Teacher tmpTeacher = (Teacher) customer;
                if(tmpTeacher.getLessonsTaughtCount() >= 5){
                    itemPrice = itemPrice - (itemPrice * 0.25);
                }
            }
            if(customer.hasSufficientTokens(itemPrice)){
                customer.updateTokenCount(-itemPrice);
                updateCafeteriaRecords(customer,itemToBuy);
                return CafeteriaStatus.SUCCESS.getMessage();
            }
            return CafeteriaStatus.LOW_BALANCE.getMessage();
        }
        return CafeteriaStatus.NO_STOCK.getMessage();

    }

    private void updateCafeteriaRecords(Person customer,String item){
        ArrayList<String> tmpItems;
        if(!cafeteriaRecords.containsKey(customer.getFirstName())){
            tmpItems = new ArrayList<String>();
            tmpItems.add(item);
            cafeteriaRecords.put(customer.getFirstName(),tmpItems);
        }else {
            tmpItems = cafeteriaRecords.get(customer.getFirstName());
            tmpItems.add(item);
            cafeteriaRecords.put(customer.getFirstName(),tmpItems);
        }
    }

    public double getCafeteriaRevenue() {
        ArrayList<ArrayList> itemsBoughtLists = new ArrayList<ArrayList>(cafeteriaRecords.values());
        double revenue = 0.00;

        for (ArrayList<String> customerList : itemsBoughtLists) {
            for (String item : customerList) {
                revenue += cafeteriaStock.get(item);
            }
        }
       return revenue;
    }

    public String getCafeteriaRecords() {
        String record = "";
        if(cafeteriaRecords.keySet().size() > 0){
            for (String customerName : cafeteriaRecords.keySet()){
            record = record+ "\nCustomer : "+ customerName+"\nItem(s)  :" +cafeteriaRecords.get(customerName)+"\n===========================================";
            }
        }
        if(record.isEmpty()){
            record = CafeteriaStatus.NO_SALES_RECORD.getMessage();
        }
        return record;
    }

}


