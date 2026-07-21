package com.cognizant.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoServiceTest {

    @Mock
    private ExternalApi mockApi;

    @InjectMocks
    private MyService myService;

    // Exercise 1: Mocking and Stubbing
    @Test
    void testFetchDataStubbing() {
        when(mockApi.getData()).thenReturn("Mock Data");
        String result = myService.fetchData();
        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    void testVerifyInteraction() {
        myService.fetchData();
        verify(mockApi).getData();
    }

    // Exercise 3: Argument Matching
    @Test
    void testArgumentMatching() {
        when(mockApi.getDataById(anyString())).thenReturn("Item Info");
        String result = myService.fetchDataById("123");
        assertEquals("Item Info", result);
        verify(mockApi).getDataById(eq("123"));
    }

    // Exercise 4: Handling Void Methods
    @Test
    void testVoidMethodInteraction() {
        doNothing().when(mockApi).sendNotification(anyString(), anyString());
        myService.notifyUser("test@user.com", "Welcome!");
        verify(mockApi).sendNotification("test@user.com", "Welcome!");
    }

    // Exercise 5: Mocking and Stubbing with Multiple Returns
    @Test
    void testMultipleReturns() {
        when(mockApi.getData()).thenReturn("First Call", "Second Call");
        assertEquals("First Call", myService.fetchData());
        assertEquals("Second Call", myService.fetchData());
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    void testInteractionOrder() {
        myService.fetchData();
        myService.fetchDataById("ABC");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).getDataById("ABC");
    }

    // Exercise 7: Handling Void Methods with Exceptions
    @Test
    void testVoidMethodWithException() {
        doThrow(new RuntimeException("Network Error"))
            .when(mockApi).sendNotification(eq("error@user.com"), anyString());

        assertThrows(RuntimeException.class, () -> {
            myService.notifyUser("error@user.com", "Alert");
        });
    }
}
