package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Credential;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  ApplicationUserService applicationUserService;

  public Response authenticate(Credential credential) {
    ApplicationUser user = applicationUserService.getApplicationUserByMail(credential.getEmail());
    boolean password_valid = BcryptUtil.matches(credential.getPassword(), user.password);
    if (password_valid){
        String token =
        Jwt.issuer("https://example.com/issuer") 
            .upn("jdoe@quarkus.io") 
            .groups(new HashSet<>(Arrays.asList(user.getRole().getType()))) 
            .expiresIn(Duration.ofHours(12))
            .claim("email", credential.email)
            .sign();
        return Response
            .ok(credential)
            .cookie(new NewCookie("coworking", token))
            .header("Authorization", "Bearer " + token)
            .build();
    } else {
        return Response.status(401).build();
    }
  }
}
