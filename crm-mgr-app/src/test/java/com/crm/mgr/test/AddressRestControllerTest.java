package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.dto.AddressDto;
import com.crm.mgr.service.impl.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmMgrApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class AddressRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AddressService addressService;

    @Test
    public void shouldCreateAddress() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        mockMvc.perform(post("/api/v1/address")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is("Warsaw")));
    }

    @Test
    public void shouldDeleteAddress() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);
        mockMvc.perform(delete("/api/v1/address/" + addressDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldModifyAddress() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);
        mockMvc.perform(put("/api/v1/address/" + addressDto.getId().toString())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is("Warsaw")));
    }

    @Test
    public void shouldGetAddressById() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);
        mockMvc.perform(get("/api/v1/address/" + addressDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is("Warsaw")));
    }

    @Test
    public void shouldGetAddresses() throws Exception {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);
        mockMvc.perform(get("/api/v1/address")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city", is("Warsaw")));
    }
}
