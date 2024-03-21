package com.example.demo.presentation.request;

import com.example.demo.application.dto.RequestAddressDto;

/**
 * PATCH 処理における住所を表すレコードクラスです.
 *
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record PatchAddressRequest(
    String zipCode,
    String prefecture,
    String city,
    String streetAddress
) {

  /**
   * RequestAddressDto へ変換します.
   *
   * @return RequestBookDto
   */
  public RequestAddressDto convertToDto() {
    return new RequestAddressDto(zipCode, prefecture, city, streetAddress);
  }
}
