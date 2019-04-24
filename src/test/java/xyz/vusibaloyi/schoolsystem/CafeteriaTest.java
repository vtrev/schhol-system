package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CafeteriaTest {

    @Test
    void buyBreakfast() {
        Person vusi = new Student("Vusi","Baloyi","vusi@baloyi.uk");
        Cafeteria caf =  new Cafeteria(vusi);
        assertEquals(caf.buyItem("Breakfast"),"let it fail");
    }

    @Test
    void buyLunch() {
    }
}