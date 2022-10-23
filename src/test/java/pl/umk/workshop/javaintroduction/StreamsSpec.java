package pl.umk.workshop.javaintroduction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
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
        var result = elements;

        //then
        assertEquals(result, asList(4, 5, 6, 7));
    }

    @Test
        // Zmien pierwszą litere każdego imienia na wielką
    void uppercase() {
        //given
        var names = asList("amadeusz", "jakub", "patryk", "mateusz", "dominik");

        //when
        var result = names;

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
        var result = emptyList();

        //then
        assertEquals(result, asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20));
    }

    @Test
        // Wybierz imiona, których długość jest nieparzysta oraz posortuj je od najkrótszego do najdłużeszego
    void nameSorting() {
        //given
        var names = asList("Amadeusz", "Mateusz", "Jakub", "Patryk", "Dominik");

        //when
        var result = names;

        //then
        assertEquals(result, asList("Jakub", "Mateusz", "Dominik"));
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
                .collect(Collectors.groupingBy(e -> e.charAt(0)));

        //then
        assertEquals(result.get('M'), asList("Monika", "Mateusz", "Mieszko"));
        assertEquals(result.get('J'), asList("Jakub", "Julia"));
        assertEquals(result.get('D'), asList("Dorota"));
    }

    @Test
        // Policz sumę wszystkich liczb w liście
    void sumOfNestedLists() {
        //given
        var integers = asList(asList(1, 2, 3), asList(4, 5, 6), asList(7, 8, 9, 10));

        // when
        var result = integers;

        //then
        assertEquals(result, 55);
    }

    @Test
        // Stwórz listę pierwszych 10 liczb naturalnych podzielnych przez 6
        // Tip: Zerknij na IntStream
    void integersStreams() {
        //given
        var integers = emptyList();

        //expect
        assertEquals(integers, asList(0, 6, 12, 18, 24, 30, 36, 42, 48, 54));
    }

    @Test
        // Policz sumę kwadratów pierwszych 20 kolejnych liczb naturalnych
    void sumOfSquares() {
        //given
        var integer = emptyList();

        //then
        assertEquals(integer, 2470);
    }

    @Test
        // Połącz imiona z nazwiskami (pasujące imiona i nazwiska posiadają tą samą pozycje na liście)
        // Tip: Można zacząć od IntStream
    void namesWithSurnames() {
        //given
        var names = asList("Michał", "Zenek", "Maryla", "Ryszard");
        var surnames = asList("Wiśniewski", "Martyniuk", "Rodowicz", "Andrzejewski");

        // when
        var result = names;

        //then
        assertEquals(result, asList("Michał Wiśniewski", "Zenek Martyniuk", "Maryla Rodowicz", "Ryszard Andrzejewski"));
    }

    @Test
        // Wygeneruj listę pierwszych 10 liczb pierwszych
    void primeNumbers() {
        //given
        var primeNumbers = emptyList();

        //then
        assertEquals(primeNumbers, asList(0, 1, 2, 3, 5, 7, 11, 13, 17, 19));
    }
}
