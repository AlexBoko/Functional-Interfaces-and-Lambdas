import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
public class Main {

    static int number = 0;
    /** Задание 1

     Напишите реализации функционального интерфейса Predicate, которые проверяют, является ли число положительным.
     Если число положительное, то предикат должен возвращать true, в противном случае — false. Реализуйте Predicate
     в двух вариантах:
     через анонимный класс,
     через лямбду.
*/
     public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 5, -5, -1, -7, 8);

        Predicate<Integer> thePositivityOfTheNumber = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        };
        for (Integer integer : numbers) {
            System.out.println(thePositivityOfTheNumber.test(integer));
        }

        System.out.println("=====================================================");

        Predicate<Integer> thePositivityOfTheNumber2 = y -> y > 0;
        for (Integer integer : numbers) {
            System.out.println(thePositivityOfTheNumber2.test(integer));
        }
        System.out.println("======================================================");

        /** Задание 2
         Создайте функциональный интерфейс Consumer, который принимает на вход имя человека
         и выводит в консоль приветствие в его адрес.
         Реализуйте его в двух вариантах: через анонимный класс и через лямбду.
         */

        List<PersonName> nameList = Arrays.asList(
                new PersonName("Cергей"),
                new PersonName("Дмитрий")
        );

        Consumer<PersonName> greeting = new Consumer<PersonName>() {
            @Override
            public void accept(PersonName personName) {
                System.out.println("Добрый день " + personName.getName());
            }
        };
        for (PersonName personName : nameList) {
            greeting.accept(personName);
        }
        System.out.println("=====================================================");

        Consumer<PersonName> greeting2 = s -> System.out.println("Добрый вечер " + s.getName());
        for (PersonName personName : nameList) {
            greeting2.accept(personName);
        }
        System.out.println("======================================================");

        /** Задание 3

         Реализуйте функциональный интерфейс Function, который принимает на вход вещественное число типа
         Double, а возвращает его округленный вариант типа Long.
         Реализуйте его в двух вариантах: через анонимный класс и через лямбду.
         */

        List<Double> numbers1 = Arrays.asList(1.2, 2.3, 3.3, 1.8, 3.4, 3.5, 12.2);

        Function<Double, Long> converter = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        for (Double aDouble : numbers1) {
            System.out.println(converter.apply(aDouble));
        }
        System.out.println("======================================================");

        Function<Double, Long> converter2 = Math::round;

        for (Double aDouble : numbers1) {
            System.out.println(converter2.apply(aDouble));
        }
        System.out.println("=======================================================");

        /** Задание 4

         Напишите Supplier, который возвращает случайное число из диапазона от 0 до 100.
         Реализуйте его в двух вариантах: через анонимный класс и через лямбду.
         */

        Random r = new Random();
        Supplier<Integer> theReturnee = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return r.nextInt(100);
            }
        };
        System.out.println(theReturnee.get());
        System.out.println("=========================================================");

        /** Задание 5 (опциональное)

         Теперь попрактикуемся в комбинировании нескольких функций в одну сложную конструкцию. Для примера построим следующую комбинацию.
         Дан предикат condition и две функции: ifTrue и ifFalse. Напишите метод ternaryOperator, который из предиката и двух функций построит новую функцию,
         возвращающую значение функции ifTrue, если предикат выполнен, а в остальных случаях —ifFalse.
         */
        Function<Integer, Integer> converter3 = Math::round;
        Function<Integer, Double> converter4 = g -> Math.abs(2.5);

        System.out.println(ternaryOperator(thePositivityOfTheNumber, converter3, converter4).apply(-2));
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return h -> condition.test(h) ? ifTrue.apply(h) : ifFalse.apply(h);
    }
}