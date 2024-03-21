package com.example.demo.presentation.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.demo.presentation.response.AddressErrorResponse;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class NullPostRequestExceptionHandlerTest {

  @InjectMocks
  NullPostRequestExceptionHandler sut;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 正しいレスポンスが返される() {
    // setup
    List<String> nullList = List.of("zipCode", "prefecture");
    NullPostRequestException exception = new NullPostRequestException(nullList);

    AddressErrorResponse expected = new AddressErrorResponse(
        "0002",
        "request validation error is occurred.",
        List.of("zipCode must not be blank", "prefecture must not be blank")
    );

    // execute
    ResponseEntity<AddressErrorResponse> actual = sut.handleValidationException(exception);

    // assert
    assertThat(Objects.requireNonNull(actual.getBody()).code()).isEqualTo(expected.code());
    assertThat(actual.getBody().message()).isEqualTo(expected.message());
    assertThat(actual.getBody().details()).isEqualTo(expected.details());
    assertThat(actual.getStatusCode()).isEqualTo(BAD_REQUEST);
  }
}
