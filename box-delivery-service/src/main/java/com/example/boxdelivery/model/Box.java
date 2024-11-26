package com.example.boxdelivery.model;

import java.util.List;

public class Box {
    private Long id;
    private String name; // Added name field
    private String txref; // Transaction reference (20 characters max)
    private double weightLimit; // Max weight limit (500gr max)
    private double batteryCapacity; // Battery percentage (0-100)
    private String state; // States: IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    private List<String> items; // List of items loaded into the box

    // Constructor
    public Box() {}

    public Box(Long id, String name, String txref, double weightLimit, double batteryCapacity, String state, List<String> items) {
        this.id = id;
        this.name = name;  // Initialize name
        this.txref = txref;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.items = items;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; } // Added getName method
    public void setName(String name) { this.name = name; } // Added setName method
    
    public String getTxref() { return txref; }
    public void setTxref(String txref) { this.txref = txref; }
    
    public double getWeightLimit() { return weightLimit; }
    public void setWeightLimit(double weightLimit) { this.weightLimit = weightLimit; }
    
    public double getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(double batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }

    // Method to add a single item to the box
    public void addItemToBox(String item) {
        if (this.items != null) {
            this.items.add(item);
        }
    }

    // Method to add multiple items to the box
    public void addItemsToBox(List<String> newItems) {
        if (this.items != null) {
            this.items.addAll(newItems);
        }
    }
}