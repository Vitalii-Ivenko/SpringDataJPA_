package practice.streams;

import java.util.Optional;

/**
 * Created by Vitalii Ivenko on 21.10.2015.
 */
public class OptionalsPracticeMain {

    public static void main(String[] args) {

        Inner i = new Inner("Foooooooo!");
        Nested n= new Nested(i);
        Outer o = new Outer(n);

        String s = Optional.of(new Outer(null))
                .flatMap(outer -> Optional.ofNullable(outer.nested))
                .flatMap(nested -> Optional.ofNullable(nested.inner))
                .flatMap(inner -> Optional.ofNullable(inner.foo))
                .orElse("Another");

        System.out.println(s);

//        Optional.of(o)
//                .map(Optional::ofNullable)
//                .orElseGet(()-> Optional.of(new Outer(null))).map(Optional::ofNullable).orElseGet(null);

    }
}
class Outer {
    Nested nested;

    public Outer(Nested nested) {
        this.nested = nested;
    }
}

class Nested {
    Inner inner;

    public Nested(Inner inner) {
        this.inner = inner;
    }
}

class Inner {
    String foo;

    public Inner(String foo) {
        this.foo = foo;
    }
}
