package com.example.demo.presentation.response;

import com.example.demo.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * レスポンスとして返される住所情報を表すレコードクラスです.
 *
 * @param id            ID
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record AddressResponse(
    @JsonProperty("id") Integer id,
    @JsonProperty("zip_code") String zipCode,
    @JsonProperty("prefecture") String prefecture,
    @JsonProperty("city") String city,
    @JsonProperty("street_address") String streetAddress
) {

  /**
   * 指定された Address オブジェクトから AddressResponse を作成して返します.
   *
   * @param address 住所情報を含む Address オブジェクト
   * @return AddressResponse オブジェクト
   */
  public static AddressResponse convertToAddressResponse(Address address) {
    return new AddressResponse(
        address.id(),
        address.zipCode(),
        address.prefecture(),
        address.city(),
        address.streetAddress()
    );
  }
}
