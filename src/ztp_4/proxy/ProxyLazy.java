/*
 * 
 *  * 
 */
package ztp_4.proxy;

import java.util.LinkedList;
import ztp_4.RealData;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public class ProxyLazy implements Data {

    private RealData tablica;
    private int rozmiar;

    public ProxyLazy(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    @Override
    public int get(int idx) {
        if (idx < 0 || idx >= rozmiar) {
            throw new IndexOutOfBoundsException("" + idx);
        }
        if (tablica == null) {
            return 0;
        } else {
            return tablica.get(idx);
        }
    }

    @Override
    public void set(int idx, int value) {
        if (tablica == null) {
            tablica = new RealData(rozmiar);
        }
        tablica.set(idx, value);
    }

    @Override
    public int size() {
        return rozmiar;
    }

    @Override
    public void cutCopies() {
        if (tablica == null) {
            return;
        } else {
            tablica.cutCopies();
        }
    }

    @Override
    public Data copy() {
        if (tablica == null) {
            tablica = new RealData(rozmiar);
        }
        return tablica.copy();
    }
}
