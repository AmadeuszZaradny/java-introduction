package pl.umk.workshop.javaintroduction.domain;


import pl.umk.workshop.javaintroduction.domain.models.MutableUser;

public final class MutableUserManager {

    private final MutableUser user;

    public MutableUserManager(MutableUser user) {
        this.user = user;
    }

    public String introduceUser() {
        return String.format("Hello!, I'm %s %s", user.getName(), user.getSurname());
    }

}
