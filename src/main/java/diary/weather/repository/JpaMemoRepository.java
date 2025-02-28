package diary.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import diary.weather.domain.entity.Memo;

@Repository
public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {

}
