package chapter7.collection;

import chapter7.model.User;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionContentTest {

    @Test
    void shouldReturnUsersPhone() {
        User user = new User();
        user.addPhone("123 456 789");
        Collection<String> phone = user.getPhones();

        assertThat(phone).containsExactly("123 456 789");
    }
}
