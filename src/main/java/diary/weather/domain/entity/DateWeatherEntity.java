package diary.weather.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "date_weather")
@Schema(description = "날짜별 날씨 데이터 엔티티")
public class DateWeatherEntity {

    @Id
    @Schema(description = "날짜 (PK)", example = "2025-02-28")
    private LocalDate date;

    @Schema(description = "날씨 상태", example = "Cloudy")
    private String weather;

    @Schema(description = "날씨 아이콘", example = "03d")
    private String icon;

    @Schema(description = "기온 (섭씨)", example = "18.7")
    private Double temperature;
}
