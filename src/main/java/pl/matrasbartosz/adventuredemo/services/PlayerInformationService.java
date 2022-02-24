package pl.matrasbartosz.adventuredemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matrasbartosz.adventuredemo.domain.PlayerInformation;
import pl.matrasbartosz.adventuredemo.repository.PlayerInformationRepository;

@Service
public class PlayerInformationService {

    @Autowired
    PlayerInformationRepository playerInformationRepository;


    public Integer getGold(){
        return playerInformationRepository.getPlayerInformationByIdPlayerInformation(1L).getGold();
    }

    public void setGold(Integer gold){
        PlayerInformation pl = playerInformationRepository.getPlayerInformationByIdPlayerInformation(1L);
        pl.setGold(gold);
        playerInformationRepository.save(pl);
    }
}
