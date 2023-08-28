package bob.homework.atm.mapper;

import bob.homework.atm.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsumerMapper {
//    @Select("SELECT * From consumer")
//    List<Consumer> getAllConsumer();

    @Select("SELECT * From consumer WHERE id = #{id}")
    Map<String, Object> getConsumer(@Param("id") Integer id);

}
