package pl.matrasbartosz.adventuredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matrasbartosz.adventuredemo.domain.Hero;

import java.util.Set;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    Hero getHeroByIdHero(Long id);
    Set<Hero> getAllByPlayerInformationIdPlayerInformation(Long id);
}
