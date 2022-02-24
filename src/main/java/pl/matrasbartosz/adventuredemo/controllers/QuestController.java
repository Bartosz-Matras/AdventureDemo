package pl.matrasbartosz.adventuredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.matrasbartosz.adventuredemo.domain.Hero;
import pl.matrasbartosz.adventuredemo.domain.Quest;

import pl.matrasbartosz.adventuredemo.services.HeroService;
import pl.matrasbartosz.adventuredemo.services.QuestService;

import java.util.List;

@Controller
@RequestMapping("/quest")
public class QuestController {

    @Autowired
    QuestService questService;

    @Autowired
    HeroService heroService;

    @GetMapping("/assignQuest")
    public String assignQuest(@RequestParam("heroId") Long heroId, Model model){
        Hero hero = heroService.getHeroById(heroId);
        List<Quest> quests = questService.getAllNotStartedQuest();
        model.addAttribute("quests", quests);
        model.addAttribute("hero", hero);
        return "assignQuest";
    }

    @PostMapping("/assignQuestToHero")
    public String assignQuestToHero(Hero hero){

        heroService.updateHero(hero);

        return "redirect:/hero/getall";
    }

}
