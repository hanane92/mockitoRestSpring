package hanane.sid.MockitoRestSpring;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
class HelloResourceTest {

    //Mvc provides support for Spring MVC testing. It encapsulates all web application beans and make them available for testing
    private MockMvc mockMvc;
    @InjectMocks
    HelloResource helloResource;

    @BeforeEach
    public void setUp() throws Exception{

        // initialize the mockMvc object
        mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();
    }

    @Test
    public void testHelloWorld() throws  Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world!"));
    }

    @Test
    public void testHelloWorldJson() throws  Exception{

        //test rest controler with json respponse
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/json")

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("greeting")))
                .andExpect(jsonPath("$.value",Matchers.is("hello hanane")))
                .andExpect(jsonPath("$.*",Matchers.hasSize(1)));

    }
}