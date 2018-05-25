package surreyjson.jsondb.service;

import surreyjson.jsondb.repository.UserRepository;

import surreyjson.jsondb.domain.User;
//import com.therealdanvega.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> list(){

        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);

    }

    /*
    public Iterable<User> save(List<User> users) {
        return userRepository.save(users);
    } */


}