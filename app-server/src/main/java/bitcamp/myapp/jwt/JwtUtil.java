package bitcamp.myapp.jwt;

import bitcamp.myapp.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // 설정파일로 빼서 환경변수로 사용하는 것을 권장
    private final String SECRET = "rutyweorituwyerotiuweyrtoiuweyrtoweiurtywoeighdfsojkghsdfgsdofiguwyertouw";

    /**
     * 토큰 생성
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put("userNo", ((DefaultSecurityUser)userDetails).getUserNo());
        return createToken(claims, userDetails.getUsername()); // username을 subject로 해서 token 생성
    }

    private String createToken(Claims claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1시간
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 토큰 유효여부 확인
     */
    public Boolean isValidToken(String token, UserDetails userDetails) {
        //log.info("isValidToken token = {}", token);
        int userNo = (Integer)(getUserNoFromToken(token));
        return (userNo == (((DefaultSecurityUser)userDetails).getUserNo()) && !isTokenExpired(token));
    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token) {
        //log.info("getAllClaims token = {}", token);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 username 가져오기
     */
    public int getUserNoFromToken(String token) {
        //log.info("getUsernameFormToken subject = {}", username);
        return (Integer)(getAllClaims(token).get("userNo"));
    }

    /**
     * 토큰 만료기한 가져오기
     */
    public Date getExpirationDate(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * 토큰이 만료되었는지
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}