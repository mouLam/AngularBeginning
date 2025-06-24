package fr.moulam.dev.spotimmo.order.infrastructure.secondary.mappers;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.User;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = AuthorityMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "dbId", target = "id"),
            @Mapping(source = "userPublicId.value", target = "publicId"),
            @Mapping(source = "email.value", target = "email"),
            @Mapping(source = "firstname.value", target = "firstName"),
            @Mapping(source = "lastname.value", target = "lastName"),
            @Mapping(source = "imageUrl.value", target = "imageURL"),
            @Mapping(source = "userAddress.street", target = "addressStreet"),
            @Mapping(source = "userAddress.city", target = "addressCity"),
            @Mapping(source = "userAddress.zipCode", target = "addressZipCode"),
            @Mapping(source = "userAddress.country", target = "addressCountry")
    })
    UserEntity toEntity(User user);

    @InheritInverseConfiguration
    User toDomain(UserEntity userEntity);

    Set<UserEntity> toEntitySet(List<User> users);

    Set<User> toDomainSet(List<UserEntity> userEntity);


}
