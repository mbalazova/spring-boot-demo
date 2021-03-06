package testing;

import static io.restassured.RestAssured.preemptive;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured; 
import io.restassured.response.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import sk.tempest.spring.boot.proj.Runner;
import sk.tempest.spring.boot.proj.entity.Book;
        
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Runner.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testing {
 
    private static final String API_ROOT = "http://localhost:8080/books";
 
    @Before
    public void setUp() { 
        RestAssured.authentication = preemptive().basic("michaela", "passwd");
    }
    private Book createRandomBook() {
        Book book = new Book();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(15));
        return book;
    }
 
    private String createBookAsUri(Book book) {
        Response response = RestAssured.given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(book)
          .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
    
    @Test
    public void getAllBooks_andPrintThem() throws UnsupportedEncodingException {
        Response response = RestAssured.get(API_ROOT);
        response.getBody().prettyPrint();
    }
    
    @Test
    public void whenGetAllBooks_thenOK() {
        Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    
    @Test
    public void whenGetBooksByTitle_thenOK() {
        final Book book = createRandomBook();
        createBookAsUri(book);

        System.out.println("getting book by title -- " + book.getTitle() );
        final Response response = RestAssured.get(API_ROOT + "/title/" + book.getTitle());
        response.getBody().prettyPeek();
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }
    
    @Test
    public void whenGetCreatedBookById_thenOK() {
        final Book book = createRandomBook();
        final String location = createBookAsUri(book);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(book.getTitle(), response.jsonPath().get("title"));
    }
    
    @Test
    public void whenGetNotExistBookById_thenNotFound() {
        final Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
//        final Response response = RestAssured.get(API_ROOT + "/" + 1);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
    @Test
    public void whenGetFirstBookById_thenOK() {
        final Response response = RestAssured.get(API_ROOT + "/" + 1);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewBook_thenCreated() {
        final Book book = createRandomBook();

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(book)
            .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidBook_thenError() {
        final Book book = createRandomBook();
        book.setAuthor(null);

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(book)
            .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }
    
    
    @Test
    public void whenUpdateCreatedBook_thenUpdated() {
        final Book book = createRandomBook();
        final String location = createBookAsUri(book);

        book.setId(Long.parseLong(location.split("books/")[1]));
        book.setAuthor("newAuthor");
        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(book)
            .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath().get("author"));
    }

    @Test
    public void whenDeleteCreatedBook_thenOk() {
        final Book book = createRandomBook();
        final String location = createBookAsUri(book);   

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }
    
    @Test
    public void createMyBook(){
        Book book = createRandomBook();
        book.setId(Long.parseLong("7"));
        book.setTitle("Azurovo-modra");
        book.setAuthor("Jozko");
        System.out.println("ID : " + book.getId());
        
        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(book)
            .post(API_ROOT);
        assertEquals(Long.parseLong("7"), book.getId());
        assertEquals("Azurovo-modra", book.getTitle());
        assertEquals("Jozko", book.getAuthor());
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }
}
    

