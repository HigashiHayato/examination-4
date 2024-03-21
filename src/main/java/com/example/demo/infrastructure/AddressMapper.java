package com.example.demo.infrastructure;

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
}
