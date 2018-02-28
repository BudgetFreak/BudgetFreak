package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;

import java.util.Arrays;
import java.util.List;

class UserTestUtils {

    private UserTestUtils() {
    }

    static List<User> createBobAndJane() {
        return Arrays.asList(
                createBob(),
                createJane()
        );
    }

    private static User createJane() {
        return createUser(2L, "Jane", "$");
    }

    static User createBob() {
        return createUser(1L, "Bob", "€");
    }

    static User createUser(long id, String name, String currency) {
        return new User().setId(id).setName(name).setCurrency(currency);
    }
}
