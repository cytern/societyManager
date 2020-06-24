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
    <el-container>
        <el-header>
            <el-menu   background-color="#545c64"
                       text-color="#fff"
                       active-text-color="#ffd04b"  class="el-menu-demo" mode="horizontal">
                <el-menu-item index="1"><a href="${pageContext.request.contextPath}/stu/sList.do" >社 团 中 心</a></el-menu-item>
                <el-menu-item index="2"><a href="${pageContext.request.contextPath}/stu/nList.do" target="_blank">新 闻 公 告~</a></el-menu-item>
                <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="3"><a href="${pageContext.request.contextPath}/admin/nManager.do">新 闻 管 理</a></el-menu-item></c:if>
                <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="4"><a href="${pageContext.request.contextPath}/admin/sManager.do">社 团 管 理</a></el-menu-item></c:if>
            </el-menu>
        </el-header>
        <el-main>
            <el-row :gutter="20">
                <c:forEach items="${news}"   varStatus="status" var="item">
                    <el-col :span="8" >
                        <el-card class="box-card" style="margin-top: 20px">
                            <div slot="header" class="clearfix">
                                <span>${item.newsName}</span>
                                <span style="font-size: 10px;margin-right: 10px;color: darkgoldenrod;text-align: center">创建时间: <fmt:formatDate value="${item.cTime}" pattern="yyyy/MM/dd  HH:mm" /></span>
                                <span style="color: #af0163;text-align: right">${item.newsType eq "news"?'新闻':'公告'}</span>

                            </div>
                            <div class="text item">
                                   <div ><p>内容:${item.conf}</p>
                                       <p style="color: blueviolet">发布者：${item.startName}</p>
                                   </div>

                            </div>
                        </el-card>
                    </el-col>
                </c:forEach>
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
                    username: null,
                    password: null
                }

            }
        },
        methods:{
            onSubmit: function () {
                axios.get('http://localhost:8080/', {
                    params: {

                    }
                })
                    .then(function (res) {
                        //成功的回调
                    })
                    .catch(function (error) {
                        //失败的回调
                    });

            }
        }
    })
</script>
</html>
