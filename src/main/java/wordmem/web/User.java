package wordmem.web;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private String username;
    private List<String> wordList;
    private String newWord;
}
