# 代码目录说明

## 后端服务器

所有源代码位于`./demo/`目录下。

打包好的可直接运行的jar包为当前目录下的`demo.jar`。

**需要至少JDK/JRE 11版本的Java支持**。项目使用JBR开发。

服务器启动后，默认运行于`localhost:8097`。请确保端口未被占用。

后端服务器提供的端口汇总于`./httpMAPfunction.md`。



## 前端服务器

所有源代码位于`./my_theme/`目录下。

需要安装`node.js`和`vue-cli`。

在`./my_theme/`目录下，运行：

- ```powershell
  npm install
  npm run serve
  ```

  来启动服务器。

服务器启动后，命令行中将给出运行的ip及端口。



## MySQL服务器

进行开发和演示时，后端服务器均连接到了部署于`217.69.9.116:3306`的MySQL服务器，数据库名为`DBprj`。该服务器由个人租赁和搭建，可能随时失效。

数据库的配置定义于`.\demo\src\main\resources\application.properties`，可以按照需求更改。

截止至展示时，从数据库中导出的SQL脚本为当前目录下的`./dump.sql`。