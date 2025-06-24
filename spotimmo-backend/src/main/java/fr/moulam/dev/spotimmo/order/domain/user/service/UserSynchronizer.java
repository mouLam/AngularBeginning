package fr.moulam.dev.spotimmo.order.domain.user.service;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserAddressToUpdate;
import fr.moulam.dev.spotimmo.order.domain.user.repository.UserRepository;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.service.kinde.KindeService;
import fr.moulam.dev.spotimmo.shared.authentication.application.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserSynchronizer {

    private static final String UPDATE_AT_KEY = "last_signed_in";

    private final UserRepository userRepository;
    private final KindeService kindeService;

    @Autowired
    public UserSynchronizer(UserRepository userRepository, KindeService kindeService) {
        this.userRepository = userRepository;
        this.kindeService = kindeService;
    }

    /**
     * Synchronize user information between Kinde and database.
     * @param jwtToken token come from Kinde
     * @param forceResync force resynchronise
     */
    public void syncWithIdp(Jwt jwtToken, boolean forceResync) {
        Map<String, Object> claims = jwtToken.getClaims();
        List<String> rolesFromToken = AuthenticatedUser.extractRolesFromToken(jwtToken);
        Map<String, Object> userInfo = kindeService.getUserInfo(claims.get("sub").toString());
        User user = User.buildUserFromTokenAttributes(userInfo, rolesFromToken);
        Optional<User> existingUser = userRepository.getOneByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (claims.get(UPDATE_AT_KEY) != null) {
                Instant lastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
                Instant idpModifiedDate = Instant.ofEpochSecond((Integer) claims.get(UPDATE_AT_KEY));

                if (idpModifiedDate.isAfter(lastModifiedDate) || forceResync) {
                    updateUser(user, existingUser.get());
                }
            }
        } else {
            user.initFieldForSignup();
            userRepository.save(user);
        }

    }

    private void updateUser(User user, User existingUser) {
        existingUser.updateFromUser(user);
        userRepository.save(existingUser);
    }

    public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
        userRepository.updateAddress(userAddressToUpdate.userPublicId(), userAddressToUpdate);
    }
}
