package com.example.demo.presentation.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.example.demo.presentation.response.AddressErrorResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

class MethodArgumentNotValidExceptionHandlerTest {

  @Test
  void MethodArgumentNotValidExceptionが発生した場合() {
    // Setup
    MethodArgumentNotValidExceptionHandler handler = new MethodArgumentNotValidExceptionHandler();
    MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        AddressErrorResponse expectedResponse = new AddressErrorResponse(
        "",
        "request validation error is occurred.",
        List.of("文字数超過：null")
    );

    // Execute
    ResponseEntity<AddressErrorResponse> responseEntity = handler.handleMethodArgumentNotValidException(exception);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
  }
}
