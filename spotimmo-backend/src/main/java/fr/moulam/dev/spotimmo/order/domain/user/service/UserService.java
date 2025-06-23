package fr.moulam.dev.spotimmo.order.domain.user.service;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserEmail;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserPublicId;
import fr.moulam.dev.spotimmo.order.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByEmail(UserEmail userEmail) {
        return userRepository.getOneByEmail(userEmail);
    }

    public Optional<User> getByPublicId(UserPublicId userPublicId) {
        return userRepository.get(userPublicId);
    }

}
