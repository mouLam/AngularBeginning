package fr.moulam.dev.spotimmo.order.domain.user.infos;

import lombok.Builder;

@Builder
public record UserAddressToUpdate(UserPublicId userPublicId, UserAddress userAddress) {

    public UserAddressToUpdate {
        // TODO : assert args not null
    }
}
