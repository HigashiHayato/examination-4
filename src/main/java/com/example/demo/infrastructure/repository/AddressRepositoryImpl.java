package com.example.demo.infrastructure.repository;

import com.example.demo.domain.Address;
import com.example.demo.domain.mapper.AddressMapper;
import com.example.demo.domain.repository.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * AddressRepository インターフェースの実装クラス.
 */
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

  private final AddressMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Address> selectAllAddress() {
    return mapper.selectAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Address selectAddressById(String id) {
    return mapper.select(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int insertAddress(Address address) {
    return mapper.insert(address);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer selectMaxId() {
    return mapper.getMaxId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int updateAddress(Address address) {
    return mapper.update(address);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int deleteAddress(String id) {
    return mapper.delete(id);
  }
}
