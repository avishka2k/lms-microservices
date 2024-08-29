package com.lms.university.service;

import com.lms.university.dto.DepartmentUpdateDto;
import com.lms.university.dto.FacultyUpdateDto;
import com.lms.university.entity.Department;
import com.lms.university.entity.Faculty;
import com.lms.university.event.DepartmentEvent;
import com.lms.university.exception.ConflictException;
import com.lms.university.exception.NotFoundException;
import com.lms.university.repository.DepartmentRepository;
import com.lms.university.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FacultyService {

    private FacultyRepository facultyRepository;
    private DepartmentRepository departmentRepository;
    private KafkaProducerService kafkaProducerService;
    // List all faculties
    public List<Faculty> listFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        if (faculties.isEmpty()) {
            throw new NotFoundException("No faculties found");
        }
        return faculties;
    }

    // Get faculty by id
    public Faculty getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        return faculty;
    }

    // Create faculty
    public Faculty createFaculty(Faculty faculty) {
        if (facultyRepository.existsByName(faculty.getName())) {
            throw new ConflictException("Faculty already exists");
        }
        return facultyRepository.save(faculty);
    }

    // Update faculty
    public Faculty updateFaculty(Long id, FacultyUpdateDto facultyUpdateDto) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        faculty.setName(facultyUpdateDto.getName());
        faculty.setDescription(facultyUpdateDto.getDescription());
        faculty.setPhone(facultyUpdateDto.getPhone());
        faculty.setEmail(facultyUpdateDto.getEmail());
        faculty.setDeanId(facultyUpdateDto.getDeanId());
        return facultyRepository.save(faculty);
    }

    // Delete faculty
    public String deleteFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        facultyRepository.delete(faculty);
        return "Faculty deleted successfully";
    }

    // Get All Departments
    public List<Department> getDepartments() {
        List<Department> department = departmentRepository.findAll();
        if (department.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return department;
    }

    // Get Department by id
    public Department getDepartmentById(Long id) {
        Department department =departmentRepository.findById(id).orElse(null);
        if(department == null) {
            throw new NotFoundException("Department not found");
        }
        return department;
    }

    public Department createDepartment(Department department) {
        if(departmentRepository.existsByName(department.getName())) {
            throw new ConflictException("Department already exists");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, DepartmentUpdateDto departmentUpdateDto) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        department.setName(departmentUpdateDto.getName());
        department.setDescription(departmentUpdateDto.getDescription());
        department.setPhone(departmentUpdateDto.getPhone());
        department.setEmail(departmentUpdateDto.getEmail());
        department.setHeadId(departmentUpdateDto.getHeadId());
        return departmentRepository.save(department);
    }

    public String deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        departmentRepository.delete(department);
        return "Department deleted successfully";
    }

    public List<Department> getDepartmentsByFacultyId(Long facultyId) {
        List<Department> departments = departmentRepository.findByFacultyId(facultyId);
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments;
    }

    public String assignDepartmentToFaculty(Long facultyId, Long departmentId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        department.setFaculty(faculty);
        departmentRepository.save(department);
        return "Department assigned to faculty successfully";
    }

    // list department without assigned to faculty
    public List<Department> listDepartmentWithoutFaculty() {
        List<Department> departments = departmentRepository.findByFacultyIsNull();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments;
    }

    // Unassign department from faculty
    public String unassignDepartmentFromFaculty(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        department.setFaculty(null);
        departmentRepository.save(department);
        return "Department unassigned from faculty successfully";
    }

    public String assignCourseToDepartment(Long departmentId, Long courseId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }

        if (department.getCourseIds().contains(courseId)) {
            throw new ConflictException("Course already assigned to department");
        }
        department.getCourseIds().add(courseId);
        Department  savedDepartment =  departmentRepository.save(department);
        DepartmentEvent departmentEvent = new DepartmentEvent(courseId, savedDepartment.getId(), "COURSE_ASSIGNED");
        kafkaProducerService.sendCourseEvent(departmentEvent);

        return "Course assigned to department successfully";
    }

    public String unassignCourseFromDepartment(Long departmentId, Long courseId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }

        if (!department.getCourseIds().contains(courseId)) {
            throw new NotFoundException("Course not assigned to department");
        }

        department.getCourseIds().remove(courseId);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentEvent departmentEvent = new DepartmentEvent(courseId, savedDepartment.getId(), "COURSE_UNASSIGNED");
        kafkaProducerService.sendCourseEvent(departmentEvent);

        return "Course unassigned from department successfully";
    }
}
