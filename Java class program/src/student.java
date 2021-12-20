import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class student {
    String code;
    String name;
    String sex;
    String age;
    String scoreChinese;
    String scoreMath;
    String scoreEnglish;
}

class Main {
    public static void main(String[] args) throws IOException {
        //建立学生管理系统
        Manage m1 = new Manage();
        m1.readFile();
        while (m1.flag) {
            m1.menu();
        }
        System.out.println("您已经退出系统");
    }
}

class Judge {//判断学生数量

    static boolean judgeNum(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] < 48 | ch[i] > 57)
                return false;
        }

        if (Integer.parseInt(str) < 0 | Integer.parseInt(str) > Manage.N - Manage.n)
            return false;
        return true;
    }

    static boolean judgeCode(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] < 48 | ch[i] > 57) {
                return false;
            }
        }
        for (int i = 0; i < Manage.n; i++) {
            if (str.equals(Manage.getCode(i)))
                return false;
        }
        return true;
    }

    static boolean judgeAge(String str) {//判断年龄
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] < 48 | ch[i] > 57) {
                return false;
            }
        }
        if (Integer.parseInt(str) < 0 | Integer.parseInt(str) > 100)
            return false;
        return true;
    }

    static boolean judgeScore(String str) {//判断分数
        char[] ch = str.toCharArray();
        if (ch[0] == 46)
            return false;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != 46) {
                if (ch[i] < 48 | ch[i] > 57)
                    return false;
            }
        }
        return true;
    }

    static boolean judgeSex(String str) {//判断性别
        if (str.equals("F") | str.equals("f") | str.equals("M") | str.equals("m"))
            return true;
        return false;
    }
}

class Manage {
    boolean flag = true;//运行开始或者结束标志变量
    static int N = 100;
    static student[] stu = new student[N];
    static int n = 0;
    int m = 0;
    Scanner sc = new Scanner(System.in);

    void menu() throws IOException {
        String num;
        System.out.println("*************************************************");
        System.out.println("             *    学生管理系统   *");
        System.out.println("             *    系统功能菜单   *");
        System.out.println("*************************************************");
        System.out.println("0.系统帮助及说明             1.刷新学生信息");
        System.out.println("2.查询学生信息               3.修改学生信息");
        System.out.println("4.增加学生信息               5.按学号删除信息");
        System.out.println("6.显示当前信息               7.保存当前学生信息");
        System.out.println("8.退出系统");
        System.out.println("*************************************************");
        System.out.println("请选择菜单编号：");
        num = sc.next();
        switch (num) {
            case "0":
                help();
                break;
            case "2":
                seek();
                break;
            case "3":
                modify();
                break;
            case "4":
                insert();
                break;
            case "5":
                del();
                break;
            case "6":
                display();
                break;
            case "8":
                flag = false;
                sc.close();
                break;
            default:
                System.out.println("请重新在 0--8 之间选择");
        }
    }

    void help() {
        System.out.println("0.欢迎进入系统帮助界面！");
        System.out.println("1.初次进入系统后，请选择增加学生信息；");
        System.out.println("2.按照菜单提示键入数字代号；");
        System.out.println("3.增加学生信息后，切记保存；");
        System.out.println("4.注意！本系统仅能对输入信息进行更改保存，打开系统会初始化原数据文件内容");
        System.out.println("5.谢谢您的使用！");
    }

    void insert() throws IOException {
        int j = n;
        System.out.print("请输入待增加学生数；");
        String str = sc.next();
        if (!Judge.judgeNum(str)) {
            System.out.println("输入数据有误请重新输入");
            return;
        }
        m = Integer.parseInt(str);
        for (int i = j; i < j + m; i++) {
            stu[i] = new student();
        }
        do { //输入学号判断
            System.out.println("请输入第" + (j - n + 1) + "个学生的学号");
            stu[j].code = sc.next();
            if (!Judge.judgeCode(stu[j].code)) {
                System.out.println("输入有误或者学号已存在，请重新输入该学生信息");
                continue;
            }
            System.out.println("请输入第" + (j - n + 1) + "个学生的姓名");
            stu[j].name = sc.next();
            System.out.println("请输入第" + (j - n + 1) + "个学生的年龄");
            stu[j].age = sc.next();
            if (!Judge.judgeAge(stu[j].age)) {
                System.out.println("输入有误，请重新输入该学生的信息");
                continue;
            }
            System.out.println("请输入第" + (j - n + 1) + "个学生的性别，男生使用F/f,女生使用M/m");
            stu[j].sex = sc.next();
            if (!Judge.judgeSex(stu[j].sex)) {
                System.out.println("输入有误，请重新输入该学生的信息");
                continue;
            }
            System.out.println("请输入第" + (j - n + 1) + "个学生的语文成绩");
            stu[j].scoreChinese = sc.next();
            if (!Judge.judgeScore(stu[j].scoreChinese)) {
                System.out.println("输入有误，请重新输入该学生的信息");
                continue;
            }
            System.out.println("请输入第" + (j - n + 1) + "个学生的英语成绩");
            stu[j].scoreEnglish = sc.next();
            if (!Judge.judgeScore(stu[j].scoreEnglish)) {
                System.out.println("输入有误，请重新输入该学生的信息");
                continue;
            }
            System.out.println("请输入第" + (j - n + 1) + "个学生的数学成绩");
            stu[j].scoreMath = sc.next();
            if (!Judge.judgeScore(stu[j].scoreMath)) {
                System.out.println("输入有误，请重新输入该学生的信息");
                continue;
            }
            //将信息存入文档
            FileWriter fw = new FileWriter("student.txt", true);
            String strTemp = (stu[j].code + " " + stu[j].name + " "
                    + stu[j].age + " " + stu[j].sex + " " + stu[j].scoreChinese + " " +
                    stu[j].scoreEnglish + " " + stu[j].scoreMath + " \r\n");
            fw.write(strTemp);
            fw.close();
            j++;
        } while (j < n + m);
        n += m;
        System.out.println("信息添加成功！");
        sort();//按学号升序进行排列
    }

    void modify() throws IOException {
        boolean flag = true;
        System.out.print("请输入要修改的学生学号：");
        String str = sc.next();
        int t;//修改学生的学生下标数值
        //需要修改的学生字符数据
        for (int i = 0; i < n; i++) {
            if (stu[i].code.equals(str)) {
                if (stu[i].code.equals(str)) {//定义str储存修改前学生的信息
                    t = i;
                    String dataOldStudent = (stu[t].code + " " + stu[t].name + " "
                            + stu[t].age + " " + stu[t].sex + " " + stu[t].scoreChinese + " " +
                            stu[t].scoreEnglish + " " + stu[t].scoreMath + " \r\n");

                    flag = false;
                    System.out.println("---------------------------------");
                    System.out.println("1.修改姓名");
                    System.out.println("2.修改年龄");
                    System.out.println("3.修改性别");
                    System.out.println("4.修改语文成绩");
                    System.out.println("5.修改英语成绩");
                    System.out.println("6.修改数学成绩");
                    System.out.println("7.保存修改信息并退出本菜单");
                    System.out.println("---------------------------------");
                    while (true) {
                        System.out.println("请选择子菜单代号");
                        String item = sc.next();
                        switch (item) {
                            case "1":
                                System.out.println("请输入新的名字：");
                                stu[i].name = sc.next();
                                break;
                            case "2":
                                System.out.println("请输入新的年龄：");
                                stu[i].age = sc.next();
                                if (!Judge.judgeAge(stu[i].age)) {
                                    System.out.println("输入有误，请重新输入该学生的信息");
                                }
                                break;
                            case "3":
                                System.out.println("请输入新的性别：");
                                stu[i].sex = sc.next();
                                break;
                            case "4":
                                System.out.println("请输入新的语文成绩：");
                                stu[i].scoreChinese = sc.next();
                                if (!Judge.judgeScore(stu[i].scoreChinese)) {
                                    System.out.println("输入有误，请重新输入该学生的信息");
                                }
                                break;
                            case "5":
                                System.out.println("请输入新的英语成绩：");
                                stu[i].scoreEnglish = sc.next();
                                if (!Judge.judgeScore(stu[i].scoreEnglish)) {
                                    System.out.println("输入有误，请重新输入该学生的信息");
                                }
                                break;
                            case "6":
                                System.out.println("请输入新的数学成绩：");
                                stu[i].scoreMath = sc.next();
                                if (!Judge.judgeScore(stu[i].scoreMath)) {
                                    System.out.println("输入有误，请重新输入该学生的信息");
                                }
                                break;
                            case "7":
                                if (!flag) {
                                    String dataNewStudent = (stu[t].code + " " + stu[t].name + " "
                                            + stu[t].age + " " + stu[t].sex + " " + stu[t].scoreChinese + " " +
                                            stu[t].scoreEnglish + " " + stu[t].scoreMath + " ");
                                    File f = new File("Student.txt");
                                    long length = f.length();
                                    FileReader fr = new FileReader("Student.txt");
                                    char[] ch = new char[(int) length];
                                    fr.read(ch);
                                    fr.close();
                                    String str1 = new String(ch);
                                    str1 = str1.replace(dataOldStudent, dataNewStudent); //进行修改操作
                                    FileWriter fw = new FileWriter("student.txt");//将新字符串写进文件
                                    fw.write(str1);
                                    fw.close();
                                }
                                return;
                            default:
                                System.out.println("请在 1--7 之间选择");
                        }
                    }
                }
            }
        }
        if (flag)
            System.out.println("该学生不在系统中");
    }

    void display() {
        System.out.println("共有" + n + "位学生信息；");
        if (n != 0) {
            System.out.println("学生学号             学生姓名   年龄   性别    语文成绩   英语成绩   数学成绩 ");
            System.out.println("---------------------------------------------------------------------------------");
            for (int i = 0; i < n; i++) {
                System.out.println(stu[i].code + " \t\t\t\t\t" + stu[i].name + " \t\t\t"
                        + stu[i].age + " \t\t" + stu[i].sex + " \t\t" + stu[i].scoreChinese + " \t\t" +
                        stu[i].scoreEnglish + " \t\t" + stu[i].scoreMath);
            }
        }
    }

    void del() throws IOException {
        System.out.println("请输入要删除学生的学号：");
        String code = sc.next();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (stu[i].code.equals(code)) {
                flag = true;
                String dataStudent = (stu[i].code + " " + stu[i].name + " "
                        + stu[i].age + " " + stu[i].sex + " " + stu[i].scoreChinese + " " +
                        stu[i].scoreEnglish + " " + stu[i].scoreMath);
                if (i == n - 1) {//删除最后一个同学
                    stu[i] = null;
                    n -= 1;
                } else {//删除的不是最后一个同学
                    for (int j = 1; i < n; j++) {
                        stu[j] = stu[j + 1];
                    }
                    stu[n - 1] = null;
                    n -= 1;
                }
                File f = new File("student.txt");
                long length = f.length();
                FileReader fr = new FileReader("student.txt");
                char[] ch = new char[(int) length];
                fr.read(ch);
                fr.close();
                String str = new String(ch);
                str = str.replace(dataStudent, " ");//删除操作
                FileWriter fw = new FileWriter("student.txt");//写入新文件
                fw.write(str);
                fw.close();
            }
        }
        if (flag == false)
            System.out.println("该学号不存在！");
        if (flag == true)
            System.out.println("删除成功");
    }

    void seek() {//查找算法
        int i;
        String item, code, name;
        boolean flag = false;
        System.out.println("---------------------------------------------");
        System.out.println("1.按学号查询");
        System.out.println("2.按姓名查询");
        System.out.println("3.退出本菜单");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("请选择子菜单编号：");
            item = sc.next();
            flag = false;
            switch (item) {
                case "1":
                    System.out.println("请输入要查询的学生的学号：");
                    code = sc.next();
                    for (i = 0; i < n; i++)
                        if (stu[i].code.equals(code)) {
                            flag = true;
                            System.out.println("学生学号             学生姓名   年龄   性别    语文成绩   英语成绩   数学成绩 ");
                            System.out.println("---------------------------------------------------------------------------------");
                            System.out.println(stu[i].code + " \t\t\t\t\t" + stu[i].name + " \t\t"
                                    + stu[i].age + " \t\t" + stu[i].sex + " \t\t" + stu[i].scoreChinese + " \t\t" +
                                    stu[i].scoreEnglish + " \t\t" + stu[i].scoreMath);
                        }
                    if (false == flag)
                        System.out.println("该学号不存在！");
                    break;
                case "2":
                    System.out.println("请输入要查询的学生的姓名：");
                    name = sc.next();
                    for (i = 0; i < n; i++)
                        if (stu[i].name.equals(name)) {
                            flag = true;
                            System.out.println("学生学号             学生姓名   年龄   性别    语文成绩   英语成绩   数学成绩 ");
                            System.out.println("---------------------------------------------------------------------------------");
                            System.out.println(stu[i].code + " \t\t\t\t\t" + stu[i].name + " \t\t"
                                    + stu[i].age + " \t\t" + stu[i].sex + " \t\t" + stu[i].scoreChinese + " \t\t" +
                                    stu[i].scoreEnglish + " \t\t" + stu[i].scoreMath);
                        }
                    if (false == flag)
                        System.out.println("该姓名不存在！");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("请在 1--3 之间选择");
            }
        }
    }

    static String getCode(int i) {//Judge类中使用
        return stu[i].code;
    }

    void sort() throws IOException {
        int[] code = new int[n];
        int temp;
        student stuTemp = new student();
        for (int i = 0; i < n; i++) {
            code[i] = Integer.parseInt(stu[i].code);
        }
        for (int i = 0; i < n - 1; i++) {//使用选择排序法对学生对象进行排序
            for (int j = i + 1; j < n; j++) {
                if (code[i] > code[j]) {
                    temp = code[i];
                    code[i] = code[j];
                    code[j] = temp;
                    stuTemp = stu[i];
                    stu[i] = stu[j];
                    stu[j] = stuTemp;
                }
            }
        }

        FileWriter fw = new FileWriter("student.txt");//按学号顺序将学生信息写入文件
        for (int i = 0; i < n; i++) {
            String strTemp = (stu[i].code + " " + stu[i].name + " "
                    + stu[i].age + " " + stu[i].sex + " " + stu[i].scoreChinese + " " +
                    stu[i].scoreEnglish + " " + stu[i].scoreMath + " \r\n");
            fw.write(strTemp);
        }
        fw.close();
    }

    void readFile() throws IOException {
        File f = new File("student.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(f);//打开文件清空文件原有内容
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int length = (int) f.length();
        if (length == 0)
            return;
        FileReader fr = new FileReader("student.txt");
        char[] ch = new char[length];
        fr.read(ch);
        fr.close();
        String str = new String(ch);

        //将字符串分割成数组并初始化
        String regex1 = "\\s{2}";
        String regex2 = "\\p{Blank}";
        String[] strArray = str.split(regex1);
        n = strArray.length;
        for (int i = 0; i < n; i++) {
            stu[i] = new student();
        }
        //初始化对象
        for (int i = 0; i < strArray.length; i++) {
            String[] strArray2 = strArray[i].split(regex2);
            stu[i].code = strArray2[0];
            stu[i].name = strArray2[1];
            stu[i].age = strArray2[2];
            stu[i].sex = strArray2[3];
            stu[i].scoreChinese = strArray2[4];
            stu[i].scoreEnglish = strArray2[5];
            stu[i].scoreMath = strArray2[6];

        }
    }
}

