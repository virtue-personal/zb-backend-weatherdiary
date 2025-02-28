package diary.weather.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "memo")
@Schema(description = "메모 엔티티")
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "메모 ID", example = "1")
    private int id;

    @Schema(description = "메모 내용", example = "중요한 일정 메모")
    private String text;
}
