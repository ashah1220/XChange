package info.bitrich.xchangestream.bitstamp;

import info.bitrich.xchangestream.bitstamp.v2.BitstampStreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamingMarketDataServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(StreamingMarketDataServiceTest.class);

    @Test
    public void test1() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe to live trades update.
        Disposable subscription1 = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Subscribe order book data with the reference to the subscription.
        Disposable subscription2 = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        // Subscribe ticker data with the reference to the subscription.
        Disposable subscription3 = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Wait for a while to see some results arrive
        Thread.sleep(5000);

        // Unsubscribe
        subscription1.dispose();
        subscription2.dispose();
        subscription3.dispose();

        assertTrue(subscription1.isDisposed());
        assertTrue(subscription2.isDisposed());
        assertTrue(subscription3.isDisposed());

        // Disconnect from exchange (blocking again)
        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test2() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe ticker data with the reference to the subscription.
        Disposable subscription1 = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe to live trades update.
        Disposable subscription2 = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Wait for a while to see some results arrive
        Thread.sleep(5000);

        subscription1.dispose();
        assertTrue(subscription1.isDisposed());

        // Disconnect from exchange (blocking again)
        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());

    }

    @Test
    public void test3() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe order book data with the reference to the subscription.
        Disposable subscription1 = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        // Subscribe ticker data with the reference to the subscription.
        Disposable subscription2 = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe to live trades update.
        Disposable subscription3 = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Wait for a while to see some results arrive
        Thread.sleep(5000);

        subscription2.dispose();
        assertTrue(subscription2.isDisposed());

        // Disconnect from exchange (blocking again)
        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test4() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Wait for a while to see some results arrive
        Thread.sleep(5000);

        tradeSub.dispose();

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());

    }

    @Test
    public void test5() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe ticker data with the reference to the subscription.
        Disposable tickerSub = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        tickerSub.dispose();
        assertTrue(tickerSub.isDisposed());

        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        tradeSub.dispose();
        assertTrue(tradeSub.isDisposed());
        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test6() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Subscribe ticker data with the reference to the subscription.
        Disposable tickerSub = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());

    }

    @Test
    public void test7() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        orderSub.dispose();
        assertTrue(orderSub.isDisposed());

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test8() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());


        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        // Subscribe ticker data with the reference to the subscription.
        Disposable tickerSub = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        orderSub.dispose();
        assertTrue(orderSub.isDisposed());

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test9() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        orderSub.dispose();
        assertTrue(orderSub.isDisposed());

        // Subscribe ticker data with the reference to the subscription.
        Disposable tickerSub = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe to live trades update.
        Disposable tradeSub = exchange.getStreamingMarketDataService()
                .getTrades(CurrencyPair.BTC_USD)
                .subscribe(
                        trade -> LOG.info("Trade: {}", trade),
                        throwable -> LOG.error("Error in trade subscription", throwable));

        tradeSub.dispose();
        assertTrue(tradeSub.isDisposed());

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }

    @Test
    public void test10() throws InterruptedException {
        // Use StreamingExchangeFactory instead of ExchangeFactory
        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(BitstampStreamingExchange.class);

        // Connect to the Exchange WebSocket API. Here we use a blocking wait.
        exchange.connect().blockingAwait();
        assertTrue(exchange.isAlive());

        // Subscribe ticker data with the reference to the subscription.
        Disposable tickerSub = exchange.getStreamingMarketDataService()
                .getTicker(CurrencyPair.BTC_USD)
                .subscribe(ticker -> LOG.info("Ticker: {}", ticker));

        // Subscribe order book data with the reference to the subscription.
        Disposable orderSub = exchange.getStreamingMarketDataService()
                .getOrderBook(CurrencyPair.BTC_USD)
                .subscribe(orderBook -> LOG.info("Order book: {}", orderBook));

        exchange.disconnect().blockingAwait();
        assertTrue(!exchange.isAlive());
    }
}
