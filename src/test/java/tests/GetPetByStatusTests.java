package tests;

import endpoints.PetEndpoint;
import models.Pet;
import models.Status;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static models.Status.AVAILABLE;
import static models.Status.SOLD;

@RunWith(SerenityParameterizedRunner.class)
public class GetPetByStatusTests {

    @Steps
    private PetEndpoint petEndpoint;
    private Long petId;

    private Status status;

    public GetPetByStatusTests(Status status) {
        this.status = status;
    }

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {AVAILABLE},
                {SOLD}
        });
    }

    @Before
    public void before() {
        Pet pet = Pet.builder()
                .id(0)
                .name("pet")
                .status(status)
                .build();
        petId = petEndpoint.createPet(pet);
    }

    @After
    public void after() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void getPetByStatusTest() {
        petEndpoint.getPetByStatus(status);
    }

}
