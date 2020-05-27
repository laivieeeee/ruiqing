package com.ruiqing.demo;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/4/16 14:14
 */

public class CreateThread {

}

class CreateThread2 extends Thread{
    // run方法中编写 多线程需要执行的代码
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
        }
    }
}

class ThreadDemo {

    public static void main(String[] args) {
        System.out.println("-----多线程创建开始-----");
        // 1.创建一个线程
        CreateThread2 createThread = new CreateThread2();
        // 2.开始执行线程 注意 开启线程不是调用run方法，而是start方法
        System.out.println("-----多线程创建启动-----");
        // 调用start方法后，代码并没有从上往下执行，而是有一条新的执行分支
        createThread.start();
        System.out.println("-----多线程创建结束-----");
    }
}
class CreateRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
        }
    }
}
class ThreadDemo2 {
    public static void main(String[] args) {
        System.out.println("-----多线程创建开始-----");
        // 1.创建一个线程
        CreateRunnable createThread = new CreateRunnable();
        // 2.开始执行线程 注意 开启线程不是调用run方法，而是start方法
        System.out.println("-----多线程创建启动-----");
        Thread thread = new Thread(createThread);
        thread.start();
        System.out.println("-----多线程创建结束-----");
    }
}
class ThreadDemo3{
    public static void main (String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< 10; i++) {
                    System.out.println("i:" + i);
                }
            }
        });
        thread.start();
        System.out.println("是否存活：" + thread.isAlive());
        Thread.sleep(10000);
        System.out.println(Thread.currentThread());
        System.out.println(thread.getId());
        System.out.println(thread.getName());
        System.out.println("是否存活thread：" + thread.isAlive());
        /*
         -- Create table
create table 项目信息SQL.txt
(
 业态            VARCHAR2(10),	langOpereType
 所属大区        NUMBER,		largeArea
 项目编号        VARCHAR2(25),	projCode
 项目名称        VARCHAR2(240), projName
 项目描述        VARCHAR2(250), projDescribe
 项目状态        VARCHAR2(80),  projStatus
 计划开始时间    DATE,	PlanStartDate
 计划结束时间    DATE,    PlanEndDate
 计划项目编号    VARCHAR2(150),PlanProjCode
 申请日期        DATE,applyDate
 申请人          VARCHAR2(240),applyUser
 项目负责人      VARCHAR2(240),projectLeader
 项目经办人      VARCHAR2(240),ProjectAgent
 资金来源        VARCHAR2(80),sourceFunds
 项目投资总金额  NUMBER,projInvestmentAmount
 币种            VARCHAR2(5)curry
);

utf8mb4
utf8mb4_general_ci
 -- Create table
create table 项目附件工作流提供外链SQL.txt
Project attachment workflow
(
 附件          varchar2(4000),attachment
 id            char(36),
 bindid        char(36),
 orgid         nvarchar2(36),
 createuser    nvarchar2(36),创建人
 createdate    date,创建时间
 updateuser    nvarchar2(36),更新人
 updatedate    date,更新时间
 isend         number(1),
 processdefid  char(36),
 附件名称      varchar2(240),附件名称:name of the attachment_name;
 附件类型      varchar2(80),附件类型:attachment_type;
 附件大小      number,附件大小:attachment_size;
 上传人        varchar2(100),heir
 附件id        number,
 附件路径      varchar2(240),附件路径:attachment_path;
 上传时间      date上传时间:upload_time;
);
utf8mb4
utf8mb4_general_ci

 -- Create table
create table 项目附件信息SQL.txt
Project the attachment information
(
 项目编号            VARCHAR2(25),proj_code
 file_business_type  VARCHAR2(100),文件的业务类型
 business_id         VARCHAR2(50),业务标识
 business_type       VARCHAR2(100),业务类型
 file_id             NUMBER,文件标识
 file_name           VARCHAR2(240),文件名称
 file_path           VARCHAR2(240),文件路径
 module_code         VARCHAR2(100),模块代码
 file_type           VARCHAR2(100),文件类型
 uploaded_by_name    VARCHAR2(100)上传的名字
);

 -- Create table
create table 项目审批信息SQL.txt
 project approve and approval information
(
 hierarchy_header_id   NUMBER,层次标题
 list_line_id          NUMBER,行id列表;
 step                  NUMBER, 步骤,
 user_name             VARCHAR2(100) ,用户名
 full_name             VARCHAR2(240),全名
 status_lookup_code    VACHAR2(30),查找代码状态
 approved_date         DATE,批准日期
 comments              VARCHAR2(2000)评论
);
项目状态
ERP集成项目信息数据表

自有资金比例
         */
    }
}