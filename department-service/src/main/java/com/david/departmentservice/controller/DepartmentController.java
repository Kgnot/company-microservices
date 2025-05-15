package com.david.departmentservice.controller;

import com.david.departmentservice.client.EmployeeClient;
import com.david.departmentservice.model.Department;
import com.david.departmentservice.repository.DepartmentRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    public static final String DEPARTMENT_SERVICE = "departmentService";

    private final DepartmentRepository departmentRepository;

    private final EmployeeClient employeeClient;

    @GetMapping()
    public ResponseEntity<List<Department>> findAll(){
        log.info("Find all departments");
        List<Department> departments = departmentRepository.findAll();

        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        log.info("Find department by id: {}", id);
        Department department = departmentRepository.findById(id).orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(department);
    }

    @PostMapping()
    public ResponseEntity<Department> add(@RequestBody Department department){
        log.info("Add department: {}", department);
        Department newDepartment = departmentRepository.save(department);

        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }


    @GetMapping("/with-employees")
//    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackCircuitBreakerFindAllWithEmployees")
//    @Retry(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackRetryFindAllWithEmployees")
//    @RateLimiter(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackRateLimiterFindAllWithEmployees")
//    @TimeLimiter(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackTimeLimiterFindAllWithEmployees")
//    @Bulkhead(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackBulkheadFindAllWithEmployees")
    public ResponseEntity<List<Department>> findAllWithEmployees(){
        log.info("Find all departments with employees");
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(x -> x.setEmployees(employeeClient.findById(x.getId())));

        return ResponseEntity.ok(departments);
    }
    //If percentage of failure on this request is greater than the established is going to throw that method
    public ResponseEntity<List<Department>> fallbackCircuitBreakerFindAllWithEmployees(Exception e){
        log.info("fallback circuit breaker find all with employees");
        List<Department> departments = departmentRepository.findAll();

        return ResponseEntity.ok(departments);
    }
    //If the request fails is going to retry a number of times this method
    public ResponseEntity<List<Department>> fallbackRetryFindAllWithEmployees(Exception e){
        log.info("fallback retry find all with employees");
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(x -> x.setEmployees(employeeClient.findById(x.getId())));

        return ResponseEntity.ok(departments);
    }
    //If the number of request is greater than the established is going to throw that method
    public ResponseEntity<List<Department>> fallbackRateLimiterFindAllWithEmployees(Exception e){
        log.info("fallback rate limiter find all with employees");
        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    //If the time of the request is greater than the established is going to throw that method
    public ResponseEntity<List<Department>> fallbackTimeLimiterFindAllWithEmployees(Exception e){

        return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    }

    //If the number of request is greater than the established is going to throw that method
    // or if the thread pool and also the waiting queue are full, depends of the implementation used
    public ResponseEntity<List<Department>> fallbackBulkheadFindAllWithEmployees(Exception e){
        log.info("fallback bulk head find all with employees");
        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

}
