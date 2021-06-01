package pl.polsl.pp.backapp.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.pp.backapp.user.UserRepository;
import pl.polsl.pp.backapp.user.User;

import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {
    private UserRepository userRepo;

    public DbSeeder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        User kowalski = new User("weee@wp.pl", "janek", "janek", "user",
                "active", 0, new Date(), new Date());
        User nowak = new User("wefddfee@wp.pl", "karolnowak", "kanowak", "admin",
                "active", 0, new Date(), new Date());
        User wisnia = new User("wisnia123@onet.pl", "michalwisnia", "michalwisnia", "user",
                "active", 0, new Date(), new Date());


        //List<Users> yourUsers = Arrays.asList(kowalski,nowak,wisnia);
        this.userRepo.save(kowalski);
        this.userRepo.save(nowak);
        this.userRepo.save(wisnia);
    }


}
