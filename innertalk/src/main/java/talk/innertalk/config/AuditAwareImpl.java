package talk.innertalk.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import talk.innertalk.service.CustomUserDetail;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication ==null || !authentication.isAuthenticated()){
            return Optional.empty();
        }
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        return Optional.of(userDetail.getId());
    }
}
