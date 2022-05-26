package com.example.lab1_3;

import com.example.lab1_3.controller.ComplexController;
import com.example.lab1_3.entities.Complex;
import com.example.lab1_3.exception.CalculationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Lab13ApplicationTests {

    @Autowired
    private ComplexController complexController;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void complexNumberCalculation_successfulResult_correctRequest() throws Exception {
        MvcResult mvcResult =
                this.mockMvc
                        .perform(get("/complex?real=1&imaginable=0"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.module").value("1.0"))
                        .andExpect(jsonPath("$.phase").value("1.5707963267948966"))
                        .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void complexNumberCalculation_successfulResult_correctRequest2() throws CalculationException {
        Complex result = complexController.complexCalculation("1", "0");
        Complex expected = new Complex(1.0, 1.5707963267948966);
        assertEquals(expected, result);
    }

    @Test
    public void complexNumberCalculation_badRequest_wrongReal() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/complex?real=10s&imaginable=7")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("Wrong parameter: Real"))
                .andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void complexNumberCalculation_badRequest_wrongImaginable() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/complex?real=1&imaginable=string")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value("Wrong parameter: Imaginable"))
                .andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }
}
