const app = getApp()
// pages/authority/authority.js
Page({
  data: {
  
  },
  onLoad:function(){
    this.authority()
  },
  authority:function(){
    wx.getUserInfo({
      success: function (res) {
        app.globalData.userInfo = res.userInfo;
        wx.redirectTo({
          url: '/pages/getUserInfo/getUserInfo'
        })
      }
    })
  }
})