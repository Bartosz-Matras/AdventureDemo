package pl.matrasbartosz.adventuredemo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quest")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quest")
    private Long idQuest;

    @Column(name = "description")
    private String description;

    @Column(name = "reward")
    private Integer reward;

    @Column(name = "experience")
    private Double experience;

    @Column(name = "length_in_seconds")
    private int lengthInSeconds;

    @Column(name = "started")
    private boolean started = false;

    @Column(name = "completed")
    private boolean completed = false;

    @Column(name = "start_date")
    protected LocalDateTime startDate;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "quest")
    private Hero hero;

    public Quest() {
    }



    public Quest(String description, Integer reward, Double experience, int lengthInSeconds) {
        this.description = description;
        this.reward = reward;
        this.experience = experience;
        this.lengthInSeconds = lengthInSeconds;
    }

    public Long getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(Long idQuest) {
        this.idQuest = idQuest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(int lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        if(started){
            this.startDate = LocalDateTime.now();
        }
        this.started = started;
    }

    public boolean isCompleted() {
        if(this.completed){
            return true;
        }else{
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime questEndDate = this.startDate.plusSeconds(this.lengthInSeconds);

            boolean isAfter = now.isAfter(questEndDate);

            if(isAfter){
                this.completed = true;
            }

            return completed;
        }
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "idQuest=" + idQuest +
                ", description='" + description + '\'' +
                ", reward=" + reward +
                ", experience=" + experience +
                ", lengthInSeconds=" + lengthInSeconds +
                ", started=" + started +
                ", completed=" + completed +
                ", startDate=" + startDate +
                '}';
    }
}
