package diary.weather.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

/**
 * 다이어리 데이터를 담는 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "DiaryDTO", description = "일기 데이터를 제공하는 데이터 전송 객체")
public class DiaryDTO {

    @Schema(description = "일기 ID (자동 생성됨)", example = "1")
    private int id;

    @Schema(description = "날씨 상태", example = "맑음")
    private String weather;

    @Schema(description = "OpenWeatherAPI에서 제공하는 날씨 아이콘 코드", example = "01d")
    private String icon;

    @Schema(description = "기온 (섭씨)", example = "18.3")
    private Double temperature;

    @Schema(description = "일기 본문", example = "오늘은 햇살이 따뜻해서 기분이 좋았다.")
    private String text;

    @Schema(description = "일기 작성 날짜 (ISO 8601 형식)", example = "2025-02-26")
    private LocalDate date;
}
