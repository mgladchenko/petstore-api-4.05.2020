package endpoints;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
    public final static String GET_PET_BY_STATUS = "/pet/findByStatus";

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json");
    }

    @Step
    public void getPet(Long petId) {
        given()
                .get(GET_PET, petId)
                .then()
                .body("id", is(petId))
                .statusCode(200);
    }

    @Step
    public void getPetByStatus(String status) {
        given()
                .param("status", status)
                .get(GET_PET_BY_STATUS)
                .then()
                .body("[0].status", is(status)) //ToDo: verify each element in array
                .statusCode(200);
    }

    @Step
    public Long createPet(Pet pet) {
        ValidatableResponse response = given()
                .body(pet)
                .post(CREATE_PET)
                .then()
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
                .body("name", is(pet.getName()))
                .statusCode(200);
    }

    @Step
    public void deletePet(Long petId) {
        given()
                .delete(DELETE_PET, petId)
                .then()
                //.body() //ToDo: unhardcode
                .statusCode(200);
    }

}
