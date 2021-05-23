package wordmem.web.data;

import java.util.List;

import wordmem.web.User;

public interface WordsRepository {

    User save(User user);

    User findAllWords(User user);
}
