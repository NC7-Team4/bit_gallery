package bitcamp.myapp.controller;

import bitcamp.myapp.App;
import bitcamp.myapp.filter.JwtAuthenticateFilter;
import bitcamp.myapp.service.CustomUserDetailsService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import bitcamp.myapp.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

  Logger log = LoggerFactory.getLogger(AuthController.class);


  {
    log.info("AuthController 생성됨!");
  }

  @Autowired
  UserService userService;

  @Autowired
  UserDetailsService userDetailsService;

  @GetMapping("form")
  public void form(@CookieValue(required = false) String email, Model model) {

    model.addAttribute("email", email);
  }

  @PostMapping("join")
  public boolean join(@RequestBody User user) throws Exception {
//    if (userService.checkLoginIdDuplicate(user.getNo())) {
//      return false;
//    }

    if (userService.add(user) >= 1) {
      return true;
    } else {
      return false;
    }
  }

  @PostMapping("/login1")
  public String login1(     String email,
                            String password,
                           HttpServletResponse response,
                            Model model) throws Exception {
    User user = userService.get(email, password);
    if (user == null) {
      model.addAttribute("refresh", "2;url=form");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    JwtUtil jwtUtil = new JwtUtil();

    String jwtToken = jwtUtil
            .generateToken
                    (((CustomUserDetailsService)userDetailsService)
                            .loadUserByUserNo(user.getNo()));

    Cookie cookie = new Cookie("Authorization", "Bearer" + jwtToken);
    cookie.setMaxAge(3600); // 토큰의 만료 시간 설정 (예: 1시간)
    cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
    response.addCookie(cookie);

    App.loginUserMap.put(user.getNo(),user.getNo());
    log.info(jwtToken);

    return "redirect:/";

  }

  @PostMapping("login")
  public String login(
          String email,
          String password,
          String saveEmail,
          HttpSession session,
          Model model,
          HttpServletResponse response) throws Exception {

    if (saveEmail != null) {
      Cookie cookie = new Cookie("email", email);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    User loginUser = userService.get(email, password);
    if (loginUser == null) {
      model.addAttribute("refresh", "2;url=form");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }


  static class LoginRequest{
    private String username;
    private String password;

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }


  static class LoginSuccessResponse {
    private String token;

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }
  }
}
