package diary.weather.repository;

import diary.weather.domain.entity.DateWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DateWeatherRepository extends JpaRepository<DateWeatherEntity, LocalDate> {
    List<DateWeatherEntity> findAllByDate(LocalDate date);
}

