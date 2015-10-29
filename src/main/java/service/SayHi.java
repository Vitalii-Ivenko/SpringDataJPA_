package service;

import org.springframework.stereotype.Service;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Service
public class SayHi implements Action {
    public void act() {
        System.out.println("Hi!");
    }
}
