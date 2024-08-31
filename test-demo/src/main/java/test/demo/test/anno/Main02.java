package test.demo.test.anno;

import java.lang.annotation.Annotation;

public class Main02 {
    public static void main(String[] args) {
        // Annotation[] annotations = Anno01.class.getAnnotations();
        // for (Annotation annotation : annotations) {
        //     Class<? extends Annotation> annotationType = annotation.annotationType();
        //     String aPackageName = annotationType.getPackage().getName();
        //     System.out.println(aPackageName);
        // }

        Annotation[] annotations1 = Anno02.class.getAnnotations();
        for (Annotation annotation : annotations1) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            String aPackageName = annotationType.getPackage().getName();
            System.out.println(aPackageName);
        }

        // Anno01 anno01 = Anno02.class.getAnnotation(Anno01.class);
    }
}
