package pl.polsl.pp.backapp.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;
import pl.polsl.pp.backapp.exception.ItemExistsInDatabaseException;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("User of id " + id + " not found"));

        return user;
    }

    public User addUser(User user) {
        if(!this.validateMail(user.getEmail()))
            throw new IllegalArgumentException("Invalid mail format");

        try {
            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw new ItemExistsInDatabaseException("User of id " + user.getId() + " exists in DB");
        }
    }

    public User updateUser(String id, User updatedUser) {
        if(!this.validateMail(updatedUser.getEmail()))
            throw new IllegalArgumentException("Invalid mail format");
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new IdNotFoundInDatabaseException("User of id " + id + " not found");

        try {
            return userRepository.save(updatedUser);
        } catch (RuntimeException e) {
            // TODO make email unique
            throw new ItemExistsInDatabaseException("User with email ( " + updatedUser.getEmail() + ") exists in DB");
        }
    }

    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    private boolean validateMail(String phoneNumber) {
        String patterns = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}" +
                "\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";;

        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(phoneNumber).matches();
    }
}
