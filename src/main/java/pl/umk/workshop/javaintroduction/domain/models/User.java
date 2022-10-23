package pl.umk.workshop.javaintroduction.domain.models;

import java.util.Objects;

public class User extends PlatformUser {

    private String name;
    private String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }
}
