package diary.weather.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

/**
 * 날짜별 날씨 정보를 담는 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "DateWeatherDTO", description = "날짜별 날씨 정보를 제공하는 데이터 전송 객체")
public class DateWeatherDTO {

    @Schema(description = "날짜", example = "2025-02-26")
    private LocalDate date;

    @Schema(description = "날씨 상태 (맑음, 흐림, 비 등)", example = "맑음")
    private String weather;

    @Schema(description = "OpenWeatherAPI에서 제공하는 날씨 아이콘 코드", example = "01d")
    private String icon;

    @Schema(description = "기온 (섭씨)", example = "15.5")
    private Double temperature;
}
