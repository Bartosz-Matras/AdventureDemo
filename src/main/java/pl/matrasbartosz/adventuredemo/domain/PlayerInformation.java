package pl.matrasbartosz.adventuredemo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player_information")
public class PlayerInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player_information")
    private Long idPlayerInformation;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "gold")
    private Integer gold;

    @OneToMany(targetEntity = Hero.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "playerInformation")
    private Set<Hero> heroes;


    public PlayerInformation() {
        this.gold = 0;
    }

    public PlayerInformation(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.gold = 0;
    }

    public void addHero(Hero hero){
        if (heroes == null){
            heroes = new HashSet<>();
        }

        heroes.add(hero);
        hero.setPlayerInformation(this);
    }

    public Long getIdPlayerInformation() {
        return idPlayerInformation;
    }

    public void setIdPlayerInformation(Long idPlayerInformation) {
        this.idPlayerInformation = idPlayerInformation;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public String toString() {
        return "PlayerInformation{" +
                "idPlayerInformation=" + idPlayerInformation +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", gold=" + gold +
                ", heroes=" + heroes +
                '}';
    }
}
