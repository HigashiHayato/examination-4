package com.example.demo.presentation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.AddressService;
import com.example.demo.domain.Address;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AddressControllerTest {

  @Autowired
  MockMvc mockMvc;

  @InjectMocks
  AddressController sut;

  @Mock
  AddressService addressService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
  }

  @Test
  void ゲットリクエストで得た全件データをjsonにして返す() throws Exception {
    // setup
    List<Address> addressList = List.of(
        new Address("1", "1000000", "東京都", "新宿区", "中落合")
    );

    when(addressService.retrieveAll()).thenReturn(addressList);

    // execute & assert
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/addresses"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.addresses[0].id").value(1))
        .andExpect(jsonPath("$.addresses[0].zip_code").value("1000000"))
        .andExpect(jsonPath("$.addresses[0].prefecture").value("東京都"))
        .andExpect(jsonPath("$.addresses[0].city").value("新宿区"))
        .andExpect(jsonPath("$.addresses[0].street_address").value("中落合"));
  }
}
