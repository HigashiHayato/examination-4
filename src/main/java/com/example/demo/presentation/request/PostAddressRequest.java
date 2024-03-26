package com.example.demo.presentation.request;

import static java.util.Objects.isNull;

import com.example.demo.application.dto.RequestAddressDto;
import com.example.demo.presentation.exception.NullPostRequestException;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * POST 処理における住所を表すレコードクラスです.
 *
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record PostAddressRequest(
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
   * 郵便番号、都道府県、市区、以降の住所において null のものをリストとして NullPostRequestException に渡しスローします.
   *
   * @param zipCode       郵便番号
   * @param prefecture    都道府県
   * @param city          市区
   * @param streetAddress 以降の住所
   */
  public PostAddressRequest(String zipCode, String prefecture, String city, String streetAddress) {
    this.zipCode = zipCode;
    this.prefecture = prefecture;
    this.city = city;
    this.streetAddress = streetAddress;

    List<String> nullList = createNullList();
    if (!nullList.isEmpty()) {
      throw new NullPostRequestException(nullList);
    }
  }

  private List<String> createNullList() {
    List<String> nullList = new ArrayList<>();

    if (isNull(zipCode)) {
      nullList.add("zipCode");
    }
    if (isNull(prefecture)) {
      nullList.add("prefecture");
    }
    if (isNull(city)) {
      nullList.add("city");
    }
    if (isNull(streetAddress)) {
      nullList.add("streetAddress");
    }
    return nullList;
  }

  /**
   * RequestAddressDto へ変換します.
   *
   * @return RequestAddressDto
   */
  public RequestAddressDto convertToDto() {
    return new RequestAddressDto(zipCode, prefecture, city, streetAddress);
  }
}
