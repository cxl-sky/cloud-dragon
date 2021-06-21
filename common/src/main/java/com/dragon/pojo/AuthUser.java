package com.dragon.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Set;

/**
 * @author chenxiaolong
 * @date 2019-09-25 16:12
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends User implements Serializable {

    private static final long serialVersionUID = 5973260445530350343L;

    private Long id;
    private String author;
    private String remark;

    public AuthUser(Long id, String username, String password, boolean enable, Set<GrantedAuthority> authorities) {
        super(username, password, enable, true, true, true, authorities);
        this.id = id;
        this.author = "longlongago";
        this.remark = "Feel the project OK ? Support it with your star. Thanks a lot => https://github.com/cxl-sky/cloud-dragon";
    }
}
