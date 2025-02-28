package diary.weather.controller;

import diary.weather.domain.entity.DiaryEntity;
import diary.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 다이어리 관련 API 컨트롤러
 */
@Tag(name = "다이어리 컨트롤러", description = "일기 작성 및 관리 기능을 제공하는 컨트롤러")
@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 새로운 일기를 생성합니다.
     *
     * @param date 생성할 일기의 날짜
     * @param text 일기의 본문 내용
     * @return 생성 완료 메시지
     */
    @Operation(summary = "다이어리 생성", description = "사용자가 입력한 일기 텍스트와 해당 날짜의 날씨 데이터를 기반으로 일기를 생성하여 저장합니다.<br><br>**string**: 저장할 일기의 새로운 내용")
    @PostMapping("/create/diary")
    public ResponseEntity<String> createDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "date", description = "일기를 저장할 날짜", example = "2025-02-26") LocalDate date,

            @RequestBody
            @Parameter(name = "text", description = "저장할 일기의 내용", example = "오늘은 날씨가 맑고 기분이 좋았다.") String text) {
        diaryService.createDiary(date, text);
        return ResponseEntity.ok("일기가 성공적으로 저장되었습니다.");
    }

    /**
     * 특정 날짜의 일기 데이터를 조회합니다.
     *
     * @param date 조회할 날짜
     * @return 해당 날짜의 일기 목록
     */
    @Operation(summary = "단일 날짜 조회", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    public ResponseEntity<List<DiaryEntity>> readDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "date", description = "조회할 날짜", example = "2025-02-26") LocalDate date) {
        return ResponseEntity.ok(diaryService.readDiary(date));
    }

    /**
     * 특정 기간 동안 작성된 모든 일기 데이터를 조회합니다.
     *
     * @param startDate 조회 시작 날짜
     * @param endDate 조회 종료 날짜
     * @return 해당 기간의 일기 목록
     */
    @Operation(summary = "범위 날짜 조회", description = "선택한 기간 내에 작성된 모든 일기 데이터를 반환합니다.")
    @GetMapping("/read/diaries")
    public ResponseEntity<List<DiaryEntity>> readDiaries(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "startDate", description = "조회할 기간의 시작 날짜", example = "2024-12-31") LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "endDate", description = "조회할 기간의 종료 날짜", example = "2025-12-31") LocalDate endDate) {
        return ResponseEntity.ok(diaryService.readDiaries(startDate, endDate));
    }

    /**
     * 특정 날짜의 일기를 수정합니다.
     *
     * @param date 수정할 일기의 날짜
     * @param text 수정된 일기 내용
     * @return 수정 완료 메시지
     */
    @Operation(summary = "일기 수정하기", description = "선택한 날짜의 기존 일기를 새로운 내용으로 수정합니다.<br><br>**string**: 수정된 일기의 새로운 내용")
    @PutMapping("/update/diary")
    public ResponseEntity<String> updateDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "date", description = "수정할 일기의 날짜", example = "2025-02-26") LocalDate date,

            @RequestBody
            @Parameter(name = "text", description = "수정된 일기의 새로운 내용", example = "수정된 일기 내용입니다.") String text) {
        diaryService.updateDiary(date, text);
        return ResponseEntity.ok("일기가 성공적으로 수정되었습니다.");
    }

    /**
     * 특정 날짜의 일기를 삭제합니다.
     *
     * @param date 삭제할 일기의 날짜
     * @return 삭제 완료 메시지
     */
    @Operation(summary = "일기 삭제", description = "선택한 날짜의 일기를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    public ResponseEntity<String> deleteDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(name = "date", description = "삭제할 일기의 날짜", example = "2025-02-26") LocalDate date) {
        diaryService.deleteDiary(date);
        return ResponseEntity.ok("일기가 성공적으로 삭제되었습니다.");
    }
}
