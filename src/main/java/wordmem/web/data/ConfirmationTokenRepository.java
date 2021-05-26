package wordmem.web.data;

import org.springframework.data.repository.CrudRepository;

import wordmem.web.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
