package pl.polsl.pp.backapp.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.polsl.pp.backapp.post.Post;
import pl.polsl.pp.backapp.post.PostRepository;
import pl.polsl.pp.backapp.section.Section;
import pl.polsl.pp.backapp.section.SectionRepository;
import pl.polsl.pp.backapp.topic.Topic;
import pl.polsl.pp.backapp.topic.TopicRepository;
import pl.polsl.pp.backapp.user.UserRepository;
import pl.polsl.pp.backapp.user.User;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {
    private UserRepository userRepo;
    private SectionRepository sectionRepo;
    private TopicRepository topicRepo;
    private PostRepository postRepo;

    public DbSeeder(UserRepository userRepo, SectionRepository sectionRepo,
                    TopicRepository topicRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.sectionRepo = sectionRepo;
        this.topicRepo = topicRepo;
        this.postRepo = postRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // password - Janek
        User kowalski = new User("weee@wp.pl", "janek", "$2y$12$AZ4MPx7noGVFkBHRJ29X5Os7bjt50fIqxt86KMdLh4MJCg7h3OhDi", "USER",
                "active", 0, new Date(), new Date());
        User nowak = new User("wefddfee@wp.pl", "karolnowak", "kanowak", "ADMIN",
                "active", 0, new Date(), new Date());
        User wisnia = new User("wisnia123@onet.pl", "michalwisnia", "michalwisnia", "USER",
                "active", 0, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()));

        userRepo.save(kowalski);
        userRepo.save(nowak);
        userRepo.save(wisnia);

        Post javaPost1 = new Post(nowak, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "Zrob restart");
        Post javaPost2 = new Post(kowalski, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "Nie dziala dalej");

        Post dotNet1 = new Post(kowalski, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "...");
        Post dotNet2 = new Post(wisnia, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "...");
        Post dotNet3 = new Post(kowalski, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "...");

        postRepo.save(javaPost1);
        postRepo.save(javaPost2);
        postRepo.save(dotNet1);
        postRepo.save(dotNet2);
        postRepo.save(dotNet3);

        Topic topicJava = new Topic("Spring boot problem", kowalski, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()),
                "Siema, mam problem ze spring bootem", null);
        Topic topicDotNet = new Topic("Dot net problem", wisnia, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()),
                "Nie dziala mi dot net :(", null);

        topicRepo.save(topicJava);
        topicRepo.save(topicDotNet);

        Section sectionIT = new Section("IT", 0, null, null);
        Section sectionCars = new Section("Cars", 0, null, null);

        sectionRepo.save(sectionIT);
        sectionRepo.save(sectionCars);

    }
}
