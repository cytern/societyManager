<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <el-header><el-menu   background-color="#545c64"
                              text-color="#fff"
                              active-text-color="#ffd04b"  class="el-menu-demo" mode="horizontal">
            <el-menu-item index="1"><a href="${pageContext.request.contextPath}/stu/sList.do" target="_blank">社 团 中 心~</a></el-menu-item>
            <el-menu-item index="2"><a href="${pageContext.request.contextPath}/stu/nList.do">新 闻 公 告</a></el-menu-item>
           <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="3"><a href="${pageContext.request.contextPath}/admin/nManager.do">新 闻 管 理</a></el-menu-item></c:if>
            <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="4"><a href="${pageContext.request.contextPath}/admin/sManager.do">社 团 管 理</a></el-menu-item></c:if>
        </el-menu>
        </el-header>
        <el-main>
            <el-row :gutter="20">
                <c:forEach items="${society}"   varStatus="status" var="item">
                <el-col :span="8" >
                    <el-card class="box-card" style="margin-top: 20px">
                        <div slot="header" class="clearfix">
                            <span>${item.societyName}</span>
                            <span style="font-size: 10px;margin-right: 10px;color: darkgoldenrod">创建时间: <fmt:formatDate value="${item.CTime}" pattern="yyyy/MM/dd  HH:mm" /></span>
                        </div>
                        <div class="text item">
                                  <p>社团简介：${item.conf}</p>
                                  <p>基本宗旨: ${item.purpose}</p>
                            社团荣誉:<c:forEach items="${item.SHonors}" var="honor" varStatus="honorIndex">
                                    <el-tag style="margin-right: 10px" type="success">${honor.SHonorName}</el-tag>
                                  </c:forEach>
                            <br>
                               活动列表:   <c:forEach items="${item.activities}" var="activity" varStatus="activityIndex">
                                      <div style="margin-right: 10px;display: block;  border: 1px solid;
    border-radius: 10%;border-image: linear-gradient(to right,#ff831c, #0ceebb) 20 20;height: 100px">
                                          <el-tooltip class="item" style="display: inline-block;margin-top: -10px" effect="dark" content="活动内容:${activity.conf}" placement="top">
                                           <el-button style="display: inline-block;width: 80px;height: 80px;margin-top: -10px">${activity.activityName}</el-button>
                                          </el-tooltip>
                                          <div style="margin-left: 100px;font-size: 10px;margin-top: 40px;display: inline-block">
                                              开始时间:   <fmt:formatDate value="${activity.STime}" pattern="yyyy/MM/dd  HH:mm" /><br>
                                              结束时间:   <fmt:formatDate value="${activity.ETime}" pattern="yyyy/MM/dd  HH:mm" />
                                          </div>
                                      </div>
                                  </c:forEach>
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
                societyList: [],

            }
        },
        created: function(){

        },
        methods:{
            getSocietyList: function () {
                axios.get('http://localhost:8080/stu/getBackS', {
                    params: {

                    }
                })
                    .then(function (res) {
                       this.societyList = res.data
                        console.log(this.societyList)
                    })
                    .catch(function (error) {
                        //失败的回调
                    });

            }
        }
    })
</script>
</html>
