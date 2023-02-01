package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    String firstName;

    String lastName;

    String address;

    String metroStation;

    String phone;

    int rentTime;

    String deliveryDate;

    String comment;

    List<String> color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this(firstName, lastName,  address, metroStation, phone, rentTime, deliveryDate, comment, Collections.emptyList());
    }

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public enum Color {
        BLACK, GREY
    }
}
