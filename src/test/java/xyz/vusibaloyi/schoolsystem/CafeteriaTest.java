package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;
import xyz.vusibaloyi.schoolsystem.cafeteria.Cafeteria;
import xyz.vusibaloyi.schoolsystem.cafeteria.CafeteriaStatus;
import xyz.vusibaloyi.schoolsystem.cafeteria.CafeteriaStock;
import xyz.vusibaloyi.schoolsystem.person.Person;
import xyz.vusibaloyi.schoolsystem.person.Teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import static org.mockito.Mockito.*;



class CafeteriaTest {

    @Test
    void shouldAllowPersonsToBuyItemsFromCafeteriaIfTokensAreSufficient() {
        Person vusiPersonMock = spy(new Person());
        //mock customer Vusi to have 20 tokens
        doReturn(20.00).when(vusiPersonMock).getTokenCount();

        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);

        assertEquals(caf.buyItem(vusiPersonMock,"Breakfast"), CafeteriaStatus.SUCCESS.getMessage());
        assertEquals(caf.buyItem(vusiPersonMock,"Lunch"),CafeteriaStatus.SUCCESS.getMessage());
        assertEquals(caf.buyItem(vusiPersonMock,"Snack"),CafeteriaStatus.SUCCESS.getMessage());
        assertEquals(caf.buyItem(vusiPersonMock,"Drink"),CafeteriaStatus.SUCCESS.getMessage());
    }

    @Test
    void shouldNotAllowPersonsBuyItemsFromCafeteriaIfTokensAreSufficient() {
        Person joePersonMock = spy(new Person());
        //mock customer Joe to have no tokens
        doReturn(0.00).when(joePersonMock).getTokenCount();

        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);

        //assertions
        assertEquals(caf.buyItem(joePersonMock,"Breakfast"),CafeteriaStatus.LOW_BALANCE.getMessage());
        assertEquals(caf.buyItem(joePersonMock,"Lunch"),CafeteriaStatus.LOW_BALANCE.getMessage());
        assertEquals(caf.buyItem(joePersonMock,"Snack"),CafeteriaStatus.LOW_BALANCE.getMessage());
        assertEquals(caf.buyItem(joePersonMock,"Drink"),CafeteriaStatus.LOW_BALANCE.getMessage());
    }

    @Test
    void shouldReturnMessageIfItemNotFoundInStock(){
        Person mikePersonMock = spy(new Person());
        //mock customer Mike to have 200 tokens
        doReturn(200.00).when(mikePersonMock).getTokenCount();
        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);

        //assertion
        assertEquals(caf.buyItem(mikePersonMock,"Some product that is not in the stock"),CafeteriaStatus.NO_STOCK.getMessage());
    }

    @Test
    void shouldGive25PercentOffToTeacherWith5Lessons(){
        Teacher billTeacherMock = spy(new Teacher("Bill","Tucker","Bill@maiil.ru",new ArrayList<Subject>()));
        doReturn(7).when(billTeacherMock).getLessonsTaughtCount();
        doReturn(4.5).when(billTeacherMock).getTokenCount();

        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);
        // Lunch costs 6 tokens but bill should be able to buy it using 4.5 since he has taught more than 5 lessons (7)
        assertEquals(caf.buyItem(billTeacherMock,"Lunch"),CafeteriaStatus.SUCCESS.getMessage());
    }

    @Test
    void getCafeteriaRevenue() {
        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);
        Person vusiPersonMock = spy(new Person());
        //mock customer Vusi to have 100 tokens
        doReturn(100.00).when(vusiPersonMock).getTokenCount();

        //buy items = 11 tokens
        caf.buyItem(vusiPersonMock,"Lunch");
        caf.buyItem(vusiPersonMock,"Drink");
        caf.buyItem(vusiPersonMock,"Snack");

        //assertion
        assertEquals(caf.getCafeteriaRevenue(),11.00);

    }

    @Test
    void shouldReturnCafeteriaRecords() {
        HashMap<String,Double> cafStock =  CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);
        Person jimmyPersonMock = spy(new Person());
        Person sammyPersonMock = spy(new Person());
        Person joeyPersonMock = spy(new Person());
        //mock customer Vusi to have 100 tokens
        doReturn(40.00).when(jimmyPersonMock).getTokenCount();
        doReturn("Jimmy").when(jimmyPersonMock).getFirstName();
        doReturn(34.00).when(sammyPersonMock).getTokenCount();
        doReturn("Sammy").when(sammyPersonMock).getFirstName();
        doReturn(16.00).when(joeyPersonMock).getTokenCount();
        doReturn("Joey").when(joeyPersonMock).getFirstName();

        //customers buy some items
        caf.buyItem(jimmyPersonMock,"Lunch");
        caf.buyItem(jimmyPersonMock,"Drink");
        caf.buyItem(sammyPersonMock,"Snack");
        caf.buyItem(joeyPersonMock,"Drink");
        caf.buyItem(sammyPersonMock,"Lunch");

        String expectedResult = "\nCustomer : Sammy\n" +
                "Item(s)  :[Snack, Lunch]\n" +
                "===========================================\n" +
                "Customer : Joey\n" +
                "Item(s)  :[Drink]\n" +
                "===========================================\n" +
                "Customer : Jimmy\n" +
                "Item(s)  :[Lunch, Drink]\n" +
                "===========================================";
        //assertion
        assertEquals(caf.getCafeteriaRecords(),expectedResult);
    }

    @Test
    void shouldReturnMessageIfNoPurchasesHaveBeenMade() {
        HashMap<String, Double> cafStock = CafeteriaStock.getCafeteriaStock();
        Cafeteria caf = new Cafeteria(cafStock);

        //assertion without buying anything
        assertEquals(caf.getCafeteriaRecords(),CafeteriaStatus.NO_SALES_RECORD.getMessage());
    }

}