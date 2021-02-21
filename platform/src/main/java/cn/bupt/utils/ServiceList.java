package cn.bupt.utils;

import java.util.*;

public class ServiceList {
    public String id;
    public List<Integer> platform;
    public ServiceList next;
    public Set<Integer> complete;


    public ServiceList() {
        complete = new HashSet();
    }

    public ServiceList(String id, List<Integer> platform) {
        this.id = id;
        this.platform = new ArrayList();
        this.platform.addAll((platform));
        complete = new HashSet();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this == null) {
            return result.toString();
        }
        ServiceList serviceList = this.next;
        Map<Integer, String> map = getMap();
        result.append(listToString(serviceList.platform, map));
        ServiceList curServiceList = serviceList.next;
        while (curServiceList != null) {
            result.append(";");
            result.append(listToString(curServiceList.platform, map));
            curServiceList = curServiceList.next;
        }
        result.append("|");
        result.append(setToString(complete, map));
        return result.toString();
    }

    private Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap();
        map.put(1, "检验检测");
        map.put(2, "专家");
        map.put(3, "专利");
        map.put(4, "仪器设备");
        return map;
    }

    private String setToString(Set<Integer> list, Map<Integer, String> map) {
        if (list == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int number = 0;
        for (Integer integer : list) {
            if (number == 0) {
                number++;
            } else {
                result.append(",");
            }
            result.append(map.get(integer));
        }
        return result.toString();
    }

    private String listToString(List<Integer> list, Map<Integer, String> map) {
        if (list == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int number = 0;
        for (Integer integer : list) {
            if (number == 0) {
                number++;
            } else {
                result.append(",");
            }
            result.append(map.get(integer));
        }
        return result.toString();
    }
}
