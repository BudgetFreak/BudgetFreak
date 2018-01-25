package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;

import java.util.Arrays;
import java.util.List;

class UserTestUtils {

    private UserTestUtils() {
    }

    static List<User> createBobAndJane() {
        return Arrays.asList(
                new User().setId(1L).setName("Bob").setCurrency("€"),
                new User().setId(2L).setName("Jane").setCurrency("$")
        );
    }
}
