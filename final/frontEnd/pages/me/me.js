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
  },
  toView: function(){
    wx.navigateTo({
      url: "/pages/pagesForSth/view/view",
    })
  },
  toAbout:function(){
    wx.navigateTo({
      url: "/pages/pagesForSth/aboutUs/aboutUs",
    })
  },
  toGuide:function(){
    wx.navigateTo({
      url: '/pages/pagesForSth/guide/guide',
    })
  },
  toConnect: function(){
    wx.navigateTo({
      url: '/pages/pagesForSth/connectUs/connectUs',
    })
  }
})