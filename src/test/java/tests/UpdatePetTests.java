package tests;

import endpoints.PetEndpoint;
import lombok.extern.slf4j.Slf4j;
import models.Category;
import models.Pet;
import models.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static config.ConfigProperties.USER_EMAIL;
import static config.ConfigProperties.USER_PASSWORD;
import static models.Status.*;

@Slf4j
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
        log.info(String.format("Created Pet with id = %s", petId));
    }

    @After
    public void after() {
        petEndPoints.deletePet(petId);
    }

    @Test
    public void updatePet() {
        System.out.println(USER_EMAIL);
        System.out.println(USER_PASSWORD);

        Pet pet = Pet.builder()
                .id(petId)
                .name("pet1")
                .status(SOLD)
                .build();
        petEndPoints.updatePet(pet);
    }

}