package pl.umk.workshop.javaintroduction.domain;

import pl.umk.workshop.javaintroduction.domain.models.User;

final public class UserManager {

    private final User user;

    public UserManager(User user) {
        this.user = user;
    }

    public String introduceUser() {
        return String.format("Hello!, I'm %s %s", user.getName(), user.getSurname());
    }

}
