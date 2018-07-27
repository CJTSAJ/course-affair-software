// pages/oneTest/oneTest.js
const app = getApp()
var sort = require('../../utils/sort.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    endTime:null,
    state:null,
    content:null,
    testId:null,
    countDown: null,
    choiceLetter: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
    question:[],
    point:[],
    studentChoose:[],
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    this.setData({
      content: options.content,
      state: options.state,
      endTime: options.endTime,
      testId: options.testId,
    })
    wx.request({
      url: 'http://127.0.0.1:8080/getTestDetail',
      data: {
        testId: this.data.testId,
        student_groupId: app.globalData.openGId,
        studentId: app.globalData.openId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res){
        let questionData=[]
        for(var i in res.data){
          let choiceData = [];
          for(var j in res.data[i][4]){
            let choiceArray = { cId: res.data[i][4][j][0], cContent: res.data[i][4][j][1]};
            choiceData.push(choiceArray);
          }
          let questionArray = {qId: res.data[i][0], point: res.data[i][1], qContent: res.data[i][2], choice: choiceData};
          questionData.push(questionArray);
          self.data.studentChoose.push(-1);
        }
        var question_sort = sort.sort(questionData);
        self.setData({
          question: question_sort,
          studentChoose: self.data.studentChoose
        })
        self.countDown();
      },
      fail: function (error) {
      }
    })
  },


  timeFormat(param) {//小于10的格式化函数
    return param < 10 ? '0' + param : param;
  },

  countDown: function () {
    let now = new Date().getTime();
    let endTime = new Date(this.data.endTime).getTime();
    let time = (endTime - now) / 1000;
    if(time > 0){
      let hou = parseInt(time / 3600);
      let min = parseInt(time % (60 * 60 * 24) % 3600 / 60);
      let sec = parseInt(time % (60 * 60 * 24) % 3600 % 60);
      let obj = {
        hou: this.timeFormat(hou),
        min: this.timeFormat(min),
        sec: this.timeFormat(sec)
      }
      this.setData({
        countDown: obj
      })
    }
    else{
      let obj = {
        hou: '00',
        min: '00',
        sec: '00'
      }
      this.setData({
        state: 2,
        countDown: obj
      })
    }
    setTimeout(this.countDown, 1000);
  },

  getQuestionContent: function(key){
    return this.data.questionsContent[key]
  },

  radioChange: function(e) {
    this.data.studentChoose[parseInt(e.currentTarget.id)] = e.detail.value;
    this.setData({
      studentChoose: this.data.studentChoose
    });
    console.log(this.data.question[1].qId);
  },

  submitConfirm: function () {
    console.log(this.data.studentChoose);
    var self = this;
    var ifMiss = -1;
    for (var i in this.data.studentChoose){
      if (this.data.studentChoose[i] == -1){
        ifMiss = i;
        break;
      }
    }
    if(ifMiss>-1){
      wx.showModal({
        content: '第' + (ifMiss+1) + '题未完成',
        showCancel: false
      })
    }
    else{
      wx.showModal({
        content: '确定提交答案(答案不可重复提交)？',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定');
            self.submit();
          }
        }
      })
    }
  },


  submit:function(){
    var self = this;
    let questionId = [];
    let point = [];
    for(var i in this.data.question){
      questionId.push(this.data.question[i].qId);
      point.push(this.data.question[i].point);
    }
    if(self.data.state == 1){
      wx.request({
        url: 'http://127.0.0.1:8080/submitTest',
        data: {
          student_groupId: app.globalData.openGId,
          studentId: app.globalData.openId,
          questionId: questionId,
          testId: self.data.testId,
          answer: this.data.studentChoose,
          point:point
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          if (res.data == "success") {
            wx.showModal({
              content: '提交成功',
              showCancel: false
            });
          }
          else {
            wx.showModal({
              content: '不能重复提交',
              showCancel: false
            })
          }
        },
        fail: function (error) {
        }
      })
    }
    else{
      wx.showModal({
        content: '测试结束',
        showCancel: false
      })
    }
  }
})