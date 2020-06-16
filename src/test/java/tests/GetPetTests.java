package tests;

import endpoints.PetEndpoint;
import models.Pet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPetTests {

    @Steps
    private PetEndpoint petEndpoint;
    private Long petId;

    @Before
    public void before() {
        Pet pet = Pet.builder()
                .id(0)
                .name("pet1")
                .build();
        petId = petEndpoint.createPet(pet);
    }

    @After
    public void after() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void getPetById() {
        petEndpoint.getPet(petId);
    }

}
