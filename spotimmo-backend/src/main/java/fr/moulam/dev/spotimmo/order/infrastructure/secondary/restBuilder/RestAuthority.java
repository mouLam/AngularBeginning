package fr.moulam.dev.spotimmo.order.infrastructure.secondary.restBuilder;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.Authority;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record RestAuthority(String name) {

    public static Set<String> fromSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> authority.getAuthorityName().name())
                .collect(Collectors.toSet());
    }

}
