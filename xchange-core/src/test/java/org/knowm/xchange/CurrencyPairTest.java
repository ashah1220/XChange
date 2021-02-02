package org.knowm.xchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.Test;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.ObjectMapperHelper;
import static org.junit.Assert.*;


public class CurrencyPairTest {

  @Test
  public void testMajors() {

    assertThat(CurrencyPair.EUR_USD.base.getCurrencyCode()).isEqualTo("EUR");
    assertThat(CurrencyPair.EUR_USD.counter.getCurrencyCode()).isEqualTo("USD");

    assertThat(CurrencyPair.GBP_USD.base.getCurrencyCode()).isEqualTo("GBP");
    assertThat(CurrencyPair.GBP_USD.counter.getCurrencyCode()).isEqualTo("USD");

    assertThat(CurrencyPair.USD_JPY.base.getCurrencyCode()).isEqualTo("USD");
    assertThat(CurrencyPair.USD_JPY.counter.getCurrencyCode()).isEqualTo("JPY");

    assertThat(CurrencyPair.USD_CHF.base.getCurrencyCode()).isEqualTo("USD");
    assertThat(CurrencyPair.USD_CHF.counter.getCurrencyCode()).isEqualTo("CHF");

    assertThat(CurrencyPair.USD_AUD.base.getCurrencyCode()).isEqualTo("USD");
    assertThat(CurrencyPair.USD_AUD.counter.getCurrencyCode()).isEqualTo("AUD");

    assertThat(CurrencyPair.USD_CAD.base.getCurrencyCode()).isEqualTo("USD");
    assertThat(CurrencyPair.USD_CAD.counter.getCurrencyCode()).isEqualTo("CAD");
  }

  @Test
  public void testBitcoinCourtesy() {

    assertThat(CurrencyPair.BTC_USD.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_USD.counter.getCurrencyCode()).isEqualTo("USD");

    assertThat(CurrencyPair.BTC_GBP.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_USD.counter.getCurrencyCode()).isEqualTo("USD");

    assertThat(CurrencyPair.BTC_EUR.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_EUR.counter.getCurrencyCode()).isEqualTo("EUR");

    assertThat(CurrencyPair.BTC_JPY.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_JPY.counter.getCurrencyCode()).isEqualTo("JPY");

    assertThat(CurrencyPair.BTC_CHF.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_CHF.counter.getCurrencyCode()).isEqualTo("CHF");

    assertThat(CurrencyPair.BTC_AUD.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_AUD.counter.getCurrencyCode()).isEqualTo("AUD");

    assertThat(CurrencyPair.BTC_CAD.base.getCurrencyCode()).isEqualTo("BTC");
    assertThat(CurrencyPair.BTC_CAD.counter.getCurrencyCode()).isEqualTo("CAD");
  }

  @Test
  public void testSerializeDeserialize() throws IOException {
    CurrencyPair jsonCopy = ObjectMapperHelper.viaJSON(CurrencyPair.XBT_USD);
    assertThat(jsonCopy).isEqualTo(CurrencyPair.XBT_USD);
  }

  /**
   * This tests the equals(Object obj) method of the CurrencyPair class, the boundaries
   * we are testing here are if the CurrencyPair objects are the same and if they are case sensitive
   */
  @Test
  public void testEqualsSameObject() {
    CurrencyPair btc_usd = new CurrencyPair("BTC-USD");
    CurrencyPair lower_btc_usd = new CurrencyPair("btc-usd");
    assertTrue(CurrencyPair.BTC_USD.equals(btc_usd));;
    assertTrue(CurrencyPair.BTC_USD.equals(lower_btc_usd));;
  }


  /**
   * This tests the equals(Object obj) method of the CurrencyPair class, the boundary
   * we are testing is if the CurrencyPair object is a null object
   */
  @Test
  public void testEqualsNullObject() {
    CurrencyPair btc_usd = null;
    assertFalse(CurrencyPair.BTC_USD.equals(btc_usd));
  }

  /**
   * This tests the equals(Object obj) method of the CurrencyPair class, the boundary
   * we are testing is if the object in question is a class different from CurrencyPair
   */
  @Test
  public void testEqualsDifferentClass() {
    assertFalse(CurrencyPair.BTC_USD.equals(Currency.BTC));
  }

  /**
   * This tests the equals(Object obj) method of the CurrencyPair class, the boundary
   * we are testing is if the two CurrencyPair objects have a different base currency
   */
  @Test
  public void testEqualsBaseDifferent() {
    CurrencyPair diffCurrencyPair = new CurrencyPair("DOGE", "USD");
    assertFalse(CurrencyPair.BTC_USD.equals(diffCurrencyPair));
  }
  /**
   * This tests the equals(Object obj) method of the CurrencyPair class, the boundary
   * we are testing is if the two CurrencyPair objects have a different counter currency
   */
  @Test
  public void testEqualsCounterDifferent() {
    CurrencyPair diffCurrencyPair = new CurrencyPair("BTC", "DOGE");
    assertFalse(CurrencyPair.BTC_USD.equals(diffCurrencyPair));
  }
}
