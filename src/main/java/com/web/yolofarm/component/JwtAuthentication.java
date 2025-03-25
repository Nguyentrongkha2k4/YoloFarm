package com.web.yolofarm.component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.web.yolofarm.dto.response.AuthenticationResponse;
import com.web.yolofarm.entity.User;
import com.web.yolofarm.exception.AppException;
import com.web.yolofarm.exception.ErrorCode;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthentication {
    @Value("${jwt.secret_key}")
    String SECRET_KEY;
    @Value("${jwt.expirationtime}")
    int expirationTime;

    public AuthenticationResponse authenticated(String userPassword, User user){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(userPassword, user.getPassword());
        if (!authenticated){
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        String token;
        try {
            token = generateToken(user);
        } catch (JOSEException e) {
            throw new AppException(ErrorCode.FAILED_GENERATE_TOKEN);
        }
        return AuthenticationResponse.builder().token(token).build();
    }

    String generateToken(User user) throws KeyLengthException, JOSEException{
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                    .subject(user.getUsername())
                                    .issuer("dev")
                                    .issueTime(new Date())
                                    .expirationTime(
                                        new Date(Instant.now().plus(expirationTime, ChronoUnit.SECONDS).toEpochMilli())
                                    )
                                    .claim("scope", user.getRole())
                                    .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jsonObject = new JWSObject(jwsHeader, payload);

        jsonObject.sign(new MACSigner(SECRET_KEY.getBytes()));

        return jsonObject.serialize();
    }


    SignedJWT parSignedJWT(String token) throws ParseException{
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT;
    }

    // public IntrospectResponse introspect(String token){
    //     try{

    //         JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());
            
    //         SignedJWT signedJWT = parSignedJWT(token);
            
    //         Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            
    //         String role = signedJWT.getJWTClaimsSet().getClaim("role").toString();

    //         var verifier = signedJWT.verify(jwsVerifier);
    //         return IntrospectResponse.builder().authenticated(verifier && expirationTime.after(new Date())).role(role).build();

    //     }catch (JOSEException e){
    //         throw new AppException(ErrorCode.VERIFY_TOKEN_EXCEPTION);
    //     } catch (ParseException e){
    //         throw new AppException(ErrorCode.TOKEN_INVALID);
    //     } catch (Exception e){
    //         throw new AppException(ErrorCode.UNCATEGORIED_EXCEPTION);
    //     }
    // }
    
    // public String getUsernameFromToken(String token){
    //     try{
    //         SignedJWT signedJWT = parSignedJWT(token);
            
    //         String username = signedJWT.getJWTClaimsSet().getSubject();

    //         return username;
    //     } catch (ParseException e){
    //         throw new AppException(ErrorCode.TOKEN_INVALID);
    //     } catch (Exception e){
    //         throw new AppException(ErrorCode.UNCATEGORIED_EXCEPTION);
    //     }
    // }

}
