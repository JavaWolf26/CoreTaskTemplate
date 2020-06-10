package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Random;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 3;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

//    private static final String name = generateString();
//    private static final String lastName = generateString();
//    private static int min = 0;
//    private static int max = 100;
//    private static final byte age = (byte) new Random().nextInt(max - min);

    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser(generateString(), generateString(), (byte) new Random().nextInt(100));
        userService.saveUser(generateString(), generateString(), (byte) new Random().nextInt(100));
        userService.saveUser(generateString(), generateString(), (byte) new Random().nextInt(100));
        userService.saveUser(generateString(), generateString(), (byte) new Random().nextInt(100));
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}