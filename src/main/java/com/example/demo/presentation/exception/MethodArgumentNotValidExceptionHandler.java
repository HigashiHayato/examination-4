package com.example.demo.presentation.exception;

import com.example.demo.presentation.response.AddressErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ポストリクエストでのバリデーションに違反した場合の例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

  /**
   * MethodArgumentNotValidException が発生した場合に、適切なエラーレスポンスを生成して返します.
   *
   * @param exception 発生した MethodArgumentNotValidException
   * @return エラーレスポンス
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<AddressErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception
  ) {

    AddressErrorResponse response = new AddressErrorResponse(
        "",
        "request validation error is occurred.",
        List.of("文字数超過：" + exception.getMessage())
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}
