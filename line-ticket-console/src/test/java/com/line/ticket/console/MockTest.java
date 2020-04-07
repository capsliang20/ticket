package com.line.ticket.console;

import com.line.ticket.common.entity.generic.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;


public class MockTest {


    @Test
    public void mockCase_20() {
        List serializableMock = mock(List.class, withSettings().serializable());
        List<Object> list = new ArrayList<>();
        List<Object> spy = mock(ArrayList.class, withSettings()
                .spiedInstance(list)
                .defaultAnswer(CALLS_REAL_METHODS)
                .serializable());

    }

    //Spying on real objects
    @Test
    public void mockCase_13() {
        ArgumentCaptor<Pair> argument = ArgumentCaptor.forClass(Pair.class);
        List list = new LinkedList();
        List spy = spy(list);

        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));
        System.out.println(spy.get(1));
        System.out.println(spy.size());

        verify(spy).add("one");
        verify(spy).add("two");
    }

    //doReturn()| doThrow()| doAnswer()| doNothing()| doCallRealMethod()| family of methods
    //doReturn(Object)| doThrow(Throwable)| doThrow(Class)| doAnswer(Answer)| doNothing()
    @Test
    public void mockCase_12() {
        List mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    //Stubbing with callbacks
    @Test
    public void mockCase_11() {
        MockObject mock = mock(MockObject.class);

        when(mock.test(anyString())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return "called method " + invocation.getMethod().getName() + " with arguments: " + Arrays.toString(args);
        });

//        above lambda
//        when(mock.test(anyString())).thenAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                Object[] args = invocation.getArguments();
//                Object mock = invocation.getMock();
//                return "called method " + invocation.getMethod().getName() + " with arguments: " + Arrays.toString(args);
//            }
//        });

        System.out.println(mock.test("foo"));
    }

    //Stubbing consecutive calls (iterator-style stubbing)
    @Test
    public void mockCase_10() {
        MockObject mock = mock(MockObject.class);
        when(mock.test("this arg"))
                .thenReturn("first", "second", "third")
                .thenThrow(new RuntimeException());

        mock.test("this arg");

        System.out.println(mock.test("this arg"));

        System.out.println(mock.test("this arg"));

        System.out.println(mock.test("this arg"));
    }

    //Finding redundant invocations
    @Test
    public void mockCase_8() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verifyNoMoreInteractions(mockedList);
    }

    //Making sure interaction(s) never happened on mock
    @Test
    public void mockCase_7() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        mockOne.add("one");
        verify(mockOne).add("one");

        verify(mockOne, never()).add("two");
        verifyNoInteractions(mockTwo, mockThree);

        mockTwo.add("two");
        verifyNoMoreInteractions(mockThree);
    }

    //Verification in order
    @Test
    public void mockCase_6() {
        //A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        singleMock.add("was added first");
        singleMock.add("was added second");

        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        //B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");

        InOrder inOrder2 = inOrder(firstMock, secondMock);
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }


    //Stubbing void methods with exceptions
    @Test
    public void mockCase_5() {
        List mockedList = mock(List.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    //Verifying exact number of invocations
    @Test
    public void mockCase_4() {
        List mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    //Argument matchers
    @Test
    public void mockCase_3() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("element");

//        when(mockedList.contains(argThat(eq("string")))).thenReturn("element");

        System.out.println(mockedList.get(999));

        verify(mockedList).get(anyInt());

//        verify(mockedList).add(argThat(someString->someString.length()>5))
    }

    //How about some stubbing
    @Test
    public void mockCase_2() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));

        when(mockedList.get(0)).thenReturn("second");

        System.out.println(mockedList.get(0));

        System.out.println(mockedList.get(999));

        verify(mockedList).get(0);

        System.out.println(mockedList.get(1));

    }

    //Let's verify some behaviour
    @Test
    public void mockCase_1() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}

class MockObject{
    public String test(String string) {
        System.out.println(string);
        return string;
    }
}
