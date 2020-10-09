package com.heima.common.common.storage;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 存储Entry
 * k-v 结构保存一个对象的字段的字段名和值
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StorageEntry {
    /**
     * 字段的Key
     */
    private String key;

    /**
     * 字段的Value
     */
    private String value;

}
