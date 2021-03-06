// pages/testResult/testResult.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:null,
    testId:null,
    choiceLetter: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
    question:[],
    grade: null,
    studentChoose:[],
    correctAnswer:[],
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
    self.setData({
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
        let questionData=[];
        let sc=[];
        let ca=[];
        let right=[];
        for(var i in res.data){
          let choiceData=[];
          for(var j in res.data[i][7]){
            let choiceArray={cId:res.data[i][7][j][0], cContent: res.data[i][7][j][1]};
            choiceData.push(choiceArray);
          }
          let questionArray={qId:res.data[i][0], point:res.data[i][1], qContent: res.data[i][2], choice: choiceData};
          questionData.push(questionArray);
          sc.push(res.data[i][3]);
          right.push(self.ifRight(res.data[i][4]));
          ca.push(res.data[i][5]);
        }
        self.setData({
          question: questionData,
          studentChoose: sc,
          correctAnswer: ca,
          ifRight: right,
          grade: res.data[i][6],
        })
        console.log(self.data);
      },
      fail: function (error) {
      }
    })
  },

})