package ex1;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class TestingClass {

    public static void start(Class cls) {
        performTests(cls);
    }

    private static void performTests(Class cls) throws RuntimeException {
        TestingClass testingObj = null;
        try {
            testingObj = (TestingClass)cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Method beforeMethod = null;
        Method afterMethod = null;
        Method[] methods = cls.getMethods();
        List<MethodsPriority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeMethod != null)
                    throw new RuntimeException("Отсутсвует метод с анотацией @BeforeSuite");
                beforeMethod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod != null)
                    throw new RuntimeException("Отсутсвует метод с анотацией @AfterSuite");
                afterMethod = method;
            } else if (method.getAnnotation(Test.class) != null){
                Test annotationTst = method.getAnnotation(Test.class);
                testingMethods.add(new MethodsPriority(method, annotationTst.value()));
            }

        testingMethods.sort(
                Comparator.comparing(MethodsPriority::getPriority));

        try {
            if (beforeMethod != null)
                beforeMethod.invoke(testingObj);

            for (MethodsPriority methodWP : testingMethods)
                methodWP.method.invoke(testingObj);

            if (afterMethod != null)
                afterMethod.invoke(testingObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(value = 5)
    public void Test5() {
        System.out.println("Тест №5");
    }
    @Test(value = 3)
    public void Test2(){
        System.out.println("Тест №2");
    }
    @Test(value = 1)
    public void Test1(){
        System.out.println("Тест №1");
    }
    @BeforeSuite
    public void BeforeAll(){
        System.out.println("Метод исполняется перед всеми тестами");
    }
    @AfterSuite
    public void AfterAll() {
        System.out.println("Метод исполняется после всех тестов");
    }
}