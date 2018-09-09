const app = getApp()
// pages/test/test.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allContent: [],
    userInfo: null,
    state: [],
    countDownList: [],
    startTimeList: [],
    endTimeList: [],
    testId:[],
    isTeacher: false
  },
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading()
    this.onShow()
  },

  onShow:function() {
    if (app.globalData.identity != "student") {
      this.setData({
        isTeacher: true
      })
    }
    this.setData({
      userInfo: app.globalData.userInfo
    })
    var self = this;
    var opengid = app.globalData.openGId;
    wx.request({
      url: app.globalData.serverUrl + 'getTest',
      data: opengid,
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        let startTime = [];
        let endTime = [];
        let content = [];
        let id = [];
        for(var i in res.data){
          content.push(res.data[i][0]);
          startTime.push(res.data[i][1]);
          endTime.push(res.data[i][2]);
          id.push(res.data[i][3]);
        }
        self.setData({
          allContent: content,
          startTimeList: startTime,
          endTimeList: endTime,
          testId: id
        });
        console.log(self.data);
        self.countDown();
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh()     
      },
      fail: function (error) {
      }
    })
  },

  timeFormat(param) {//小于10的格式化函数
    return param < 10 ? '0' + param : param;
  },

  toEditTest: function () {
   /*wx.navigateTo({
      url: '/pages/testStatistics/testStatistics',
    })*/
    wx.navigateTo({
      url: '/pages/editTest/editTest',
    })
  },

  countDown: function(){
    var self = this;
    let now = new Date().getTime();
    let countDownArr = [];
    let timeState = [];
    for (var i in self.data.startTimeList){
      let startTime = new Date(this.data.startTimeList[i]).getTime();
      let endTime = new Date(this.data.endTimeList[i]).getTime();
      if(now - startTime < 0){
        timeState.push(0);
        countDownArr.push(null);
      }
      else if(now - endTime > 0){
        timeState.push(2);
        countDownArr.push(null);
      }
      else{
        timeState.push(1);
        let time = (endTime - now) / 1000;
        let hou = parseInt(time / 3600);
        let min = parseInt(time % (60 * 60 * 24) % 3600 / 60);
        let sec = parseInt(time % (60 * 60 * 24) % 3600 % 60);
        let obj = {
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
  }
})