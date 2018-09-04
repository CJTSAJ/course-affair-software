// pages/vote/vote.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allTitle: [],
    userInfo: null,
    state: null,
    countDownList: [],
    startTimeList: [],
    endTimeList: [],
    voteId: [],
    isTeacher: false
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (app.globalData.identity != "student") {
      this.setData({
        isTeacher: true
      });
    }
    this.setData({
      userInfo: app.globalData.userInfo
    });
    var self = this;
    var opengid = app.globalData.openGId;
    wx.request({
      url: 'http://127.0.0.1:8080/getVote',
      data: opengid,
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res){
        let startTime = [];
        let endTime = [];
        let id = [];
        let title = [];
        for (var i in res.data) {
          startTime.push(res.data[i][0]);
          endTime.push(res.data[i][1]);
          id.push(res.data[i][2]);
          title.push(res.data[i][3]);
        };
        self.setData({
          allTitle: title,
          startTimeList: startTime,
          endTimeList: endTime,
          voteId: id
        });
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
    var self = this;
    let now = new Date().getTime();
    let countDownArr = [];
    let timeState = [];
    for (var i in self.data.startTimeList) {
      let startTime = new Date(this.data.startTimeList[i]).getTime();
      let endTime = new Date(this.data.endTimeList[i]).getTime();
      if (now - startTime < 0) {
        timeState.push(0);
        countDownArr.push(null);
      }
      else if (now - endTime > 0) {
        timeState.push(2);
        countDownArr.push(null);
      }
      else {
        timeState.push(1);
        let time = (endTime - now) / 1000;
        let day = parseInt(time / (3600*24));
        let hou = parseInt(time % (3600*24)/3600);
        let min = parseInt(time % (60 * 60 * 24) % 3600 / 60);
        let sec = parseInt(time % (60 * 60 * 24) % 3600 % 60);
        let obj = {
          day: this.timeFormat(day),
          hou: this.timeFormat(hou),
          min: this.timeFormat(min),
          sec: this.timeFormat(sec)
        }
        countDownArr.push(obj);
      }
    }
    this.setData({
      countDownList: countDownArr,
      state: timeState
    })
    setTimeout(this.countDown, 1000);
  },

  toEditVote: function(){
    wx.navigateTo({
      url: '/pages/editVote/editVote',
    })
  }

})