package pl.polsl.pp.backapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private UserRepository userRepo;

    public DbSeeder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Users kowalski = new Users("weee@wp.pl", "janek", "janek", "user",
                "active", 0, new Date(), new Date());
        Users nowak = new Users("wefddfee@wp.pl", "karolnowak", "kanowak", "admin",
                "active", 0, new Date(), new Date());
        Users wisnia = new Users("wisnia123@onet.pl", "michalwisnia", "michalwisnia", "user",
                "active", 0, new Date(), new Date());


        //List<Users> yourUsers = Arrays.asList(kowalski,nowak,wisnia);
        this.userRepo.save(kowalski);
        this.userRepo.save(nowak);
        this.userRepo.save(wisnia);
    }


}
