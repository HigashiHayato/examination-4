package com.example.demo.presentation;

import com.example.demo.application.AddressService;
import com.example.demo.presentation.request.PatchAddressRequest;
import com.example.demo.presentation.request.PostAddressRequest;
import com.example.demo.presentation.response.AddressResponse;
import com.example.demo.presentation.response.AddressesResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

  /**
   * 指定された ID の Address を取得するエンドポイントです.
   *
   * @param id 取得する Address の ID
   * @return ResponseEntity オブジェクト
   */
  @GetMapping("v1/addresses/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AddressResponse get(@PathVariable String id) {
    return AddressResponse.convertToAddressResponse(addressService.retrieve(id));
  }

  /**
   * 新しい Address を登録するエンドポイントです.
   *
   * @param address 登録する PostAddressRequest オブジェクト
   * @param request HttpServletRequest
   * @return ResponseEntity オブジェクト
   */
  @PostMapping("v1/addresses")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> post(
      @RequestBody @Validated PostAddressRequest address,
      HttpServletRequest request
  ) {
    String nextId = addressService.register(address.convertToDto());

    URI uri = UriComponentsBuilder
        .fromUriString(request.getRequestURL().toString())
        .pathSegment(nextId)
        .build()
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  /**
   * 指定された ID の Address を部分的に更新するエンドポイントです.
   *
   * @param address 更新する Address オブジェクト
   * @param id      更新する Address の ID
   */
  @PatchMapping("v1/addresses/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void patch(@RequestBody PatchAddressRequest address, @PathVariable String id) {
    addressService.update(address.convertToDto(), id);
  }
}
