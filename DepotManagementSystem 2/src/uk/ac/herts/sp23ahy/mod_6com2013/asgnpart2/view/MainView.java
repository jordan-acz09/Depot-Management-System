package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTextArea parcelListArea;
    private JTextArea customerQueueArea;
    private JTextArea currentParcelArea;

    public MainView() {
        setTitle("Depot Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(1, 3));

        parcelListArea = new JTextArea();
        parcelListArea.setEditable(false);
        centerPanel.add(new JScrollPane(parcelListArea));

        customerQueueArea = new JTextArea();
        customerQueueArea.setEditable(false);
        centerPanel.add(new JScrollPane(customerQueueArea));

        currentParcelArea = new JTextArea();
        currentParcelArea.setEditable(false);
        centerPanel.add(new JScrollPane(currentParcelArea));

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setParcelList(String parcels) {
        parcelListArea.setText(parcels);
    }

    public void setCustomerQueue(String customers) {
        customerQueueArea.setText(customers);
    }

    public void setCurrentParcel(String parcel) {
        currentParcelArea.setText(parcel);
    }
}