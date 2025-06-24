package fr.moulam.dev.spotimmo.order.infrastructure.secondary.mappers;

import fr.moulam.dev.spotimmo.order.domain.user.aggregate.Authority;
import fr.moulam.dev.spotimmo.order.infrastructure.secondary.entity.AuthorityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Mapping(source = "authorityName.name", target = "name")
    AuthorityEntity toEntity(Authority authority);

    @InheritInverseConfiguration
    Authority toDomain(AuthorityEntity authority);

    Set<AuthorityEntity> toEntitySet(List<Authority> authorities);

    Set<Authority> toDomainSet(List<AuthorityEntity> authorityEntities);
}
