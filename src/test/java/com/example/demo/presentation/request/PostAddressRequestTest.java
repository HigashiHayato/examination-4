package com.example.demo.presentation.request;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.presentation.exception.NullPostRequestException;
import org.junit.jupiter.api.Test;

class PostAddressRequestTest {

  @Test
  void titleがnullの場合() {
    assertThrows(NullPostRequestException.class, () -> {
      new PostAddressRequest(null, "prefecture", "city", "100");
    });
  }

  @Test
  void authorがnullの場合() {
    assertThrows(NullPostRequestException.class, () -> {
      new PostAddressRequest("title", null, "city", "100");
    });
  }

  @Test
  void publisherがnullの場合() {
    assertThrows(NullPostRequestException.class, () -> {
      new PostAddressRequest("title", "prefecture", null, "100");
    });
  }

  @Test
  void priceがnullの場合() {
    assertThrows(NullPostRequestException.class, () -> {
      new PostAddressRequest("title", "prefecture", "city", null);
    });
  }


}
