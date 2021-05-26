package wordmem.web.data;

import wordmem.web.UserWords;

public interface WordsRepository {

    void save(UserWords user);

    UserWords findAllWords(UserWords user);
}