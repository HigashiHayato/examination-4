package com.example.demo.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.Address;
import com.example.demo.infrastructure.mapper.AddressMapper;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DBRider
@DBUnit
class AddressMapperTest {

  private static final Address ADDRESS =
      new Address(1, "1000000", "東京都", "新宿区", "中落合");

//  private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
//  private static final String DB_USER = "sa";
//  private static final String DB_PASSWORD = "sa";
//
//  private static final ConnectionHolder connectionHolder =
//      () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

  @Autowired
  AddressMapper sut;

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address.yml")
  void 全件取得できる() {
    // setup
    List<Address> expected = List.of(
        ADDRESS
    );

    // execute
    List<Address> actual = sut.selectAll();

    // assert
    assertEquals(expected, actual);
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address.yml")
  void idを指定してデータを取得できる() {
    // setup
    // execute
    Address actual = sut.select("1");

    // assert
    assertEquals(ADDRESS, actual);
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address-post.yml")
  void テーブルに1行データが挿入される() {
    // setup
    Address address = new Address(2, "1000000", "東京都", "豊島区", "長崎");

    // execute & assert
    assertEquals(1, sut.insert(address));
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address-patch.yml")
  void テーブルの行を更新できる場合() {
    // setup
    Address address = new Address(1, "1000000", "神奈川県", "横浜市", "旭区");

    // execute
    // assert
    assertEquals(1, sut.update(address));
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address-delete.yml")
  void テーブルの指定した行を削除できる場合() {
    // setup & execute
    int actual = sut.delete("1");

    // assert
    assertEquals(1, actual);
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address.yml")
  void テーブルの指定した行が存在しておらず削除できない場合() {
    // setup
    // execute
    int actual = sut.delete("0");

    // assert
    assertEquals(0, actual);
  }

  @Test
  @DataSet(value = "datasets/setup/address.yml")
  @ExpectedDataSet(value = "datasets/expected/address.yml")
  void テーブルにあるデータのうち最大のidを取得できる() {
    // setup
    // execute
    Integer actual = sut.getMaxId();

    // assert
    assertEquals(1, actual);
  }
}
