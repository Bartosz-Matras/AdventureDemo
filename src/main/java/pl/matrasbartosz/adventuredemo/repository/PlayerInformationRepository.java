package pl.matrasbartosz.adventuredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matrasbartosz.adventuredemo.domain.PlayerInformation;

@Repository
public interface PlayerInformationRepository extends JpaRepository<PlayerInformation, Long> {

    PlayerInformation getPlayerInformationByIdPlayerInformation(Long id);

}
