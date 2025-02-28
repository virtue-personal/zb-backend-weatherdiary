package diary.weather.repository;

import diary.weather.domain.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemoRepository extends JpaRepository<MemoEntity, Integer> {

}
