package sunnyxiaobai5.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExportAnnotation {
    /**
     * 导出的表格表头
     *
     * @return
     */
    String name() default "";
}
