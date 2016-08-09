package sunnyxiaobai5.core.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class HibernateNamingStrategy extends ImprovedNamingStrategy {

    //不注解的将由class或property名称转换
    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className).toUpperCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName).toUpperCase();
    }

    //注解的将由注解table或column的name属性转换
    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName).toUpperCase();
    }

    @Override
    public String columnName(String columnName) {
        return super.columnName(columnName).toUpperCase();
    }
}
