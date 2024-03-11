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
    private final String membId;

    @Getter
    private final String membNm;

//    @Getter
//    private final String nickNm;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(
            String seq,
            String userId,
            String userNm,
            Collection<? extends GrantedAuthority> authorities) {
        this.membId = userId;
        this.seq = seq;
        this.membNm = userNm;
        this.authorities = authorities;
    }

    public static UserPrincipal create(TbCustMasterDto user) {
        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(Role.USER.toString()));
        return new UserPrincipal(
                user.getMembId(),
                user.getId().toString(),
                user.getMembNm() == null ? user.getNickNm() : user.getMembNm(),
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
        return membNm;
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
