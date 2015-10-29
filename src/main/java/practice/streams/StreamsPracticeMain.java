package practice.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vitalii Ivenko on 20.10.2015.
 */
public class StreamsPracticeMain {

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

        Map<Integer, List<String>> pf =
                persons
                        .stream()
                        .collect(
                                Collectors.groupingBy(Person::getAge,
                                Collectors.mapping(Person::getName,Collectors.toList())));

        System.out.println(pf);

        pf
                .forEach((f, p) -> {
                            System.out.println("Map.forEach: f - " + f + "  " + p);
                            long count = p.stream()
                                    .collect(Collectors.counting());
                            System.out.println("Total number of elements in " + f + " is " + count);

                        }
                );


//        Device d5 = new Device(90, "Galaxy Note 3", "White", 128, null);
//        Optional<Device> optional = Optional.of(d5);
//        optional.ifPresent(System.out::print);
//        Device d4 = null;
//        Optional<Device> optional2 = Optional.ofNullable(d4);
//        optional2.isPresent();


//        List<Foo> foos = new ArrayList<>();
//
//// create foos
//        IntStream
//                .range(1, 4)
//                .forEach(i -> foos.add(new Foo("Foo" + i)));
//
//// create bars
//        foos.forEach(f ->
//                IntStream
//                        .range(1, 4)
//                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
//
//        foos.stream()
//                .flatMap(f -> {
//                    System.out.println("FlatMap for - " + f.name);
//                    return f.bars.stream();
//                })
//                .forEach(b -> {
//                    System.out.println("ForEach - b.name:  " + b.name);
//                });


//        IntStream
//                .range(0, 10)
//                .map(f -> {
//                    System.out.println("Map - f - " + f);
//                    return f * 2;
//                })
//                .forEach(f -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(f);
//                });

    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
}
