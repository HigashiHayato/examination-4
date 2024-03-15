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


}
