package adminski.modul.app.security;

import io.jsonwebtoken.*;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import adminski.modul.app.model.Admin;

@Component
public class TokenUtil {

  private String secret = "k1jh^278#92s-=2s";

  private Long expiration = (30l * 60l * 1000l); // 30 min

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      created = new Date((Long) claims.get("created"));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  public String getAudienceFromToken(String token) {
    String audience;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      audience = (String) claims.get("audience");
    } catch (Exception e) {
      audience = null;
    }
    return audience;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
        .setSigningKey(this.secret.getBytes("UTF-8"))
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  private Date generateCurrentDate() {
    return new Date(System.currentTimeMillis());
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = this.getExpirationDateFromToken(token);
    return expiration.before(this.generateCurrentDate());
  }

  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }


  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("sub", userDetails.getUsername());
    claims.put("created", this.generateCurrentDate());
    return this.generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
      try {
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                  .compact();
      } catch (UnsupportedEncodingException ex) {
          //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
          return Jwts.builder()
                  .setClaims(claims)
                  .setExpiration(this.generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, this.secret)
                  .compact();
      }
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = this.getCreatedDateFromToken(token);
    return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && (!(this.isTokenExpired(token))));
  }

  public String refreshToken(String token) {
    String refreshedToken;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      claims.put("created", this.generateCurrentDate());
      refreshedToken = this.generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    Admin user = (Admin) userDetails; // pretpostavka
    final String username = this.getUsernameFromToken(token);
    final Date created = this.getCreatedDateFromToken(token);
    final Date expiration = this.getExpirationDateFromToken(token);
    return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)));
  }

}