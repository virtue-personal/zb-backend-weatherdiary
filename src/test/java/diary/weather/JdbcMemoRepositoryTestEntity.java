package diary.weather;


import diary.weather.domain.entity.MemoEntity;
import diary.weather.repository.JdbcMemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Transactional
class JdbcMemoRepositoryTestEntity {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {

        // given
        MemoEntity newMemoEntity = new MemoEntity(2, "insertMemoTest");

        // when
        jdbcMemoRepository.save(newMemoEntity);

        // then
        Optional<MemoEntity> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insertMemoTest");
    }

    @Test
    void fineAllMemoTest() {
        List<MemoEntity> memoEntityList = jdbcMemoRepository.findAll();
        memoEntityList.forEach(System.out::println);
        assertNotNull(memoEntityList);
    }
}
