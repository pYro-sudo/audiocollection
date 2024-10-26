package org.example;

import java.util.Date;
import java.util.List;

public class Admin extends User {
    private UserDetails userDetails;
    private List<User> bufferedUsers;
    private Subscription subscription = Subscription.ADMIN;
    private SubscriptionEntity subscriptionEntity;
    private AcquiringAudioManager acquiringAudioManager;

    public Admin(String id, String password, Date registrationDate, UserType type, SubscriptionEntity subscription, Settings settings) {
        super(id, password, registrationDate, type.name(), subscription, settings);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<User> getBufferedUsers() {
        return bufferedUsers;
    }

    public void setBufferedUsers(List<User> bufferedUsers) {
        this.bufferedUsers = bufferedUsers;
    }

    public SubscriptionEntity getSubscriptionEntity() {
        return subscriptionEntity;
    }

    public void setSubscriptionEntity(SubscriptionEntity subscriptionEntity) {
        this.subscriptionEntity = subscriptionEntity;
    }

    public AcquiringAudioManager getAcquiringAudioManager() {
        return acquiringAudioManager;
    }

    public void setAcquiringAudioManager(AcquiringAudioManager acquiringAudioManager) {
        this.acquiringAudioManager = acquiringAudioManager;
    }
}
