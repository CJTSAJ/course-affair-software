const app = getApp()
Page({
  data:{
    allContent: [],
    time0:"2018-6-10 19:17",
    time1: "2018-6-10 20:08",
    userInfo: null,
    isTeacher: false
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
    if(app.globalData.identity == "teacher"){
      this.setData({
        isTeacher: true
      })
    }
    var self = this;
    var opengid = app.globalData.openGId;
    console.log("openid:" + opengid);
    wx.request({
      url: 'http://207.148.114.118:8080/courseAffair/hibernate/getNotice',
      data: opengid,
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