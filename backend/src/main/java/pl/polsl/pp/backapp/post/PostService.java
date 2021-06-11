package pl.polsl.pp.backapp.post;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.auth.UserPrincipal;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;
import pl.polsl.pp.backapp.user.User;
import pl.polsl.pp.backapp.user.UserRepository;

import java.util.Date;


@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPost(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Post of id " + id + " not found"));

        return post;
    }

    public Post addPost(Post post) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userPrincipal.getUsername());

        post.setCreateDate(new Date());
        post.setLastChange(new Date());
        post.setAuthor(user);

        user.incPostsNumber();
        userRepository.save(user);
        return postRepository.save(post);
    }

    public Post updatePost(String id, Post updatedPost) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new IdNotFoundInDatabaseException("Post of id " + id + " not found"));

        post.setLastChange(new Date());
        post.setText(updatedPost.getText());

        return postRepository.save(post);
    }

    public void deletePost(String id) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByLogin(userPrincipal.getUsername());

            user.decPostsNumber();
            userRepository.save(user);

            postRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
