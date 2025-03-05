package diary.weather.repository;

import diary.weather.domain.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemoRepository extends JpaRepository<MemoEntity, Integer> {}
