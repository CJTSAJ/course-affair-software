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
    dateTime: null,
    dateTimeArray: null,
    time: '00:30',
    startTime:null,
    endTime:null,
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
      dataTimeArray2: obj.dateTimeArray,
      dataTime2: obj.dateTime
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
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], 
dateArr[1][arr[1]]);

    this.setData({
      dateTimeArray: dateArr,
      dateTime: arr
    });
  },

  changeTime: function(e) {
    this.setData({ time: e.detail.value });
  },
  
})