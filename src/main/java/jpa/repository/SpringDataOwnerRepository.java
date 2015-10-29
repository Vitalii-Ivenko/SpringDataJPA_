package jpa.repository;

import jpa.entity.Owner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
public interface SpringDataOwnerRepository extends PagingAndSortingRepository<Owner, Integer> {
    Owner findOwnerByFirstName(String firstName);

    List<Owner> findByFirstNameLike(String s);

    List<Owner> findByFirstNameEndingWithOrderByAgeAsc(String s);

    List<Owner> findByDevices_NameLike(String name);

    Slice<Owner> findByDevices_PriceLessThan(int price, Pageable pageable);

    List<Owner> findFirst3ByDevices_PriceLessThan(int price);
}
