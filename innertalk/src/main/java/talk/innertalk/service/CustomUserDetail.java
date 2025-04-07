package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import talk.innertalk.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
public class CustomUserDetail implements UserDetails {

    private final Member member;


    //현재 user의 role을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority("ROLE_" + member.getMemberRole()));
        return collection;
    }

    //user의 id 값 반환 (pk)
    public Long getId(){
        return member.getId();
    }

    // user의 비밀번호 반환
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // user의 username 반환
    @Override
    public String getUsername() {
        return member.getEmail();
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
