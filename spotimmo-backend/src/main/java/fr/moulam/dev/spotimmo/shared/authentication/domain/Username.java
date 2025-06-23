package fr.moulam.dev.spotimmo.shared.authentication.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public record Username(String username) {

    public Username {
        //TODO : handle null username
    }

    public String get() {
        return username();
    }

    public static Optional<Username> of(String username) {
        return Optional.ofNullable(username).filter(StringUtils::isNotBlank).map(Username::new);
    }
}
