package fr.moulam.dev.spotimmo.order.domain.user.aggregate;

import fr.moulam.dev.spotimmo.order.domain.user.infos.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long dbId;

    private UserLastname lastname;

    private UserFirstname firstname;

    private UserEmail email;

    private UserPublicId userPublicId;

    private UserImageUrl imageUrl;

    private Set<Authority> authorities;

    private UserAddress userAddress;

    private Instant createdDate;

    private Instant lastModifiedDate;

    private Instant lastSeen;

    public void initFieldForSignup() {
        this.userPublicId = new UserPublicId(UUID.randomUUID());
    }

    public void updateFromUser(User user) {
        this.email = user.email;
        this.imageUrl = user.imageUrl;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
    }

    public static User buildUserFromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
        UserBuilder userBuilder = new UserBuilder();

        if(attributes.containsKey("preferred_email")) {
            userBuilder.email(new UserEmail(attributes.get("preferred_email").toString()));
        }

        if(attributes.containsKey("last_name")) {
            userBuilder.lastname(new UserLastname(attributes.get("last_name").toString()));
        }

        if(attributes.containsKey("first_name")) {
            userBuilder.firstname(new UserFirstname(attributes.get("first_name").toString()));
        }

        if(attributes.containsKey("picture")) {
            userBuilder.imageUrl(new UserImageUrl(attributes.get("picture").toString()));
        }

        if(attributes.containsKey("last_signed_in")) {
            userBuilder.lastSeen(Instant.parse(attributes.get("last_signed_in").toString()));
        }

        Set<Authority> authorities = rolesFromAccessToken
                .stream()
                .map(authority -> Authority.builder().authorityName(new AuthorityName(authority)).build())
                .collect(Collectors.toSet());

        userBuilder.authorities(authorities);


        return userBuilder.build();
    }
}
