package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;



class CafeteriaTest {

    @Test
    void shouldAllowPersonsToBuyItemsFromCafeteriaIfTokensAreSufficient() {
        Person vusiPersonMock = spy(new Person());
        //mock customer Vusi to have 20 tokens
        doReturn(20.00).when(vusiPersonMock).getTokenCount();
        Cafeteria caf =  new Cafeteria(vusiPersonMock);
        assertEquals(caf.buyItem("Breakfast"),"Transaction successful");
        assertEquals(caf.buyItem("Lunch"),"Transaction successful");
        assertEquals(caf.buyItem("Snack"),"Transaction successful");
        assertEquals(caf.buyItem("Drink"),"Transaction successful");
    }

    @Test
    void shouldNotAllowPersonsBuyItemsFromCafeteriaIfTokensAreSufficient() {
        Person joePersonMock = spy(new Person());
        //mock customer Joe to have no tokens
        doReturn(0.00).when(joePersonMock).getTokenCount();
        Cafeteria caf =  new Cafeteria(joePersonMock);

        //assertions
        assertEquals(caf.buyItem("Breakfast"),"Insufficient token balance");
        assertEquals(caf.buyItem("Lunch"),"Insufficient token balance");
        assertEquals(caf.buyItem("Snack"),"Insufficient token balance");
        assertEquals(caf.buyItem("Drink"),"Insufficient token balance");
    }

    @Test
    void shouldReturnMessageIfItemNotFoundInStock(){
        Person mikePersonMock = spy(new Person());
        //mock customer Mike to have 200 tokens
        doReturn(200.00).when(mikePersonMock).getTokenCount();
        Cafeteria caf =  new Cafeteria(mikePersonMock);

        //assertions
        assertEquals(caf.buyItem("Some product that is not in the stock"),"Item not found in stock");
    }

    @Test
    void shouldGive25PercentOffToTeacherWith5Lessons(){
        Teacher billTeacherMock = spy(new Teacher("Bill","Tucker","Bill@maiil.ru",new ArrayList<Subject>()));
        doReturn(7).when(billTeacherMock).getLessonsTaughtCount();
        doReturn(4.5).when(billTeacherMock).getTokenCount();
        Cafeteria caf = new Cafeteria(billTeacherMock);
        // Lunch costs 6 tokens but bill should be able to buy it using 4.5 since he has taught more than 5 lessons (7)
        assertEquals(caf.buyItem("Lunch"),"Transaction successful");

    }

    @Test
    void getCafeteriaRevenue() {

        Person vusi = new Person();
        Cafeteria caf =  new Cafeteria(vusi);
        caf.getCafeteriaRevenue();
    }
}