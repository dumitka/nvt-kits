package com.backend.springboot.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import com.backend.springboot.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


//Utility klasa za rad sa JSON Web Tokenima
@Component
public class TokenUtils {
		//Izdavac tokena
		@Value("backend")
		private String APP_NAME;

		//Tajna koju samo backend aplikacija treba da zna kako bi mogla da generise i proveri JWT
		@Value("somesecret")
		public String SECRET;

		//Period vazenja tokena - 30 minuta
		@Value("1800000")
		private int EXPIRES_IN;
		
		//Naziv headera kroz koji ce se prosledjivati JWT u komunikaciji server-klijent
		@Value("Authorization")
		private String AUTH_HEADER;
		
		//Generisemo JWT za WEB klijente
		private static final String AUDIENCE_WEB = "web";

		//Algoritam za potpisivanje JWT
		private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
		

		
		
		// ============= Funkcije za generisanje JWT tokena =============
		//generise token na osnovu prosljedjenog korisnickog imena
		public String generateToken(String username) {
			return Jwts.builder()
					.setIssuer(APP_NAME)
					.setSubject(username)
					.setAudience(generateAudience())
					.setIssuedAt(new Date())
					.setExpiration(generateExpirationDate())
					.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
		}
		

		//funkcija za utvrdjivanje uredjaja za koji se JWT kreira
		private String generateAudience() {
			return AUDIENCE_WEB;
		}

		
		//funkcija za generisanje datuma do kog je JWT token validan + postavljenog naseg vremenskog perioda
		private Date generateExpirationDate() {
			return new Date(new Date().getTime() + EXPIRES_IN);
		}
		
		
		
		
		// ============= Funkcije za citanje informacija iz JWT tokena =============
		//funkcija za preuzimanje JWT tokena iz zahtjeva
		//ukoliko se token ne nalazi u zahtjevu vraca null
		public String getToken(HttpServletRequest request) {
			String authHeader = getAuthHeaderFromHeader(request);
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				return authHeader.substring(7); // preuzimamo samo token (vrednost tokena je nakon "Bearer " prefiksa)
			}
			return null;
		}
		
		
		//funkcija za preuzimanje vlasnika tokena
		public String getUsernameFromToken(String token) {
			String username;
			try {
				final Claims claims = this.getAllClaimsFromToken(token);
				username = claims.getSubject();
			} catch (ExpiredJwtException ex) {
				throw ex;
			} catch (Exception e) {
				username = null;
			}
			return username;
		}

		
		//funkcija za preuzimanje datuma kreiranja tokena
		public Date getIssuedAtDateFromToken(String token) {
			Date issueAt;
			try {
				final Claims claims = this.getAllClaimsFromToken(token);
				issueAt = claims.getIssuedAt();
			} catch (ExpiredJwtException ex) {
				throw ex;
			} catch (Exception e) {
				issueAt = null;
			}
			return issueAt;
		}

		
		//funkcija za preuzimanje informacije o uredjaju iz tokena
		public String getAudienceFromToken(String token) {
			String audience;
			try {
				final Claims claims = this.getAllClaimsFromToken(token);
				audience = claims.getAudience();
			} catch (ExpiredJwtException ex) {
				throw ex;
			} catch (Exception e) {
				audience = null;
			}
			return audience;
		}

		
		//funkcija za preuzimanje datuma do kada token vazi
		public Date getExpirationDateFromToken(String token) {
			Date expiration;
			try {
				final Claims claims = this.getAllClaimsFromToken(token);
				expiration = claims.getExpiration();
			} catch (ExpiredJwtException ex) {
				throw ex;
			} catch (Exception e) {
				expiration = null;
			}
			return expiration;
		}
		
		
		//funkcija za citanje svih podataka iz tokena
		//preuzimanje proizvoljnih podataka je moguce pozivom funkcije claims.get(key)
		private Claims getAllClaimsFromToken(String token) {
			Claims claims;
			try {
				claims = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(token)
						.getBody();
			} catch (ExpiredJwtException ex) {
				throw ex;
			} catch (Exception e) {
				claims = null;
			}
			return claims;
		}
		
	
		
		
		// ============= Funkcije za validaciju JWT tokena =============
		//funkcija za validaciju JWT tokena
		public Boolean validateToken(String token, UserDetails userDetails) {
			//User user = (User) userDetails;
			final String username = getUsernameFromToken(token);
			//final Date created = getIssuedAtDateFromToken(token);
			
			// Token je validan kada:
			return (username != null // korisnicko ime nije null
				&& username.equals(userDetails.getUsername())); // korisnicko ime iz tokena se podudara sa korisnickom imenom koje pise u bazi
				//&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())); // nakon kreiranja tokena korisnik nije menjao svoju lozinku 
		}
		
		//funkcija proverava da li je lozinka korisnika izmenjena nakon izdavanja tokena.
		/*
		private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
			return (lastPasswordReset != null && created.before(lastPasswordReset));
		}*/
		
		
		
		//funkcija za preuzimanje perioda vaÅ¾enja tokena.
		public int getExpiredIn() {
			return EXPIRES_IN;
		}

		//funkcija za preuzimanje sadrÅ¾aja AUTH_HEADER-a iz zahteva
		public String getAuthHeaderFromHeader(HttpServletRequest request) {
			return request.getHeader(AUTH_HEADER);
		}
		
}
