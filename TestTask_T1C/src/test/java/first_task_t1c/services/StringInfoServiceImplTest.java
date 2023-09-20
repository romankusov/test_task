package first_task_t1c.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import first_task_t1c.dto.StringInfoResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class StringInfoServiceImplTest {

    private MockMvc mock;

    @Autowired
    public StringInfoServiceImplTest(MockMvc mock) {
        this.mock = mock;
    }


    @Test
    void getCharCount() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        String requestStr = "99aa99abbc':";
        LinkedHashMap<Character, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put('9', 4);
        expectedMap.put('a', 3);
        expectedMap.put('b', 2);
        expectedMap.put('c', 1);
        StringInfoResponse expectedResponse = new StringInfoResponse();
        expectedResponse.setStringInfoResponse(expectedMap);

        String jsonRequest = mapper.writeValueAsString(requestStr);
        String jsonExpectedResult = mapper.writeValueAsString(expectedResponse);

        var requestBuilder = post("/getStringInfo")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(jsonRequest);

         MvcResult mvcResult = mock.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(jsonExpectedResult, actual);

    }
}