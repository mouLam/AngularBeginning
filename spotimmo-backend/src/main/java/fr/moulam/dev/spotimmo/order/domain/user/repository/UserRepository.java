package fr.moulam.dev.spotimmo.order.domain.user.repository;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserAddressToUpdate;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserEmail;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserPublicId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    void save(User user);

    Optional<User> get(UserPublicId userPublicId);

    Optional<User> getOneByEmail(UserEmail userEmail);

    void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress);
}
