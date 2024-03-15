package com.example.demo.application.exception;

/**
 * 住所IDが見つからない場合にスローされる例外クラスです.
 */
public class AddressNotFoundException extends RuntimeException {

  /**
   * 指定されたテキストで新しい AddressNotFoundException を構築します.
   *
   * @param text handlerで必要となる文字列
   */
  public AddressNotFoundException(String text) {
    super(text);
  }
}
