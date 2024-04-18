package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("Nissan Fairlady Z", 1999);
      Car car2 = new Car("Mazda RX-7 Veilside", 1997);
      Car car3 = new Car("Nissan Skyline GT-R R33", 1996);
      Car car4 = new Car("Mitsubishi Lancer Evolution IX", 2006);

      user1.setCar(car4);
      car4.setUser(user1);

      user2.setCar(car3);
      car3.setUser(user2);

      user3.setCar(car2);
      car2.setUser(user3);

      user4.setCar(car1);
      car1.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User driver = userService.findUserByHisCar(car4);
      System.out.println("\nЭту машину водит: " + driver);

      context.close();
   }
}
