const app = getApp()
// pages/uploadFile/uploadFile.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    opengid: "",
    openid: ""
  },
  onLoad:function(){
    this.setData({
      opengid: app.globalData.openGId,
      openid: app.globalData.openId
    })
  }
})