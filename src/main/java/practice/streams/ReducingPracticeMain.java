package practice.streams;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vitalii Ivenko on 21.10.2015.
 */
public class ReducingPracticeMain {

    public static void main(String[] args) {

        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 18),
                        new Person("Mick", 9),
                        new Person("Guy", 34),
                        new Person("Annabel", 3),
                        new Person("Joc", 23));

        persons
                .stream()
                .reduce(((p1, p2) -> p1.getName().length() > p2.getName().length() ? p1 : p2))
                .ifPresent(System.out::println);

        Person result = persons
                .stream()
                .filter(person -> person.getAge() <= 18)
                .reduce(new Person("", 0), ((p1, p2) -> {
                    p1.setAge(p1.getAge() + p2.getAge());
                    p1.setName(p1.getName() + p2.getName());
                    return p1;
                }));

        System.out.println(result);

        Integer ageSum = persons
                .stream()
                .map(person -> person.getAge())
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

        System.out.println(ageSum);


    }
}


