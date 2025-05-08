package DAL_Layer.Repositories.Interfaces;

import DAL_Layer.Models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IBaseRepo<User,Long>{
}
