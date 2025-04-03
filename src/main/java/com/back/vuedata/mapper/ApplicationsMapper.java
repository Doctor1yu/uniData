package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Applications;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApplicationsMapper {
    // 插入新的申请记录
    @Insert("INSERT INTO applications (student_id, apply_reason, applied_at) " +
            "VALUES (#{studentId}, #{applyReason}, #{appliedAt})")
    void insertApplication(Applications application);
}
