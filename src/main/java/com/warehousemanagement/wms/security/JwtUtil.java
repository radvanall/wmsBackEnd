package com.warehousemanagement.wms.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String[] extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        String claimRoles=(String) claims.get("roles");
        claimRoles=claimRoles.replace("[","").replace("]","");
        String[] roleNames=claimRoles.split(",");
        return roleNames;

//        String subject=(String)claims.get(Claims.SUBJECT);
//        String[] roles=subject.split(",");
//        List<String> roles = (List<String>) claims.get("roles");

//        return roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities().toString());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, NewUserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//        final String username = extractUsername(token);
//        final String[] rolesFromToken = extractRoles(token);
//        for (String aRoleName:rolesFromToken){
//            userDetails.addRole(aRoleName);
//        }
//        final List<GrantedAuthority> rolesFromUserDetails = (List<GrantedAuthority>) userDetails.getAuthorities();
//        return (username.equals(userDetails.getUsername()) &&
//                !isTokenExpired(token) &&
//                rolesFromToken.containsAll(rolesFromUserDetails));
//    }
    }
}