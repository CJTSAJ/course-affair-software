// editTest.js
const app = getApp();
var dateTimePicker = require('../../utils/dateTimePicker.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    titleContent: null,
    voteContent: [null],
    choiceContent: [[null, null]],
    choiceLetter: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
    dateTime: null,
    dateTimeArray: null,
    time: '00:30',
    startTime: null,
    focus: false,
    startYear: 2018,
    endYear: 2050,
    startX: 0,
    startY: 0,
    isTouchMove: [[false, false]],
  },

  touchS: function (e) {
    console.log(e);
    for (var i in this.data.isTouchMove) {
      for (var j in this.data.isTouchMove[i]) {
        if (this.data.isTouchMove[i][j]) {
          this.data.isTouchMove[i][j] = false;
        }
      }
    }
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      isTouchMove: this.data.isTouchMove
    })
  },

  angle: function (start, end) {
    var _X = end.X - start.X;
    var _Y = end.Y - start.Y;
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  },

  touchM: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var id = parseInt(e.currentTarget.id);
    var startX = that.data.startX;
    var startY = that.data.startY;
    var touchMoveX = e.changedTouches[0].clientX;
    var touchMoveY = e.changedTouches[0].clientY;
    var angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    for (var i in that.data.isTouchMove) {
      for (var j in that.data.isTouchMove[i]) {
        that.data.isTouchMove[i][j] = false;
        if (Math.abs(angle) > 30) return;
        if (j == index && i == id) {
          if (touchMoveX > startX) {
            that.data.isTouchMove[i][j] = false;
          }
          else {
            that.data.isTouchMove[i][j] = true;
          }
        }
      }
    }
    that.setData({
      isTouchMove: that.data.isTouchMove
    })
  },

  inputTitle: function (e) {
    this.setData({
      titleContent: e.detail.value
    })
    console.log(this.data.titleContent);
  },

  onLoad: function (options) {
    var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    var lastArray = obj.dateTimeArray.pop();
    var lastTime = obj.dateTime.pop();
    this.setData({
      dateTimeArray: obj.dateTimeArray,
      dateTime: obj.dateTime,
    })
    let startTimeTemp = this.data.dateTimeArray[0][this.data.dateTime[0]] + '-' + this.data.dateTimeArray[1][this.data.dateTime[1]] + '-' + this.data.dateTimeArray[2][this.data.dateTime[2]] + ' ' + this.data.dateTimeArray[3][this.data.dateTime[3]] + ':' + this.data.dateTimeArray[4][this.data.dateTime[4]] + ':00';
    this.setData({
      startTime: startTimeTemp
    })
    console.log(this.data);
  },

  

  addChoice: function (e) {
    if (this.data.choiceContent[e.currentTarget.id].length > 7) {
      wx.showModal({
        content: "最多只能有8个选项",
        showCancel: false
      })
    }
    else {
      this.data.choiceContent[e.currentTarget.id].push(null);
      this.data.isTouchMove[e.currentTarget.id].push(false);
      this.setData({
        choiceContent: this.data.choiceContent,
        isTouchMove: this.data.isTouchMove
      })
      console.log(this.data);
    }
  },

  delChoice: function (e) {
    if (this.data.choiceContent[e.currentTarget.id].length < 3) {
      wx.showModal({
        content: "最少需要2个选项",
        showCancel: false
      })
    }
    else {
      var index = e.currentTarget.dataset.index;
      var id = parseInt(e.currentTarget.id);
      this.data.choiceContent[id].splice(index, 1);
      this.data.isTouchMove[id].splice(index, 1);
      this.setData({
        choiceContent: this.data.choiceContent,
        isTouchMove: this.data.isTouchMove,
      });
      console.log(this.data);
    }
  },


  inputChoiceContent: function (e) {
    let temp = e.currentTarget.id.split("+");
    let key = temp[0];
    let choiceKey = temp[1];
    let choiceTemp = this.data.choiceContent;
    choiceTemp[parseInt(key)][parseInt(choiceKey)] = e.detail.value;
    this.setData({
      choiceContent: choiceTemp
    })
  },

  changeDateTime: function (e) {
    this.setData({ dateTime: e.detail.value });
  },


  changeDateTimeColumn: function (e) {
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

  changeTime: function (e) {
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
    if (app.globalData.openGId.length == null) {
      wx.showModal({
        content: "缺少openGId",
        showCancel: false
      })
    }
    else if (this.data.titleContent == null || this.data.titleContent == "") {
      wx.showModal({
        content: "缺少投票标题",
        showCancel: false
      })
    }
    else if (this.data.voteContent[0] == null || this.data.voteContent[0] == "") {
      wx.showModal({
        title: '缺少投票内容',
        showCancel: false
      })
    }
    else {
      wx.request({
        url: app.globalData.serverUrl + 'submitVoteEdit',
        data: {
          openGid: app.globalData.openGId,
          titleContent: this.data.titleContent,
          startTime: this.data.startTime,
          time: this.data.time + ":00",
          voteContent: this.data.voteContent,
          choiceContent: this.data.choiceContent,
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          console.log(res.data);
          if (res.data == "投票上传成功") {
            wx.showModal({
              content: res.data,
              showCancel: false,
              success: function (res) {
                if (res.confirm == true) {
                  wx.navigateBack();
                }
              }
            })
          }
          else {
            wx.showModal({
              content: res.data,
              showCancel: false,
            })
          }
        },
        fail: function (error) {
          wx.showModal({
            content: '投票上传失败',
            showCancel: false
          })
        }
      })
    }
  },
})