package xyz.vusibaloyi.schoolsystem;
import java.util.HashMap;

public class CafeteriaStock {

     private static HashMap<String, Double> cafeteriaStock = new HashMap<String, Double>(){{
         put("Breakfast",4.00);
         put("Lunch",6.00);
         put("Snack",3.00);
         put("Drink",2.00);
     }};


     //getter
    public  static HashMap<String, Double> getCafeteriaStock() {

         return cafeteriaStock;
    }
}
