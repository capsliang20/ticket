package com.line.ticket.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("mockito")
public class MockitoTest {

    @BeforeEach
    public void before(){
        System.out.println("before each");
    }

    @Test
    public void mockCase_2() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));

        when(mockedList.get(0)).thenReturn("second");

        System.out.println(mockedList.get(0));

        System.out.println(mockedList.get(999));

//        verify(mockedList).get(0);

//        System.out.println(mockedList.get(1));

    }

    //Let's verify some behaviour
    @Test
    public void mockCase_1() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
        System.out.println("mockCase_1");
    }
}
