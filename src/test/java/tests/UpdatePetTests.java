package tests;

import endpoints.PetEndpoint;
import models.Category;
import models.Pet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UpdatePetTests {
    private Long petId;

    @Steps
    private PetEndpoint petEndPoints;

    @Before
    public void before() {
        Pet pet = Pet.builder()
                .name("pet1")
                .category(Category.builder()
                        .id(0)
                        .name("test")
                        .build())
                .build();
        petId = petEndPoints.createPet(pet);
    }

    @After
    public void after() {
        petEndPoints.deletePet(petId);
    }

    @Test
    public void updatePet() {
        Pet pet = Pet.builder()
                .id(petId)
                .name("pet1")
                .status("sold")
                .build();
        petEndPoints.updatePet(pet);
    }

}