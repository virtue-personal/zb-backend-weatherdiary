package diary.weather.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String weather;

    private String icon;

    private Double temperature;

    private String text;

    private LocalDate date;

    public void setDateWeather(DateWeatherEntity dateWeatherEntity) {
        this.date = dateWeatherEntity.getDate();
        this.weather = dateWeatherEntity.getWeather();
        this.icon = dateWeatherEntity.getIcon();
        this.temperature = dateWeatherEntity.getTemperature();

    }
}
