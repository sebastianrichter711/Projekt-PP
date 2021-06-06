package pl.polsl.pp.backapp.auth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.ItemExistsInDatabaseException;
import pl.polsl.pp.backapp.user.User;
import pl.polsl.pp.backapp.user.UserService;

import java.util.Collection;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtToken;
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtToken,
                                    UserPrincipalDetailsService userPrincipalDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userPrincipalDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register")
    public User registerNewUser(@RequestBody RegisterForm registerForm) {
        try {
            return userService.addUser(registerForm);
        } catch (ItemExistsInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public Collection<SimpleGrantedAuthority> authorities() {
        return (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
