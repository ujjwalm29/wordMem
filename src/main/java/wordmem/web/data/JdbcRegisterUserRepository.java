package wordmem.web.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import wordmem.web.Users;

@Repository
public class JdbcRegisterUserRepository implements RegisterUserRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcRegisterUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void saveUser(Users user) {
        String query = "INSERT INTO users (username,email,password,enabled) VALUES ('" + user.getUsername() + "','"
                + user.getEmail() + "','" + user.getPassword() + "',FALSE);";
        jdbc.execute(query);
        query = "INSERT INTO authorities (username,authority) VALUES ('" + user.getUsername() + "','ROLE_USER');";
        jdbc.execute(query);
        System.out.println("User Registered " + query);
    }

}
