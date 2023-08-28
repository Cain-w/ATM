package bob.homework.atm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface BankCardMapper {

    @Select("SELECT id From bankcard")
    List<Integer> getAllId();
    @Select("SELECT * From bankcard WHERE id = #{id}")
    Map<String, Object> getBankCard(@Param("id") Integer id);

    @Update("UPDATE bankcard SET balance = balance + #{money} WHERE id = #{bankCardId}")
    Boolean saveMoney(@Param("money") BigDecimal money, @Param("bankCardId") Integer bankCardId);

    @Update("UPDATE bankcard SET balance = balance - #{money} WHERE id = #{bankCardId}")
    Boolean bringMoney(@Param("money") BigDecimal money, @Param("bankCardId") Integer bankCardId);
}
