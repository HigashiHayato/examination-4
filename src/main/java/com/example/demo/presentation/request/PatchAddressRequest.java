package com.example.demo.presentation.request;

import com.example.demo.application.dto.RequestAddressDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * PATCH 処理における住所を表すレコードクラスです.
 *
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record PatchAddressRequest(
    @JsonProperty("zip_code")
    @Length(max = 7, message = "文字数超過 zipCode は 7 文字以下")
    String zipCode,
    @JsonProperty("prefecture")
    @Length(max = 20, message = "文字数超過 prefecture は 20 文字以下")
    String prefecture,
    @JsonProperty("city")
    @Length(max = 20, message = "文字数超過 city は 20 文字以下")
    String city,
    @JsonProperty("street_address")
    @Length(max = 100, message = "文字数超過 streetAddress は 20 文字以下")
    String streetAddress
) {

  /**
   * RequestAddressDto へ変換します.
   *
   * @return RequestAddressDto
   */
  public RequestAddressDto convertToDto() {
    return new RequestAddressDto(zipCode, prefecture, city, streetAddress);
  }
}
