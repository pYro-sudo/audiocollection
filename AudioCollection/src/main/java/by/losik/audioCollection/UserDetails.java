package by.losik.audioCollection;

import java.util.Date;

public class UserDetails {
    private Date registrationDate;
    private String password;
    private String id;
    private SubscriptionEntity subscription;
    private String type;
    private Settings settings;

    public UserDetails(String id, String password, Date registrationDate, String type, SubscriptionEntity subscription, Settings settings){
        this.type = type;
        this.id = id;
        this.password = password;
        this.registrationDate = registrationDate;
        this.subscription = subscription;
        this.settings = new Settings();
        this.settings.setFont(Fonts.TOYZ);
        this.settings.setTheme(ThemeTypes.LIGHT);
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setPassword(String password1) {
        this.password = password1;
    }

    public String getId() {
        return id;
    }

    public SubscriptionEntity getSubscription() {
        return subscription;
    }

    public SubscriptionEntity setSubscription(SubscriptionEntity subscription) {
        this.subscription = subscription;
        return subscription;
    }

    public String getType() {
        return type;
    }

    public UserType setType(UserType type) {
        this.type = type.name();
        return type;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public String getPassword() {
        return password;
    }
}
