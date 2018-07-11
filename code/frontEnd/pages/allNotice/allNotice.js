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
    var openGId = app.globalData.openGId;
    console.log("openGid:" + openGId);
    var openidData = {
      'opengid': openGId
    }
    wx.request({
      url: 'http://127.0.0.1:8080/hibernate/getNotice',
      data: openGId,
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        console.log("content::" + res.data[0])
        self.setData({
          
          allContent: res.data
        })
      },
      fail: function (error) {
      }
    })
  }
})