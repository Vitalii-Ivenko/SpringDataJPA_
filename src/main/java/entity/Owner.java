package entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Document
public class Owner implements Serializable {
    private static final long serialVersionUID = -5153256328007368573L;

    public Owner() {
    }

    public Owner(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Id
    private ObjectId id;

    private String firstName;
    private String lastName;
    private int age;
    private Set<Device> devices;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}