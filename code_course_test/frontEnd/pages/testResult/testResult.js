// pages/testResult/testResult.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:null,
    testId:null,
    questionsContent:[],
    questionsId:[],
    point:[],
    totalPoints: null,
    studentChoose:[],
    correctAnswer:[],
    studentChooseLetter:[],
    correctAnswerLetter:[],
    ifRight:[]
  },

  ifRight: function(param){
    if(param == "false"){
      return false;
    }
    else if(param == "true"){
      return true;
    }
  },

  
  getQuestionContent: function (key) {
    return this.data.questionsContent[key]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    this.setData({
      content: options.content,
      testId: options.testId,
    })
    wx.request({
      url: 'http://127.0.0.1:8080/getTestResult',
      data: {
        testId: self.data.testId,
        student_groupId: app.globalData.openGId,
        studentId: app.globalData.openId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        let quesId = [];
        let quesCon = [];
        let quesPoint = [];
        let answer = [];
        let correct = [];
        let right = [];
        let total = [];
        let answerLetter = [];
        let correctLetter = [];
        for (var i in res.data) {
          quesId.push(res.data[i][0]);
          quesPoint.push(res.data[i][1]);
          quesCon.push(res.data[i][2]);
          answer.push(res.data[i][3]);
          right.push(self.ifRight(res.data[i][4]));
          correct.push(res.data[i][5]);
          answerLetter.push(String.fromCharCode(parseInt(res.data[i][3]) + 65));
          correctLetter.push(String.fromCharCode(parseInt(res.data[i][5]) + 65));
        }
        self.setData({
          questionsId: quesId,
          questionsContent: quesCon,
          point: quesPoint,
          studentChoose: answer,
          totalPoints: res.data[i][6],
          correctAnswer: correct,
          ifRight: right,
          studentChooseLetter: answerLetter,
          correctAnswerLetter: correctLetter
        })
      },
      fail: function (error) {
      }
    })
  },

})