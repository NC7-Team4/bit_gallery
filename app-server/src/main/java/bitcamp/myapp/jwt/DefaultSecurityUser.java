package bitcamp.myapp.jwt;

import bitcamp.myapp.jwt.config.SecurityUser;
import bitcamp.myapp.vo.User;

public class DefaultSecurityUser extends SecurityUser {

    private int userNo;

    public DefaultSecurityUser(User user) {
        super(user);
        userNo = user.getNo();
    }

    public int getUserNo() {
        return this.userNo;
    }
}
