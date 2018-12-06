package com.mikey.design.views.student;import com.mikey.design.entity.Student;import com.mikey.design.listerner.common.LoginOut;import com.mikey.design.service.TitleOfStudentService;import com.mikey.design.utils.SpringUtil;import com.mikey.design.utils.ThreadLoaclUtil;import com.mikey.design.views.common.UpPwdPanel;import com.mikey.design.views.common.UpdatePanel;import com.mikey.design.views.common.WelcomeJpanel;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;/** * @author Mikey * @Title: * @Description: * @Email:1625017540@qq.com * @date 2018/11/30 19:37 * @Version 1.0 */public class StudentMainView extends JFrame {  //0.继承JFrame    private TitleOfStudentService titleOfStudentService;    public StudentMainView(JFrame login){        showView(login);    }    public void showView(JFrame login) {        //创建组件        JPanel Menu=new JPanel();        Menu.setLayout(new GridLayout(10,1,3,3));        JButton menuChild1 = new JButton("填报志愿");        JButton menuChild2 = new JButton("浏览教师");        JButton menuChild3 = new JButton("浏览题目");        JButton menuChild4 = new JButton("修改信息");        JButton menuChild5 = new JButton("修改密码");        JButton menuChild6 = new JButton("退出登入");        Menu.add(menuChild1);        Menu.add(menuChild2);        Menu.add(menuChild3);        Menu.add(menuChild4);        Menu.add(menuChild5);        Menu.add(menuChild6);        Menu.setBackground(Color.GRAY);        Menu.setVisible(true);        /**         * 菜单栏         */        JMenuBar menuBar = new JMenuBar();        JMenu fileMenu = new JMenu("文件");        JMenu editMenu = new JMenu("编辑");        JMenu viewMenu = new JMenu("视图");        JMenu aboutMenu = new JMenu("关于");        // 一级菜单添加到菜单栏        menuBar.add(fileMenu);        menuBar.add(editMenu);        menuBar.add(viewMenu);        menuBar.add(aboutMenu);        /**         * 中间布局         */        JPanel center=new JPanel();        center.setVisible(true);        CardLayout cardLayout=new CardLayout(10,10);        center.setLayout(cardLayout);        JPanel wispPanel=new WispPanel(menuChild1);//志愿        JPanel teacherPanel=new TeacherJpanel();//教师        JPanel titlePanel=new TitleJpanel();//课设题目        JPanel updatePanel=new UpdatePanel();//更新个人信息        JPanel upPwdPanel=new UpPwdPanel();//更新个人密码        JPanel alrealdFillWispPanel=new AlrealdFillWispPanel();//已经填报页面        menuChild1.addActionListener(new AbstractAction() {            @Override            public void actionPerformed(ActionEvent e) {                //进行判断是否已经填报过志愿                if (fillWishOrNot()){                    ((AlrealdFillWispPanel) alrealdFillWispPanel).refreshData();                    cardLayout.show(center,"alrealdFillWispPanel");                }else{                    cardLayout.show(center,"wispPanel");                }            }        });        menuChild2.addActionListener(new AbstractAction() {            @Override            public void actionPerformed(ActionEvent e) {                cardLayout.show(center,"teacherPanel");            }        });        menuChild3.addActionListener(new AbstractAction() {            @Override            public void actionPerformed(ActionEvent e) {                cardLayout.show(center,"titlePanel");            }        });        menuChild4.addActionListener(new AbstractAction() {            @Override            public void actionPerformed(ActionEvent e) {                cardLayout.show(center,"updatePanel");            }        });        menuChild5.addActionListener(new AbstractAction() {            @Override            public void actionPerformed(ActionEvent e) {                cardLayout.show(center,"upPwdPanel");            }        });        menuChild6.addActionListener(new LoginOut(login,this));//注册注销监听器        center.add(new WelcomeJpanel(),"welcomeJpanel");        center.add(wispPanel,"wispPanel");        center.add(teacherPanel,"teacherPanel");        center.add(titlePanel,"titlePanel");        center.add(updatePanel,"updatePanel");        center.add(upPwdPanel,"upPwdPanel");        center.add(alrealdFillWispPanel,"alrealdFillWispPanel");//已经填报过志愿的页面        this.add(center, BorderLayout.CENTER);  //中间        this.add(menuBar, BorderLayout.NORTH);  //北边        this.add(Menu, BorderLayout.WEST);   //西边        this.setTitle("毕业设计选题系统");        this.setSize(700, 500);        this.setLocation(600, 300);        this.setVisible(true);        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    }    /**     * 判断该学生是否已经填报志愿     * @return     */    private boolean fillWishOrNot(){        Student self= (Student) ThreadLoaclUtil.get();        titleOfStudentService= (TitleOfStudentService) SpringUtil.getBean("titleOfStudentServiceImpl");        return titleOfStudentService.fillWishOrNot(self.getStudentId());    }    public static void main(String[] args) {        StudentMainView testBorderLayout = new StudentMainView(null);    }}