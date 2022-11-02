package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        User userOne = new User("Alain", "Aspect", (byte) 75);
        User userTwo = new User("Syukuro", "Manabe", (byte) 91);
        User userThree = new User("Reinhard ", "Genzel", (byte) 70);
        User userFour = new User("Roger", "Penrose", (byte) 91);

        userService.saveUser(userOne.getName(), userOne.getLastName(), userOne.getAge());
        System.out.println("User с именем – " + userOne.getName() + " добавлен в базу данных");

        userService.saveUser(userTwo.getName(), userTwo.getLastName(), userTwo.getAge());
        System.out.println("User с именем – " + userTwo.getName() + " добавлен в базу данных");

        userService.saveUser(userThree.getName(), userThree.getLastName(), userThree.getAge());
        System.out.println("User с именем – " + userThree.getName() + " добавлен в базу данных");

        userService.saveUser(userFour.getName(), userFour.getLastName(), userFour.getAge());
        System.out.println("User с именем – " + userFour.getName() + " добавлен в базу данных");

        String allUsers = userService.getAllUsers().toString();
        System.out.println(allUsers);

        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection(UserDaoJDBCImpl.connection);


    }

}

