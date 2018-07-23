// editTest.js
const app = getApp();
var dateTimePicker = require('../../utils/dateTimePicker.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    titleContent:null,
    questionContent:[],
    questionNum:[],
    choiceContent:[],
    correctAnswer:[],
    dateTime: null,
    dateTimeArray: null,
    time: '00:30',
    startTime:null,
    focus: false,
    startYear: 2018,
    endYear: 2050
  },

  inputTitle: function(e){
    this.setData({
      titleContent: e.detail.value
    })
    console.log(this.data.titleContent);
  },

  inputStartTime: function(e){
    this.setData({
      startTime: e.detail.value
    })
    console.log(this.data.startTime)
  },

  inputQuestionContent: function(e){
    let inputTemp = this.data.questionContent;
    inputTemp[e.target.id] = e.detail.value;
    this.setData({
      questionContent:inputTemp
    })
    console.log(this.data.questionContent);
  },

  

  onLoad: function (options) {
    var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    var lastArray = obj.dateTimeArray.pop();
    var lastTime = obj.dateTime.pop();
    let num = this.data.questionNum;
    this.data.questionContent.push(null);
    this.data.choiceContent.push(null);
    num.push(1);
    this.setData({
      questionNum:num,
      dateTimeArray: obj.dateTimeArray,
      dateTime: obj.dateTime,
    })
    let startTimeTemp = this.data.dateTimeArray[0][this.data.dateTime[0]] + '-' + this.data.dateTimeArray[1][this.data.dateTime[1]] + '-' + this.data.dateTimeArray[2][this.data.dateTime[2]] + ' ' + this.data.dateTimeArray[3][this.data.dateTime[3]] + ':' + this.data.dateTimeArray[4][this.data.dateTime[4]] + ':00';
    this.setData({
      startTime: startTimeTemp 
    })
    console.log(this.data);
  },

  addQuestion: function(){
    let num = this.data.questionNum
    this.data.questionContent.push(null);
    this.data.choiceContent.push(null);
    num.push(1);
    this.setData({
      questionNum: num
    });
    console.log(this.data);
  },
  
  changeDateTime: function(e) {
    this.setData({ dateTime: e.detail.value });
  },


  changeDateTimeColumn: function(e) {
    let arr = this.data.dateTime;
    let dateArr = this.data.dateTimeArray;
    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
    let startTimeTemp = this.data.dateTimeArray[0][this.data.dateTime[0]] + '-' + this.data.dateTimeArray[1][this.data.dateTime[1]] + '-' + this.data.dateTimeArray[2][this.data.dateTime[2]] + ' ' + this.data.dateTimeArray[3][this.data.dateTime[3]] + ':' + this.data.dateTimeArray[4][this.data.dateTime[4]] + ":00";
    this.setData({
      startTime: startTimeTemp
    })
    this.setData({
      dateTimeArray: dateArr,
      dateTime: arr,
    });
    console.log(this.data);
  },

  changeTime: function(e) {
    this.setData({ time: e.detail.value });
  },

  submitConfirm: function () {
    var self = this;
    wx.showModal({
      content: '确定提交此次编辑？',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定');
          self.submit();
        }
      }
    })
  },
  
  submit: function () {
    if (app.globalData.openGId.length == 29) {
      wx.showModal({
        content: "缺少openGId",
        showCancel: false
      })
    }
    else if (this.data.titleContent == null) {
      wx.showModal({
        content: "缺少测试标题",
        showCancel: false
      })
    }
    else {
      wx.request({
        url: 'http://207.148.114.118:8080/courseAffair/submitTestEdit',
        data: {
          openGid: app.globalData.openGId,
          titleContent: this.data.titleContent,
          startTime: this.data.startTime,
          time: this.data.time + ":00",
          questionContent: this.data.questionContent,
          choiceContent: this.data.choiceContent,
          correctAnswer: this.data.correctAnswer,
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          console.log(res.data);
          wx.showModal({
            content: res.data,
            showCancel: false
          })
          wx.navigateBack({
            delta: 1
          })
        },
        fail: function (error) {
          wx.showModal({
            content: '测试上传失败',
            showCancel: false
          })
        }
      })
    }
  },
})