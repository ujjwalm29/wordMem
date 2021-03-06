package wordmem.web.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import wordmem.web.UserWords;

@Repository
public class JdbcWordsRepository implements WordsRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcWordsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(UserWords user) {
        String query = "INSERT INTO words (username,word) VALUES ('" + user.getUsername() + "','" + user.getNewWord()
                + "');";
        jdbc.execute(query);
    }

    @Override
    public UserWords findAllWords(UserWords user) {
        String query = "SELECT word FROM words WHERE username = '" + user.getUsername() + "'";

        List<String> words = jdbc.queryForList(query, String.class);
        user.setWordList(words);
        System.out.println(query + "\n List size = " + words.size());
        return user;
    }

}