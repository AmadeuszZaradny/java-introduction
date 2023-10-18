package pl.umk.workshop.javaintroduction.domain;


import pl.umk.workshop.javaintroduction.domain.models.ImmutableUser;

public final class ImmutableUserManager {

    private final ImmutableUser user;

    public ImmutableUserManager(ImmutableUser user) {
        this.user = user;
    }

    public String introduceUser() {
        return String.format("Hello!, I'm %s %s", user.getName(), user.getSurname());
    }

}
