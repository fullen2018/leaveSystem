package edu.develop.leave;

import edu.develop.leave.model.ClassModel;
import edu.develop.leave.model.StudentModel;
import edu.develop.leave.model.TeacherModel;
import edu.develop.leave.service.ClassService;
import edu.develop.leave.service.StudentService;
import edu.develop.leave.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaveApplicationTests {

    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    @Autowired
    TeacherService teacherService;



    // 添加测试数据
    @Test
    public void contextLoads() throws Exception {
        setStudentName();
//        studentAdd();
    }

    /**
     * 添加班级
     * @throws Exception
     */
    private void classAdd() throws Exception{

        // 获取所有的班级
         List<ClassModel>  classModelList = classService.findAllClass();

         for(int i = 1; i < classModelList.size(); i++){
             TeacherModel teacherModel = new TeacherModel(classModelList.get(i).getClassId()
             ,2
             ,"教师"+i
             ,"123456");
             teacherService.add(teacherModel);
         }
    }

    /**
     * 添加学生
     */
     private void studentAdd() throws Exception {
         // 获取所有的班级
         List<ClassModel>  classModelList = classService.findAllClass();

         for(int i = 0; i < 300; i++){
             int classIndex = (int)(Math.random()*8);
             StudentModel studentModel = new StudentModel();
             studentModel.setRoleId(1);
             studentModel.setStudentName("学生"+i);
             studentModel.setClassId(classModelList.get(classIndex).getClassId());
             studentModel.setStudentPassword("123456");
             studentService.add(studentModel);
         }
     }

    /**
     * 修改学生名字
     */
    private void setStudentName() throws Exception{
        Long amount = studentService.queryAmount(null);

        List<StudentModel> stuList = studentService.list(1,amount.intValue(),null);

        int i = 1;
        for (StudentModel stu:
             stuList) {
            StudentModel studentModel = new StudentModel();
            studentModel.setStudentId(stu.getStudentId());
            studentModel.setStudentName("学生"+i);
            studentService.update(studentModel);
            i++;
        }

     }

}
