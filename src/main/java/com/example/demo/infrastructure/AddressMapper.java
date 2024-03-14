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

}
