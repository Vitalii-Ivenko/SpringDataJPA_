package service;

import org.springframework.beans.factory.annotation.Autowired;
import jpa.repository.DeviceRepository;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public void getAll() {
//        List<Device> deviceList= deviceRepository.getAll();
//        for(Device device: deviceList) {
//            System.out.println(device);
//        }

    }

}
