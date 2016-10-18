package ztp_4;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractListModel;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public class Baza extends AbstractListModel {

    private ArrayList<Data> ar = new ArrayList<Data>();

    public void add(Data tab) {
        ar.add(tab);
        try{
        fireIntervalAdded(tab,0,this.getSize());}
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void remove(int idx) {
        Object tmp = this.getElementAt(idx);
        ar.remove(idx);
        fireIntervalRemoved(tmp, 0, this.getSize());
    }

    @Override
    public int getSize() {
        return ar.size();
    }

    @Override
    public Object getElementAt(int index) {
        if(getSize() < index)
            throw new IndexOutOfBoundsException("Pojęcia nie mam jak to zostałow wywołane. Po implentacji Adaptera: index " + index + " size: " +getSize());
        return ar.get(index);
        //TODO Zmienić zwracany obiekt - utowrzyć kopie. Nigdy nie powinno zwracać się klientowi oryginału.
        //return new RealData(ar.get(index));
        
    }
   
}
