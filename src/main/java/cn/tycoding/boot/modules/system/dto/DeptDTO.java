package cn.tycoding.boot.modules.system.dto;

import cn.tycoding.boot.modules.system.entity.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对Dept 实体类的扩展
 *
 * @author tycoding
 * @since 2020/10/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptDTO extends Dept {

    public static final String DES_KEY = "des";
    public static final String CREATE_TIME_KEY = "createTime";
}
