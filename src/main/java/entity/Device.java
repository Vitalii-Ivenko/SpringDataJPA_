package entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.io.Serializable;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Document
public class Device implements Serializable{

    private static final long serialVersionUID = -5153256328227368573L;

    public Device() {
    }

    public Device(int price, String name, String color, int memory) {
        this.price = price;
        this.name = name;
        this.color = color;
        this.memory = memory;
    }

    private int price;
    @Id
    private ObjectId id;
    private String name;
    private String color;
    private int memory;


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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Device: name - " + this.name + " color - " + this.color + " memory - " + this.memory;
    }

}
