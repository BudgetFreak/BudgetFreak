package de.budgetfreak.usermanagement;

import de.budgetfreak.usermanagement.domain.User;

import java.util.Arrays;
import java.util.List;

public class UserTestUtils {

    private UserTestUtils() {
    }

    public static List<User> createBobAndJane() {
        return Arrays.asList(
                createBob(),
                createJane()
        );
    }

    public static User createJane() {
        return createUser(2L, "Jane", "$");
    }

    public static User createBob() {
        return createUser(1L, "Bob", "€");
    }

    public static User createUser(long id, String name, String currency) {
        return new User().setId(id).setName(name).setCurrency(currency);
    }
}
