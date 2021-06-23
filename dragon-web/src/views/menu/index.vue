<template>
  <div class="app-container">
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="菜单名称">
        <el-input v-model="searchForm.titleLike" placeholder="请输入" clearable></el-input>
      </el-form-item>
      <el-form-item label="菜单权限">
        <el-input v-model="searchForm.permsLike" placeholder="请输入" clearable></el-input>
      </el-form-item>
      <el-form-item label="菜单类型">
        <el-select v-model="searchForm.type" placeholder="请选择" clearable>
          <el-option label="目录" :value="0"></el-option>
          <el-option label="菜单" :value="1"></el-option>
          <el-option label="按钮" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="菜单状态">
        <el-select v-model="searchForm.status" placeholder="请选择" clearable>
          <el-option label="隐藏" :value="0"></el-option>
          <el-option label="显示" :value="1"></el-option>
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
        row-key="menuId"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column
          align="center"
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column align="center" label="菜单id" width="95">
          <template slot-scope="scope">
            {{ scope.row.menuId }}
          </template>
        </el-table-column>
        <el-table-column label="菜单名称">
          <template slot-scope="scope">
            {{ scope.row.title }}
          </template>
        </el-table-column>
        <el-table-column label="前端组件" align="left">
          <template slot-scope="scope">
            <span>{{ scope.row.component }}</span>
          </template>
        </el-table-column>
        <el-table-column label="菜单路由" align="center">
          <template slot-scope="scope">
            {{ scope.row.url }}
          </template>
        </el-table-column>
        <el-table-column label="菜单代码" align="center">
          <template slot-scope="scope">
            {{ scope.row.code }}
          </template>
        </el-table-column>
        <el-table-column label="菜单权限" align="center">
          <template slot-scope="scope">
            {{ scope.row.perms }}
          </template>
        </el-table-column>
        <el-table-column label="菜单图标" align="center">
          <template slot-scope="scope" v-if="scope.row.icon">
            <i v-if="scope.row.icon.includes('el-icon')" :class="scope.row.icon" />
            <svg-icon v-else :icon-class="scope.row.icon" />
          </template>
        </el-table-column>
        <el-table-column label="菜单状态" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status === 1 ? '显示' : '隐藏' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="菜单顺序" align="center">
          <template slot-scope="scope">
            {{ scope.row.orderNum }}
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
import {menuPage} from "@/api/menu";

export default {
  filters: {
    statusFilter(needAuth) {
      const statusMap = {
        1: 'success',
        0: 'danger'
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
        titleLike: '',
        permsLike: '',
        type: '',
        status: '',
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
      menuPage(param).then(({data}) => {
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
