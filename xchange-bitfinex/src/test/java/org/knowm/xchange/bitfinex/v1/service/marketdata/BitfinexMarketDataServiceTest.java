package org.knowm.xchange.bitfinex.v1.service.marketdata;

import org.junit.Ignore;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitfinex.BitfinexExchange;
import org.knowm.xchange.bitfinex.service.BitfinexMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.LoanOrderBook;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;

public class BitfinexMarketDataServiceTest {
    @Test
    public void testGetOrderBook() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
        BitfinexMarketDataService marketDataService = (BitfinexMarketDataService) exchange.getMarketDataService();

        // valid args
        Integer[] args = {1, 1};
        OrderBook ob = marketDataService.getOrderBook(new CurrencyPair("BTC", "USD"), args );
        System.out.println(ob.toString());
        assertThat(ob).isNotNull();

        // invalid args
        Object[] args0 = {"s", 1};
        try {
            ob = marketDataService.getOrderBook(new CurrencyPair("BTC", "USD"), args0);
            System.out.println(ob.toString());
            fail("Argument 0 must be an Integer!");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Argument 0 must be an Integer!"));
        }
        Object[] args1 = {1, "s"};
        try {
            ob = marketDataService.getOrderBook(new CurrencyPair("BTC", "USD"), args1);
            System.out.println(ob.toString());
            fail("Argument 1 must be an Integer!");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Argument 1 must be an Integer!"));
        }
    }

    @Test
    public void testGetLendOrderBook() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
        BitfinexMarketDataService marketDataService = (BitfinexMarketDataService) exchange.getMarketDataService();

        // valid args
        Integer[] args = {1, 1};
        LoanOrderBook ob = marketDataService.getLendOrderBook("BTC", args);
        System.out.println(ob.toString());
        assertThat(ob).isNotNull();

        // invalid args
        Object[] args0 = {"s", 1};
        try {
            ob = marketDataService.getLendOrderBook("BTC", args0);
            System.out.println(ob.toString());
            fail("Argument 0 must be an Integer!");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Argument 0 must be an Integer!"));
        }
        Object[] args1 = {1, "s"};
        try {
            ob = marketDataService.getLendOrderBook("BTC", args1);
            System.out.println(ob.toString());
            fail("Argument 1 must be an Integer!");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Argument 1 must be an Integer!"));
        }
    }

    @Test
    public void testGetTrades() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
        BitfinexMarketDataService marketDataService = (BitfinexMarketDataService) exchange.getMarketDataService();

        // valid args
        Integer[] args = {0, 1, 2, 3};
        Trades ob = marketDataService.getTrades(new CurrencyPair("BTC", "USD"), args );
        System.out.println(ob.toString());
        assertThat(ob).isNotNull();

        // invalid args
        Object[] args0 = {"s", 1};
        try {
            ob = marketDataService.getTrades(new CurrencyPair("BTC", "USD"), args0);
            System.out.println(ob.toString());
            fail("expect error");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Extra argument #0 must be an int/long was: class java.lang.String"));
        }
    }

    @Test
    public void testGetTickers() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class);
        BitfinexMarketDataService marketDataService = (BitfinexMarketDataService) exchange.getMarketDataService();

        Collection<CurrencyPair> args = null;
        List<Ticker> ob = marketDataService.getTickers((Params) args);
        System.out.println(ob.toString());
        assertThat(ob).isNotNull();
    }



}
