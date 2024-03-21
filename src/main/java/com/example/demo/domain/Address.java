package com.example.demo.domain;

/**
 * 住所エンティティを表すレコードクラスです.
 *
 * @param id            ID
 * @param zipCode       郵便番号
 * @param prefecture    都道府県
 * @param city          市区
 * @param streetAddress 以降の住所
 */
public record Address(
    String id,
    String zipCode,
    String prefecture,
    String city,
    String streetAddress
) {

}
