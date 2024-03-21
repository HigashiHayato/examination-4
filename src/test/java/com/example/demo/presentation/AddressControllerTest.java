package com.example.demo.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.AddressService;
import com.example.demo.domain.Address;
import com.example.demo.presentation.request.PostAddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  private final Address ADDRESS =
      new Address(1, "1000000", "東京都", "新宿区", "中落合");

  private final PostAddressRequest POST_ADDRESS_REQUEST =
      new PostAddressRequest("1000000", "東京都", "豊島区", "長崎");

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
        ADDRESS
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

  @Test
  void ゲットリクエストで指定したデータをjsonにして返す() throws Exception {
    // setup
    when(addressService.retrieve("1")).thenReturn(ADDRESS);

    // execute & assert
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/addresses/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("id").value("1"))
        .andExpect(jsonPath("zip_code").value("1000000"))
        .andExpect(jsonPath("prefecture").value("東京都"))
        .andExpect(jsonPath("city").value("新宿区"))
        .andExpect(jsonPath("street_address").value("中落合"));
  }

  @Test
  void POSTリクエストにより正常なデータを送った場合() throws Exception {
    //setup
    when(addressService.register(any()))
        .thenReturn(89);

    // execute & assert
    mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(POST_ADDRESS_REQUEST))
        )
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "http://localhost/v1/addresses/89"));
  }
}
