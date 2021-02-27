package org.knowm.xchange.bitstamp.service;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class BitstampAccountServiceTest {

    @Test
    public void bitstampAccountWithdrawalTest() throws IOException {
        BigDecimal bd = new BigDecimal(100);
        BigDecimal bd2 = new BigDecimal(5500);
        BitstampAccountService bas = Mockito.mock(BitstampAccountService.class);
        AccountInfo ai = new AccountInfo("fakeUsername");
        when(bas.getAccountInfo()).thenReturn(ai);
        when(bas.withdrawFunds(Currency.USD, bd, "fakeAddress")).thenReturn("Transaction ID: 123456789");
        when(bas.withdrawFunds(Currency.USD, bd2, "fakeAddress")).thenReturn("Transaction ID: 76204830");

        assertThat(bas.getAccountInfo().getUsername().equals("fakeUsername"));
        bas.withdrawFunds(Currency.USD, bd, "fakeAddress");
        bas.withdrawFunds(Currency.USD, bd, "fakeAddress");
        assertThat(bas.withdrawFunds(Currency.USD, bd2, "fakeAddress").equals("Transaction ID: 76204830"));

        verify(bas).getAccountInfo();
        verify(bas, times(2)).withdrawFunds(Currency.USD, bd, "fakeAddress");
        verify(bas, times(1)).withdrawFunds(Currency.USD, bd2, "fakeAddress");
        verifyNoMoreInteractions(bas);
    }

    @Test
    public void testWithoutMockingFails() throws IOException {
        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class);
        AccountService marketDataService = bitstamp.getAccountService();
        try {
            System.out.println(marketDataService.withdrawFunds(Currency.USD, new BigDecimal(100), "fakeAddress"));
            fail("Can't work without mocking");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
