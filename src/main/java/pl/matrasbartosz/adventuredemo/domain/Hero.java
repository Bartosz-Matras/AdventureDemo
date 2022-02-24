package pl.matrasbartosz.adventuredemo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hero")
    private Long idHero;

    @NotNull
    @Size(min=2, max=40)
    @Column(name = "name")
    private String name;

    @Column(name = "health")
    private Double health;

    @Column(name = "damage")
    private Double damage;

    @Column(name = "level")
    private Integer level;

    @Column(name = "experience")
    private Double experience;

    @Transient
    private Double requireExperience;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_player_information")
    private PlayerInformation playerInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_quest")
    private Quest quest;

    public Hero() {
        this.health = 100.0;
        this.damage = 10.0;
        this.level = 1;
        this.experience = 0.0;
        this.requireExperience = 800.0;
    }

    public Hero(String name) {
        this.name = name;
        this.health = 100.0;
        this.damage = 10.0;
        this.level = 1;
        this.experience = 0.0;
        this.requireExperience = 800.0;
    }



    public Long getIdHero() {
        return idHero;
    }

    public void setIdHero(Long idHero) {
        this.idHero = idHero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getRequireExperience() {
        return requireExperience;
    }

    public void setRequireExperience(Double requireExperience) {
        this.requireExperience = requireExperience;
    }

    public PlayerInformation getPlayerInformation() {
        return playerInformation;
    }

    public void setPlayerInformation(PlayerInformation playerInformation) {
        this.playerInformation = playerInformation;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }


    @Override
    public String toString() {
        return "Hero{" +
                "idHero=" + idHero +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", level=" + level +
                ", experience=" + experience +
                ", requireExperience=" + requireExperience +
                ", playerInformation=" + playerInformation +
                ", quest=" + quest +
                '}';
    }
}
