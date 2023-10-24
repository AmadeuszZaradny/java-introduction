package pl.umk.workshop.javaintroduction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Korzystając ze streamów javowych spraw aby testy przeszły
@SpringBootTest
public class StreamsSpec {

    @Test
        // Stwórz nową listę, która nie zawiera trzech pierwszych i ostatnich elementów
    void cutting() {
        //given
        var elements = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //when
        var result = elements.stream()
                .skip(3)
                .limit(4)
                .toList();

        //then
        assertEquals(result, asList(4, 5, 6, 7));
    }

    @Test
        // Zmien pierwszą litere każdego imienia na wielką
        // .map((element)-> element.doSomething())
    void uppercase() {
        //given
        var names = asList("amadeusz", "jakub", "patryk", "mateusz", "dominik");

        //when
        var result = names.stream().map(t -> t.substring(0, 1).toUpperCase() + t.substring(1)).toList();

        //then
        assertEquals(result, asList("Amadeusz", "Jakub", "Patryk", "Mateusz", "Dominik"));
    }

    @Test
        // Utwórz jedną listę z list z sekcji 'given'. W połączonej liście elementy nie powinny się powtarzać.
    void concatenation() {
        //given
        var firstList = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var secondList = asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20);

        //when
        var result = Stream.concat(firstList.stream(), secondList.stream()).distinct().toList();

        //then
        assertEquals(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20), result);
    }

    @Test
        // Wybierz imiona, których długość jest nieparzysta oraz posortuj je od najkrótszego do najdłużeszego
    void namesSorting() {
        //given
        var names = asList("Amadeusz", "Mateusz", "Jakub", "Patryk", "Dominik");

        //when
        var result = names.stream()
                .filter(element -> element.length() % 2 == 1)
                .sorted(Comparator.comparing(String::length))
                .toList();

        //then
        assertEquals(asList("Jakub", "Mateusz", "Dominik"), result);
    }

    @Test
        // Pogrupuj imiona ze względu na pierwszą literę.
        // Oczekiwany output to Mapa gdzie kluczem jest litera a wartością imię
        // Tip: Sprawdź Collectors
    void groupingNames() {
        //given
        var names = asList("Monika", "Jakub", "Mateusz", "Dorota", "Julia", "Mieszko");

        //when
        var result = names.stream()
                .collect(Collectors.groupingBy(element -> element.charAt(0)));

        //then
        assertEquals(asList("Monika", "Mateusz", "Mieszko"), result.get('M'));
        assertEquals(asList("Jakub", "Julia"), result.get('J'));
        assertEquals(asList("Dorota"), result.get('D'));
    }

    @Test
        // Policz sumę wszystkich liczb w liście
    void sumOfNestedLists() {
        //given
        var integers = asList(asList(1, 2, 3), asList(4, 5, 6), asList(7, 8, 9, 10));

        // when
        var result = integers.stream()
                .flatMap(Collection::stream)
                .reduce(Integer::sum)
                .orElse(0);

        //then
        assertEquals(55, result);
    }

    @Test
        // Stwórz listę pierwszych 10 liczb naturalnych podzielnych przez 6
        // Tip: Zerknij na IntStream
    void integersStreams() {
        //given
        var integers = IntStream
                .generate(new AtomicInteger()::getAndIncrement)
                .limit(10)
                .boxed()
                .map(element -> element * 6)
                .toList();

        //expect
        assertEquals(asList(0, 6, 12, 18, 24, 30, 36, 42, 48, 54), integers);
    }

    @Test
        // Policz sumę kwadratów pierwszych 20 kolejnych liczb naturalnych
    void sumOfSquares() {
        //given
        var integer = IntStream
                .generate(new AtomicInteger()::getAndIncrement)
                .limit(20)
                .boxed()
                .map(element -> element * element)
                .reduce(Integer::sum)
                .get();

        //then
        assertEquals(2470, integer);
    }

    @Test
        // Połącz imiona z nazwiskami (pasujące imiona i nazwiska posiadają tą samą pozycje na liście)
        // Tip: Można zacząć od IntStream
    void namesWithSurnames() {
        //given
        var names = asList("Michał", "Zenek", "Maryla", "Ryszard");
        var surnames = asList("Wiśniewski", "Martyniuk", "Rodowicz", "Andrzejewski");

        // when
        var result = IntStream
                .generate(new AtomicInteger()::getAndIncrement)
                .limit(names.size())
                .boxed()
                .map(iterator -> String.format("%s %s", names.get(iterator), surnames.get(iterator)))
                .toList();

        //then
        assertEquals(asList("Michał Wiśniewski", "Zenek Martyniuk", "Maryla Rodowicz", "Ryszard Andrzejewski"), result);
    }

    @Test
        // Wygeneruj listę pierwszych 10 liczb pierwszych
    void primeNumbers() {
        //given
        var primeNumbers = IntStream
                .generate(new AtomicInteger()::getAndIncrement)
                .filter(StreamsSpec::isPrime)
                .limit(10)
                .boxed()
                .toList();

        //then
        assertEquals(asList(2, 3, 5, 7, 11, 13, 17, 19), primeNumbers);
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }
}
