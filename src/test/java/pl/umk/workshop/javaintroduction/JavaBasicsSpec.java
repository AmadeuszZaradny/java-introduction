package pl.umk.workshop.javaintroduction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.umk.workshop.javaintroduction.domain.*;
import pl.umk.workshop.javaintroduction.domain.models.Admin;
import pl.umk.workshop.javaintroduction.domain.models.Client;
import pl.umk.workshop.javaintroduction.domain.models.Seller;
import pl.umk.workshop.javaintroduction.domain.models.User;
import pl.umk.workshop.javaintroduction.domain.models.exceptions.NotAllowedOperationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Zadania są od siebie zależne. Należy wykonywac je w kolejnosci
@SpringBootTest
public class JavaBasicsSpec {

    @Test
        // Zmien klasę User tak aby test przechodził
        // Tip: HashMap korzysta z metody hashCode oraz equals
    void equalsAndHashcode() {
        //given
        HashMap<User, Integer> userMap = new HashMap<>();

        //when
        for (int i = 0; i < 100; i++) {
            userMap.put(new User("Jacek" + i, "Kowalski"), i);
        }

        //then
        assertEquals(100, userMap.size());
    }

    @Test
        // Zmien klasę User oraz sekcję 'when' tak aby test przechodził
        // Tip: setter
    void mutability() {
        //given
        User user = new User("Jacek", "Kowalski");

        //when

        //then
        assertEquals("Jacek", user.getName());
        assertEquals("Placek", user.getSurname());
    }

    @Test
        // Zmieniając sekcje 'when' spraw aby test przeszedł
    void mutabilityProblem() {
        //given
        User user = new User("Jacek", "Kowalski");
        UserManager userManager = new UserManager(user);

        //when

        //then
        assertEquals("Hello!, I'm Jacek Placek", userManager.introduceUser());
    }

    @Test
        // Zmien klasę User tak aby test przechodził
    void immutability() {
        //given
        User user = new User("Jacek", "Kowalski");
        UserManager userManager = new UserManager(user);

        //when
        setNameUsingMagic(user);

        //then
        assertEquals("Hello!, I'm Jacek Kowalski", userManager.introduceUser());
    }

    @Test
        // Zmien sekcję 'when' tak aby test przechodził
        // Pamiętaj, że pola klasy są już niemutowalne
    void editImmutableField() {
        //given
        User user = new User("Jacek", "Kowalski");
        UserManager userManager;

        //when
        userManager = new UserManager(user);

        //then
        assertEquals("Hello!, I'm Jacek Placek", userManager.introduceUser());
    }

    @Test()
        // Odkomentuj sekcję given i expect!!!
        // Zmien klasy Client, Seller i Admin tak aby test przechodził
        // Nie definiuj pól 'name' i 'surname' bezpośrednio w tych klasach
        // Tip: Zerknij do klas PlatformUser i  PlatformFacade
    void inheritanceAndPolymorphism() {
        //given

//        var client = new Client("Jacek", "Kowalski");
//        var seller = new Seller("Jacek", "Kowalski");
//        var admin = new Admin("Jacek", "Kowalski");
//        PlatformFacade platformFacade = new PlatformFacade();

        //expect
//        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(client, "offer-id"));
//        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(seller, "offer-id"));
//        assertEquals("You bought an offer: offer-id", platformFacade.buyOffer(admin, "offer-id"));
//
//        assertThrows(NotAllowedOperationException.class, () -> platformFacade.listOffer(client, "offer-id"));
//        assertEquals("You sold an offer: offer-id", platformFacade.listOffer(seller, "offer-id"));
//        assertEquals("You sold an offer: offer-id", platformFacade.listOffer(admin, "offer-id"));
//
//        assertThrows(NotAllowedOperationException.class, () -> platformFacade.doMagicWithOffer(client, "offer-id"));
//        assertThrows(NotAllowedOperationException.class, () -> platformFacade.doMagicWithOffer(seller, "offer-id"));
//        assertEquals("You cast a spell on an offer: offer-id", platformFacade.doMagicWithOffer(admin, "offer-id"));
    }

    private void setNameUsingMagic(User user) {
        try {
            Field privateField = User.class.getDeclaredField("surname");

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
