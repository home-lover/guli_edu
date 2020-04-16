package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin //跨域
//@Api(tags="讲师管理")
public class TeacherAdminController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    @ApiOperation(value = "所有讲师列表")
    public R list(){
        List<Teacher> list = teacherService.list(null);
       /* HashMap<String,List<Teacher>> map = new HashMap<>();
        map.put("items",list);
        return R.ok().data(map);*/
       return R.ok().data("items",list).message("查询讲师列表成功");
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacherService.save(teacher);
        return R.ok();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除讲师")
    public R removeById(@PathVariable String id){
        teacherService.removeById(id);
        return R.ok().message("根据id删除讲师成功");
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher).message("根据id查询讲师成功");
    }

    @ApiOperation(value = "根据id修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok().message("根据id修改讲师信息成功");
    }



    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery){

        if(page <= 0 || limit <= 0){
            //throw new GuliException(21003, "参数不正确1");
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records).message("获取分页讲师列表成功");
    }


}
