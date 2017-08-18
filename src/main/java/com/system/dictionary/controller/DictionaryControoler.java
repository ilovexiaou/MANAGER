package com.system.dictionary.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.dictionary.bean.Dictionary;
import com.system.dictionary.service.DictionaryService;
import com.utils.json.JsonUtils;

@Controller
public class DictionaryControoler {
    @Resource
    DictionaryService dictionaryService;
    
    @RequestMapping("dictionary/getAll.do")
    public  String listAll(){
        return "/jsp/system/dictionary/showDictionary";
    }
    
    
    @RequestMapping(value = "dictionary/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Dictionary> dictionaryList;
        int count;
        Map<String, Object> map;
        dictionaryList = dictionaryService.getAll(offset, limit);
        count = dictionaryService.countAll();
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", dictionaryList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("dictionary/addDictionary.do")
    public  String addDictionary(){
        return "/jsp/system/dictionary/addDictionary";
    }
    
    @RequestMapping(value = "dictionary/saveDictionary.do", method = RequestMethod.POST)
    public String saveDictionary(Dictionary dictionary,final RedirectAttributes redirectAttributes){
        try{
            String[] names = dictionary.getNames();
            String[] sequences = dictionary.getSequences();
            String[] comments = dictionary.getComments();
            String kind = dictionary.getKind();
            List<Dictionary> list = new ArrayList<Dictionary>();
            for (int i = 0; i < names.length; i++) {
                Dictionary dt = new Dictionary();
                dt.setName(names[i]);
                if(sequences[i]!="")dt.setSequence(Integer.valueOf(sequences[i]));
                if(comments[i]!="")dt.setComment(comments[i]);
                dt.setKind(kind);
                list.add(dt);
            }
            dictionaryService.addDictionaryBatch(list);
            redirectAttributes.addFlashAttribute("message", "成功新建字典！");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "新建字典失败！");
        }
        
        return "redirect:getAll.do";
    }
    
    @RequestMapping("dictionary/lookupDictionary.do")
    public String lookupDictionary(@RequestParam("id") Integer id,HttpServletRequest request){
        List<Dictionary> list = dictionaryService.editDictionary(id);
        request.setAttribute("list", list);
        return "/jsp/system/dictionary/lookupDictionary";
    }
    @RequestMapping("dictionary/editDictionary.do")
    public String editDictionary(@RequestParam("id") Integer id,HttpServletRequest request){
        List<Dictionary> list = dictionaryService.editDictionary(id);
        request.setAttribute("list", list);
        String ids="";
        for (Dictionary dictionary : list) {
            ids+=dictionary.getId().toString()+",";
        }
        request.setAttribute("oldIds", ids.subSequence(0, ids.length()-1));
        return "/jsp/system/dictionary/editDictionary";
    }
    
    @RequestMapping(value = "dictionary/deleteDictionary.do", method=RequestMethod.GET)
    public String deleteDictionary(@RequestParam("id") Integer id,final RedirectAttributes redirectAttributes){
        try {
            dictionaryService.deleteDictionary(id);
            redirectAttributes.addFlashAttribute("message", "成功删除字典！");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "删除字典失败！");
        }
        return "redirect:getAll.do";
    }
    @RequestMapping(value = "dictionary/updateDictionary.do", method = RequestMethod.POST)
    public String updateDictionary(Dictionary dictionary,final RedirectAttributes redirectAttributes){
        try{
            String[] names = dictionary.getNames();
            String[] sequences = dictionary.getSequences();
            String[] comments = dictionary.getComments();
            Integer[] ids = dictionary.getIds();
            String kind = dictionary.getKind();
            String oldIds = dictionary.getOldIds();
            List<Dictionary> list = new ArrayList<Dictionary>();
            for (int i = 0; i < names.length; i++) {
                Dictionary dt = new Dictionary();
                dt.setName(names[i]);
                if(sequences[i]!="")dt.setSequence(Integer.valueOf(sequences[i]));
                if(comments[i]!="")dt.setComment(comments[i]);
                if(null!=ids[i])dt.setId(ids[i]);
                dt.setKind(kind);
                list.add(dt);
            }
            dictionaryService.updateDictionary(list, oldIds);
            redirectAttributes.addFlashAttribute("message", "成功修改字典！");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "修改字典失败！");
        }
        
        return "redirect:getAll.do";
    }
    
    @RequestMapping(value = "dictionary/ajaxByCondition.do", method = RequestMethod.POST)
    public void ajaxByCondition(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        String name = request.getParameter("name");
        String kind = request.getParameter("kind");
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Dictionary> dictionaryList;
        int count;
        Map<String, Object> map;
        dictionaryList = dictionaryService.getselectCondition(kind, name, offset, limit);
        count = dictionaryService.countByselect(kind, name);
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", dictionaryList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
