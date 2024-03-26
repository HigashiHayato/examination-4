package com.example.demo.domain.repository;

import com.example.demo.domain.Address;
import java.util.List;

/**
 * Address に関するリポジトリ.
 */
public interface AddressRepository {

  /**
   * 全件取得におけるマッパーのメソッドを呼び出します.
   *
   * @return Address のリスト
   */
  public List<Address> selectAllAddress();

  /**
   * 選択取得におけるマッパーのメソッドを呼び出します.
   *
   * @param id 取得する Address の ID
   * @return 指定された ID の Address オブジェクト
   */
  public Address selectAddressById(String id);

  /**
   * 挿入におけるマッパーのメソッドを呼び出します.
   *
   * @param address 挿入する Address オブジェクト
   * @return 挿入が成功した場合は1、それ以外の場合は0
   */
  public int insertAddress(Address address);

  /**
   * テーブル内の最大の Address ID 取得におけるマッパーのメソッドを呼び出します.
   *
   * @return @return 最大の Address ID
   */
  public Integer selectMaxId();

  /**
   * 更新におけるマッパーのメソッドを呼び出します.
   *
   * @param address 更新する Address オブジェクト
   * @return 更新が成功した場合は1、それ以外の場合は0
   */
  public int updateAddress(Address address);

  /**
   * 削除におけるマッパーのメソッドを呼び出します.
   *
   * @param id 削除する Address の ID
   * @return 削除が成功した場合は1、それ以外の場合は0
   */
  public int deleteAddress(String id);
}
