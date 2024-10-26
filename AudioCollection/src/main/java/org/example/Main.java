package org.example;

import java.sql.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Searcher searcher = new Searcher();
        User user;
        String username = new String();
        String password = new String();
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username");
            username = scanner.nextLine();
            System.out.println("Enter password");
            password = scanner.nextLine();
            if(!searcher.authorised(username,password)){
                System.out.println("Wrong credentials\nMaybe register?[yes/no]");
                String temporary = scanner.nextLine();
                switch (temporary){
                    case "yes":
                        Date expirationDate = new Date();
                        expirationDate.setYear(3000);
                        user = new User(username,password, new Date(), UserType.USER.name(),new SubscriptionEntity(Subscription.NONE.name(),new Date(),expirationDate),new Settings());
                        user.setUserDetails(searcher.insertIntoUserDetails(new UserDetails(username,password, new Date(), UserType.USER.name(),new SubscriptionEntity(Subscription.NONE.name(),new Date(),expirationDate),new Settings())));
                        break;
                    case "no":
                        System.out.println("Then as a Guest?[yes/anything]");
                        String option = scanner.nextLine();
                        if (option.equals("yes")){
                            Date expirationDateGuest = new Date();
                            expirationDateGuest.setYear(3000);
                            searcher.insertIntoUserDetails(new UserDetails(username,password, new Date(), UserType.GUEST.name(),new SubscriptionEntity(Subscription.NONE.name(),new Date(),expirationDateGuest),new Settings()));
                        }
                        else{
                            System.out.println("try again then");
                        }
                        break;
                    default:
                        System.out.println("Could not comprehend");
                        break;
                }
            }
            else{
                try {
                    user = new User(username,password,searcher.getUserDetails(username,password).getDate("registrationDate"),searcher.getUserDetails(username,password).getString("type"),
                            new SubscriptionEntity(searcher.getSubscription(username).getString("subscriptionType"),searcher.getSubscription(username).getDate("beginningDate"), searcher.getSubscription(username).getDate("expirationDate")),new Settings());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
        Manager manager = new Manager();
        manager.setSearcher(searcher);
        manager.UserOperations(user);
    }


}