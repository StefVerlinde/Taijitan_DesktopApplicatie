package dto;

import domain.User;

import java.util.Date;
import java.util.List;

public class ActivityDTO {
    private int activityId;
    private String name;
    private int type;
    private Date startDate;
    private Date endDate;
    private int score;
    private int maxParticpants;
    private boolean isFull;
    private List<User> users;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxParticpants() {
        return maxParticpants;
    }

    public void setMaxParticpants(int maxParticpants) {
        this.maxParticpants = maxParticpants;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
