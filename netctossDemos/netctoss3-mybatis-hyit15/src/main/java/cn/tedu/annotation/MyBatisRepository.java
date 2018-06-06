package cn.tedu.annotation;

/**
 * 默认情况下，MapperScannerConfigurer扫描
 * 		包中的所有接口
 * 如果不希望扫描所有的接口，配置MSC时，声明，
 * 		只扫描带有特定注解的接口。
 * 
 * 注意：该注解，需要我们程序员定义，
 * 		只作标记作用，没有具体内容
 */
public @interface MyBatisRepository {

}
