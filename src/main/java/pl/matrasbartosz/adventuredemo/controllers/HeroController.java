package pl.matrasbartosz.adventuredemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.matrasbartosz.adventuredemo.domain.Hero;
import pl.matrasbartosz.adventuredemo.domain.PlayerInformation;
import pl.matrasbartosz.adventuredemo.repository.HeroRepository;
import pl.matrasbartosz.adventuredemo.repository.PlayerInformationRepository;
import pl.matrasbartosz.adventuredemo.services.HeroService;
import pl.matrasbartosz.adventuredemo.services.PlayerInformationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    PlayerInformationService playerInformationService;

    @Autowired
    HeroService heroService;

    @GetMapping("/getall")
    public String getHeroes(Model model){
        model.addAttribute("heroes", heroService.getAllPlayerHero());
        model.addAttribute("gold", playerInformationService.getGold());
        return "heroes";
    }

    @GetMapping("/newhero")
    public String createHero(Model model){
        model.addAttribute("hero", new Hero());
        return "heroform";
    }

    @PostMapping("/createhero")
    public String saveHero(@Valid Hero hero, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "heroform";
        }else{
            heroService.saveHero(hero);
            return "redirect:/hero/getall";
        }
    }


}
