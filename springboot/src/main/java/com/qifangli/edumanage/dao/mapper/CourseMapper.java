package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper {

    Course findCourseById(@Param("id") String id);
}
