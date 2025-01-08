package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ManagerGui extends JFrame {
    private QueOfCustomers queOfCustomers;
    private Parcelmap parcelMap;
    private Worker worker;

    // GUI components
    private JTextField searchField;
    private JButton searchButton, showParcelDetails, showListOfCustomers, showListOfParcels;
    private JTextArea displayList;

    public ManagerGui(QueOfCustomers queue, Parcelmap parcels, Worker worker) {
        this.queOfCustomers = queue;
        this.parcelMap = parcels;
        this.worker = worker;

        setTitle("Depot GUI Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupNorthPanel();
        setupCenterPanel();
        setupSouthPanel();

        pack();
        setVisible(true);
    }

    private void setupNorthPanel() {
        // North Panel for buttons
        JPanel northPanel = new JPanel();
        showListOfParcels = new JButton("List Parcels");
        showListOfParcels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateParcelList();
            }
        });

        showListOfCustomers = new JButton("Load Customer Queue");
        showListOfCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerQueue();
            }
        });

        showParcelDetails = new JButton("Load Current Processing Parcel Details");
        showParcelDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentParcel();
            }
        });

        northPanel.add(showListOfParcels);
        northPanel.add(showListOfCustomers);
        northPanel.add(showParcelDetails);
        this.add(northPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        // Center Panel for displaying lists
        displayList = new JTextArea(15, 50);
        displayList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        displayList.setEditable(false);

        JScrollPane scrollList = new JScrollPane(displayList);
        this.add(scrollList, BorderLayout.CENTER);
    }

    private void setupSouthPanel() {
        // South Panel for search functionality
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchPanel.add(new JLabel("Enter Parcel ID"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        searchButton = new JButton("Search Parcel");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchParcel();
            }
        });
        searchPanel.add(searchButton);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(searchPanel);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void searchParcel() {
        String searchString = searchField.getText().trim();
        if (!searchString.isEmpty()) {
            Parcel parcel = parcelMap.getParcel(searchString);
            if (parcel != null) {
                updateTextArea("Parcel Details:\n" + parcel.toString());
            } else {
                updateTextArea("Parcel not found");
            }
        } else {
            updateTextArea("No ID entered");
        }
    }

    private void updateTextArea(String text) {
        // Update displayList JTextArea with new text
        displayList.setText(text);
    }

    private void updateParcelList() {
        String parcelDetails = parcelMap.displayAllParcels();
        updateTextArea("Parcel List:\n" + parcelDetails);
    }

    private void updateCustomerQueue() {
        String customerDetails = queOfCustomers.listByName();
        updateTextArea("Customer Queue:\n" + customerDetails);
    }

    private void updateCurrentParcel() {
        String currentParcelDetails = worker.getCurrentParcelDetails();
        Parcel currentParcel = worker.getCurrentParcel();
        if (currentParcel != null) {
            float finalFee = calculateFee(
                (float)currentParcel.getLength() * (float)currentParcel.getWidth() * (float)currentParcel.getHeight(),
                (float)currentParcel.getWeight(),
                currentParcel.getDaysInDepot(),
                currentParcel.getParcelID()
            );
            currentParcelDetails += "\nFinal Fee: £" + finalFee;
        }
        updateTextArea("Current Processing Parcel Details:\n" + currentParcelDetails);
    }

    private float calculateFee(float dimension, float weight, int daysInDepot, String parcelId) {
        // No discount applied
        float initialFee = weight * (daysInDepot * daysInDepot);
        return initialFee;
    }
}
