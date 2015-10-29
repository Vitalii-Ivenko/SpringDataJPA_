import entity.Device;
import entity.Owner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.OwnerRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitalii Ivenko on 16.10.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-mongo-config.xml")
@Transactional
public class SpringDataMongoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataMongoTest.class);

    @Autowired
    private OwnerRepository ownerRepository;

    @Before
    @Rollback(false)
    public void setUp(){
        Owner o1 = new Owner("John", "Martin", 45);
        Device d1 = new Device(600, "iPhone 6", "Grey", 16);
        Device d2 = new Device(350, "iPad Mini", "Grey", 64);
        Set<Device> devices = new HashSet<Device>();
        devices.add(d1);
        devices.add(d2);
        o1.setDevices(devices);
        Owner o2 = new Owner("Mary", "Jane", 18);
        Device d3 = new Device(800, "iPhone 6+", "Grey", 64);
        Device d4 = new Device(1250, "MacBook", "White", 256);
        Set<Device> devicesO2 = new HashSet<Device>();
        devicesO2.add(d3);
        devicesO2.add(d4);
        o2.setDevices(devicesO2);

        Owner o3 = new Owner("Malcolm", "Davis", 25);
        Device d5= new Device(600, "iPhone 5s", "Black", 64);
        Set<Device> devicesO3 = new HashSet<Device>();
        devicesO3.add(d5);
        o3.setDevices(devicesO3);

        ownerRepository.save(Arrays.asList(o1, o2, o3));
        LOGGER.info("-----------Creating all---------------");
    }

    @After
    public void clean() {
        LOGGER.info("-----------Cleaning all---------------");
        ownerRepository.deleteAll();
    }

    @Test
    public void testCount() {
        LOGGER.info("-------Getting count--------");
        long count = ownerRepository.count();
        LOGGER.info("-------Result is " + count + "-----------");
        Assert.assertEquals(count, 3);
    }

    @Test
    public void testDeleteOwner() {
        LOGGER.info("----------Delete Owner---------------");
        Owner malcolm = ownerRepository.findOwnerByFirstName("Malcolm");
        ownerRepository.delete(malcolm);
        Owner afterDelete = null;
        try {
            afterDelete = ownerRepository.findOwnerByFirstName("Malcolm");
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("----------There is no such Owner---------------");
        }
        Assert.assertNull(afterDelete);
    }

    @Test
    public void testFindByName() {
        LOGGER.info("----------Find Owner by firstName---------------");
        Owner malcolm = ownerRepository.findOwnerByFirstName("Malcolm");
        Assert.assertNotNull(malcolm);
        Assert.assertEquals("Davis", malcolm.getLastName());
    }

    @Test
    public void testFindByFirstNameEndingWith() {
        LOGGER.info("----------Find Owner by firstName EndingWith---------------");
        List<Owner> owners = ownerRepository.findByFirstNameEndingWithOrderByAgeAsc("hn");
        for (Owner owner : owners) {
            LOGGER.info(owner.toString());
        }
        Assert.assertEquals(1, owners.size());
    }


    @Test
    public void testFindByFirstNameLike() {
        LOGGER.info("----------Find Owner by firstName Like---------------");
        List<Owner> owners = ownerRepository.findByFirstNameLike("Ma");
        for (Owner owner : owners) {
            LOGGER.info(owner.toString());
        }
        Assert.assertEquals(2, owners.size());
    }



    @Test
    public void testSaveOwner() {
        LOGGER.info("----------Save Owner---------------");
        long countBefore = ownerRepository.count();
        Owner barry = new Owner("Barry","Allen",60);
        ownerRepository.save(barry);

        long countAfter = ownerRepository.count();

        Assert.assertEquals(countAfter, ++countBefore);
    }


    @Test
    public void testFindByDevices() {
        LOGGER.info("----------Find Owner By devices---------------");

        Owner john = ownerRepository.findOwnerByFirstName("John");
        final String testDeviceName = "UniqueDevice";
        final Device device = new Device(300,testDeviceName,"Green",256);
        john.getDevices().add(device);
        List<Owner> owners = ownerRepository.findByDevices_NameLike(testDeviceName);
//        owners.forEach(s -> {if(s.getFirstName().equals("John")){Assert.assertTrue(s.getDevices().contains(device));}});
    }
//
    @Test
    @Rollback(false)
    public void testFindAll() {
        LOGGER.info("----------Find All---------------");

        Owner o1 = new Owner("Abadon", "Rik",22);
        Owner o2 = new Owner("Brandon", "Fetch",25);
        Owner o3 = new Owner("William", "May",55);
        ownerRepository.save(Arrays.asList(o1, o2, o3));
        List<Owner> owners  = (List<Owner>) ownerRepository.findAll(new Sort(Sort.Direction.ASC, "lastName"));
        for (Owner owner : owners) {
            LOGGER.info(owner.toString());
        }
        Assert.assertFalse(owners.isEmpty());
        Assert.assertEquals(ownerRepository.count(), 6l);
    }


    @Test
    public void testFindByPrice() {
        LOGGER.info("----------Find By Price---------------");

        Owner o1 = new Owner("Abadon", "Rik",22);
        Device d3 = new Device(90, "iPhone 6+", "Grey", 64);
        Device d4 = new Device(90, "iPod", "White", 64);
        Set<Device> devices = new HashSet<Device>();
        devices.add(d3);
        devices.add(d4);
        o1.setDevices(devices);

        Owner o2 = new Owner("Brandon", "Fetch",25);
        Device d1 = new Device(90, "Nexxus 5", "Grey", 128);
        Device d2 = new Device(90, "Galaxy Note 3", "White", 128);
        Set<Device> devices2 = new HashSet<Device>();
        devices2.add(d1);
        devices2.add(d2);
        o2.setDevices(devices);


        Owner o3 = new Owner("William", "May",55);
        Device d5 = new Device(90, "Galaxy Note 3", "White", 128);
        Set<Device> devices3 = new HashSet<Device>();
        devices3.add(d5);
        o3.setDevices(devices3);

        ownerRepository.save(Arrays.asList(o1, o2, o3));

        final PageRequest page1 = new PageRequest(
                0, 3, Sort.Direction.ASC, "firstName"
        );


//        Slice<Owner> slice = ownerRepository.findByDevices_PriceLessThan(1000, page1);
//        Assert.assertEquals(slice.getContent().size(),3);
    }
}

