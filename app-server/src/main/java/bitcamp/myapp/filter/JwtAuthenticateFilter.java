package bitcamp.myapp.filter;

import bitcamp.myapp.jwt.JwtUtil;
import bitcamp.myapp.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@RequiredArgsConstructor
@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(JwtAuthenticateFilter.class);

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthenticateFilter 호출");
        Cookie[] cookies =  request.getCookies();
        Cookie cookie= null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                log.info(c.getName());
                if (c.getName().equals("Authorization")) {
                    cookie = c;
                }
            }

        }

        String authorization = cookie == null ? "" : cookie.getValue();
        //String authorization = request.getHeader("Authorization"); // 헤더 파싱
        String token = "";
        int userNo = -1;
        //log.info("authorization  : " + authorization);

        if (authorization != null && authorization.startsWith("Bearer")) { // Bearer 토큰 파싱
            token = authorization.substring(6); // jwt token 파싱
            //log.info("token : " + token);
            //username = jwtUtil.getUsernameFromToken(token); // username 얻어오기
            userNo = jwtUtil.getUserNoFromToken(token); // userNo 얻어오기
        }
        //log.info("userNo : " + userNo);
        // 현재 SecurityContextHolder 에 인증객체가 있는지 확인
        if (userNo != -1 && SecurityContextHolder.getContext().getAuthentication() == null) {
            //log.info("jwt filter = {}", userNo);
            //log.info("jwt filter = {}", userNo);
            UserDetails userDetails = userDetailsService.loadUserByUserNo(userNo);

            // 토큰 유효여부 확인
            log.info("JWT Filter token = {}", token);
            log.info("userDetails " + userDetails);
            //log.info("JWT Filter userDetails = {}", userDetails.getUsername());
            if (jwtUtil.isValidToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}