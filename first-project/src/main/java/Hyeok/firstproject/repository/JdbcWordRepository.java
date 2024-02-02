package Hyeok.firstproject.repository;

import Hyeok.firstproject.domain.Word;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcWordRepository implements WordRepository {
    private final DataSource dataSource;
    public JdbcWordRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Word save(Word word) {
        String sql = "insert into word(term,definition) values(?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, word.getTerm());
            pstmt.setString(2, word.getDefinition());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                word.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return word;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Word> findById(Long id) {
        String sql = "select * from word where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Word word = new Word();
                word.setId(rs.getLong("id"));
                word.setTerm(rs.getString("term"));
                word.setDefinition(rs.getString("definition"));
                return Optional.of(word);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<Word> findAll() {
        String sql = "select * from word";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Word> words = new ArrayList<>();
            while(rs.next()) {
                Word word = new Word();
                word.setId(rs.getLong("id"));
                word.setTerm(rs.getString("term"));
                word.setDefinition(rs.getString("Definition"));
                words.add(word);
            }
            return words;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<Word> findByTerm(String term) {
        String sql = "select * from word where term = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, term);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Word word = new Word();
                word.setId(rs.getLong("id"));
                word.setTerm(rs.getString("term"));
                return Optional.of(word);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
