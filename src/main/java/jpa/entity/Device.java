package jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Entity
@Table(name = "device")
public class Device implements Serializable{

    private static final long serialVersionUID = -5153256328227368573L;

    public Device() {
    }

    public Device(int price, String name, String color, int memory, Owner owner) {
        this.price = price;
        this.name = name;
        this.color = color;
        this.memory = memory;
        this.owner = owner;
    }

    private int price;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String color;
    private int memory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Device: name - " + this.name + " color - " + this.color + " memory - " + this.memory;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
