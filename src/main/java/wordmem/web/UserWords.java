package wordmem.web;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserWords {
    private String username;
    private String email;
    private List<String> wordList;
    private String newWord;
}
