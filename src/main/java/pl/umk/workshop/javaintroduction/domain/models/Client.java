package pl.umk.workshop.javaintroduction.domain.models;

public final class Client extends User {

    public Client(String name, String surname) {
        super(name, surname);
    }

    @Override
    public boolean hasBuyingAbility() {
        return true;
    }
}
