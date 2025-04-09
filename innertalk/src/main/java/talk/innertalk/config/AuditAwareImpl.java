package talk.innertalk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import talk.innertalk.service.CustomUserDetail;

import java.util.Optional;

@Slf4j
public class AuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        log.info("👀 getCurrentAuditor() 호출됨!");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(0L);
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetail userDetail) {
            return Optional.of(userDetail.getId());
        }

        return Optional.empty(); // anonymousUser거나 예상치 못한 principal 타입일 경우
    }
}
