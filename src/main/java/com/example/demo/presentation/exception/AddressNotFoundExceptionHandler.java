package com.example.demo.presentation.exception;

import com.example.demo.application.exception.AddressNotFoundException;
import com.example.demo.presentation.response.AddressErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 住所 ID が見つからない場合の例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class AddressNotFoundExceptionHandler {

  /**
   * AddressNotFoundException が発生した場合に、適切なエラーレスポンスを生成して返します.
   *
   * @param exception 発生した AddressNotFoundException
   * @return エラーレスポンス
   */
  @ExceptionHandler(AddressNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<AddressErrorResponse> handleAddressNotFoundException(
      AddressNotFoundException exception
  ) {

    AddressErrorResponse response = new AddressErrorResponse(
        "0003",
        "specified address [id = " + exception.getMessage() + "] is not found.",
        List.of()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
