package cn.bupt.mapper;

import cn.bupt.entity.Patent;
import cn.bupt.entity.Table;
import org.apache.ibatis.annotations.*;

import java.util.LinkedHashMap;
import java.util.List;

public interface ResourcePoolMapper {

    @Delete("DROP TABLE ${tableName}")
    void delete(String tableName);

    @Update("${sql}")
    void create(String sql);

    @Update("CREATE TABLE ${tableName}(name varchar(10))")
    void update(String tableName);

    @Select("SELECT ID,FNUM,CPNUM,VID,LANG FROM patent_infor")
    List<Patent> findall();


    @Select("SELECT ID,FNUM,CPNUM,VID,LANG FROM patent_infor WHERE ID = #{id}")
    List<Patent> findById(String id);

    @Select("SELECT ID,FNUM,CPNUM,VID,LANG FROM patent_infor WHERE ID = ${id}")
    List<Patent> Id_test(String id);


    @Insert("INSERT INTO new_table(record,name) VALUES(#{record},#{name})")
    void insert(@Param("record")String record,@Param("name")String name);

    @Select("SELECT * FROM new_table")
    List<Table> Choose();

    @Delete("delete from new_table")
    void clear_table();

    @Select("${sql}")
    List<Patent> findbysth(String sql);

    @Select("${sql}")
    LinkedHashMap findbygroup(String sql);
}