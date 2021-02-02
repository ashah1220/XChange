package org.knowm.xchange.dto.account;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountInfoTest {

    @Test
    /**
     * This tests the getWallet(String id) function from the AccountInfoTest class. The boundaries are the casings
     * of String id, this method is case sensitive as it asserts null if the cases are different
     */
    public void getWalletTest() {
        ArrayList<Wallet> wallets = new ArrayList<>();
        Collection<Balance> balances = new Collection<Balance>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Balance> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Balance balance) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Balance> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Set<Wallet.WalletFeature> features = new Set<Wallet.WalletFeature>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Wallet.WalletFeature> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Wallet.WalletFeature walletFeature) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Wallet.WalletFeature> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        wallets.add(new Wallet("id1", "joe", balances, features, new BigDecimal(1), new BigDecimal(1)));
        wallets.add(new Wallet("id2", "bob", balances, features, new BigDecimal(2), new BigDecimal(2)));

        AccountInfo original = new AccountInfo(wallets);
        assertNull(original.getWallet("ID1"));
        assertEquals("id1", original.getWallet("id1").getId());
    }

}
