package pl.umk.workshop.javaintroduction.domain.models;

public final class Seller extends ImmutableUser {

    public Seller(String name, String surname) {
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
}
