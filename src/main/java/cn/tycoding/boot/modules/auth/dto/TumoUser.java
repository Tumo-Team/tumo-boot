package cn.tycoding.boot.modules.auth.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展SpringSecurity User属性
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class TumoUser extends User {
    private static final long serialVersionUID = -1462618142392426177L;

    @Getter
    private final Long id;

    @Getter
    private final Long deptId;

    public TumoUser(Long id, Long deptId, String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
    }
}
