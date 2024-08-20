package com.lms.university.controller;

import com.lms.university.dto.DepartmentUpdateDto;
import com.lms.university.dto.FacultyUpdateDto;
import com.lms.university.entity.Department;
import com.lms.university.entity.Faculty;
import com.lms.university.exception.ConflictException;
import com.lms.university.exception.NotFoundException;
import com.lms.university.service.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/uni")
public class FacultyController {

    private final FacultyService facultyService;

    // List all faculties
    @GetMapping("/faculty")
    public ResponseEntity<?> listFaculties() {
        try {
            List<Faculty> faculties = facultyService.listFaculties();
            return new ResponseEntity<>(faculties, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to list faculties", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // List faculty by id
    @GetMapping("/faculty/{id}")
    public ResponseEntity<?> getFaculty(@PathVariable(name = "id") Long id) {
        try {
            Faculty faculty = facultyService.getFacultyById(id);
            return new ResponseEntity<>(faculty, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get faculty ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Faculty
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/faculty")
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        try {
            Faculty newFaculty = facultyService.createFaculty(faculty);
            return new ResponseEntity<>(newFaculty, HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Faculty
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/faculty/{id}/update")
    public ResponseEntity<?> updateFaculty(@RequestBody FacultyUpdateDto facultyUpdateDto, @PathVariable(name = "id") Long id) {
        try {
            Faculty updatedFaculty = facultyService.updateFaculty(id, facultyUpdateDto);
            return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Faculty
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/faculty/{id}/delete")
    public ResponseEntity<String> deleteFaculty(@PathVariable(name = "id") Long id) {
        try {
            String response = facultyService.deleteFaculty(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // List all department
    @GetMapping("/department")
    public ResponseEntity<?> listDepartments() {
        try {
            List<Department> departments = facultyService.getDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to list departments", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // List department by id
    @GetMapping("/department/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable(name = "id") Long id) {
        try {
            Department department = facultyService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get department",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Department
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/department")
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            Department newDepartment = facultyService.createDepartment(department);
            return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Department
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("department/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") Long id, @RequestBody DepartmentUpdateDto department) {
        try {
            Department updatedDepartment = facultyService.updateDepartment(id, department);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Department
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("department/{id}/delete")
    public ResponseEntity<String> deleteDepartment(@PathVariable(name = "id") Long id) {
        try {
            String response = facultyService.deleteDepartment(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // List all departments by faculty id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/faculty/{id}/departments")
    public ResponseEntity<?> listDepartmentsByFacultyId(@PathVariable(name = "id") Long id) {
        try {
            List<Department> departments = facultyService.getDepartmentsByFacultyId(id);
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to list departments", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // assign department to faculty
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/faculty/{facultyId}/department/{departmentId}")
    public ResponseEntity<?> assignDepartmentToFaculty(@PathVariable(name = "facultyId") Long facultyId, @PathVariable(name = "departmentId") Long departmentId) {
        try {
            String response = facultyService.assignDepartmentToFaculty(facultyId, departmentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to assign department to faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // list department without assigned to faculty
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/department/unassigned")
    public ResponseEntity<?> listDepartmentWithoutFaculty() {
        try {
            List<Department> departments = facultyService.listDepartmentWithoutFaculty();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to list departments", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // unassign department from faculty
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/department/{id}/unassign")
    public ResponseEntity<?> unassignDepartmentFromFaculty(@PathVariable(name = "id") Long departmentId) {
        try {
            String response = facultyService.unassignDepartmentFromFaculty(departmentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to unassign department from faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
