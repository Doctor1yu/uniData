package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Applications;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApplicationsMapper {
    @Insert("INSERT INTO applications (student_id, apply_reason, applied_at) " +
            "VALUES (#{studentId}, #{applyReason}, #{appliedAt})")
    void insertApplication(Applications application);

    @Select("SELECT status FROM applications WHERE student_id = #{studentId} ORDER BY applied_at DESC LIMIT 1")
    String findStatusByStudentId(@Param("studentId") String studentId);

    @Update("UPDATE applications SET status = '2' WHERE student_id = #{studentId}")
    void updateStatusByStudentId(@Param("studentId") String studentId);
}
