package pl.matrasbartosz.adventuredemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.matrasbartosz.adventuredemo.domain.Hero;
import pl.matrasbartosz.adventuredemo.domain.PlayerInformation;
import pl.matrasbartosz.adventuredemo.domain.Quest;
import pl.matrasbartosz.adventuredemo.repository.HeroRepository;
import pl.matrasbartosz.adventuredemo.repository.PlayerInformationRepository;
import pl.matrasbartosz.adventuredemo.repository.QuestRepository;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {


    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PlayerInformationRepository playerInformationRepository;

    @Autowired
    QuestRepository questRepository;


    @Override
    public void run(String... args) throws Exception {

        PlayerInformation playerInformation = new PlayerInformation("123", "123");
        playerInformationRepository.save(playerInformation);

//        PlayerInformation playerInformation2 = new PlayerInformation("1234", "1234");
//        playerInformationRepository.save(playerInformation2);

        Hero hero = new Hero("arek");
        Hero hero2 = new Hero("adam");
        Hero hero3 = new Hero("bartek");

        playerInformation.addHero(hero);
        playerInformation.addHero(hero2);
        playerInformation.addHero(hero3);

        playerInformationRepository.save(playerInformation);


//        Quest quest1 = new Quest("quest 1", 100, 110.0, 10);
//        Quest quest2 = new Quest("quest 2",200, 120.0, 20);
//        Quest quest3 = new Quest("quest 3",300, 130.0, 30);

//        quest1.setStarted(true);
//        quest2.setStarted(true);
//
//        questRepository.save(quest1);
//        questRepository.save(quest2);
//        questRepository.save(quest3);

//
//        Optional<PlayerInformation> playerInformation1 = playerInformationRepository.findById(1L);
//        System.out.println(playerInformation1.get().getHeroes());






//        System.out.println();
//        System.out.println(pl.getHeroes());

//        Optional<PlayerInformation> byId = playerInformationRepository.findById(1L);

//        byId.ifPresent(information -> System.out.println(information.getHeroes()));

//        System.out.println(pl.getHeroes());

    }
}
