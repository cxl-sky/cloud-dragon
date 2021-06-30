<template>
  <div class="app-container">
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="searchForm.usernameLike" placeholder="请输入" clearable></el-input>
      </el-form-item>
      <el-form-item label="用户状态">
        <el-select v-model="searchForm.status" placeholder="请选择" clearable>
          <el-option label="启用" :value="1"></el-option>
          <el-option label="禁用" :value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户角色">
        <el-select v-model="searchForm.roleId" placeholder="请选择" clearable>
          <el-option v-for="role in roleList" :key="role.roleId"
                     :label="role.roleName" :value="role.roleId"></el-option>
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
        <el-table-column label="用户名" align="center">
          <template slot-scope="scope">
            {{ scope.row.username }}
          </template>
        </el-table-column>
        <el-table-column label="头像" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.avatar" class="user-avatar">
          </template>
        </el-table-column>
        <el-table-column label="用户角色" align="center">
          <template slot-scope="scope">
            {{ roleNameShow(scope.row.roles) }}
          </template>
        </el-table-column>
        <el-table-column label="用户状态" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status === 1 ? '启用' : '禁用' }}</el-tag>
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
import {userPage} from "@/api/user";
import {rolePage} from "@/api/role";

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
        usernameLike: '',
        status: '',
        roleId: ''
      },
      roleList: []
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {
    this.getRoles()
  },
  methods: {
    roleNameShow(roles) {
      return roles.map(role => role.roleName).join(",")
    },
    fetchData() {
      this.listLoading = true
      let param = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        ...this.searchForm
      }
      userPage(param).then(({data}) => {
        this.records = data.records
        this.total = data.total
        this.listLoading = false
      }).catch(() => {
        this.listLoading = false
      })
    },
    getRoles() {
      let param = {pageNum: 1, pageSize: 99999}
      rolePage(param).then(({data}) => {
        this.roleList = data.records
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
