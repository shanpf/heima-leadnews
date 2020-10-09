package com.heima.common.common.storage;

<<<<<<< HEAD
import lombok.Getter;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> 项目修改提交
import lombok.Setter;

/**
 * 存储Entry
 * k-v 结构保存一个对象的字段的字段名和值
 */
@Setter
@Getter
<<<<<<< HEAD
public class StorageEntry {
    /**
     * 空的构造方法
     */
    public StorageEntry() {
    }

    /**
     * 构造方法
     *
     * @param key
     * @param value
     */
    public StorageEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }
=======
@NoArgsConstructor
@AllArgsConstructor
public class StorageEntry {
>>>>>>> 项目修改提交

    /**
     * 字段的Key
     */
    private String key;

    /**
     * 字段的Value
     */
    private String value;

}
