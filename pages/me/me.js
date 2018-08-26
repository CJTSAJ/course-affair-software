const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: []
  },

  onLoad: function () {
    this.setData({
      userInfo: app.globalData.userInfo
    })
  },
  toPerson: function(){
    wx.navigateTo({
      url: '/pages/person/person',
    })
  }
})