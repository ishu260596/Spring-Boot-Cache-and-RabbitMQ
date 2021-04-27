package com.example.ecommerce.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "customer_id")
    int customer_id;
    @Column(name = "item")
    String item;
    @Column(name = "status")
    int status;
    @Column(name = "price")
    float price;

    public Orders() {
    }

    public Orders(int customer_id, String item, int status, float price) {
        this.customer_id = customer_id;
        this.item = item;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", item='" + item + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
