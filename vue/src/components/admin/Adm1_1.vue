<template>
  <div style="width:1100px">
  <el-row type="flex" justify="space-between" style="margin-bottom:5px">
  <el-col :span="3">
      <el-select v-model="deptSelected" placeholder="请选择学院" style="width:150px" size="small">
            <el-option
            v-for="item in deptOptions"
            :key="item.dpt"
            :label="item.dpt"
            :value="item.dpt">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="3">
        <el-select v-model="gradeSelected" placeholder="请选择年级" style="width:150px" size="small">
            <el-option
            v-for="item in gradeOptions"
            :key="item.grade"
            :label="item.grade"
            :value="item.grade">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="12">
    <el-input v-model="search" placeholder="请输入搜索内容"  size="small">
        <el-button slot="append" icon="el-icon-search" @click="searchOk">搜索</el-button>
    </el-input>
  </el-col>
     <el-button type="primary" size="small" @click="dialogFormVisible = true" icon="el-icon-plus">添加学生</el-button>
     <el-button type="primary" plain size="small" @click="exportExcel" icon="el-icon-folder-add">导入学生</el-button>
  </el-row>

  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-table id="tableId"
    :data="tables.slice((currentPage-1)*pageSize,currentPage*pageSize)"
    v-loading="loading"
    border
    stripe
    style="width: 100%"
    :header-row-style="{height:0+'px'}"
    :header-cell-style="{padding:0+'px'}"
    :row-style="{height:'20px'}"
    :cell-style="{padding:'2px'}">
    <el-table-column type="index" label="序号" width="59">
    </el-table-column>
    <el-table-column prop="id" label="学号" width="110">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="150">
    </el-table-column>
    <el-table-column prop="status" label="状态" width="60">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="50">
    </el-table-column>
    <el-table-column prop="classAndGrade" label="班级" width="100">
    </el-table-column>
    <el-table-column prop="department" label="学院" width="180">
    </el-table-column>
    <el-table-column prop="idCard" label="身份证号" width="200">
    </el-table-column>
    <el-table-column prop="birth" label="出生日期" width="110">
    </el-table-column>
    <el-table-column prop="political" label="政治面貌" width="120">
    </el-table-column>
    <el-table-column prop="graduate" label="毕业学校" width="200">
    </el-table-column>
    <el-table-column prop="telephone" label="电话号码" width="120">
    </el-table-column>
    <el-table-column fixed="right" prop="operate" label="操作" width="150">
      <template slot-scope="scope">
          <el-button size="mini" plain type="primary"
            @click="handleAdd(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" plain type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
    </el-table-column>
  </el-table>
  </div>
  <el-pagination
  background
  layout="prev, pager, next"
  :page-size="pageSize"
  :total="totalCount"
  :current-page="currentPage"
  @current-change="handleCurrentChange"
  style="text-align:center">
  </el-pagination>

  <el-dialog title="新增学生档案" :visible.sync="dialogFormVisible" close-on-click-modal=false width="60%">
  <el-form :model="form">
    <el-form-item>
      <el-col :span="12">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="姓名" :label-width="formLabelWidth" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option label="男" value="m"></el-option>
            <el-option label="女" value="f"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="毕业院校" :label-width="formLabelWidth" >
          <el-input v-model="form.graduate" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <div class="block">
          <span class="demonstration">出生日期</span>
          <el-date-picker
            v-model="form.birth"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="14">
        <el-form-item label="身份证号" :label-width="formLabelWidth" >
          <el-input v-model="form.idCard" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="政治面貌" :label-width="formLabelWidth">
          <el-select v-model="form.political" placeholder="请选择">
            <el-option label="群众" value="p1"></el-option>
            <el-option label="共青团员" value="p2"></el-option>
            <el-option label="入党积极分子" value="p3"></el-option>
            <el-option label="共产党员" value="p4"></el-option>
            <el-option label="其他党派人士" value="p5"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="电话号码" :label-width="formLabelWidth" >
          <el-input v-model="form.telephone" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="8">
        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="form.department" placeholder="请选择">
          <el-option
            v-for="item in deptOptions"
            :key="item.dpt"
            :label="item.dpt"
            :value="item.dpt">
          </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-select v-model="form.classAndGrade" placeholder="请选择">
          <el-option
              v-for="item in deptOptions"
              :key="item.dept"
              :label="item.label"
              :value="item.dept">
              </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="状态" :label-width="formLabelWidth" >
          <el-select v-model="form.status" placeholder="请选择">
            <el-option label="毕业" value="Graduation"></el-option>
            <el-option label="在校" value="AtSchool"></el-option>
            <el-option label="休学" value="Suspended"></el-option>
            <el-option label="退学" value="Dropped"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCourseData">提交</el-button>
  </div>
</el-dialog>
  </div>
</template>

<script>
import {getCookie} from '../global/cookie'
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
  export default {
    data() {
      return {
        deptOptions:[],
        deptSelected: '',
        gradeSelected:'',
        search: '',

        tableData:[],//目前前端所拥有的所有课程信息

        pageSize:17,
        currentPage:1,
        totalCount:1,
        loading:false,

        dialogFormVisible: false,
        form: {
          no:'',
          name: '',
          sex:'',
          graduate:'',
          birth:'',
          idCard: '',
          hours: '',
          credit: '',
          political:'',
          telephone:'',
          department:'',
          classAndGrade:'',
          status:'',
          pass:'',
        },
        
        formLabelWidth: '80px'
      }
    },
    computed:{
        gradeOptions(){
            let myData = new Date()
            var year1 = myData.getFullYear()
            let month1 = myData.getMonth()
            var options = []
            if(month1<8){
                year1--
            }
            for(var i=0;i<6;i++){
                options[i] = {
                    grade:year1,
                    label:year1+'级'
                }
                year1--
            }
            return options
        },
        tables () {
        const search = this.search
        if (search) {
          return this.tableData.filter(data => {
            return Object.keys(data).some(key => {
              return String(data[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        return this.tableData
      }
    },
    methods:{
      getDptName(){
        this.$axios
        .post('/dpt/getDptName', {})
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.deptOptions = result.data.datas
            }else{
              alert(result.data.msg)
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      //根据条件请求某一页数据
      getTableData(){
        this.$axios
        .post('/admin/stuAdmin', { //search为空,dept为all查询全部课程，search为空dept不为all查询某学院课程，
        //search不为空dept为空在所有课程中搜索，search和dept都不为空在某学院里搜索

        })
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.totalCount = result.data.datas.length
              this.tableData = result.data.datas
            }else{
              alert(result.data.msg)
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      handleCurrentChange(val) {
        this.currentPage = val
        this.getTableData(this.pageSize,val)
      },

      searchOk(){
        this.currentPage = 1
        this.getTableData(this.pageSize,1)
      },

      //添加课程
      addCourseData(){
        this.$axios
        .post('/api/addCourseData', { //提交成绩接口

        })
        .then((result)=> {
            if (result.data.code === 1) {
              alert("成绩更新成功！")
            }else{
              alert("成绩更新失败！")
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

    },
    created(){
      this.getTableData()
      this.getDptName()
    }
  }
</script>