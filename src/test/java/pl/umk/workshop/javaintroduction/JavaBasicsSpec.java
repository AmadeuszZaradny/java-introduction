package pl.umk.workshop.javaintroduction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.umk.workshop.javaintroduction.domain.*;
import pl.umk.workshop.javaintroduction.domain.models.*;
import pl.umk.workshop.javaintroduction.domain.models.exceptions.NotAllowedOperationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Zadania są od siebie zależne. Należy wykonywać je w kolejnosci.
@SpringBootTest
public class JavaBasicsSpec {

    @Test
        // Zmien klasę MutableUser tak aby test przechodził
        // Tip: HashMap korzysta z metody hashCode oraz equals
        // Komentarz: HashMapa to struktura, która przechowuje dane w parach klucz-wartość, ułatwiając szybkie wyszukiwanie.
        // Metoda `hashCode` jest w niej używana do przypisania każdemu kluczowi unikalnego kodu, który służy do jego umiejscowienia w strukturze.
        // Metoda `equals` pozwala sprawdzić, czy dwa klucze są takie same.
        // Ważne jest to, aby upewnić się, że klucz jest unikalny, bo inaczej może to powodować wycieki pamięci jak w zadaniu.
    void equalsAndHashcode() {
        //given
        HashMap<MutableUser, Integer> userMap = new HashMap<>();

        //when
        for (int i = 0; i < 100; i++) {
            userMap.put(new MutableUser("Jacek" + i, "Kowalski"), i);
        }

        //then
        assertEquals(100, userMap.size());
    }

    @Test
        // Zmien klasę MutableUser oraz sekcję 'when' tak aby test przechodził
        // Tip: setter
        // Komentarz: Obiekt `user` jest mutowalny co oznacza, że po jego utworzeniu możliwa jest modyfikacja pól (danych),
        // które w nim się znajduą. O konsekwencjach takiego podejścia dowiesz się w zadaniu `mutabilityProblem`
    void mutability() {
        //given
        MutableUser user = new MutableUser("Jacek", "Kowalski");

        //when
        user.setSurname("Placek");

        //then
        assertEquals("Jacek", user.getName());
        assertEquals("Placek", user.getSurname());
    }

    @Test
        // Zmieniając sekcje 'when' spraw aby test przeszedł
        // Tip: setter
        // Komentarz: Ze względu na to, że obiekt `user` jest mutowalny istnieje możliwość zmiany jego zawartości podczas
        // działania aplikacji. Jest to szczególnie niebezpieczne w wielowątkowych aplikacjach gdzie kilka operacji może dziać
        // się równolegle. Wyobraź sobię sytuację w której taki obiekt przechowuje informację o ilości pieniędzy, które należy
        // pobrać z konta pewnemu użytkownikowi, wtedy zamiast podmiany nazwiska jak w zadaniu możemy mieć do czynienia z dużymi
        // stratami finansowymi.
    void mutabilityProblem() {
        //given
        MutableUser user = new MutableUser("Jacek", "Kowalski");
        MutableUserManager userManager = new MutableUserManager(user);

        //when
        user.setSurname("Placek");

        //then
        assertEquals("Hello!, I'm Jacek Placek", userManager.introduceUser());
    }

    @Test
        // Zmien klasę ImmutableUser tak aby test przechodził
        // Tip: final keyword
        // Komentarz: Po ustawieniu pól klasy na `final` mamy pewność, że dane, które w nim się znajduą nie będą
        // zmienione podczas działania aplikacji. Taki obiekt może być dostępny dla wielu wątków
        // bez konsekwencji opisanych w zadaniu `mutabilityProblem`.
    void immutability() {
        //given
        ImmutableUser user = new ImmutableUser("Jacek", "Kowalski");
        ImmutableUserManager userManager = new ImmutableUserManager(user);

        //when
        setNameUsingMagic(user);

        //then
        assertEquals("Hello!, I'm Jacek Kowalski", userManager.introduceUser());
    }

    @Test
        // Zmien sekcję 'when' tak aby test przechodził
        // Pamiętaj, że pola klasy są już niemutowalne
        // Tip: operator new
        // Komentarz: Aby "edytować" obiekt musimy uworzyć go od nowa ze zmienionymi danymi.
    void editImmutableField() {
        //given
        ImmutableUser user = new ImmutableUser("Jacek", "Kowalski");
        ImmutableUserManager userManager;

        //when
        userManager = new ImmutableUserManager(new ImmutableUser(user.getName(), "Placek"));

        //then
        assertEquals("Hello!, I'm Jacek Placek", userManager.introduceUser());
    }

    @Test()
        // Odkomentuj sekcję given i expect!!!
        // Zmien klasy Client, Seller i Admin tak aby test przechodził
        // Nie definiuj pól 'name' i 'surname' bezpośrednio w tych klasach
        // Tip: Zerknij do klas PlatformUser i  PlatformFacade
        // Komentarz: Interfejs `PlatformUser` pozwala nam na zdefiniowanie swego rodzaju kontraktu, który musi zostać
        // spełniony przez każdą klasę, która go implementuje. Dzięki temu możemy tworzyć użytkowników, którzy posiadają
        // różne zbiory praw.
    void inheritanceAndPolymorphism() {
        //given

        var client = new Client("Jacek", "Kowalski");
        var seller = new Seller("Jacek", "Kowalski");
        var admin = new Admin("Jacek", "Kowalski");
        PlatformFacade platformFacade = new PlatformFacade();

        //expect
        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(client, "offer-id"));
        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(seller, "offer-id"));
        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(admin, "offer-id"));

        assertThrows(NotAllowedOperationException.class, () -> platformFacade.listOffer(client, "offer-id"));
        assertEquals("You sold an offer: offer-id", platformFacade.listOffer(seller, "offer-id"));
        assertEquals("You sold an offer: offer-id", platformFacade.listOffer(admin, "offer-id"));

        assertThrows(NotAllowedOperationException.class, () -> platformFacade.doMagicWithOffer(client, "offer-id"));
        assertThrows(NotAllowedOperationException.class, () -> platformFacade.doMagicWithOffer(seller, "offer-id"));
        assertEquals("You cast a spell on an offer: offer-id", platformFacade.doMagicWithOffer(admin, "offer-id"));
    }

    private void setNameUsingMagic(ImmutableUser user) {
        try {
            Field privateField = ImmutableUser.class.getDeclaredField("surname");

            if ((privateField.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                return;
            }

            privateField.setAccessible(true);
            privateField.set(user, "Placek");
            privateField.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException("Hacking failed :(");
        }
    }
}
