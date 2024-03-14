package com.example.demo.presentation.response;

import com.example.demo.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * レスポンスとして返される住所情報を表すレコードクラスです.
 */
public record AddressResponse(
    @JsonProperty("id") Integer id,
    @JsonProperty("zipCode") String zipCode,
    @JsonProperty("prefecture") String prefecture,
    @JsonProperty("city") String city,
    @JsonProperty("streetAddress") String streetAddress
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
