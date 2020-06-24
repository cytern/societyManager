<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/everyone/downLoad/index.css">
    <title>Title</title>
</head>
<body>
<div id="app">
    <el-container style="width: 100%;height: 100%">
        <el-header><img src="${pageContext.request.contextPath}/everyone/downLoad/banner.png" style="width: 100%;height: 200px"></el-header>
        <el-main style="margin-top: 300px">
            <el-row>
                <el-col :span="7"><div> <br></div></el-col>
                <el-col :span="10"><div class="grid-content bg-purple-dark" style="text-align: center;margin: auto">
                    <c:if test="${requestScope.msg !=null}">
                        <p style="color: red">${requestScope.msg}</p>
                    </c:if>
              <div><p style="color: red" id="msg"><p/></div>
                    <el-form ref="form" :model="form">
                        <el-form-item label="用户名">
                            <el-input  v-model="form.username"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input  v-model="form.password"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">立即登录</el-button>
                        </el-form-item>
                    </el-form>
                </div></el-col>

            </el-row>
        </el-main>

    </el-container>
</div>
</body>
<script src="${pageContext.request.contextPath}/everyone/downLoad/vue.js"></script>
<!-- 引入组件库 -->
<script src="${pageContext.request.contextPath}/everyone/downLoad/index.js"></script>
<script src="${pageContext.request.contextPath}/everyone/downLoad/min.js"></script>
<script src="${pageContext.request.contextPath}/everyone/downLoad/axios.js"></script>
<script>
    new Vue({
        el: '#app',
        data: function() {
            return {
                visible: false,
                form:{
                    username: "abb" ,
                    password: "abb"
                },
                msg: null,

            }
        },
        methods:{
            onSubmit: function () {
                axios.get('http://localhost:8080/everyOne/login', {
                    params: {
                        username: this.form.username,
                        password: this.form.password
                    }
                })
                    .then(function (res) {
                      if (res.data.success){
                              window.location="http://localhost:8080/stu/sList.do"
                      }else {
                         this.msg = res.data.error
                          document.getElementById("msg").innerHTML = res.data.error
                      }
                    })
                    .catch(function (error) {

                    });

            }
        }
    })
</script>
</html>
