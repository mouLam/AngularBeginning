package fr.moulam.dev.spotimmo.order.domain.user.aggregate;

import fr.moulam.dev.spotimmo.order.domain.user.infos.AuthorityName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    private AuthorityName authorityName;

}
