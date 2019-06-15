<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
             :router="true">
        <el-menu-item index="/article">文章</el-menu-item>
        <el-menu-item index="/file">文件上下</el-menu-item>
        <!--                <el-submenu index="2">-->
        <!--                    <template slot="title">我的工作台</template>-->
        <!--                    <el-menu-item index="2-1">选项1</el-menu-item>-->
        <!--                    <el-submenu index="2-4">-->
        <!--                        <template slot="title">选项4</template>-->
        <!--                        <el-menu-item index="2-4-1">选项1</el-menu-item>-->
        <!--                    </el-submenu>-->
        <!--                </el-submenu>-->
        <el-menu-item index="/message">快速留言版</el-menu-item>
        <el-menu-item index="/about">设置</el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "Header",
        data(){
            return{
                activeIndex: '1'
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
#web-name{
    font-weight: bold;
    display: inline;
}
</style>