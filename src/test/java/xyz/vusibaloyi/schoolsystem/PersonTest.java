package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;
import xyz.vusibaloyi.schoolsystem.person.Person;
import xyz.vusibaloyi.schoolsystem.person.Student;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
@Test
void updateTokenCount() {
    
    Person mike = new Student("Mike","Davids","chakaron@makaron.com");
    double currentTokenCount = mike.getTokenCount();
    mike.updateTokenCount(15);
    
    assertEquals(currentTokenCount+15,mike.getTokenCount());
    }
}