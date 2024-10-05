package org.ms_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ms_demo.entity.Account;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
