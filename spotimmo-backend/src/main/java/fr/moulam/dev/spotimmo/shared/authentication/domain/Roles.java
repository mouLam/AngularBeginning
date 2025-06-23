package fr.moulam.dev.spotimmo.shared.authentication.domain;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

public record Roles(Set<Role> roleSet) {

    public static Roles EMPTY = new Roles(null);

    public Roles(Set<Role> roleSet) {
        this.roleSet = Collections.unmodifiableSet(roleSet);
    }

    public boolean hasRoles() {
        return !roleSet.isEmpty();
    }

    public boolean hasRole(Role role) {
        //TODO : role null
        return this.roleSet.contains(role);
    }

    public Set<Role> get() {
        return roleSet;
    }

    public Stream<Role> toStream() {
        return get().stream();
    }

}
