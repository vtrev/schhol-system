package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessonNoteTest {

    @Test
    void shouldGetSubjectTheNotesAreFor() {
        LessonNote accountingNotes = new LessonNote(Subject.ACCOUNTING);
        assertEquals(accountingNotes.getSubject(),Subject.ACCOUNTING);
    }

    @Test
    void shouldGetNotes() {
        LessonNote computerScienceNotes = new LessonNote(Subject.COMPUTER_SCIENCE);
        assertEquals(computerScienceNotes.getNotes(),"COMPUTER_SCIENCE_NOTES");
    }
}