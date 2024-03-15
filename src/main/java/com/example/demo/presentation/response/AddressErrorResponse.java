package com.example.demo.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 住所情報取得におけるエラーレスポンスを表現するレコードクラスです.
 *
 * @param code    レスポンスコード
 * @param message メッセージ
 * @param details 詳細
 */
public record AddressErrorResponse(
    @JsonProperty("code") String code,
    @JsonProperty("message") String message,
    @JsonProperty("details") List<String> details
) {

}
