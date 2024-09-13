package com.lms.university.service.impl;

import com.lms.university.dto.DepartmentUpdateDto;
import com.lms.university.dto.FacultyUpdateDto;
import com.lms.university.entity.Department;
import com.lms.university.entity.Faculty;
import com.lms.university.event.DepartmentEvent;
import com.lms.university.exception.ConflictException;
import com.lms.university.exception.NotFoundException;
import com.lms.university.repository.DepartmentRepository;
import com.lms.university.repository.FacultyRepository;
import com.lms.university.service.FacultyService;
import com.lms.university.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepository facultyRepository;
    private DepartmentRepository departmentRepository;
    private KafkaProducerService kafkaProducerService;

    /**
     * Retrieves a list of all faculties.
     * @return List of Faculty objects.
     */
    public List<Faculty> listFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        if (faculties.isEmpty()) {
            throw new NotFoundException("No faculties found");
        }
        return faculties;
    }

    /**
     * Retrieves a faculty by its ID.
     * @param id ID of the faculty.
     * @return The Faculty object.
     */
    public Faculty getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        return faculty;
    }

    /**
     * Creates a new faculty.
     * @param faculty Faculty object to be created.
     * @return The created Faculty object.
     */
    public Faculty createFaculty(Faculty faculty) {
        if (facultyRepository.existsByName(faculty.getName())) {
            throw new ConflictException("Faculty already exists");
        }
        return facultyRepository.save(faculty);
    }

    /**
     * Updates an existing faculty by its ID.
     * @param id ID of the faculty.
     * @param facultyUpdateDto Data Transfer Object with updated faculty data.
     * @return The updated Faculty object.
     */
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

    /**
     * Deletes a faculty by its ID.
     * @param id ID of the faculty.
     * @return A success message.
     */
    public String deleteFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            throw new NotFoundException("Faculty not found");
        }
        facultyRepository.delete(faculty);
        return "Faculty deleted successfully";
    }

    /**
     * Retrieves a list of all departments.
     * @return List of Department objects.
     */
    public List<Department> getDepartments() {
        List<Department> department = departmentRepository.findAll();
        if (department.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return department;
    }

    /**
     * Retrieves a department by its ID.
     * @param id ID of the department.
     * @return The Department object.
     */
    public Department getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if(department == null) {
            throw new NotFoundException("Department not found");
        }
        return department;
    }

    /**
     * Creates a new department.
     * @param department Department object to be created.
     * @return The created Department object.
     */
    public Department createDepartment(Department department) {
        if(departmentRepository.existsByName(department.getName())) {
            throw new ConflictException("Department already exists");
        }
        return departmentRepository.save(department);
    }

    /**
     * Updates an existing department by its ID.
     * @param id ID of the department.
     * @param departmentUpdateDto Data Transfer Object with updated department data.
     * @return The updated Department object.
     */
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

    /**
     * Deletes a department by its ID.
     * @param id ID of the department.
     * @return A success message.
     */
    public String deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        departmentRepository.delete(department);
        return "Department deleted successfully";
    }

    /**
     * Retrieves a list of departments associated with a specific faculty.
     * @param facultyId ID of the faculty.
     * @return List of Department objects.
     */
    public List<Department> getDepartmentsByFacultyId(Long facultyId) {
        List<Department> departments = departmentRepository.findByFacultyId(facultyId);
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments;
    }

    /**
     * Assigns a department to a faculty.
     * @param facultyId ID of the faculty.
     * @param departmentId ID of the department.
     * @return A success message.
     */
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

    /**
     * Retrieves a list of departments that are not assigned to any faculty.
     * @return List of Department objects.
     */
    public List<Department> listDepartmentWithoutFaculty() {
        List<Department> departments = departmentRepository.findByFacultyIsNull();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments;
    }

    /**
     * Unassigns a department from a faculty.
     * @param departmentId ID of the department.
     * @return A success message.
     */
    public String unassignDepartmentFromFaculty(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }
        department.setFaculty(null);
        departmentRepository.save(department);
        return "Department unassigned from faculty successfully";
    }

    /**
     * Assigns a course to a department.
     * @param departmentId ID of the department.
     * @param courseId ID of the course.
     * @return A success message.
     */
    public String assignCourseToDepartment(Long departmentId, Long courseId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new NotFoundException("Department not found");
        }

        if (department.getCourseIds().contains(courseId)) {
            throw new ConflictException("Course already assigned to department");
        }
        department.getCourseIds().add(courseId);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentEvent departmentEvent = new DepartmentEvent(courseId, savedDepartment.getId(), "COURSE_ASSIGNED");
        kafkaProducerService.sendCourseEvent(departmentEvent);

        return "Course assigned to department successfully";
    }

    /**
     * Unassigns a course from a department.
     * @param departmentId ID of the department.
     * @param courseId ID of the course.
     * @return A success message.
     */
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
