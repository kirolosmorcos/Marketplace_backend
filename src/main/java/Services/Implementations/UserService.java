package Services.Implementations;

import com.example.Market.Place.Models.User;
import Repositories.Interfaces.UserRepository;
import Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

}
