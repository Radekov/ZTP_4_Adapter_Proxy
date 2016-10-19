package ztp_4;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ztp_4.adapters.Adapter;
import ztp_4.adapters.AdapterExtend;
import ztp_4.interfaces.Data;
import ztp_4.proxy.ProxyLazy;

/**
 *
 * @author Radosław Naruszewicz
 */
public class ZTP_4 {

    public static void main(String[] args) {
        final Baza dane = new Baza();
        Adapter adapter = new AdapterExtend();

        final JFrame frame = new JFrame("Zadanie 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane splitPane = new JSplitPane();

        /*TODO 
        ZTP_4_Adapter_Proxy\src\ztp_4\ZTP_4.java:22: warning: [unchecked] unchecked call to JList(ListModel<E>) as a member of the raw type JList
        final JList list = new JList(dane);
        where E is a type-variable:
            E extends Object declared in class JList
        1 warning*/
        final JList list = new JList(dane);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Tablice: "));
        splitPane.setLeftComponent(scrollPane);

        JTable table = new JTable(adapter);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Zawartość: "));
        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane);

        JMenuBar bar = new JMenuBar();
        JButton add = new JButton("Dodaj tablicę");
        JButton del = new JButton("Usuń tablicę");
        bar.add(add);
        bar.add(del);

        frame.setJMenuBar(bar);

        frame.setSize(600, 450);
        frame.setVisible(true);

        splitPane.setDividerLocation(0.5);

        add.addActionListener((ActionEvent e) -> {
            String value = JOptionPane.showInputDialog(frame,
                    "Podaj rozmiar tablicy",
                    "Dodaj",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                int size = Integer.parseInt(value);
                dane.add(new ProxyLazy(size));
                adapter.setData((Data) dane.getElementAt(dane.getSize() - 1));
            } catch (Exception ex) {
                System.out.println(ex + " PROBLEMO DODANO");
            };
        });
        del.addActionListener((ActionEvent e) -> {
            int idx = list.getSelectedIndex();
            try {
                dane.remove(idx);
                try {
                    adapter.setData((Data) dane.getElementAt(idx));
                } catch (Exception ex2) {
                    adapter.setData(null);
                }
            } catch (Exception ex) {
                System.out.println(ex + " PROBLEMO USUNIETO INDEXO:" + idx);
            };
        });

        // zmiana wyboru na liście powoduje odświeżenie tabeli
        list.addListSelectionListener((ListSelectionEvent e) -> {
            int idx = list.getSelectedIndex();
            if (idx >= 0) {
                adapter.setData((Data) dane.getElementAt(idx));

            }
        });
    }

}
