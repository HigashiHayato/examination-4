package com.example.demo.domain.mapper;

import com.example.demo.domain.Address;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * Address オブジェクトに対するデータベース操作を行う Mapper インターフェースです.
 */
@Mapper
public interface AddressMapper {

  /**
   * すべての Address を取得します.
   *
   * @return Address のリスト
   */
  List<Address> selectAll();

  /**
   * 指定された ID の Address を取得します.
   *
   * @param id 取得する Address の ID
   * @return 指定された ID の Address オブジェクト
   */
  Address select(String id);

  /**
   * 新しい Address を挿入します.
   *
   * @param address 挿入する Address オブジェクト
   * @return 挿入が成功した場合は1、それ以外の場合は0
   */
  int insert(Address address);

  /**
   * テーブル内の最大の Address ID を取得します.
   *
   * @return 最大の Address ID
   */
  Integer getMaxId();

  /**
   * 指定された Address を更新します.
   *
   * @param address 更新する Address オブジェクト
   * @return 更新が成功した場合は1、それ以外の場合は0
   */
  int update(Address address);

  /**
   * 指定された ID の Address を削除します.
   *
   * @param id 削除する Address の ID
   * @return 削除が成功した場合は1、それ以外の場合は0
   */
  int delete(String id);
}
