package pl.polsl.pp.backapp.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.pp.backapp.user.User;
import pl.polsl.pp.backapp.user.UserRepository;

import java.util.Date;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public UserPrincipalDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepo.findByLogin(s).orElseThrow(() -> new UsernameNotFoundException("Username " + s + " not found"));
        user.setLastLogin(new Date());
        userRepo.save(user);
        return new UserPrincipal(user);
    }
}
