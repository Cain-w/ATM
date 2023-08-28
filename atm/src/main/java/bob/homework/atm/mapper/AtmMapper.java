package bob.homework.atm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AtmMapper {
    @Select("SELECT * From atm")
    List<Map<String, Object>> getAllAtm();

    @Update("UPDATE atm SET status = #{status}, bankcardId = #{bankCardId} WHERE id = #{id}")
    boolean updateAtm(@Param("status") String status,
                      @Param("bankCardId") Integer bankCardId,
                      @Param("id") Integer id);
}
