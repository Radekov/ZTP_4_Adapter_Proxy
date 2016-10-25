/*
 * 
 *  * 
 */
package ztp_4.proxy;

import java.util.LinkedList;
import javax.management.JMX;
import ztp_4.RealData;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public class ProxyCopy implements Data {

    private Data oryginal, kopia;

    public ProxyCopy(Data oryginal) {
        this.oryginal = oryginal;
    }
    
    
    
    @Override
    public int get(int idx) {
        if (kopia != null) {
            return kopia.get(idx);
        }
        return  oryginal.get(idx);
    }

    @Override
    public void set(int idx, int value) {
        if(kopia == null) oryginal.cutCopies();
        kopia.set(idx, value);
    }

    @Override
    public int size() {
        if (kopia != null) {
            return kopia.size();
        }
        return  oryginal.size();
    }
    
    public void copying(){
        if(kopia != null) return;
        kopia = new RealData(oryginal.size());
        for(int i = 0; i<oryginal.size();i++)
            kopia.set(i, oryginal.get(i));
        oryginal = null;
    }

    @Override
    public void cutCopies() {
        if(kopia != null) kopia.cutCopies();
        oryginal.cutCopies();
    }

    @Override
    public Data copy() {
        if(kopia != null) return kopia.copy();
        return oryginal.copy();
    }
}
