package com.diploma.ergi.service;

import com.diploma.ergi.entity.Exercise;
import com.diploma.ergi.repository.ExerciseRepository;
import com.diploma.ergi.repository.UserExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private UserExerciseRepository userExerciseRelateRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    private List<Exercise> exercises;
    private Exercise exercise;

    @Before
    public void setUp() {
        exercises = new ArrayList<>();
        exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("Push Ups");
        exercise.setDescription("A basic push-up exercise");
        exercise.setImage("pushups.jpg");
        exercise.setReps(10);
        exercise.setDuration(30);
        exercise.setRoute("push-ups");
        exercises.add(exercise);
    }

    @Test
    public void testAddExercise() {
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        Exercise createdExercise = exerciseService.add(exercise);

        assertNotNull(createdExercise);
        assertEquals(exercise.getId(), createdExercise.getId());
        verify(exerciseRepository, times(1)).save(any(Exercise.class));
    }

    @Test
    public void testGetAllExercises() {
        when(exerciseRepository.findAll()).thenReturn(exercises);

        List<Exercise> fetchedExercises = exerciseService.getAll();

        assertNotNull(fetchedExercises);
        assertEquals(exercises.size(), fetchedExercises.size());
        assertEquals(exercises.get(0).getName(), fetchedExercises.get(0).getName());
        verify(exerciseRepository, times(1)).findAll();
    }

    @Test
    public void testFindExerciseById() {
        when(exerciseRepository.findExerciseById(1)).thenReturn(exercise);

        Exercise foundExercise = exerciseService.findById(1);

        assertNotNull(foundExercise);
        assertEquals(exercise.getName(), foundExercise.getName());
        verify(exerciseRepository, times(1)).findExerciseById(1);
    }

    @Test
    public void testDeleteExercise() {
        doNothing().when(userExerciseRelateRepository).deleteByExerciseId(1);
        doNothing().when(exerciseRepository).deleteById(1);

        exerciseService.delete(1);

        verify(userExerciseRelateRepository, times(1)).deleteByExerciseId(1);
        verify(exerciseRepository, times(1)).deleteById(1);
    }

    @Test
    public void testFindTopExercises() {
        List<Object[]> topExercises = new ArrayList<>();
        Object[] topExercise = new Object[]{"Push Ups", 100};
        topExercises.add(topExercise);

        when(userExerciseRelateRepository.findTopExercisesByUser(1)).thenReturn(topExercises);

        List<Object[]> fetchedTopExercises = exerciseService.findTopExercises(1);

        assertNotNull(fetchedTopExercises);
        assertEquals(1, fetchedTopExercises.size());
        assertEquals("Push Ups", fetchedTopExercises.get(0)[0]);
        assertEquals(100, fetchedTopExercises.get(0)[1]);
        verify(userExerciseRelateRepository, times(1)).findTopExercisesByUser(1);
    }
}
