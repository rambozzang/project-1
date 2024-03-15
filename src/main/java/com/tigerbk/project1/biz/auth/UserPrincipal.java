package com.tigerbk.project1.biz.auth;


import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.enums.Role;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class UserPrincipal implements UserDetails {

    @Getter
    private final String seq;

    @Getter
    private final String custId;

    @Getter
    private final String custNm;

//    @Getter
//    private final String nickNm;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(
            String seq,
            String userId,
            String userNm,
            Collection<? extends GrantedAuthority> authorities) {
        this.custId = userId;
        this.seq = seq;
        this.custNm = userNm;
        this.authorities = authorities;
    }

    public static UserPrincipal create(TbCustMasterDto user) {
        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(Role.USER.toString()));
        return new UserPrincipal(
                user.getCustId(),
                user.getId().toString(),
                user.getCustNm() == null ? user.getNickNm() : user.getCustNm(),
                authorities);
    }

    public String getId() {
        return "";
    }

    public String getEmail() {
        return "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return custNm;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
