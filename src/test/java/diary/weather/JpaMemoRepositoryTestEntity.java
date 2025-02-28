package diary.weather;

import diary.weather.domain.entity.MemoEntity;
import diary.weather.repository.JpaMemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTestEntity {
    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        MemoEntity newMemoEntity = new MemoEntity(10, "this is jpa memo");

        // when
        jpaMemoRepository.save(newMemoEntity);

        // then
        List<MemoEntity> memoEntityList = jpaMemoRepository.findAll();
        memoEntityList.forEach(System.out::println);
        assertTrue(memoEntityList.size() > 0);
    }

    @Test
    void findById() {
        // given
        MemoEntity newMemoEntity = new MemoEntity(11, "jpa");

        // when
        MemoEntity memoEntity = jpaMemoRepository.save(newMemoEntity);
        System.out.println(memoEntity.getId());

        // then
        Optional<MemoEntity> result = jpaMemoRepository.findById(memoEntity.getId());
        assertEquals(result.get().getText(), "jpa");
    }
}
