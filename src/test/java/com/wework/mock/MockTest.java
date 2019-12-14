package com.wework.mock;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockTest {

    @Test
    public void testMockA() {
        /**
         * 待修改方法在被测方法内部的mock方式
         */
        AMock aMock = mock(AMock.class);
        when(aMock.divid(anyInt(),anyInt(),anyInt())).thenCallRealMethod();
        when(aMock.complex(anyInt(),anyInt())).thenReturn(10);
        assertThat(aMock.divid(0,0,5), equalTo(2));

    }

    @Test
    public void testMockB() {
        /**
         * 待修改方法与被测方法不在同一个类的mock方式
         */
        BMock bMock = mock(BMock.class);
        when(bMock.complex(anyInt(),anyInt())).thenReturn(10);
        System.out.println(bMock.complex(0, 0));
//        bMock作为参数传入
        assertThat(new CDemo().divid(0,0,5,bMock),equalTo(2));
    }
}
