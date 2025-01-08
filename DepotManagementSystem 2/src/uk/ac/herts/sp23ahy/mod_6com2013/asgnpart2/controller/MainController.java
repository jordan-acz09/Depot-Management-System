package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller;

import src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.*;
import src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private QueOfCustomers queOfCustomers;
    private Parcelmap parcelMap;
    private Worker worker;
    private MainView mainView;

    public MainController(QueOfCustomers queOfCustomers, Parcelmap parcelMap, Worker worker, MainView mainView) {
        this.queOfCustomers = queOfCustomers;
        this.parcelMap = parcelMap;
        this.worker = worker;
        this.mainView = mainView;

        updateView();

        setupListeners();
    }

    private void setupListeners() {
        // Add listeners to handle actions and update the view
        // Example: Add a button to the view to simulate processing a parcel
        mainView.addProcessParcelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.getCurrentParcelDetails();
                updateView();
            }
        });

        // Add more listeners as needed
    }

    public void updateView() {
        mainView.setParcelList(parcelMap.displayAllParcels());
        mainView.setCustomerQueue(queOfCustomers.listByName());
        mainView.setCurrentParcel(worker.getCurrentParcelDetails());
    }
}