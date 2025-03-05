package diary.weather.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
@Schema(description = "일기 엔티티")
public class DiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "일기 ID", example = "1")
    private int id;

    @Schema(description = "날씨 상태", example = "Clear")
    private String weather;

    @Schema(description = "날씨 아이콘", example = "01d")
    private String icon;

    @Schema(description = "기온 (섭씨)", example = "22.5")
    private Double temperature;

    @Schema(description = "일기 내용", example = "오늘은 날씨가 좋았다.")
    private String text;

    @Schema(description = "일기 작성 날짜", example = "2025-02-28")
    private LocalDate date;

    public void setDateWeather(DateWeatherEntity dateWeatherEntity) {
        this.date = dateWeatherEntity.getDate();
        this.weather = dateWeatherEntity.getWeather();
        this.icon = dateWeatherEntity.getIcon();
        this.temperature = dateWeatherEntity.getTemperature();
    }
}
