package fr.moulam.dev.spotimmo.order.domain.user.infos;

import lombok.Builder;

@Builder
public record UserAddress(String street, String city, String zipCode, String country) {

    public UserAddress {
        // TODO : Assert args not null
    }
}
