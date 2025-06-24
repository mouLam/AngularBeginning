package fr.moulam.dev.spotimmo.order.application;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserAddressToUpdate;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserEmail;
import fr.moulam.dev.spotimmo.order.domain.user.repository.UserRepository;
import fr.moulam.dev.spotimmo.order.domain.user.service.UserService;
import fr.moulam.dev.spotimmo.order.domain.user.service.UserSynchronizer;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.service.kinde.KindeService;
import fr.moulam.dev.spotimmo.shared.authentication.application.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

    private final UserSynchronizer userSynchronizer;
    private final UserService userService;

    @Autowired
    public UsersApplicationService(UserRepository userRepository, KindeService kindeService) {
        this.userSynchronizer = new UserSynchronizer(userRepository, kindeService);
        this.userService = new UserService(userRepository);
    }

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync) {
        userSynchronizer.syncWithIdp(jwtToken, forceResync);
        return userService.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return userService.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional
    public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
        userSynchronizer.updateAddress(userAddressToUpdate);
    }
}
