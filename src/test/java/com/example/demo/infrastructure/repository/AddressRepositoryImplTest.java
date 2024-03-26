package com.example.demo.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Address;
import com.example.demo.domain.mapper.AddressMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.expression.AnnotatedElementKey;

class AddressRepositoryImplTest {

  private final Address ADDRESS =
      new Address(1, "1000000", "東京都", "新宿区", "中落合");

  @InjectMocks
  AddressRepositoryImpl sut;

  @Mock
  AddressMapper mapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件取得における処理() {
    // setup
    when(mapper.selectAll()).thenReturn(List.of(ADDRESS));

    // execute
    List<Address> actual = sut.selectAllAddress();

    // assert
    assertEquals(List.of(ADDRESS), actual);
  }

  @Test
  void 選択取得における処理() {
    // setup
    when(mapper.select("1")).thenReturn(ADDRESS);

    // execute
    Address actual = sut.selectAddressById("1");

    // assert
    assertEquals(ADDRESS, actual);
  }

  @Test
  void 挿入における処理() {
    // setup
    when(mapper.insert(ADDRESS)).thenReturn(1);

    // execute
    int actual = sut.insertAddress(ADDRESS);

    // assert
    assertEquals(1, actual);
  }

  @Test
  void 更新における処理() {
    // setup
    when(mapper.update(ADDRESS)).thenReturn(1);

    // execute
    int actual = sut.updateAddress(ADDRESS);

    // assert
    assertEquals(1, actual);
  }

  @Test
  void 削除における処理() {
    // setup
    when(mapper.delete("1")).thenReturn(1);

    // execute
    int actual = sut.deleteAddress("1");

    // assert
    assertEquals(1, actual);
  }

  @Test
  void 最大id取得における処理() {
    // setup
    when(mapper.getMaxId()).thenReturn(1);

    // execute
    int actual = sut.selectMaxId();

    // assert
    assertEquals(1, actual);
  }

}
