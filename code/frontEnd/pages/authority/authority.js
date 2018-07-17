const app = getApp()
// pages/authority/authority.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  authority:function(){
    wx.getUserInfo({
      success: function (res) {
        console.log("userInfo:" + res)
        app.globalData.userInfo = res.userInfo
        wx.showLoading({
          title: '加载中',
        })
        wx.redirectTo({
          url: '/pages/getUserInfo/getUserInfo'
        })
      }
    })
  }
})