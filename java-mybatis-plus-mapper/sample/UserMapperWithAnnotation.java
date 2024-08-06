import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapperWithAnnotation extends BaseMapper<User> {
    @Select("SELECT * FROM users WHERE age = #{age} AND name = #{name} AND email = #{email}")
    List<User> selectUsersByMultipleFields(int age, String name, String email);

    @Select("SELECT * FROM ${tableName} WHERE age = #{age}")
    List<User> selectUsersByTableName(String tableName, int age);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteUserById(Long id);

    @Update("UPDATE users SET email = #{email} WHERE id = ${id}")
    int updateUserEmailById(Long id, String email);

    @Insert("INSERT INTO users (name, age, email) VALUES (#{name}, #{age}, #{email})")
    int insertUser(String name, int age, String email);

    @Select("SELECT * FROM users WHERE email LIKE CONCAT('%', #{email}, '%')")
    List<User> findUsersByEmail(String email);

    // 动态 SQL 使用例子
    @Select("<script>" +
            "SELECT * FROM users " +
            "<where> " +
            "   <if test='name != null'> AND name = #{name} </if>" +
            "   <if test='email != null'> AND email = #{email} </if>" +
            "</where>" +
            "</script>")
    List<User> findUsersByOptionalCriteria(@Param("name") String name, @Param("email") String email);

    // 批量删除
    @Delete("<script>" +
            "DELETE FROM users WHERE id IN " +
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>" +
            "   #{id}" +
            "</foreach>" +
            "</script>")
    int deleteUsersByIds(@Param("ids") List<Long> ids);

    // 更新多个字段
    @Update("UPDATE users SET age = #{age}, email = #{email} WHERE id = #{id}")
    int updateUserById(Long id, int age, String email);
}
