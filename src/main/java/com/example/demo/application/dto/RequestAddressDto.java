package com.example.demo.application.dto;

/**
 * リクエスト処理における住所を表すレコードクラスです.
 *
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record RequestAddressDto(
    String zipCode,
    String prefecture,
    String city,
    String streetAddress
) {
}
