package ztp_4;

import java.util.LinkedList;
import ztp_4.interfaces.Data;
import ztp_4.proxy.ProxyCopy;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public class RealData implements Data{
    
    private int[] tab = null;
    private LinkedList<Data> copies = null;
            
    public RealData(int size) {
        tab = new int[size];
        copies = new LinkedList<>();
        /*
        Random random = new Random();
        for(int i=0;i<size;i++){
            tab[i] = random.nextInt();
        }
        */
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
        if(!copies.isEmpty())cutCopies();
        tab[idx] = value;
    }

    @Override
    public int size() {
        return tab.length;
    }

    @Override
    public void cutCopies() {
        for(Data d : copies) 
            if(d instanceof ProxyCopy)
                ((ProxyCopy) d).copying();
        copies.clear();
    }

    @Override
    public Data copy() {
        Data kopia = new ProxyCopy(this);
        copies.addLast(kopia);
        return kopia;
    }
}
