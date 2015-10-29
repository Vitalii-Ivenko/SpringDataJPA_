package entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import service.Action;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
public class Frog {

    @Autowired
    @Qualifier("jumpOver")
    private Action action;

    public Frog() {

    }

    private String name;

    private int speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void makeAction() {
        action.act();
    }

    @Override
    public String toString() {
        return "Frog - " + this.name + " with speed - " + this.speed;
    }
}
