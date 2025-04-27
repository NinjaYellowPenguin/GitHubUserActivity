package glaciar.anotaciones;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.Set;

//TODO: Por el momento no está en uso, no aporta nada

@SupportedAnnotationTypes("penguin.PenguinAttribute")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class PenguinProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(PenguinAttribute.class)) {
            Element enclosingElement = element.getEnclosingElement();

            if (enclosingElement.getAnnotation(PenguinEntity.class) == null) {
                processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.ERROR,
                    "The field '" + element.getSimpleName() + "' uses @PenguinAttribute, but the class '" 
                    + enclosingElement.getSimpleName() + "' is not a @PenguinEntity.",
                    element
                );
            }
        }
        return true;
    }
}
