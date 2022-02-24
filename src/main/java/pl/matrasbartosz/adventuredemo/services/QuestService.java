package pl.matrasbartosz.adventuredemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.matrasbartosz.adventuredemo.domain.Hero;
import pl.matrasbartosz.adventuredemo.domain.Quest;
import pl.matrasbartosz.adventuredemo.repository.HeroRepository;
import pl.matrasbartosz.adventuredemo.repository.QuestRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class QuestService {

    private final static Random rand = new Random();


    @Autowired
    QuestRepository questRepository;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    HeroService heroService;

    @Autowired
    PlayerInformationService playerInformationService;


    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public List<Quest> getAllNotStartedQuest(){
        return questRepository.getQuestsByStartedIsFalse();
    }


    @Scheduled(fixedDelayString = "${questCreationDelay}")
    @Transactional
    public void createRandomQuest(){
        List<Quest> quests = questRepository.getQuestsByStartedIsFalse();

        if (quests.size() <= 10){
            Integer randomTime = randomTime();

            Integer randomReward = rand.nextInt(100 - 10) + 10;

            BigDecimal exp = BigDecimal.valueOf((rand.nextDouble(99900.0 - 10.0) + 10.0) + randomTime * 0.05)
                    .setScale(2, RoundingMode.HALF_UP);


            Quest questToSave = new Quest(randomDescriptions(), randomReward, Double.valueOf(String.valueOf(exp)), randomTime);

            questRepository.save(questToSave);
        }
    }

    @Scheduled(fixedDelayString = "${questRemovalDelay}")
    @Transactional
    public void deleteRandomQuest(){
        List<Quest> quests = questRepository.getQuestsByStartedIsFalse();

        if(!quests.isEmpty() && quests.size() <= 10){
            Quest quest = quests.get(rand.nextInt(quests.size()));
            questRepository.deleteQuestsByIdQuest(quest.getIdQuest());
        }
    }

    private String randomDescriptions(){
        List<String> descriptions = new ArrayList<>();
        descriptions.add("Save the princess");
        descriptions.add("Save the cat");
        descriptions.add("Save the dog");
        descriptions.add("Save the cow");
        descriptions.add("Save the farmer");
        descriptions.add("Kill the dragon");
        descriptions.add("Kill the troll");
        descriptions.add("Kill the ork");
        descriptions.add("Kill the bandits");
        descriptions.add("Kill the 4 boars");
        descriptions.add("Kill the bear");
        descriptions.add("Take part in tournament");
        descriptions.add("Release peasants");

        return descriptions.get(rand.nextInt(descriptions.size()));
    }

    private Integer randomTime(){
        List<Integer> times = new ArrayList<>();
        times.add(3);
        times.add(4);
//        times.add(10);
//        times.add(30);
//        times.add(60);
//        times.add(120);
//        times.add(180);
//        times.add(300);
//        times.add(600);
//        times.add(900);
//        times.add(1800);
//        times.add(3600);
//        times.add(7200);
//        times.add(10800);
//        times.add(18000);
//        times.add(43200);
//        times.add(86400);


        return times.get(rand.nextInt(times.size()));
    }

    @Scheduled(fixedDelayString = "${updateData}", initialDelayString = "5000")
    public void isQuestCompleted(){
        Set<Hero> allHeroes = heroRepository.getAllByPlayerInformationIdPlayerInformation(1L);

        allHeroes.forEach(hero -> {
            if(hero.getQuest() != null){
                boolean completed = hero.getQuest().isCompleted();
                if(completed){
                    questRepository.save(hero.getQuest());
                }
            }
        });


        int currentGold = playerInformationService.getGold();

        playerInformationService.setGold(currentGold + heroService.collectReward());


        Set<Quest> quests = questRepository.getQuestsByCompletedIsTrue();
        questRepository.deleteAll(quests);

        heroService.updateHeroLevel();
    }

}
