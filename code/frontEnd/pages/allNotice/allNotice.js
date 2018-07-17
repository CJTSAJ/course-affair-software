const app = getApp()
Page({
  data:{
    allContent: [],
    time0:"2018-6-10 19:17",
    time1: "2018-6-10 20:08",
    userInfo: null
  },
  toDetail:function(){
    wx.navigateTo({
      url: '/pages/notice/notice',
    })
  },
  toEdit:function(){
    wx.redirectTo({
      url: '/pages/addNotice/addNotice',
    })
  },
  onShow: function(){
    this.setData({
      userInfo: app.globalData.userInfo
    })
    var self = this;
    var opengid = app.globalData.openGId;
    console.log("openid:" + opengid);
    var openidData = {
      'opengid': opengid
    }
    wx.request({
      url: 'http://127.0.0.1:8080/getNotice',
      data: {
        openid: openid
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        console.log("success:" + res.data);
        console.log("content::" + res.data[0])
        self.setData({
          
          allContent: res.data
        })
      },
      fail: function (error) {
        console.log("error:" + error);
      }
    })
  }
})