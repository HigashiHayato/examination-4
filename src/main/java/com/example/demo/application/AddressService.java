package com.example.demo.application;

import static java.util.Objects.isNull;

import com.example.demo.application.exception.AddressNotFoundException;
import com.example.demo.domain.Address;
import com.example.demo.infrastructure.AddressMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Address に関するビジネスロジックを提供するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressMapper mapper;

  /**
   * 全ての Address を取得します.
   *
   * @return 全ての Address のリスト
   */
  public List<Address> retrieveAll() {
    return mapper.selectAll();
  }

  /**
   * 指定された ID の Address を取得します.
   *
   * @param id 取得対象の Address の ID
   * @return 指定された ID の Address
   * @throws AddressNotFoundException 指定された ID の Address が存在しない場合
   */
  public Address retrieve(String id) {

    Address address = mapper.select(id);

    if (isNull(address)) {
      throw new AddressNotFoundException(id);
    }

    return address;
  }
}
