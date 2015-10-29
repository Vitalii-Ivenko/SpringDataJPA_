import jpa.entity.Device;
import jpa.entity.Owner;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.OwnerService;

import java.util.HashSet;
import java.util.Set;

/**
 * A Good example of wired entities altogether in one-to-many relationship
 * and showing how do cascade and orphanRemoval works
 */
public class App {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("application-config.xml");
//
//        Frog frog = (Frog)appContext.getBean("frog");
//        System.out.println(frog);
//        frog.makeAction();

        Owner x = null;
        Device y = null;
        Set<Device> ds = null;
        OwnerService ownerService = (OwnerService) appContext.getBean("ownerService");


        Owner o1 = new Owner("John", "Martin", 45);
        Device d1 = new Device(600, "iPhone 6", "Grey", 16, o1);
        Device d2 = new Device(350, "iPad Mini", "Grey", 64, o1);
        Set<Device> devices = new HashSet<Device>();
        devices.add(d1);
        devices.add(d2);
        o1.setDevices(devices);
        ownerService.save(o1);
//
////        Owner o2 = ownerService.deleteOneDeviceByName("John");

//        ownerService.deleteOwnerByName("John");
        LOG.info("sssssssss");

    }
}
