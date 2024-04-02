package net.deepikaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.deepikaguides.ems.dto.DepartmentDto;
import net.deepikaguides.ems.entity.Department;
import net.deepikaguides.ems.exception.ResourceNotFoundException;
import net.deepikaguides.ems.mapper.DepartmentMapper;
import net.deepikaguides.ems.repository.DepartmentRepository;
import net.deepikaguides.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department= DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment= departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department=departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Departemt is not exists with given id: "+departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments= departmentRepository.findAll();
        return departments.stream().map(department->DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department=departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department is not exists with given id"+departmentId));
        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
        Department savedDepartment =departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department=departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department Does not exists with given id"+departmentId));
        departmentRepository.deleteById(departmentId);
    }


}
