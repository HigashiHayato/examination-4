package com.example.demo.application;

import static java.util.Objects.isNull;

import com.example.demo.application.dto.RequestAddressDto;
import com.example.demo.application.exception.AddressNotFoundException;
import com.example.demo.domain.Address;
import com.example.demo.domain.repository.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Address に関するビジネスロジックを提供するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository repository;

  /**
   * 全ての Address を取得します.
   *
   * @return 全ての Address のリスト
   */
  public List<Address> retrieveAll() {
    return repository.selectAllAddress();
  }

  /**
   * 指定された ID の Address を取得します.
   *
   * @param id 取得対象の Address の ID
   * @return 指定された ID の Address
   * @throws AddressNotFoundException 指定された ID の Address が存在しない場合
   */
  public Address retrieve(String id) {

    Address address = repository.selectAddressById(id);

    if (isNull(address)) {
      throw new AddressNotFoundException(id);
    }

    return address;
  }

  /**
   * 新しい Address を登録します.
   *
   * @param address 登録する PostRequestAddressDto
   * @return 挿入した住所の ID
   */
  public String register(RequestAddressDto address) {

    Integer maxId = repository.selectMaxId();
    String nextId = null;

    if (isNull(maxId)) {
      nextId = "1";
    } else {
      nextId = String.valueOf(maxId + 1);
    }

    repository.insertAddress(
        new Address(
            Integer.parseInt(nextId),
            address.zipCode(),
            address.prefecture(),
            address.city(),
            address.streetAddress())
    );

    return nextId;
  }

  /**
   * 指定された ID の Address を更新します.
   *
   * @param address 更新する Address の情報
   * @param id      更新対象の Address の ID
   * @throws AddressNotFoundException 指定された ID の Address が存在しない場合
   */
  public void update(RequestAddressDto address, String id) {

    Address existingAddress = repository.selectAddressById(id);

    if (isNull(existingAddress)) {
      throw new AddressNotFoundException(id);
    }

    Address postAddress = new Address(
        Integer.parseInt(id),
        isNull(address.zipCode()) ? existingAddress.zipCode() : address.zipCode(),
        isNull(address.prefecture()) ? existingAddress.prefecture() : address.prefecture(),
        isNull(address.city()) ? existingAddress.city() : address.city(),
        isNull(address.streetAddress()) ? existingAddress.streetAddress() : address.streetAddress()
    );

    repository.updateAddress(postAddress);
  }

  /**
   * 指定された ID の Address を削除します.
   *
   * @param id 削除対象の Address の ID
   * @throws AddressNotFoundException 指定された ID の Address が存在しない場合
   */
  public void delete(String id) {
    int deleteStatusCode = repository.deleteAddress(id);

    if (deleteStatusCode == 0) {
      throw new AddressNotFoundException(id);
    }
  }
}
