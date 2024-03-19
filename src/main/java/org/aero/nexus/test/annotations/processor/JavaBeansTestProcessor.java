// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package org.aero.nexus.test.annotations.processor;

import com.google.auto.service.AutoService;
import org.aero.nexus.test.annotations.JavaBeansTest;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

@SupportedAnnotationTypes("org.aero.nexus.test.annotations.JavaBeansTest")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class JavaBeansTestProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(JavaBeansTest.class).forEach(this::handleAnnotatedClass);

        return true;
    }

    private void handleAnnotatedClass(Element element) {
        PackageElement packageElement = ((PackageElement) element.getEnclosingElement());
        String packageName = packageElement.getQualifiedName().toString();
        TypeElement testClass = (TypeElement) element;
        JavaBeansTest annotation = testClass.getAnnotation(JavaBeansTest.class);
        Class<? extends Serializable> clazzToTest = annotation.value();
        Arrays.stream(clazzToTest.getDeclaredFields()).forEach(
                field -> {
                    processingEnv.getFiler();
                }
        );
        //processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class to test: %s".formatted(annotation.value()), element);
    }
}
