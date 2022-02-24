package pl.matrasbartosz.adventuredemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matrasbartosz.adventuredemo.domain.Quest;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    List<Quest> getQuestsByStartedIsFalse();
    void deleteQuestsByIdQuest(Long idQuest);
    Set<Quest> getQuestsByCompletedIsTrue();

}
