package com.andy.complier;

import com.andy.annotation.BindView;
import com.andy.annotation.onClick;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class ButterKnifeProcess extends AbstractProcessor {

    private Elements elementUtils;//Elements中包含用于操作的工具
    private Filer filer;//用于创建新的源文件，class文件以及其他


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
    }



    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //添加支持bindview,onclick注解的类型
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(onClick.class.getCanonicalName());

        return types;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        //返回JDK的版本
        return SourceVersion.RELEASE_8;
    }

    //注解处理器的核心方法，处理具体的注解，生成新的Java Class文件
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //write


        try {
            JavaFileObject javaFileObject = filer.createSourceFile("MainActivity$ViewBinder");
            Writer writer = javaFileObject.openWriter();

            writer.write("package com.andy.customview;");
            writer.write("\n");
            writer.write("import com.andy.library.ViewBinder;");
            writer.write("public class MainActivity$ViewBinder implements ViewBinder<MainActivity> {");
            writer.write("\n");
            writer.write("public void bind(MainActivity target) {");
            writer.write("\n");
            writer.write("target.mImageView = target.findViewById(R.id.tv_hello);");
            writer.write("\n");
            writer.write("}\n}");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
