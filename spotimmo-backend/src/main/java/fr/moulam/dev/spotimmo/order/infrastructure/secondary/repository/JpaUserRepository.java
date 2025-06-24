package fr.moulam.dev.spotimmo.order.infrastructure.secondary.repository;

import fr.moulam.dev.spotimmo.order.infrastructure.secondary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

    List<UserEntity> findUsersByPublicIdIn(List<UUID> publicIds);

    Optional<UserEntity> findOneUserByPublicId(UUID publicId);

    @Modifying
    @Query("UPDATE UserEntity  user " +
            "SET user.addressStreet = :street, user.addressCity = :city, " +
            " user.addressCountry = :country, user.addressZipCode = :zipCode " +
            "WHERE user.publicId = :userPublicId")
    void updateAddress(UUID userPublicId, String street, String city, String country, String zipCode);

}
