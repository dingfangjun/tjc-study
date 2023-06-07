<template>
  <div class="app-container">
    <span
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</span>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="date">
      <el-date-picker
      v-model="queryParams.date"
      type="date"
      placeholder="选择日期"
      format="yyyy 年 MM 月 dd 日"
      value-format="yyyy-MM-dd">
    </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:logs:list']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:logs:list']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:logs:list']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:logs:list']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logsList" @selection-change="handleSelectionChange">

      <el-table-column
       width="75"
      label="周几">
      <template slot-scope="scope">
        <el-tag>{{getWeekDayByDate(scope.row)}}</el-tag>
      </template>
    </el-table-column>
      <el-table-column  width="150" label="日期" align="center" prop="date" />
      <el-table-column
      label="打卡人员"
      width="500">
      <template slot-scope="scope">
        <span>{{getUserNameByIds(scope.row)}}</span>
      </template>
    </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:logs:list']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:logs:list']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改周六记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="日期" prop="date">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="打卡人员" prop="userList">
          <!-- <el-input v-model="form.userList" type="textarea" placeholder="请输入内容" /> -->
          <user-select-component v-model="form.userList" :userListStr="form.userList"></user-select-component>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLogs, getLogs, delLogs, addLogs, updateLogs } from "@/api/system/logs";
// import UserSelectComponent from '@/components/UserSelectComponent'
import UserSelectComponent from '../../components/UserSelectComponent.vue';
export default {
  name: "Logs",
  dicts: ['sys_user_list'],
  components:{UserSelectComponent},
  data() {
    return {
      dicts: ['sys_user_list'],
      selectUserListArray:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 周六记录表格数据
      logsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        date: null,
        userList: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getUserListOptions()

  },
  methods: {
    /** 查询周六记录列表 */
    getList() {
      this.loading = true;
      listLogs(this.queryParams).then(response => {
        this.logsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        date: null,
        userList: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加周六记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLogs(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改周六记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
        //  let tmpStr = this.selectUserListArray.toString()
        //   this.form.userList = tmpStr

          if (this.form.id != null) {
            updateLogs(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLogs(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除周六记录编号为"' + ids + '"的数据项？').then(function() {
        return delLogs(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/logs/export', {
        ...this.queryParams
      }, `logs_${new Date().getTime()}.xlsx`)
    },
    getUserNameByIds(rowData){

      let _this = this
      let userList = rowData.userList
      let tmpArray = userList.split(',')
      let resString = ""
      if(Array.isArray(tmpArray) && tmpArray.length>0 ){
        tmpArray.forEach(item =>{
          if(item.trim() !== ''){
            resString = resString + _this.getUserNameById(item)+','
          }
        })
      }
      if(resString.endsWith(',')){
        resString = resString.substring(0,resString.length-1)
      }
      return resString
    },
    getWeekDayByDate(rowData){
      let _this = this
      let date = rowData.date

        var dateArray = date.split("-");
        date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
        return "周" + "日一二三四五六".charAt(date.getDay());
    },
    getUserNameById(id){
      let _this = this
      let arr = _this.userListOptions
      if(Array.isArray(arr) && arr.length>0){
        let name =""
        arr.forEach(item=>{
         if(item.dictValue == id) {
           name = item.dictLabel
         }
        })
        return name
      }
    },
    getUserListOptions(){
      let _this = this
      _this.getDicts("sys_user_list").then(response => {

       let tmpArray = response.data
       tmpArray.sort(function(a,b){

         return a.dictLabel.localeCompare(b.dictLabel)
       })
       _this.userListOptions = tmpArray
       _this.getList();
      });
    }
  },
  mounted(){

  }
};
</script>
