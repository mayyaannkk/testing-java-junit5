package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.ServiceTests;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// These nested tests allows us to follow a flow. Here we save a pet type then use nested tests to check if it was saved or not. Similarly for owner as well

@DisplayName("Owner Map Service Test -")
class OwnerMapServiceTest implements ServiceTests {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petService = new PetMapService();
        petTypeService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First Before Each");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        int ownersCount = ownerMapService.findAll().size();
        assertEquals(0, ownersCount);
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType1 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType1);

            System.out.println("Nested Before Each");
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertEquals(2, petTypeCount);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTest {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
                System.out.println("Saved Owners Before Each");
            }

            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");
                Owner savedOwner = ownerMapService.save(owner);
                assertNotNull(savedOwner);
            }

            @DisplayName("Find Saved Owners")
            @Nested
            class FindSavedOwners {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertNotNull(foundOwner);
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {
                    // This will result in not found as the parent class BeforeEach will be called each time and 1L will be save before each method call, so 2L will not be found. It gets refreshed because of the first BeforeEach, and a value is added in the above BeforeEach

                    Owner foundOwner = ownerMapService.findById(2L);
                    assertNull(foundOwner);
                }
            }
        }
    }

    @DisplayName("Verify Still Zero Owners - ")
    @Test
    void verifyStillZeroOwners() {
        // Zero coz its before each is called and nothing is added for this particular class
        int ownersCount = ownerMapService.findAll().size();
        assertEquals(0, ownersCount);
    }
}