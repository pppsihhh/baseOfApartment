package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apartment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String district;
    private String address;
    private double area;
    private int rooms;
    private int price;

    public Apartment () {}

    public Apartment(String district, String address, double area, int rooms, int price) {
        this.id = id;
        this.district = district;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public double getArea() {
        return area;
    }

    public int getRooms() {
        return rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
