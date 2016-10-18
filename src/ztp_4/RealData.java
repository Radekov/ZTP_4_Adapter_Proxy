package ztp_4;

import java.util.Random;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public class RealData implements Data{
    
    private int[] tab = null;
    
    RealData(int size) {
        tab = new int[size];
        Random random = new Random();
        for(int i=0;i<size;i++){
            tab[i] = random.nextInt();
        }
    }
    @Override
    public int get(int idx) {
        if(tab == null)
            throw new NullPointerException("Tablica nie istnieje");
        if(tab.length < idx)
            throw new IndexOutOfBoundsException("Przekroczono rozmiar tablicy: " + idx);
        return tab[idx];
    }

    @Override
    public void set(int idx, int value) {
        if(tab == null)
            throw new NullPointerException("Tablica nie istnieje");
        if(tab.length < idx)
            throw new IndexOutOfBoundsException("Przekroczono rozmiar tablicy: " + idx);
        tab[idx] = value;
    }

    @Override
    public int size() {
        return tab.length;
    }
    
}
