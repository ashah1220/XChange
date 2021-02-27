package org.knowm.xchange.cryptopia.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CryptopiaOrderBook {

  private final Date timestamp;
  private final List<CryptopiaOrder> bids;
  private final List<CryptopiaOrder> asks;

  public CryptopiaOrderBook(
      @JsonProperty("timestamp") Long timestamp,
      @JsonProperty("Buy") List<CryptopiaOrder> bids,
      @JsonProperty("Sell") List<CryptopiaOrder> asks) {
    this.bids = bids;
    this.asks = asks;
    this.timestamp = new Date(timestamp * 1000);
  }

  public Date getTimestamp() { return timestamp; }

  public List<CryptopiaOrder> getBids() {
    return bids;
  }

  public List<CryptopiaOrder> getAsks() {
    return asks;
  }

  @Override
  public String toString() {
    return "CryptopiaOrderBook{" + "bids=" + bids + ", asks=" + asks + '}';
  }
}
