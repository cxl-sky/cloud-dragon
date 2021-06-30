<template>
  <div class="app-container">
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="角色名称">
        <el-input v-model="searchForm.roleNameLike" placeholder="请输入" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="success" @click="onSubmit">新增</el-button>
        <el-button type="danger" @click="onSubmit">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-table
        v-loading="listLoading"
        :data="records"
        element-loading-text="加载中"
        border
        fit
        highlight-current-row
      >
        <el-table-column
          align="center"
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column align="center" label="序号" width="95">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="角色名称" align="center">
          <template slot-scope="scope">
            {{ scope.row.roleName }}
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center">
          <template slot-scope="scope">
            {{ scope.row.remark }}
          </template>
        </el-table-column>
        <!--        <el-table-column align="center" prop="created_at" label="Display_time" width="200">-->
        <!--          <template slot-scope="scope">-->
        <!--            <i class="el-icon-time"/>-->
        <!--            <span>{{ scope.row.display_time }}</span>-->
        <!--          </template>-->
        <!--        </el-table-column>-->
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text">编辑</el-button>
            <el-button type="text" style="color: red">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" justify="end">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total" :current-page="pageNum" :page-size="pageSize">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import {rolePage} from "@/api/role";

export default {
  filters: {},
  data() {
    return {
      records: null,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      listLoading: true,
      searchForm: {
        roleNameLike: ''
      }
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {
  },
  methods: {
    fetchData() {
      this.listLoading = true
      let param = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        ...this.searchForm
      }
      rolePage(param).then(({data}) => {
        this.records = data.records
        this.total = data.total
        this.listLoading = false
      }).catch(() => {
        this.listLoading = false
      })
    },
    onSubmit() {
      this.fetchData();
    }
  }
}
</script>
<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.user-avatar {
  cursor: pointer;
  width: 50px;
  height: 50px;
  border-radius: 10px;
}
</style>
