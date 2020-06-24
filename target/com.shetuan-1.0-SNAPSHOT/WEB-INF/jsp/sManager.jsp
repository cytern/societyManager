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
                <el-menu-item index="2"><a href="${pageContext.request.contextPath}/stu/nList.do" target="_blank">新 闻 公 告</a></el-menu-item>
                <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="3"><a href="${pageContext.request.contextPath}/admin/nManager.do">新 闻 管 理</a></el-menu-item></c:if>
                <c:if test="${user.userType eq 'admin'}"> <el-menu-item index="4"><a href="${pageContext.request.contextPath}/admin/sManager.do">社 团 管 理</a></el-menu-item></c:if>
            </el-menu>

        </el-header>
        <el-main>
            <el-button @click="addNews" type="primary">创建社团</el-button>
            <el-row :gutter="20">
                <el-col :span="3"><div class="grid-content bg-purple">~</div></el-col>
                <el-col :span="18">
                    <el-table
                            :data="news"
                            stripe
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="societyId"
                                label="社团id"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                label="社团名"
                                width="180">
                            <template slot-scope="scope">
                                <el-popover trigger="hover" placement="top">
                                    <p>简介 {{ scope.row.conf }}</p>
                                    <div slot="reference" class="name-wrapper">
                                        <el-tag size="medium">{{ scope.row.societyName }}</el-tag>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="purpose"
                                label="目标"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                label="创建 时间"
                                prop="cTime"
                                width="180">
                        </el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="changeData(scope)">编辑</el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="deleteData(scope)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
                <el-col :span="3"><div class="grid-content bg-purple">~</div></el-col>
            </el-row>
        </el-main>
        <el-dialog
                title="更新"
                :visible.sync="showChange"
                width="30%"
                :before-close="handleClose">
            <el-form ref="form" :model="tempNews" label-width="120px">
                <el-form-item label="社团id">
                    <el-input :disabled="true" v-model="tempNews.societyId"></el-input>
                </el-form-item>
                <el-form-item label="社团名">
                    <el-input v-model="tempNews.societyName"></el-input>
                </el-form-item>
                <el-form-item label="社团简介">
                    <el-input v-model="tempNews.conf"></el-input>
                </el-form-item>
                <el-form-item label="社团目标">
                    <el-input v-model="tempNews.purpose"></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input :disabled="true" v-model="tempNews.cTime"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="showChange = false">取 消</el-button>
    <el-button type="primary" @click="sendChange">确 定</el-button>
  </span>
        </el-dialog>


        <el-dialog
                title="添加"
                :visible.sync="showNew"
                width="30%"
                :before-close="handleClose">
            <el-form ref="form" :model="tempNews" label-width="120px">
                <el-form-item label="社团id">
                    <el-input :disabled="true" v-model="tempNews.societyId"></el-input>
                </el-form-item>
                <el-form-item label="社团名">
                    <el-input v-model="tempNews.societyName"></el-input>
                </el-form-item>
                <el-form-item label="社团简介">
                    <el-input v-model="tempNews.conf"></el-input>
                </el-form-item>
                <el-form-item label="社团目标">
                    <el-input v-model="tempNews.purpose"></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input :disabled="true" v-model="tempNews.cTime"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="showNew = false">取 消</el-button>
    <el-button type="primary" @click="sendAdd">确 定</el-button>
  </span>
        </el-dialog>
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
                news:[],
                tempNews:{
                 societyId: null,
                    societyName: null,
                    conf: null,
                    purpose: null,
                    imgUrl: null,
                    cTime: null
                },
                tempNews1:{
                    societyId: null,
                    societyName: null,
                    conf: null,
                    purpose: null,
                    imgUrl: null,
                    cTime: null
                },
                showChange: false,
                showNew: false,
            }
        },
        mounted: function(){
            this.onSubmit()
        },
        methods:{
            onSubmit: function () {
                let that = this
                axios.get('http://localhost:8080/admin/getSociety', {
                    params: {

                    }
                })
                    .then(function (res) {
                        that.news = res.data.news

                    })
                    .catch(function (error) {
                        //失败的回调
                    });

            },
            handleClose(done) {
                let that = this
                this.$confirm('确认关闭？')
                    .then(_ => {
                        that.tempNews = that.tempNews1
                    })
                    .catch(_ => {});
            },
            changeData (scope){
                this.showChange = true
                this.tempNews = scope.row
            },
            deleteData (scope){
                let that = this
                axios({
                    url: 'http://localhost:8080/admin/deleteSociety',
                    method: 'post',
                    data:scope.row,
                }).then(res =>{
                    that.onSubmit()
                    that.showChange = false
                    that.tempNews = that.tempNews1
                }).catch(error =>{
                    that.$message.error("网络错误")
                })
            },
            sendChange (){
                let that = this
                axios({
                    url: 'http://localhost:8080/admin/updateSociety',
                    method: 'post',
                    data:that.tempNews,
                }).then(res =>{
                    that.onSubmit()
                    that.showChange = false
                    that.tempNews = that.tempNews1
                }).catch(error =>{
                    that.$message.error("网络错误")
                })
            },
            sendAdd () {
                let that = this
                axios({
                    url: 'http://localhost:8080/admin/addSociety',
                    method: 'post',
                    data:that.tempNews,
                }).then(res =>{
                    that.onSubmit()
                    that.showNew = false
                    that.tempNews = that.tempNews1
                }).catch(error =>{
                    that.$message.error("网络错误")
                })
            },
            addNews() {
                this.showNew = true
                this.tempNews = this.tempNews1
            }
        }
    })
</script>
</html>
