<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划时间" prop="planTime">
        <el-input
          v-model="queryParams.planTime"
          placeholder="请输入计划时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实际日期" prop="realTime">
        <el-input
          v-model="queryParams.realTime"
          placeholder="请输入实际日期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:outside:add']"
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
          v-hasPermi="['system:outside:edit']"
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
          v-hasPermi="['system:outside:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:outside:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-row>
      <el-col :span="7.5"  v-for="(item,index) in outsideList" :key="index" >
          <together-outside-item  :itemData="item"></together-outside-item>
      </el-col>
    </el-row>
    
    <!-- <el-table v-loading="loading" :data="outsideList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="计划时间" align="center" prop="planTime" />
      <el-table-column label="实际日期" align="center" prop="realTime" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="参与人员" align="center" prop="userList" />
      <el-table-column label="活动起因" align="center" prop="reason" />
      <el-table-column label="活动内容" align="center" prop="content" />
      <el-table-column label="图片链接" align="center" prop="imgUrls" />
      <el-table-column label="是否真是举办" align="center" prop="really" />
      <el-table-column label="备用字段" align="center" prop="other" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:outside:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:outside:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="计划时间" prop="planTime">
          <el-date-picker
            v-model="form.planTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际日期" prop="realTime">
          <el-date-picker
            v-model="form.realTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="参与人员" prop="userList">
          <el-input v-model="form.userList" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="活动起因" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="活动内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="图片链接" prop="imgUrls">
          <el-input v-model="form.imgUrls" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否真是举办" prop="really">
          <el-input v-model="form.really" placeholder="请输入是否真是举办" />
        </el-form-item>
        <el-form-item label="备用字段" prop="other">
          <el-input v-model="form.other" type="textarea" placeholder="请输入内容" />
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
import { listOutside, getOutside, delOutside, addOutside, updateOutside } from "@/api/system/outside";
import TogetherOutsideItem from './TogetherOutsideItem.vue'
export default {
  components: { TogetherOutsideItem },
  name: "Outside",
  data() {
    return {
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
      // 活动表格数据
      outsideList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planTime: null,
        realTime: null,
        address: null,
        userList: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动列表 */
    getList() {
      this.loading = true;
      listOutside(this.queryParams).then(response => {
        this.outsideList = response.rows;
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
        planTime: null,
        realTime: null,
        address: null,
        userList: null,
        reason: null,
        content: null,
        imgUrls: null,
        really: null,
        other: null
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
      this.title = "添加活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOutside(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOutside(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOutside(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除活动编号为"' + ids + '"的数据项？').then(function() {
        return delOutside(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/outside/export', {
        ...this.queryParams
      }, `outside_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
