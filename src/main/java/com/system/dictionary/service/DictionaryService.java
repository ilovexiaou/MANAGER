package com.system.dictionary.service;

import java.util.List;

import com.system.dictionary.bean.Dictionary;

public interface DictionaryService {
    public List<Dictionary> getAll(int offset, int limit);
    public int countAll();  
    public void addDictionaryBatch(List<Dictionary> dictionarys);
    public List<Dictionary> editDictionary(Integer id);
    /**
     * list为现在的字典表，oldIds为以前的字典表id集合，以逗号隔开
     * @param list
     * @param oldIds
     */
    public void updateDictionary(List<Dictionary> list,String oldIds);
    public void deleteDictionary(Integer id);
    public List<Dictionary> getselectCondition(String kind, String name, int offset, int limit);
    public int countByselect(String kind, String name);
}
