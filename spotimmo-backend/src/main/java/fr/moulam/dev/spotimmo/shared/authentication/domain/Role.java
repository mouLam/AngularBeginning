package fr.moulam.dev.spotimmo.shared.authentication.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    ADMIN,
    USER,
    UNKNOWN,
    ANONYMOUS;

    private static final String PREFIX = "ROLE_";
    private static final Map<String, Role> ROLES = buildRoles();

    /**
     * Construire une Map<String, Role> à partir des constantes de l’Enum Role,
     * où la clé est générée par la méthode key(),
     * et la valeur est l’instance Role.
     * "admin" : ROLE_ADMIN,
     * "user" : ROLE_USER
     * @return Map
     */
    private static Map<String, Role> buildRoles() {
        return Stream.of(values()).collect(Collectors.toUnmodifiableMap(Role::key, Function.identity()));
    }

    private String key() {
        return PREFIX + name();
    }

    public static Role getRoleFromRole(String role) {
        //TODO : Handle NPE
        return ROLES.getOrDefault(role, UNKNOWN);
    }

}
