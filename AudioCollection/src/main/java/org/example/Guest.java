package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Guest extends User {
    private UserDetails userDetails;


    public Guest(String id, String password, Date registrationDate, UserType type, SubscriptionEntity subscription, Settings settings) {
        super(id, password, registrationDate, type.name(), subscription, settings);
    }
}
