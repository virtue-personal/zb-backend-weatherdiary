package diary.weather.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "date_weather")
public class DateWeatherEntity {
    @Id
    private LocalDate date;
    private String weather;
    private String icon;
    private Double temperature;

}
