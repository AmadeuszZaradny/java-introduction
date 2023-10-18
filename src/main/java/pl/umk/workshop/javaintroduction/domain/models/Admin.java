package pl.umk.workshop.javaintroduction.domain.models;

public final class Admin extends ImmutableUser{

    public Admin(String name, String surname) {
        super(name, surname);
    }

    @Override
    public boolean hasSellingAbility() {
        return true;
    }

    @Override
    public boolean hasBuyingAbility() {
        return true;
    }

    @Override
    public boolean hasMagicAbility() {
        return true;
    }
}
