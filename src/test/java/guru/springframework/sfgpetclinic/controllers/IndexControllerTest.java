package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @DisplayName("Test proper view name is returned for index page")
    @Test
    void index() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Message shown when test fails");
        assertEquals("index", indexController.index(), () -> "Overloaded assertEquals method that takes in a lambda to generate message. But the message is only generated when it is needed, i.e. at failure.");
    }

    @Test
    @DisplayName("Test Exception")
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class,
                () -> indexController.oopsHandler());
    }
}