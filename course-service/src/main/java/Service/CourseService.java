package Service;

import Entity.Course;

import java.util.List;

public interface CourseService {

    public Course createCourse(Course course);

    public Course getCourseById(Long id);

    public Course updateCourse(Long id, Course courseDetails);

    public void deleteCourse(Long id);

    public List<Course> getAllCourses();
}
