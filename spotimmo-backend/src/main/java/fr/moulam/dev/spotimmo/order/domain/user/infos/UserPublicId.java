package fr.moulam.dev.spotimmo.order.domain.user.infos;

import java.util.UUID;

public record UserPublicId(UUID value) {

    public UserPublicId {
        // TODO : Assert value not null
    }
}
