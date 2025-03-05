package diary.weather.repository;

import diary.weather.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {
    List<DiaryEntity> findAllByDate(LocalDate date);

    List<DiaryEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    DiaryEntity getFirstByDate(LocalDate date);

    @Transactional
    void deleteAllByDate(LocalDate date);
}
