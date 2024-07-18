package org.stringnull.utils.builder;

import com.google.auto.service.AutoService;
import com.google.common.collect.Streams;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("org.stringnull.utils.builder.BuilderProperty")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class BuilderPropertyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement e : annotations){

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                    "BUILDER PROPERTY ---> " + e.toString() + " " + e.getSimpleName().toString());
            Set<? extends Element> annotatedMethods = roundEnv.getElementsAnnotatedWith(e);

            //package name and classname
            Set<TypeElement> groupByPackage = annotatedMethods
                    .stream()
                    .map(Element::getEnclosingElement)
                    .filter(enc -> enc instanceof TypeElement)
                    .map(enc -> (TypeElement) enc)
                    .collect(Collectors.toSet());

            groupByPackage.forEach(pkgElement -> {

                log("Classname: " + pkgElement.getSimpleName());
                log("Packagename: " + pkgElement.getQualifiedName());

                Set<? extends Element> setters = annotatedMethods.stream()
                        .filter(element -> element.getEnclosingElement().toString().equals(pkgElement.getQualifiedName().toString()))
                        .collect(Collectors.toSet());
//
//                );
                setters.forEach(element -> {
                    log("Method: " + element.getSimpleName().toString());
                    log("Method Type: " + ((ExecutableType)element.asType()).getParameterTypes().toString());
                });
                try {
                    generateBuilderFile(pkgElement.getQualifiedName().toString(), pkgElement.getSimpleName().toString(), setters);
                }catch(IOException ioex){
                    ioex.printStackTrace();
                }

            });
        }
        return true;
    }

    private void log(String msg){
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }

    private void generateBuilderFile(String pkg, String name, Set<? extends Element> setters) throws IOException {
        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(pkg + "Builder");
        String className = name + "Builder";
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
            out.print("package ");
            out.print(pkg.substring(0, pkg.lastIndexOf('.')));
            out.println(";");
            out.println();
            out.print("import ");
            out.print(pkg);
            out.print(";");
            out.println();

            out.print("public class ");
            out.print(className);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(name);
            out.print(" object = new ");
            out.print(name);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(name);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setters.forEach(setter -> {
                String methodName = setter.getSimpleName().toString();
                String argumentType = ((ExecutableType)setter.asType()).getParameterTypes().toString();

                out.print("    public ");
                out.print(className);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");

        } catch (IOException exception){ exception.printStackTrace();}
    }
}
