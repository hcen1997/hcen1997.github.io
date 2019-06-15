<template>
    <div>
        <h1>文件上传</h1>
        <el-upload class="upload-demo" action="http://localhost:8002/api/file-upload/upload" :on-preview="handlePreview"
            :on-remove="handleRemove" multiple :limit="99" :on-exceed="handleExceed" :file-list="fileList" :on-success="getFileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">单个文件大小不超过128MB</div>
        </el-upload>
        <el-divider></el-divider>
        <!--        下载列表-->
        <h1>文件下载</h1>
        <el-table :data="fileData" border style="width: 100%">
            <el-table-column fixed prop="name" label="文件名" align="center">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100" align="center">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">下载</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "File",
        data() {
            return {
                fileList: [],
                fileData: []
            };
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(
                    `当前限制选择 99 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            handleClick(row) {
                location.href = row.url
            },
            getFileList() {
                this.fileData = []
                this.axios.get('http://localhost:8002/api/file-download/list')
                    .then(response => {
                        const strList = response.data
                        strList.forEach(element => {
                            const name = element.substring(element.lastIndexOf('/') + 1)
                            const url = element
                            this.fileData.push({
                                name,
                                url
                            })
                        });
                        this.fileData.sort()
                    })
            }
        },
        created() {
            this.getFileList()
        }
    }
</script>

<style scoped>
    h1 {
        text-align: left;
        margin-left: 20px;
    }
</style>