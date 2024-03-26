package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.application.dto.RequestAddressDto;
import com.example.demo.application.exception.AddressNotFoundException;
import com.example.demo.domain.Address;
import com.example.demo.domain.repository.AddressRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AddressServiceTest {

  private final Address ADDRESS =
      new Address(1, "1610032", "東京都", "新宿区", "中落合");

  private final RequestAddressDto REQUEST_ADDRESS_DTO =
      new RequestAddressDto("1000000", "東京都", "新宿区", "中落合");

  @InjectMocks
  private AddressService sut;

  @Mock
  private AddressRepository repository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件取得により住所リストが返される() {
    // setup
    List<Address> addressList = List.of(
        ADDRESS
    );

    when(repository.selectAllAddress()).thenReturn(addressList);

    // execute
    List<Address> actual = sut.retrieveAll();

    // assert
    assertEquals(addressList, actual);
  }

  @Test
  void 選択した住所idが存在する場合() {
    // setup
    when(repository.selectAddressById(any())).thenReturn(ADDRESS);

    // execute
    Address actual = sut.retrieve("1");

    // assert
    assertEquals(ADDRESS, actual);
  }

  @Test
  void 選択した住所idが存在しない場合() {
    // setup
    when(repository.selectAddressById(any())).thenReturn(null);

    // execute
    // assert
    assertThatThrownBy(() -> sut.retrieve("99"))
        .isInstanceOf(AddressNotFoundException.class)
        .hasMessage("99");
  }

  @Test
  void データを挿入した際挿入したidが返される() {
    // setup
    when(repository.selectMaxId()).thenReturn(88);

    // execute
    String actual = sut.register(REQUEST_ADDRESS_DTO);

    // assert
    assertEquals("89", actual);
  }

  @Test
  void データがないテーブルに挿入する場合() {
    // setup
    when(repository.selectMaxId()).thenReturn(null);

    // execute
    String actual = sut.register(REQUEST_ADDRESS_DTO);

    // assert
    assertEquals("1", actual);
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      # zipCode | prefecture | city | streetAddress
                |    東京都   | 新宿区| 中落合
       1610032  |            | 新宿区| 中落合
       1610032  |    東京都   |      | 中落合
       1610032  |    東京都   | 新宿区| 
      """)
  void 行を更新する際指定したidがテーブルに存在する場合(String zipCode, String prefecture, String city, String streetAddress) {
    // setup
    when(repository.selectAddressById("1")).thenReturn(ADDRESS);
    RequestAddressDto dto = new RequestAddressDto(zipCode, prefecture, city, streetAddress);

    // execute
    sut.update(dto, "1");

    // assert
    verify(repository, times(1)).updateAddress(ADDRESS);
  }

  @Test
  void 行を更新する際指定したidがテーブルに存在しない場合() {
    // setup
    when(repository.selectAddressById("99")).thenReturn(null);

    // execute
    // assert
    assertThatThrownBy(() -> sut.update(REQUEST_ADDRESS_DTO, "99"))
        .isInstanceOf(AddressNotFoundException.class)
        .hasMessage("99");
  }

  @Test
  void 行を削除する際指定したidがテーブルに存在する場合() {
    // setup
    when(repository.deleteAddress("1")).thenReturn(1);

    // execute
    sut.delete("1");

    // assert
    verify(repository, times(1)).deleteAddress("1");
  }

  @Test
  void 行を削除する際指定したidがテーブルに存在しない場合() {
    // setup
    when(repository.deleteAddress(any())).thenReturn(0);

    // execute & assert
    assertThatThrownBy(() -> sut.delete("99"))
        .isInstanceOf(AddressNotFoundException.class)
        .hasMessage("99");
  }
}
