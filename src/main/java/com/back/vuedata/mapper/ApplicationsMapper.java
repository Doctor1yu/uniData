package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Applications;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ApplicationsMapper {
    // 插入新的申请记录
    @Insert("INSERT INTO applications (student_id, apply_reason, applied_at) " +
            "VALUES (#{studentId}, #{applyReason}, #{appliedAt})")
    void insertApplication(Applications application);

    // 根据学号查询所有申请记录
    @Select("SELECT * FROM applications WHERE student_id = #{studentId}")
    List<Applications> findApplicationsByStudentId(@Param("studentId") String studentId);
}
