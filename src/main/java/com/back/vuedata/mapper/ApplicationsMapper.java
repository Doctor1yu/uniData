package com.back.vuedata.mapper;

import com.back.vuedata.pojo.Applications;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationsMapper {
    @Insert("INSERT INTO applications (student_id, apply_reason, applied_at) " +
            "VALUES (#{studentId}, #{applyReason}, #{appliedAt})")
    void insertApplication(Applications application);
}
