package diary.weather.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 메모 데이터를 담는 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "MemoDTO", description = "메모 데이터를 제공하는 데이터 전송 객체")
public class MemoDTO {

    @Schema(description = "메모 ID (자동 생성됨)", example = "1")
    private int id;

    @Schema(description = "메모 내용", example = "회의에서 논의한 주요 사항을 정리했다.")
    private String text;
}
