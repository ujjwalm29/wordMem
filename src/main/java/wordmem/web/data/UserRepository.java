package wordmem.web.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wordmem.web.Users;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<Users, String> {
    Users findByEmail(String email);
}
