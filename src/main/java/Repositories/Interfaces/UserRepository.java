package Repositories.Interfaces;

import Models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IBaseRepo<User,Long>{
}
