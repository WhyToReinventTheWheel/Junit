Study Notes: Ref- https://www.tutorialspoint.com/junit/

-----
JUnit
-----

	JUnit promotes the idea of "first testing then coding".
	This approach is like "test a little, code a little, test a little, code a little." 
	It increases the productivity of the programmer and the stability of program code, 
	which in turn reduces the stress on the programmer and the time spent on debugging.
	
----------------
Power Mock Setup 
-----------------
```
dependencies {
    testCompile 'junit:junit:4.12'
    testCompile 'org.mockito:mockito-core:2.8.0'
    testCompile 'org.powermock:powermock-api-mockito2:1.7.0RC2'
    testCompile 'org.powermock:powermock-module-junit4:1.7.0'
    testCompile 'org.powermock:powermock-core:1.7.0'
    testCompile 'org.powermock:powermock-module-junit4-rule:1.7.0'
}

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalMethod.class)
public class FinalMethodTest {
    public FinalMethod finalMethod = PowerMockito.mock(FinalMethod.class);
    @Before
    public void setup(){
        try {
            PowerMockito.whenNew(FinalMethod.class).withNoArguments().thenReturn(finalMethod);
            PowerMockito.when(finalMethod.helloMethod()).thenReturn("MockMessage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinalMethod(){
       FinalMethod testNew = new FinalMethod();
       System.out.printf("Hello:="+testNew.helloMethod());
    }

}
```
