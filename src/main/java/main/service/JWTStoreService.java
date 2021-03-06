package main.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import main.exception.SystemException;
import main.domain.JWTCredential;
import main.security.KeyGenerator;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Thom van de Pas on 23-4-2018
 */
@Stateless
public class JWTStoreService {

    private static final Instant CURRENT_TIME = Instant.now();
    private static final Instant EXPIRED_TIME = CURRENT_TIME.plus(3, ChronoUnit.MINUTES);

    @Inject
    KeyGenerator keyGenerator;

    public String generateToken(final String username, final List<String> groupNames) {
        try {
            String secretKey = this.keyGenerator.generateKey();

            // Create HMAC signer
            JWSSigner signer = new MACSigner(secretKey);

            // Prepare JWT with claims set
            JWTClaimsSet.Builder claimSet = new JWTClaimsSet.Builder();

            claimSet.issuer("swhp");
            claimSet.subject(username);
            claimSet.audience("JavaEE Soteria JWT"); // your application
            claimSet.issueTime(Date.from(CURRENT_TIME));
            claimSet.expirationTime(Date.from(EXPIRED_TIME));

            JSONArray groupValues = new JSONArray();
            groupValues.addAll(groupNames);

            Map<String, Object> groups = new HashMap<>();
            groups.put("groups", groupValues);

            claimSet.claim("realm_access", groups);

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimSet.build());

            // apply the HMAC protection
            signedJWT.sign(signer);

            // serialize the compact form
            return signedJWT.serialize();

        } catch (KeyLengthException ex) {
            throw new SystemException(ex.getMessage());
        } catch (JOSEException ex) {
            throw new SystemException(ex.getMessage());
        }
    }

    public JWTCredential getCredential(String token) {
        try {
            String secretKey = this.keyGenerator.generateKey();

            SignedJWT signedJWT = SignedJWT.parse(token);

            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            JWSVerifier verifier = new MACVerifier(secretKey);

            if (!signedJWT.verify(verifier)) {
                throw new SystemException("Not Verified");
            }

            if (!isTokenTimeValid(claimsSet.getIssueTime(), claimsSet.getExpirationTime())) {
                // TODO: Give proper message to clinet / mapping the exception to be able show the message.
                throw new SystemException("Expired Token");
            }

            JSONObject realmAccess = (JSONObject) claimsSet.getClaim("realm_access");
            JSONArray groupArray = (JSONArray) realmAccess.get("groups");

            Set<String> groups = new HashSet<>();
            groupArray.forEach(g -> groups.add(g.toString()));

            return new JWTCredential(claimsSet.getSubject(), groups);

        } catch (ParseException | JOSEException ex) {
            throw new SystemException(ex.getMessage());
        }
    }

    protected static boolean isTokenTimeValid(final Date creation, final Date expiration) {
        Date now = new Date();

        return creation.before(now) && now.before(expiration);
    }
}
