# SAP请求工具
## 2.0版本更新
其实目前的这个代码已经是2.0版本了，之前写过一个1.0的版本，1.0版本呢，是单纯的为了重构历史代码，没有实际的使用经验和理解，所以1.0的代码是具有一定的局限性的。

所以在另外一个项目中，实际使用起来会发现一些问题：

1. 如果用1.0的代码使用`SapTemplate`对象，必须和一个SAP接口强绑定，也就是我无法用一个`SapTemplate`调用完接口1之后，根据接口1的结果来调用接口2，如果要这样的话，我必须重新new一个新的SapTemplate对象。
2. 框架的使用是基于使用者对框架非常熟悉的基础之上的，比如在调用`execute()`之前，必须先调用了`function()`方法（之前的版本，这个方法用来获取`JCoFunction`对象），否则的话，在调用`execute()`的时候就会报出空指针异常，而且这种情况的话框架无法避免，只能寄希望于使用者。
3. 1.0版本创建连接的方式是固定的（默认的创建方式），但是可能有的公司自己封装了创建连接的方法，那这个时候框架就派不上用场了。

基于以上几个问题，我就想着对1.0版本的代码再次进行一个重构，希望解决以上的问题，让这个框架变得更好用。

## 快速开始

**配置实体类**

```java
public class SapWorkshopSection {
    @SapField("VBELN") // <1>
    private String vbeln;

    @SapField("KTEXT")
    private String ktext;

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) { // <2>
        this.vbeln = vbeln;
    }

    public String getKtext() {
        return ktext;
    }

    public void setKtext(String ktext) {
        this.ktext = ktext;
    }
}
```

1. 配置SAP返回的字段名；
2. 反射会通过set方法设置值，所以一定要生成字段的setter（也可以自定义一个set方法，具体看后面的介绍）。

**请求数据**

```java
import com.curry.sap.conn.SapTemplate;

public class SampleSync {
    public void sync() {
        SapTemplate.connect().function("function").execute();
    }
}
```

## 一些细节

#### 自定义连接方式

```java
import com.curry.sap.conn.SapTemplate;

public class ConnectSample {
    public void sample() {
        SapTemplate.connect(name -> null);
        SapTemplate.connect("sample", name -> null);
    }
}
```

#### 日期类型转换

目前适配了Date、LocalDate、LocalTime、LocalDateTime。

```java
public class Demo {
    @SapField(value = "date", formatter = "yyyyMMdd")
    private LocalDate billDate;
}
```

#### 指定set方法

默认框架是以setXxxx作为setter，如果需要自定义，可以使用注解配置。

```java
public class Demo {
    @SapField(value = "number", setter = "number")
    private String number;

    private void number(String number) {
        this.number = number;
    }
}
```

#### 自定义数据转换器

比如SAP返回的字段是字符串，Java端需要按照具体的值，映射成枚举类型或者Boolean类型等等。

```java
import com.curry.sap.conn.SapField;

public class Demo {
    @SapField(value = "LGOUT", converter = CustomConverter.class)
    private String warehouse;
}
```
```java
public class CustomConverter implements Converter<String> {
    @Override
    public String convert(Object obj) {
        return "自定义转换" + obj.toString();
    }
}

```

## 一些取舍

其实`JCoRecord`对象里还有`JCoStructure`对象，用`getStructure()`来获取，之前的1.0版本针对这个类型做了适配，但是实际使用中发现，SAP一般不会返回这种数据格式，要么就是返回表格式，要么就是返回多个单个字段，不会额外的再封装一层，所以在2.0版本中我反而是把这个功能拿掉了。

如果后续发现真的有这种情况再加回来吧。

然后这个框架也没有加入配置文件生成的功能，因为可能大部分公司已经有了自己的配置，然后在调用的时候或者启动项目的时候通过配置就自动生成了文件，又或者有的公司是直接按照版本直接将配置文件都添加到项目中，所以考虑到这些，感觉可能这个功能不是特别的重要，甚至有可能是冗余的功能。