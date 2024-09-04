package com.lms.course.service.impl;

import com.lms.course.dto.CourseUpdateDto;
import com.lms.course.dto.ModuleUpdateDto;
import com.lms.course.entity.CModule;
import com.lms.course.entity.Course;
import com.lms.course.exception.ConflictException;
import com.lms.course.exception.NotFoundException;
import com.lms.course.repository.CourseRepository;
import com.lms.course.repository.ModuleRepository;
import com.lms.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private ModuleRepository moduleRepository;

    public Course createCourse(Course course) {
        if (courseRepository.existsByTitle(course.getTitle())) {
            throw new RuntimeException("Course already exists");
        }
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Faculty not found");
        }
        return course;
    }

    public Course updateCourse(Long id, CourseUpdateDto courseUpdateDto) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found with id " + id);
        }
        course.setTitle(courseUpdateDto.getTitle());
        course.setDescription(courseUpdateDto.getDescription());
        course.setLecturer(courseUpdateDto.getLecturer());
        course.setDuration(courseUpdateDto.getDuration());
        course.setLevel(courseUpdateDto.getLevel());
        course.setLanguage(courseUpdateDto.getLanguage());
        course.setFormat(courseUpdateDto.getFormat());
        course.setCredits(courseUpdateDto.getCredits());

        return courseRepository.save(course);
    }

    public String deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        courseRepository.delete(course);
        return "Faculty deleted successfully";
    }

    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }
        return courses;
    }

    // get all modules
    public List<CModule> getModules() {
        List<CModule> modules = moduleRepository.findAll();
        if (modules.isEmpty()) {
            throw new NotFoundException("No modules found");
        }
        return modules;
    }

    // Get Module by id
    public CModule getModuleById(Long id) {
        CModule module = moduleRepository.findById(id).orElse(null);
        if(module == null) {
            throw new NotFoundException("Module not found");
        }
        return module;
    }

    public CModule createModule(CModule module) {
        if(moduleRepository.existsByTitle(module.getTitle())) {
            throw new ConflictException("Module already exists");
        }
        return moduleRepository.save(module);
    }

    public CModule updateModule(Long id, ModuleUpdateDto moduleUpdateDto) {
        CModule module = moduleRepository.findById(id).orElse(null);
        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        module.setTitle(moduleUpdateDto.getTitle());
        module.setDescription(moduleUpdateDto.getDescription());
        module.setDuration(moduleUpdateDto.getDuration());
        module.setLevel(moduleUpdateDto.getLevel());
        module.setLanguage(moduleUpdateDto.getLanguage());
        module.setCredits(moduleUpdateDto.getCredits());
        module.setSemester(moduleUpdateDto.getSemester());
        return moduleRepository.save(module);
    }

    public String deleteModule(Long id) {
        CModule module = moduleRepository.findById(id).orElse(null);
        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        moduleRepository.delete(module);
        return "Module deleted successfully";
    }

    // get all modules by course id
    public List<CModule> getModulesByCourseId(Long courseId) {
        List<CModule> modules = moduleRepository.findByCourseId(courseId);
        if (modules.isEmpty()) {
            throw new NotFoundException("No modules found");
        }
        return modules;
    }

    // assign module to course
    public String assignModuleToCourse(Long moduleId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        CModule module = moduleRepository.findById(moduleId).orElse(null);
        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        module.setCourse(course);
        moduleRepository.save(module);
        return "Module assigned to course successfully";
    }

    // list modules without assigned to course
    public List<CModule> getModulesWithoutAssigned() {
        List<CModule> modules = moduleRepository.findByCourseIsNull();
        if (modules.isEmpty()) {
            throw new NotFoundException("No modules found");
        }
        return modules;
    }

    // Unassign module from course
    public String unassignModuleFromCourse(Long moduleId) {
        CModule module = moduleRepository.findById(moduleId).orElse(null);
        if (module == null) {
            throw new NotFoundException("Module not found");
        }
        module.setCourse(null);
        moduleRepository.save(module);
        return "Module unassigned from course successfully";
    }

    public List<Course> getCoursesWithoutAssigned() {
        List<Course> courses = courseRepository.findByDepartmentIdIsNull();
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }
        return courses;
    }

    public List<Course> getCoursesByDepartmentId(Long departmentId) {
        List<Course> courses = courseRepository.findByDepartmentId(departmentId);
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }
        return courses;
    }

    public String unassignCourseFromDepartment(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        course.setDepartmentId(null);
        courseRepository.save(course);
        return "Course unassigned from department successfully";
    }

    public String getCourseNameById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        return course.getTitle();
    }

}
