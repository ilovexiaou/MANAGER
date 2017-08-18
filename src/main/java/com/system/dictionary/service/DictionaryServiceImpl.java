package com.system.dictionary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dictionary.bean.Dictionary;
import com.system.dictionary.mapper.DictionaryMapper;

@Service
public class DictionaryServiceImpl implements DictionaryService{
    @Resource
    DictionaryMapper dictionaryMapper;
    
    @Override
    public List<Dictionary> getAll(int offset, int limit) {
        return dictionaryMapper.getAll(offset, limit);
    }

    @Override
    public int countAll() {
        return dictionaryMapper.countAll();
    }

    @Override
    public void addDictionaryBatch(List<Dictionary> dictionarys) {
        dictionaryMapper.addDictionaryBatch(dictionarys);
    }

    @Override
    public List<Dictionary> editDictionary(Integer id) {
        return dictionaryMapper.editDictionary(id);
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void updateDictionary(List<Dictionary> list, String oldIds) {
        String[] ids = oldIds.split(",");
        List<String> idList = new ArrayList<String>();
        List<Dictionary> newList = new ArrayList<Dictionary>();
        for (Dictionary dictionary : list) {
            Integer id = dictionary.getId();
            if(null!=id){//已存在的
                idList.add(id.toString());
            }else{//新加的
                newList.add(dictionary);
            }
        }
        if(newList.size()>0)dictionaryMapper.addDictionaryBatch(newList);
        boolean flag;
        for (String str : ids) {
            flag = idList.contains(str);
            if(flag){//包含 更新
                Dictionary dictionary = findDictionary(str,list);
                dictionaryMapper.updateDictionary(dictionary);
            }else{//不包含 删除
                dictionaryMapper.deleteDictionary(Integer.valueOf(str));
            }
        }
    }

    @Override
    public void deleteDictionary(Integer id) {
        dictionaryMapper.deleteDictionary(id);
    }
    /**
     * 通过id 查找list里的记录
     * @return
     */
    private Dictionary findDictionary(String id,List<Dictionary> list){
        Integer did = Integer.valueOf(id);
        for (Dictionary dictionary : list) {
            if(dictionary.getId().equals(did))return dictionary;
        }
        return null;
    }

    @Override
    public List<Dictionary> getselectCondition(String kind, String name, int offset, int limit) {
        return dictionaryMapper.getselectCondition(kind, name, offset, limit);
    }

    @Override
    public int countByselect(String kind, String name) {
        return dictionaryMapper.countByselect(kind, name);
    }
}
