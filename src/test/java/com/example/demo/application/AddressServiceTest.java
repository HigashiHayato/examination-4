package com.example.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Address;
import com.example.demo.infrastructure.AddressMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AddressServiceTest {

  private final Address ADDRESS =
      new Address("1", "1000000", "東京都", "新宿区", "中落合");

  @InjectMocks
  private AddressService sut;

  @Mock
  private AddressMapper mapper;

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

    when(mapper.selectAll()).thenReturn(addressList);

    // execute
    List<Address> actual = sut.retrieveAll();

    // assert
    assertEquals(addressList, actual);
  }
}
