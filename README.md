#### AUTO-WORD使用介绍  

##### 技术基础  

> 该项目主要实现根据Java Model属性值自动绑定到doc文档，技术基础依靠freemarker。  

##### 开始环境  

|    lib    | version |
| ---------- | --- |
| spring-boot-starter-freemarker |  不低于2.0.1.RELEASE |
| fastjson       |  不低于1.2 |   
| lombok |*|  
|commons-io|不低于2.4|  
|commons-lang3|不低于3.7|  

##### 扩展介绍  

注:若代理参数为null则返回字节数组
> 该项目运行成果是doc文档字节数组，在handler模块中对该数组进行了结果代理扩展设计，实现ResultActionProxy，扩展自定义代理，进行绑定结果平滑处理(eg:上传至oss等，web模块test实现了oss上传案例，可进行参照)。对于doc字节数组获取方面
>本项目提供了同步和异步两种方式，同步暂不介绍，可自行观阅。关于异步调用，在handler模块utils中可查线程工厂、线程池、拒绝策略、静态资源单例等实现范式，关于异步执行和状态跟踪结果回调等链路在web模块test下已实现案例。

##### 使用帮助   

1. 创建需要的word文档，关键字进行占位符设计(${variable})，对文档编辑完成后进行保存(注意:保存格式选择xml而非doc)，保存完毕后将文档拷贝到项目启动模块下resources/templates,然后修改文件后缀.xml----->.ftl   
2. 创建Model，该model内部属性字段名称必须与上文档中占位符相对应，该model必须继承TemplateBaseModel。且类名需与上文档名相对应(保持一致)
3. 完成以上两步后，即可结合业务进行调用。   

##### 自定义类型介绍  

> 该项目扩展了可填充字段类型，当前支持checkbox(多选)、DateSlice(页脚日期署名)、ImageData(图片填充)  
> 若想使用扩展类型，则word文档中占位符不用改动，只需要将model对应的属性的类型修改为扩展类型即可。  

##### 案例  

> web模块下test中实现了异步多图片扩展类型且结果使用代理案例。  


