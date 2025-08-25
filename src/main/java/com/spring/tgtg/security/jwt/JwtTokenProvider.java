package com.spring.tgtg.security.jwt;

import com.spring.tgtg.security.CustomUserDetails;
import com.spring.tgtg.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final CustomUserDetailsService userDetailsService;

    private final SecretKey secretKey;
    private final long validityInMilliseconds;

    public JwtTokenProvider(CustomUserDetailsService userDetailsService,
                            @Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.access-token-validity-ms}") long validityInMilliseconds) {
        this.userDetailsService = userDetailsService;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .claim("userName", userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    //Authentication 객체는 Security 가 현재 요청의 신원 + 자격 + 권한 을 표준화 해서 담아두는 객체
    // principal : 누구인가를 의미, 보통 UserDetails 혹은 ID/eamil
    // credentials : 자격을 증명, 비밀번호/토큰 (성공 후 보통 null)
    // authorities : 권한 목록
    public Authentication getAuthentication(String token) {
        String email = getEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        //이 클래스는 "토큰 타입"(인증 요청/ 결과를 담는 그릇)이다. 이름 때문에 "폼 로그인" 같지만, 폼 로그인에 특화된건 아니고 principal/credentials/authorities 를 담을 수 있는 범용 토큰
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    public String getEmail(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
