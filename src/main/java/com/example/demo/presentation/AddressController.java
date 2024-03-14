package com.example.demo.presentation;

import com.example.demo.application.AddressService;
import com.example.demo.presentation.response.AddressesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Address 関連のリクエストを処理するコントローラークラスです.
 */
@RestController
@RequiredArgsConstructor
public class AddressController {

  private final AddressService addressService;

  /**
   * すべての Address を取得するエンドポイントです.
   *
   * @return AddressesResponseオブジェクト
   */
  @GetMapping("v1/addresses")
  @ResponseStatus(HttpStatus.OK)
  public AddressesResponse get() {
    return AddressesResponse.of(addressService.retrieveAll());
  }
}
