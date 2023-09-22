package bitcamp.myapp.service;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.jwt.DefaultSecurityUser;
import bitcamp.myapp.jwt.config.SecurityUser;
import bitcamp.myapp.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private int userNo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //log.info("loadUserByUsername username = {}", username);
        User user = userDao.findBy(Integer.parseInt(userName));
        return new SecurityUser(user);
    }

    public UserDetails loadUserByUserNo(int userNo) throws UsernameNotFoundException {
        User user = userDao.findBy(userNo);
        return new DefaultSecurityUser(user);
    }
}