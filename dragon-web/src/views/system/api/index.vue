<template>
  <div class="app-container">
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="接口URL">
        <el-input v-model="searchForm.urlLike" placeholder="请输入" clearable></el-input>
      </el-form-item>
      <el-form-item label="请求方式">
        <el-select v-model="searchForm.method" placeholder="请选择" clearable>
          <el-option label="GET" value="get"></el-option>
          <el-option label="POST" value="post"></el-option>
          <el-option label="PUT" value="put"></el-option>
          <el-option label="DELETE" value="delete"></el-option>
        </el-select>
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
        <el-table-column label="服务名">
          <template slot-scope="scope">
            {{ scope.row.service }}
          </template>
        </el-table-column>
        <el-table-column label="接口URL" align="left">
          <template slot-scope="scope">
            <span>{{ scope.row.url }}</span>
          </template>
        </el-table-column>
        <el-table-column label="请求方式" align="center">
          <template slot-scope="scope">
            {{ scope.row.method }}
          </template>
        </el-table-column>
        <el-table-column label="接口权限" align="center">
          <template slot-scope="scope">
            {{ scope.row.perms }}
          </template>
        </el-table-column>
        <el-table-column label="是否需要鉴权" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.needAuth | statusFilter">{{ scope.row.needAuth === 1 ? '是' : '否' }}</el-tag>
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
import {apiPage} from "@/api/api";

export default {
  filters: {
    statusFilter(needAuth) {
      const statusMap = {
        0: 'success',
        1: 'danger'
      }
      return statusMap[needAuth]
    }
  },
  data() {
    return {
      records: null,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      listLoading: true,
      searchForm: {
        method: '',
        urlLike: ''
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      let param = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        ...this.searchForm
      }
      apiPage(param).then(({data}) => {
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
</style>
