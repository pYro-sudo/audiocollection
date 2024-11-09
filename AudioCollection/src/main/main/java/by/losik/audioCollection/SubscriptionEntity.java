package by.losik.audioCollection;

import java.util.Date;

public class SubscriptionEntity {
    private String subscriptionType;
    private Date beginningDate;
    private Date expirationDate;

    public SubscriptionEntity(String subscriptionType, Date beginningDate, Date expirationDate){
        this.beginningDate = beginningDate;
        this.subscriptionType = subscriptionType;
        this.expirationDate = expirationDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(Subscription subscriptionType) {
        this.subscriptionType = subscriptionType.name();
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
