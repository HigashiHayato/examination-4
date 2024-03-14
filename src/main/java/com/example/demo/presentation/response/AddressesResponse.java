package com.example.demo.presentation.response;

import com.example.demo.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 住所レスポンスのリストを表すレコードクラスです. このクラスは住所レスポンスのリストをラップします。
 *
 * @param addressResponseList 住所レスポンスリスト
 */
public record AddressesResponse(
    @JsonProperty("addresses") List<AddressResponse> addressResponseList
) {

  /**
   * 住所エンティティのリストから AddressesResponse インスタンスを生成します.
   *
   * @param addressList 住所エンティティのリスト
   * @return 住所レスポンスのリストを表す AddressesResponse インスタンス
   */
  public static AddressesResponse of(List<Address> addressList) {
    return new AddressesResponse(
        addressList.stream().map(AddressResponse::convertToAddressResponse).toList());
  }
}
