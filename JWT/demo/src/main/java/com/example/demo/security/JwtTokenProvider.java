package com.example.demo.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    private final String JWT_SECRET="NhuNhien";

    private final Long JWT_EXPIRED=6048000000L;

    public String generateToken(CustomUserDetails customUserDetails)
    {
        Date now =new Date();

        Date expiredDate=new Date(now.getTime()+JWT_EXPIRED);

        return Jwts.builder().setSubject(Long.toString(customUserDetails.user.getId())).setExpiration(expiredDate).setIssuedAt(now).signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();

    }
    public Long getUserIdFromJWT(String token)
    {
        Claims claims= Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    public boolean validateToken(String authenToken)
    {
        try{
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authenToken);
            return true;
        }
        catch (MalformedJwtException ex)
        {
            log.error("Invalid Jwt token");
        }
        catch (ExpiredJwtException ex)
        {
            log.error("Expired JWT Token");
        }
        catch (UnsupportedJwtException ex)
        {
            log.error("Unsupported JWT Token");
        }
        catch (IllegalArgumentException ex)
        {
            log.error("Jwt claims is empty");
        }
        return false;
    }

}
