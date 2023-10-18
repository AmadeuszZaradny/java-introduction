package pl.umk.workshop.javaintroduction.domain;

import pl.umk.workshop.javaintroduction.domain.models.PlatformUser;
import pl.umk.workshop.javaintroduction.domain.models.exceptions.NotAllowedOperationException;

public class PlatformFacade {

    public String buyOffer(PlatformUser user, String offerId) {
        if (user.hasBuyingAbility()) {
            return String.format("You bought an offer: %s", offerId);
        } else {
            throw new NotAllowedOperationException();
        }
    }

    public String listOffer(PlatformUser user, String offerId) {
        if (user.hasSellingAbility()) {
            return String.format("You sold an offer: %s", offerId);
        } else {
            throw new NotAllowedOperationException();
        }
    }
    public String doMagicWithOffer(PlatformUser user, String offerId) {
        if (user.hasMagicAbility()) {
            return String.format("You cast a spell on an offer: %s", offerId);
        } else {
            throw new NotAllowedOperationException();
        }
    }
}

