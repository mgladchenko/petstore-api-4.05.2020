package endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.CoreMatchers.is;

public class PetEndpoint {

    public final static String CREATE_PET = "/pet";
    public final static String UPDATE_PET = "/pet";
    public final static String DELETE_PET = "/pet/{petId}";
    public final static String GET_PET = "/pet/{petId}";

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .log().all()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json");
    }

    @Step
    public void getPet(Long petId) {
        given()
                .get(GET_PET, petId)
                .then()
                .log().all()
                .body("id", is(petId))
                .statusCode(200);
    }

    @Step
    public Long createPet(Pet pet) {
        ValidatableResponse response = given()
                .body(pet)
                .post(CREATE_PET)
                .then()
                .log().all()
                .body("name" , is(pet.getName()))
                .statusCode(200);
        return response.extract().path("id");
    }

    @Step
    public void updatePet(Pet pet) {
        given()
                .body(pet)
                .put(UPDATE_PET)
                .then()
                .log().all()
                .body("name", is(pet.getName()))
                .statusCode(200);
    }

    @Step
    public void deletePet(Long petId) {
        given()
                .delete(DELETE_PET, petId)
                .then()
                .log().all()
                //.body() //ToDo: unhardcode
                .statusCode(200);
    }

}
