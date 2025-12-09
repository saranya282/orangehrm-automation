package compareclub.orangehrms.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
public class EmployeeApiValidator {

    public String getJobTitle(String employeeId, String sessionCookie) {

        Response response =
                RestAssured.given()
                        .baseUri("https://opensource-demo.orangehrmlive.com")
                        .basePath("/web/index.php/api/v2/pim/employees")
                        .cookie("orangehrm", sessionCookie) 
                        .queryParam("limit", 50)
                        .queryParam("offset", 0)
                        .queryParam("model", "detailed")
                        .queryParam("employeeId", employeeId)
                        .queryParam("includeEmployees", "onlyCurrent")
                        .queryParam("sortField", "employee.firstName")
                        .queryParam("sortOrder", "ASC")
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract().response();

        return response.jsonPath().getString("data[0].jobTitle.title");  
        }


    public String getEmploymentStatus(String employeeId, String sessionCookie) {

        Response response =
                RestAssured.given()
                        .baseUri("https://opensource-demo.orangehrmlive.com")
                        .basePath("/web/index.php/api/v2/pim/employees")
                        .cookie("orangehrm", sessionCookie) 
                        .queryParam("limit", 50)
                        .queryParam("offset", 0)
                        .queryParam("model", "detailed")
                        .queryParam("employeeId", employeeId)
                        .queryParam("includeEmployees", "onlyCurrent")
                        .queryParam("sortField", "employee.firstName")
                        .queryParam("sortOrder", "ASC")
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract().response();

        return response.jsonPath().getString("data[0].empStatus.name");  
    }
    public boolean isEmployeeDeletedInApi(String employeeId, String cookie) {

        Response response =
                RestAssured.given()
                        .baseUri("https://opensource-demo.orangehrmlive.com")
                        .basePath("/web/index.php/api/v2/pim/employees")
                        .cookie("orangehrm", cookie)
                        .queryParam("limit", 50)
                        .queryParam("offset", 0)
                        .queryParam("model", "detailed")
                        .queryParam("employeeId", employeeId)
                        .queryParam("includeEmployees", "onlyCurrent")
                        .queryParam("sortField", "employee.firstName")
                        .queryParam("sortOrder", "ASC")
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract().response();

        int totalRecords = response.jsonPath().getInt("meta.total");

        return totalRecords == 0;
    }

}
