package org.example.demotesttaskropa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoTestTaskRopaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

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
    @DisplayName("Test 2: 15.06.2020 10:00:00 - Price should be 30.50 EUR")
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

    private ResultActions performRequest (Long productId, Long brandId, String date) throws Exception{
        return mockMvc.perform(get("/prices")
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON));
    }

}
