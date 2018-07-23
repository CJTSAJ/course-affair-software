// pages/oneTest/oneTest.js
const app = getApp()
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
    questionsContent:[],
    questionsId:[],
    point:[],
    studentChoose:[],
    studentChooseSubmit:[]
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
      url: 'http://207.148.114.118:8080/courseAffair/getTestDetail',
      data: parseInt(self.data.testId),
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        let quesId = [];
        let quesCon = [];
        let quesPoint = [];
        let answer = [];
        let answerSubmit = [];
        for (var i in res.data) {
          quesId.push(res.data[i][0]);
          quesPoint.push(res.data[i][1]);
          quesCon.push(res.data[i][2]);
          answer.push("未作答");
          answerSubmit.push(null);
        }
        self.setData({
          questionsId: quesId,
          questionsContent: quesCon,
          point: quesPoint,
          studentChoose: answer,
          studentChooseSubmit:answerSubmit
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

  submitConfirm: function () {
    var self = this;
    wx.showModal({
      content: '确定提交答案(答案不可重复提交)？',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定');
          self.submit();
        }
      }
    })
  },

  submit:function(){
    var self = this;
    if(self.data.state == 1){
      wx.request({
        url: 'http://207.148.114.118:8080/courseAffair/submitTest',
        data: {
          student_groupId: app.globalData.openGId,
          studentId: app.globalData.openId,
          questionId: self.data.questionsId,
          testId: self.data.testId,
          answer: self.data.studentChooseSubmit,
          point: self.data.point
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          if (res.data == "success") {
            wx.showModal({
              content: '提交成功',
            });
          }
          else {
            wx.showModal({
              content: '不能重复提交',
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
      })
    }
  }
})