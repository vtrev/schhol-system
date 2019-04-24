package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;



class CafeteriaTest {

    @Test
    void buyBreakfast() {
        Person vusiPersonMock = spy(new Person());
        //mock customer Vusi to have 20 tokens
        doReturn(20.00).when(vusiPersonMock).getTokenCount();
        Cafeteria caf =  new Cafeteria(vusiPersonMock);
        caf.buyItem("Drink");
        assertEquals(caf.buyItem("Breakfast"),"Transaction successful");
    }

    @Test
    void buyLunch() {
    }
}