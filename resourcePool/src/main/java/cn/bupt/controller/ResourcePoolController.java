package cn.bupt.controller;

import cn.bupt.entity.Patent;
import cn.bupt.entity.Table;
import cn.bupt.mapper.ResourcePoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/resourcePool")
public class ResourcePoolController {

    private int count=0;

    private Map<String, Object> m = new HashMap();

    @Autowired
    private ResourcePoolMapper resourcePoolMapper;

    @RequestMapping(value = "/mybatisTest", method = RequestMethod.GET)
    public Object mybatisTest(String id) {
        System.out.println(id);
        return resourcePoolMapper.Id_test(id).toString();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Object findById(String id) {
        return resourcePoolMapper.findById(id).toString();
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Object findAll() {
        return resourcePoolMapper.findall().toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Object create(String tableName) {
        resourcePoolMapper.update(tableName);
        return "create success";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public Object drop(String tableName) {
        System.out.println(tableName);
        resourcePoolMapper.delete(tableName);
        return "delete success";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody LinkedHashMap user) {
        resourcePoolMapper.insert(user.get("record").toString(), user.get("name").toString());
        user.put("information", "添加成功");
        return user;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Object confirm(@RequestBody LinkedHashMap user) {

        String sql = "create table " + user.get("name") + "(";
        List<Table> list = resourcePoolMapper.Choose();
        for (int i = 0; i < list.size(); i++) {
            String Record = list.get(i).getRecord();
            String Name = list.get(i).getName();
            if (i != list.size() - 1)
                sql += Record + " " + Name + ",";
            else
                sql += Record + " " + Name;
        }
        sql += ");";
        System.out.println(sql);
        resourcePoolMapper.create(sql);
        user.put("information", "创建表成功");
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody LinkedHashMap user) {
        resourcePoolMapper.clear_table();
        user.put("information", "操作成功");
        return user;
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    public Object drop(@RequestBody LinkedHashMap user) {
        resourcePoolMapper.delete(user.get("name").toString());
        user.put("information", "删除表成功");
        return user;
    }

    @RequestMapping(value = "/draw", method = RequestMethod.POST)
    public Object draw(@RequestBody LinkedHashMap user) {
        LinkedHashMap map = new LinkedHashMap();
        String sql = "SELECT COUNT(*) AS number,"+user.get("record")+" FROM "+ user.get("title")+" group by "+ user.get("record")+" limit 5";
        System.out.println(sql);
        List<Patent> patent = resourcePoolMapper.findbysth(sql);
        for (int i = 0; i < patent.size(); i++) {
            map.put(i, patent.get(i).querys(user.get("record").toString()));
        }
        return map;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Object query(@RequestBody LinkedHashMap user) {
        int tag = 0;
        String sql = "SELECT patentType,APD,PR,INVIEW,PAVIEW,ID FROM patent_infor";
        String s = " WHERE ";
        Iterator iter = user.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (entry.getValue() != "" && entry.getKey() != "year" && entry.getKey() != "month" && entry.getKey() != "date") {
                if (tag == 0) {
                    s += entry.getKey() + "=\'" + entry.getValue() + "\'";
                    tag = 1;
                } else
                    s += " and " + entry.getKey() + "=\'" + entry.getValue() + "\'";
            }
//                System.out.println(entry.getKey()+" "+entry.getValue());
        }
        if (user.get("year") != "" && user.get("month") != "" && user.get("date") != "") {
            if (s != " WHERE ")
                s += " and ";
            s += "APD=\'" + user.get("year") + ".";
            if (user.get("month").toString().length() == 1)
                s += "0" + user.get("month");
            else
                s += user.get("month");
            s += ".";
            if (user.get("date").toString().length() == 1)
                s += "0" + user.get("date");
            else
                s += user.get("date");
            s += "\'";
        }

        if (s != " WHERE ")
            sql += s;
        sql += " limit 20";
        System.out.println(sql);
        LinkedHashMap map = new LinkedHashMap();
        List<Patent> patent = resourcePoolMapper.findbysth(sql);
        for (int i = 0; i < patent.size(); i++) {
            map.put(i, patent.get(i).tohashmap());
        }
        return map;
    }
}