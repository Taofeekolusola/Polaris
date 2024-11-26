package com.example.boxdelivery.model;

public class Item {
    private Long id;
    private Long boxId;
    private String description;
    private double weight;

    // Constructors
    public Item() {}

    public Item(Long id, Long boxId, String description, double weight) {
        this.id = id;
        this.boxId = boxId;
        this.description = description;
        this.weight = weight;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBoxId() { return boxId; }
    public void setBoxId(Long boxId) { this.boxId = boxId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
}
