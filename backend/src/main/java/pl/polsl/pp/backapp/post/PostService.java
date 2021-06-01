package pl.polsl.pp.backapp.post;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;
import pl.polsl.pp.backapp.topic.Topic;
import pl.polsl.pp.backapp.topic.TopicRepository;

import java.util.Optional;


@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
        return postRepository.save(post);
    }

    public Post updatePost(String id, Post updatedPost) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty())
            throw new IdNotFoundInDatabaseException("Post of id " + id + " not found");

        return postRepository.save(updatedPost);
    }

    public void deletePost(String id) {
        try {
            postRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
