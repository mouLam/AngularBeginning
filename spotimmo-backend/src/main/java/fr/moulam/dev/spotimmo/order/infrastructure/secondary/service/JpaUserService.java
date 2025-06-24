package fr.moulam.dev.spotimmo.order.infrastructure.secondary.service;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserAddressToUpdate;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserEmail;
import fr.moulam.dev.spotimmo.order.domain.user.infos.UserPublicId;
import fr.moulam.dev.spotimmo.order.domain.user.repository.UserRepository;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.entity.UserEntity;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.mappers.UserMapper;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserService implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Autowired
    public JpaUserService(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (user.getDbId() != null) {
            Optional<UserEntity> userToUpdateOpt = jpaUserRepository.findById(user.getDbId());
            if (userToUpdateOpt.isPresent()) {
                UserEntity userToUpdate = userToUpdateOpt.get();
                userToUpdate.updateFromUser(user);
                jpaUserRepository.saveAndFlush(userToUpdate);
            }
        } else {
            jpaUserRepository.save(userMapper.toEntity(user));
        }
    }

    @Override
    public Optional<User> get(UserPublicId userPublicId) {
        return jpaUserRepository.findOneUserByPublicId(userPublicId.value())
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> getOneByEmail(UserEmail userEmail) {
        return jpaUserRepository.findUserByEmail(userEmail.value())
                .map(userMapper::toDomain);
    }

    @Override
    public void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddress) {
        jpaUserRepository.updateAddress(userPublicId.value(),
                userAddress.userAddress().street(),
                userAddress.userAddress().city(),
                userAddress.userAddress().country(),
                userAddress.userAddress().zipCode());
    }
}
