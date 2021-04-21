<!--
 * @Description: 
 * @Autor: niumiaomiao
 * @Date: 2020-03-30 21:29:28
 * @LastEditors: fuping
 * @LastEditTime: 2020-07-23 17:05:42
 -->
<template>
  <div :class="RoleManage.container">
    <BorderBox title="角色列表" type="table">
      <VForm :option="formObj" ref="form"></VForm>
      <div :class="RoleManage.listBox">
        <div :class="RoleManage.singleList" v-for="item in roleList" :key="item.code">
          <div :class="RoleManage.title">角色名称：{{ item.name }}</div>
          <div :class="RoleManage.operatorBox">
            <div :class="RoleManage.operatorDetail" @click="handleLookRole(item.code, item.name)">
              <i :class="['el-icon-view', RoleManage.iconSize]"></i>
              查看
            </div>
            <div :class="RoleManage.operatorDetail" @click="showEditRole(item.code, item.name)">
              <i :class="['el-icon-edit-outline', RoleManage.iconSize]"></i>
              编辑
            </div>
            <div :class="RoleManage.operatorDetail" @click="handleDeleteRole(item.code)">
              <i :class="['el-icon-delete', RoleManage.iconSize]"></i>
              删除
            </div>
          </div>
        </div>
      </div>
    </BorderBox>
    <el-dialog :title="dialogTitle" :visible.sync="dialogShow" width="40%">
      <VForm :option="diaFormObj" ref="diaForm"></VForm>
      <el-tree
        ref="tree"
        v-loading="loading"
        :data="treeData"
        :props="designTree"
        :accordion="true"
        node-key="id"
        :default-checked-keys="checkDatas"
        show-checkbox
        @check-change="changeNode"
      ></el-tree>
      <span slot="footer" class="dialog-footer" v-if="switchBtn == 'look'">
        <el-button type="primary" @click="dialogShow = false">关闭</el-button>
      </span>
      <span slot="footer" class="dialog-footer" v-if="switchBtn == 'edit'">
        <el-button type="primary" @click="handleEditRole">保存</el-button>
        <el-button @click="dialogShow = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Ref } from 'vue-property-decorator'
import BorderBox from '@/components/Common/BorderBox.vue'
import { trim } from 'lodash'
import { http } from '@/common/request'
const UrlRoleList = '/userManage/selectRole'
const UrlLookRole = '/userManage/MenuByRoleId'
const UrlEditRole = '/userManage/setPermission'
const UrlDelRole = '/userManage/deleteRoleManage'
interface IPrams {
  code: number
  data: number[]
  message: string
}
@Component({
  components: {
    BorderBox
  }
})
export default class RoleManage extends Vue {
  @Ref() readonly form!: VP.VForm
  @Ref() readonly diaForm!: VP.VForm
  private loading = false
  private formObj: any = {
    inline: true,
    btnPos: 'right',
    items: [
      {
        label: '角色名称：',
        type: 'text',
        comOpt: {
          id: 'roleName',
          width: 150,
          placeholder: '请输入'
        }
      }
    ],
    btns: [
      {
        comOpt: {
          id: 'query',
          value: '查询',
          disabled: false,
          click: this.handleRoleQueryBtn
        }
      },
      {
        comOpt: {
          id: 'reset',
          value: '重置',
          disabled: false,
          click: this.handleRoleResetBtn
        }
      }
      // {
      //   comOpt: {
      //     id: 'add',
      //     value: '新增',
      //     disabled: false,
      //     click: this.handleRoleAddBtn
      //   }
      // }
    ]
  }
  private diaFormObj: any = {
    inline: true,
    items: [
      {
        label: '角色名称：',
        type: 'text',
        comOpt: {
          id: 'roleName',
          disabled: true,
          width: 150,
          placeholder: ' '
        }
      }
    ]
  }
  private dialogTitle = '角色编辑'
  private dialogShow = false
  private roleList: any[] = []
  private treeData: any = []
  private switchBtn = ''
  private checkData: any = []
  private checkDatas: any = []
  private pramData = ''
  private disableData: any = []
  private editId = ''
  private isEdit = false
  private designTree: any = {
    label: 'name',
    children: 'sonmenu'
  }
  mounted() {
    this.handleRoleQueryBtn()
  }
  handleRoleQueryBtn() {
    const roleName = trim(this.form.getValue().roleName)
    // 角色列表
    http
      .post<IPrams>(UrlRoleList, {
        roleName: roleName
      })
      .then((resp: any) => {
        const { data } = resp
        this.roleList = data
      })
  }
  handleRoleResetBtn() {
    this.form.clearValue()
  }
  // handleRoleAddBtn() {
  //   this.dialogTitle = '新增角色'
  //   this.dialogShow = true
  // }
  handleLookRole(id, name) {
    this.dialogTitle = '角色查看'
    this.switchBtn = 'look'
    this.dialogShow = true
    setTimeout(() => {
      this.diaForm.setValue([{ id: 'roleName', value: name }])
    }, 10)
    this.treeData = []
    this.loading = true
    this.searchTreeData(id)
  }
  // 权限列表查询
  searchTreeData(id) {
    http
      .post<IPrams>(UrlLookRole, {
        roleid: id
      })
      .then((resp: any) => {
        const { data } = resp
        this.treeData = data
        this.checkData = []
        this.ergodicRouterData(data).map((item: any) => {
          if (item.checked) {
            this.checkData.push(item.id)
            const newArr = []
            this.checkData.forEach(its => {
              this.checked(its, this.treeData, newArr)
            })
            this.checkDatas = newArr
            this.pramData = this.checkData.join()
          }
        })
        this.loading = false
      })
  }
  checked(id, data, newArr) {
    data.forEach(item => {
      if (item.id == id) {
        if (item.sonmenu.length == 0) {
          newArr.push(item.id)
        }
      } else {
        if (item.sonmenu.length != 0) {
          this.checked(id, item.sonmenu, newArr)
        }
      }
    })
  }
  // 返回树形所有节点
  ergodicRouterData(data) {
    let cArr = []
    const mArr: any = []
    function routerEach(item) {
      cArr = cArr.concat(item)
      item.forEach(it => {
        if (it['sonmenu'] instanceof Array && it['sonmenu'].length > 0) {
          routerEach(it['sonmenu'])
        } else {
          return cArr
        }
      })
    }
    data.forEach(item => {
      if (item['sonmenu'] instanceof Array && item['sonmenu'].length > 0) {
        routerEach(item['sonmenu'])
      } else {
        mArr.push(item)
      }
    })
    return cArr.concat(mArr)
  }
  showEditRole(id, name) {
    this.dialogTitle = '角色编辑'
    this.switchBtn = 'edit'
    this.treeData = []
    this.dialogShow = true
    setTimeout(() => {
      this.diaForm.setValue([{ id: 'roleName', value: name }])
    }, 10)
    this.editId = id
    this.loading = true
    // 查看
    this.searchTreeData(id)
  }
  handleEditRole() {
    if (this.isEdit) {
      // 编辑
      http
        .post<IPrams>(UrlEditRole, {
          roleId: this.editId,
          ids: this.pramData
        })
        .then((resp: any) => {
          if (resp.data) {
            this.$message({
              type: 'success',
              message: '修改成功'
            })
            this.dialogShow = false
          }
        })
    } else {
      this.dialogShow = false
    }
  }
  handleDeleteRole(id) {
    if (id === '1') {
      this.$message.error('管理员账号不允许删除')
    } else {
      this.$alert('是否该角色下的所有权限', '删除角色', {
        confirmButtonText: '确定',
        callback: action => {
          http
            .post(UrlDelRole, {
              roleId: id
            })
            .then((resp: any) => {
              if (resp.data) {
                this.$message({
                  type: 'success',
                  message: '删除成功'
                })
                this.searchTreeData(id)
              }
            })
        }
      })
    }
  }
  changeNode() {
    this.isEdit = true
    const treedom = this.$refs.tree as any
    const kids = treedom.getCheckedKeys()
    const parents = treedom.getHalfCheckedKeys()
    this.pramData = kids.concat(parents).join()
    // this.pramData = (this.$refs.tree as any).getCheckedKeys().join()
  }
}
</script>

<style module="RoleManage">
.container {
  width: 100%;
  height: 100%;
}
.listBox {
  width: 100%;
  height: calc(100% - 50px);
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  overflow-y: scroll;
}
.singleList {
  margin-bottom: 10px;
  width: 24%;
  height: 130px;
  margin-right: 1%;
  border: 1px solid #ebeef0;
  border-radius: 4px;
}
.title {
  height: 78px;
  line-height: 78px;
  background: #f3f4f8;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  padding-left: 10px;
}
.operatorBox {
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.operatorDetail {
  width: 33%;
  height: 52px;
  line-height: 52px;
  text-align: center;
  flex: 1;
  font-size: 12px;
  color: #2593f2;
}
.operatorDetail:hover {
  cursor: pointer;
  color: #259;
}
.iconSize {
  font-size: 12px;
}
</style>
