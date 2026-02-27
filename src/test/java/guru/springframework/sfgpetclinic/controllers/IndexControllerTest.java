package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


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

    @Disabled("Demo of Timeout")
    @Test
    void testTimeout() {
        // Runs in the same thread, so after execution, time is compared if it took more than the given one. So this runs completely and then compares time
        assertTimeout(Duration.ofMillis(100),() -> {
            Thread.sleep(5000);
                    System.out.println("I got here");
        });
    }

    @Disabled("Demo of Timeout")
    @Test
    void testTimeoutPreempt() {
        // Runs in a separate thread and when the given duration is passed, the thread is killed preemptively and hence does not allow the test to run fully if it exceeds the time limit.
        assertTimeoutPreemptively(Duration.ofMillis(100),() -> {
            Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Disabled(value = "Failing assumption can be seen as aborted / skipped")
    @Test
    void testAssumption() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMacOS() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWindowsOS() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testOnJava8() {
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "mayank")
    @Test
    void testIfUserMayank() {
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
    @Test
    public void testIfUserFred() {
    }
}