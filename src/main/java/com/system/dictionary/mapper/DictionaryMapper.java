package com.system.dictionary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.dictionary.bean.Dictionary;

public interface DictionaryMapper {
    /**
     * 得到所有的字典表
     * @return
     */
    public List<Dictionary> getAll(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 分页计算所有的页数
     * @return
     */
    public int countAll();  
    /**
     * 添加
     * @param dictionary
     */
    public void addDictionaryBatch(List<Dictionary> dictionarys);
    /**
     * 根据id，查找出相同kind的字典表
     * @param dictionary
     * @return
     */
    public List<Dictionary> editDictionary(Integer id);
    public void updateDictionary(Dictionary dictionary);
    public void deleteDictionary(Integer id);
    /**
     * 根据条件分页显示
     * @param kind
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    public List<Dictionary> getselectCondition(@Param("kind") String kind, @Param("name") String name, @Param("offset") int offset, @Param("limit") int limit);
    public int countByselect(@Param("kind") String kind, @Param("name") String name);
}
