// pages/oneVote/oneVote.js
const app = getApp()
var sort = require('../../utils/sort.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    endTime: null,
    state: null,
    title: null,
    content: null,
    voteId: null,
    countDown: null,
    choiceLetter: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
    choices: [],
    studentChoose: -1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    this.setData({
      title: options.title,
      state: options.state,
      endTime: options.endTime,
      voteId: options.voteId,
    });
    wx.request({
      url: app.globalData.serverUrl + 'getVoteDetail',
      data: {
        voteId: self.data.voteId,
        student_groupId: app.globalData.openGId,
        studentId: app.globalData.openId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        let contentData = res.data[0][0];
        let choicesData=[];
        for (var i = 1; i < (res.data.length-1); i ++) {
          let choiceArray = { cId: res.data[i][0], cContent: res.data[i][1] };
          choicesData.push(choiceArray);
        }
        var choice_sort = sort.onlySortChoice(choicesData);
        var choose = res.data[(res.data.length-1)][0];
        self.setData({
          choices: choice_sort,
          studentChoose: choose,
          content: contentData
        })
        self.countDown();
        console.log(self.data);
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
    if (time > 0) {
      let day = parseInt(time / (3600 * 24));
      let hou = parseInt(time % (3600 * 24) / 3600);
      let min = parseInt(time % (60 * 60 * 24) % 3600 / 60);
      let sec = parseInt(time % (60 * 60 * 24) % 3600 % 60);
      let obj = {
        day: this.timeFormat(day),
        hou: this.timeFormat(hou),
        min: this.timeFormat(min),
        sec: this.timeFormat(sec)
      }
      this.setData({
        countDown: obj
      })
    }
    else {
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

  radioChange: function (e) {
    this.data.studentChoose = e.detail.value;
    this.setData({
      studentChoose: this.data.studentChoose
    });
    console.log(this.data.studentChoose);
  },

  submitConfirm: function () {
    var self = this;
    if (self.data.studentChoose == -1) {
      wx.showModal({
        content: '未选择',
        showCancel: false
      })
    }
    else {
      wx.showModal({
        content: '确定投票(投票不可重复提交)？',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定');
            self.submit();
          }
        }
      })
    }
  },

  submit: function () {
    var self = this;
    if (self.data.state == 1) {
      wx.request({
        url: app.globalData.serverUrl + 'submitVote',
        data: {
          student_groupId: app.globalData.openGId,
          studentId: app.globalData.openId,
          voteId: self.data.voteId,
          answer: self.data.studentChoose,
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
    else {
      wx.showModal({
        content: '投票结束',
        showCancel: false
      })
    }
  }

})