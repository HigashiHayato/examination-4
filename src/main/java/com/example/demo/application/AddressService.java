package com.example.demo.application;

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
}
