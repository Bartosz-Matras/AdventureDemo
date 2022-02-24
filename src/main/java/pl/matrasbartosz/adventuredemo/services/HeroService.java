package pl.matrasbartosz.adventuredemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matrasbartosz.adventuredemo.domain.Hero;
import pl.matrasbartosz.adventuredemo.domain.PlayerInformation;
import pl.matrasbartosz.adventuredemo.domain.Quest;
import pl.matrasbartosz.adventuredemo.repository.HeroRepository;
import pl.matrasbartosz.adventuredemo.repository.PlayerInformationRepository;
import pl.matrasbartosz.adventuredemo.repository.QuestRepository;


import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HeroService {


    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Autowired
    QuestRepository questRepository;


    public Set<Hero> getAllPlayerHero(){

        PlayerInformation pl = playerInformationRepository.getPlayerInformationByIdPlayerInformation(1L);

        return pl.getHeroes();
    }

    public void saveHero(Hero hero) {
        PlayerInformation currentPlayerInformation = playerInformationRepository.getPlayerInformationByIdPlayerInformation(1L);
        currentPlayerInformation.addHero(hero);
        playerInformationRepository.save(currentPlayerInformation);
    }


    public void updateHeroLevel(){
        Set<Hero> playerHeroes = heroRepository.getAllByPlayerInformationIdPlayerInformation(1L);

        for (Hero hero : playerHeroes){

            if(hero.getExperience() > hero.getRequireExperience()){
                System.out.println(hero);
                hero.setLevel(hero.getLevel()+1);
                Double expToPass = hero.getExperience() - hero.getRequireExperience();
                hero.setExperience(expToPass);
                hero.setRequireExperience(hero.getRequireExperience() * 1.5);
            }
        }

    }

    public void updateHero(Hero hero) {
        Hero heroByIdHero = heroRepository.getHeroByIdHero(hero.getIdHero());
        heroByIdHero.setQuest(hero.getQuest());
        heroByIdHero.getQuest().setStarted(true);
        heroRepository.save(heroByIdHero);
    }

    public Hero getHeroById(Long heroId) {
        return heroRepository.getHeroByIdHero(heroId);
    }

    public int collectReward() {
        Predicate<Hero> heroPredicate = hero -> {
           if(hero.getQuest() != null){
               return hero.getQuest().isCompleted();
           }else {
               return false;
           }
        };


        int sum = heroRepository.getAllByPlayerInformationIdPlayerInformation(1L).stream()
                .filter(heroPredicate)
                .mapToInt(hero -> hero.getQuest().getReward())
                .sum();



        Set<Hero> heroes = heroRepository.getAllByPlayerInformationIdPlayerInformation(1L).stream()
                .filter(heroPredicate).collect(Collectors.toSet());

        for (Hero hero : heroes){
            hero.setExperience(hero.getExperience() + hero.getQuest().getExperience());
            hero.setQuest(null);
            heroRepository.save(hero);
        }

        return sum;
    }
}
