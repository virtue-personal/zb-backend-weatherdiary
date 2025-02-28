package diary.weather.repository;

import diary.weather.domain.entity.MemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MemoEntity save(MemoEntity memoEntity) {
        String sql = "insert into memo values(?,?)";
        jdbcTemplate.update(sql, memoEntity.getId(), memoEntity.getText());
        return memoEntity;
    }

    public List<MemoEntity> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Optional<MemoEntity> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    private RowMapper<MemoEntity> memoRowMapper() {
        return (rs, rowNum) -> new MemoEntity(
                rs.getInt("id"),
                rs.getString("text")
        );
    }

}
