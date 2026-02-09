package edu.aitu.oop3.entities;

public class Room {
    private int id;
    private String type;
    private double price;

    public Room(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public double getPrice() { return price; }
}
