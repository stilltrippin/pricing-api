package org.example.demotesttaskropa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoTestTaskRopaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    @DisplayName("Test 1: 14.06.2020 10:00:00 - Price should be 35.50 EUR")
    void test1() throws Exception {
        performRequest(35455L, 1L, "2020-06-14T10:00:00")
                .andExpect(jsonPath("$.price").value("35.50"))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 2: 14.06.2020 16:00:00 - Price should be 25.45 EUR")
    void test2() throws Exception {
        performRequest(35455L, 1L, "2020-06-14T16:00:00")
                .andExpect(jsonPath("$.price").value("25.45"))
                .andExpect(jsonPath("$.priceList").value(2));
    }

    @Test
    @DisplayName("Test 3: 14.06.2020 21:00:00 - Price should be 35.50 EUR")
    void test3() throws Exception {
        performRequest(35455L, 1L, "2020-06-14T21:00:00")
                .andExpect(jsonPath("$.price").value("35.50"))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    @DisplayName("Test 4: 15.06.2020 10:00:00 - Price should be 30.50 EUR")
    void test4() throws Exception {
        performRequest(35455L, 1L, "2020-06-15T10:00:00")
                .andExpect(jsonPath("$.price").value("30.50"))
                .andExpect(jsonPath("$.priceList").value(3));
    }

    @Test
    @DisplayName("Test 5: 16.06.2020 21:00:00 - Price should be 38.95 EUR")
    void test5() throws Exception {
        performRequest(35455L, 1L, "2020-06-16T21:00:00")
                .andExpect(jsonPath("$.price").value("38.95"))
                .andExpect(jsonPath("$.priceList").value(4));
    }

    @Test
    @DisplayName("Returns 404 when no price exists for given parameters")
    void testNotFound() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "99999")
                        .param("brandId", "99999")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    @DisplayName("Returns 400 when date parameter is missing")
    void testMissingDate() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("Returns 400 when date format is invalid")
    void testInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "not-a-date")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("Returns 400 when productId is missing")
    void testMissingProductId() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("Returns 400 when productId is not a number")
    void testInvalidProductIdType() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "abc")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    private ResultActions performRequest (Long productId, Long brandId, String date) throws Exception{
        return mockMvc.perform(get("/prices")
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON));
    }

}
