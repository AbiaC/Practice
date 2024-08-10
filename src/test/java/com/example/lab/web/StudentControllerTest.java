package com.example.lab.web;

import com.example.lab.entities.Student;
import com.example.lab.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;

class StudentControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private Model model;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;
    Student student;

    @BeforeEach
    void setup() throws ParseException {
        // Initialize the Student object
        student = new Student();
        student.setId(1L);
        student.setName("John");

        // Parse the date and set other properties
        String sDate1 = "2012/11/11";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        student.setDob(date1);
        student.setPassed(true);
        student.setGpa(3.5);

        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void findAll_ListView() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student);

        when(studentRepository.findByNameContainingIgnoreCase("John")).thenReturn(students);

        mockMvc.perform(get("/index").param("keyword", "John"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listStudents", students))
                .andExpect(view().name("students"))
                .andExpect(model().attribute("listStudents", hasSize(2)));

        verify(studentRepository, times(1)).findByNameContainingIgnoreCase("John");
        verifyNoMoreInteractions(studentRepository);
    }


    @Test
    void students() {
    }

    @Test
    void delete() {
    }

    @Test
    void formStudents() {
    }

    @Test
    void save() {
    }

    @Test
    void editStudents() {
    }

    @Test
    void students2() {
    }
}