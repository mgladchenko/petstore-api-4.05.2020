Simple Serenity REST api project for https://petstore.swagger.io/

**Run All tests:**  
`mvn clean verify`

**View Serenity HTML report:**  
`mvn serenity:aggregate`
/target/site/serenity/index.html

To run specific test class on specific env:  
`mvn clean verify -Denv=stg -Dtest.name=GetPetTests`